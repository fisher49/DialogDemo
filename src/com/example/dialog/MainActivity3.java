package com.example.dialog;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity3 extends Activity implements OnTouchListener{

	private Bitmap m_Bitmap = null;
	private Button m_SaveButton;
	private Button m_ClearButton;
	private Canvas m_Canvas;
	private ImageView m_ImageView;
	private Paint m_Paint;
	private float m_StartX;
	private float m_StartY;
	private float m_MoveX;
	private float m_MoveY;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main3);
		
		m_SaveButton = (Button) findViewById(R.id.saveButton);
		m_ClearButton = (Button) findViewById(R.id.clearButton);
		m_ImageView = (ImageView) findViewById(R.id.paintImageView);
		
		m_ImageView.setOnTouchListener(this);
		m_ImageView.setBackgroundColor(Color.WHITE);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch(event.getAction()){
		case MotionEvent.ACTION_DOWN:
			if(m_Bitmap == null){
				m_Bitmap = Bitmap.createBitmap(m_ImageView.getWidth(), m_ImageView.getHeight(), 
						Config.ARGB_8888);
				m_Canvas = new Canvas(m_Bitmap);
				
				m_Canvas.drawColor(Color.WHITE);
				
				m_Paint = new Paint();
				m_Paint.setColor(Color.RED);
				m_Paint.setStrokeWidth(5);
			}
			m_StartX = event.getX();
			m_StartY = event.getY();
			
			m_Canvas.drawPoint(m_StartX, m_StartY, m_Paint);
			m_ImageView.setImageBitmap(m_Bitmap);
			break;
		case MotionEvent.ACTION_MOVE:
			m_MoveX = event.getX();
			m_MoveY = event.getY();
			
			m_Canvas.drawLine(m_StartX, m_StartY, m_MoveX, m_MoveY, m_Paint);
			
			m_ImageView.setImageBitmap(m_Bitmap);
			m_StartX = m_MoveX;
			m_StartY = m_MoveY;
			
			break;
		case MotionEvent.ACTION_UP:
//			File file = new File(Environment.getExternalStorageDirectory() + "/" + SystemClock.currentThreadTimeMillis()+".jpg");
//			FileOutputStream outputStream = new FileOutputStream(file);
			
			break;
		}
		return true;
	}
	
	public void saveClick(View v){
		if(m_Bitmap != null){
			File file = new File(Environment.getExternalStorageDirectory() + "/" + System.currentTimeMillis()+".jpg");
			try {
				FileOutputStream outputStream = new FileOutputStream(file);
				
				boolean result = m_Bitmap.compress(CompressFormat.JPEG, 100, outputStream);
				
				if(result){
					Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
				}else{
					Toast.makeText(this, "保存失败", Toast.LENGTH_SHORT).show();
				}
				
				outputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				m_Bitmap = null;
			}
		}else{
			Toast.makeText(this, "没有图片可以保存", Toast.LENGTH_SHORT).show();
		}
			
	}
	
	public void clearClick(View v){
		if(m_Bitmap != null){
			m_Bitmap = null;
//			m_ImageView.setColorFilter(Color.WHITE);
		}
		m_ImageView.setImageBitmap(null);
	}
}
