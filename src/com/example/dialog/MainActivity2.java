package com.example.dialog;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;

import com.loopj.android.image.SmartImage;
import com.loopj.android.image.SmartImageView;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

public class MainActivity2 extends Activity {

	private final Handler m_handler = new Handler();
	private ImageView m_ImageView;
	private SmartImageView smartImageView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main2);
		m_ImageView = (ImageView) findViewById(R.id.imageView1);
		smartImageView = (SmartImageView) findViewById(R.id.imageView2);
	}
	
	public void closeactivity2(View v){
		finish();
	}
	
	private void getnetImage(final String urlPath){
		new Thread(new Runnable() {
			
			public void run() {
				// TODO Auto-generated method stub
//				String path = "http://ww4.sinaimg.cn/bmiddle/a68a323ajw1ey2spwnl27j20dw09an06.jpg";
				try {
					URL url = new URL(urlPath);
					HttpURLConnection connection = (HttpURLConnection) url.openConnection();
					connection.setReadTimeout(5000);
					connection.setRequestMethod("GET");
					int result = connection.getResponseCode();
					if(200 == result){
						File file = new File(Environment.getExternalStorageDirectory() + "/downloadimg.jpg");
						FileOutputStream fileOutputStream = new FileOutputStream(file);
						ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
						InputStream inputStream = connection.getInputStream();
						byte buffer[] = new byte[1024];
						int length;
						while((length=inputStream.read(buffer)) > 0){
							fileOutputStream.write(buffer, 0, length);
							byteStream.write(buffer, 0, length);
						}
						fileOutputStream.close();
						inputStream.close();
						byteStream.close();
						
						final Bitmap bit= BitmapFactory.decodeByteArray(byteStream.toByteArray(), 0, byteStream.size());
						
						m_handler.post(new Runnable() {
							public void run() {
								m_ImageView.setImageBitmap(bit);
							}
						});
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	public void getImageFromNet(View v){
		String path = "http://ww4.sinaimg.cn/bmiddle/a68a323ajw1ey2spwnl27j20dw09an06.jpg";
//		getnetImage(path);
		
		smartImageView.setImageUrl(path);
	}
}
