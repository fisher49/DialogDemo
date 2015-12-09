package com.example.dialog;

import java.util.ArrayList;

import com.example.dialog.FragmentDialogNote.LoginInputListener;

import android.app.Activity;
import android.app.Fragment;
import android.app.AlertDialog.Builder;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.os.SystemClock;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity implements LoginInputListener{

	private static final String m_Citys[] = new String[]{"湖南", "福州", "杭州"};
	private static ArrayList<Boolean> m_selected = new ArrayList<Boolean>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		m_selected.add(false);
		m_selected.add(false);
		m_selected.add(false);
	}
	
	public void ButtonClick(View v)
	{
		switch (v.getId()) {
		case R.id.notifyBTNID:
		{
			Builder builder = new Builder(this);
			builder.setIcon(R.drawable.notify);
			builder.setTitle("通知框test");
			builder.setMessage("通知框内容");
			builder.setCancelable(true);
			builder.setPositiveButton("确认", new OnClickListener(){
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Toast.makeText(MainActivity.this, "确认键", Toast.LENGTH_SHORT).show();
				}
			});
			builder.setNegativeButton("取消", new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Toast.makeText(MainActivity.this, "取消键", Toast.LENGTH_SHORT).show();
				}
			});
			builder.show();
		}
			break;
		case R.id.listBTNID:
		{
			Builder builder = new Builder(this);
			builder.setIcon(R.drawable.notify);
			builder.setTitle("请选择");
			builder.setItems(m_Citys, new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Toast.makeText(MainActivity.this, m_Citys[which], Toast.LENGTH_SHORT).show();
				}
			});
//			builder.setMessage("通知框内容");
			builder.show();
		}
			break;
		case R.id.optionBTNID:
		{
			Builder builder = new Builder(this);
			builder.setIcon(R.drawable.notify);
			builder.setTitle("请选择");
			builder.setSingleChoiceItems(m_Citys, -1, new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Toast.makeText(MainActivity.this, m_Citys[which], Toast.LENGTH_SHORT).show();
					
				}
			});
			builder.setPositiveButton("确认", new OnClickListener(){
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Toast.makeText(MainActivity.this, "确认键", Toast.LENGTH_SHORT).show();
				}
			});
			builder.setNegativeButton("取消", new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Toast.makeText(MainActivity.this, "取消键", Toast.LENGTH_SHORT).show();
				}
			});
			builder.show();
		}
			break;
		case R.id.multiBTNID:
		{
			Builder builder = new Builder(this);
			for(int i=0; i<m_selected.size(); i++){
				m_selected.set(i, false);
			}
			builder.setIcon(R.drawable.notify);
			builder.setTitle("请选择");
			builder.setMultiChoiceItems(m_Citys, null, new OnMultiChoiceClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which, boolean isChecked) {
//					Log.i("testttttttttttt", m_Citys[which] + "," + (isChecked?"选中":"取消"));
					m_selected.set(which, isChecked);
				}
			});
			
			builder.setPositiveButton("确认", new OnClickListener(){
				@Override
				public void onClick(DialogInterface dialog, int which) {
					StringBuilder text = new StringBuilder();
					for(int i=0; i<m_selected.size(); i++)
					{
						if(m_selected.get(i))
						{
							text.append(m_Citys[i]);
							text.append(",");
						}
					}
					Toast.makeText(MainActivity.this, "选择结果:"+text.toString(), Toast.LENGTH_SHORT).show();
				}
			});
			builder.setNegativeButton("取消", new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Toast.makeText(MainActivity.this, "取消键", Toast.LENGTH_SHORT).show();
				}
			});
			builder.show();
		}
			break;
		case R.id.proccessBTNID:
		{
			final ProgressDialog progressDialog = new ProgressDialog(this);
			progressDialog.setIcon(R.drawable.notify);
			progressDialog.setTitle("下载进度");
			progressDialog.setMax(100);
			progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			progressDialog.show();
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					int i = 0;
					
					while(i<100){
						SystemClock.sleep(200);
						progressDialog.incrementProgressBy(1);
						++i;
						if(progressDialog.getProgress() >= progressDialog.getMax()){
							progressDialog.dismiss();
							Looper.prepare();
							Toast.makeText(MainActivity.this, "下载完成", Toast.LENGTH_SHORT).show();
							Looper.loop();
						}
					}
				}
			}).start();
		}
			break;
		case R.id.statusBTNID:
			notifyProcess(v);
			break;
		case R.id.sendactivityBTNID:
			startActivity(new Intent(this, MainActivity2.class));
			break;
		case R.id.PaintBTNID:
			startActivity(new Intent(this, MainActivity3.class));
			break;
		case R.id.fragdialogBTNID:
//			FragmentManager fm = getFragmentManager();
//			
//			FragmentDialogNote fragmentDialogNote = new FragmentDialogNote();
//			fragmentDialogNote.show(fm, "EditNameDialog");
			showDialogInDifferentScreen(v);
			break;
		case R.id.ninepatchBTNID:
			startActivity(new Intent(this, MainActivity4.class));
			break;
		}
	}
	
	public void showDialogInDifferentScreen(View view){
		FragmentManager fragmentManager = getFragmentManager();
		FragmentDialogNote note = new FragmentDialogNote();
		
		if(getResources().getBoolean(R.bool.large_layout)){
			note.show(fragmentManager, "dialog");
		}else{
//			ContentFragment note1 = new ContentFragment();
			
			FragmentTransaction transaction = fragmentManager.beginTransaction();
//			transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
			transaction.replace(R.id.fragmentlogin, note);
			transaction.addToBackStack(null);
			transaction.commit();
//			Toast.makeText(this, "boot small activity", Toast.LENGTH_SHORT).show();
			
//			FragmentTransaction transaction = fragmentManager  
//                    .beginTransaction();  
//            transaction  
//                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);  
//            transaction.replace(R.id.fragmentlogin, note)  
//                    .commit(); 
		}
    }
	
	public void notifyProcess(View v){
//		Notification notification = new Notification(R.drawable.notify, "down ok", System.currentTimeMillis());
//		PendingIntent pendingIntent = PendingIntent.getActivity(this, 100, new Intent(this, MainActivity2.class), PendingIntent.FLAG_ONE_SHOT);
//		notification.setLatestEventInfo(this, "下载完成", "完成100%", pendingIntent);
//		notification.flags = Notification.FLAG_AUTO_CANCEL;
//		
//		
//		NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//		manager.notify(999, notification);
		
//		startActivity(new Intent(this, MainActivity2.class));
		
//		Notification.Builder mBuilder =
//		        new Notification.Builder(this).setSmallIcon(R.drawable.notify)
//		        .setContentTitle("down ok")
//		        .setContentText("Hello World!");
		// Creates an explicit intent for an Activity in your app
//		Intent resultIntent = new Intent(this, MainActivity2.class);

		// The stack builder object will contain an artificial back stack for the
		// started Activity.
		// This ensures that navigating backward from the Activity leads out of
		// your application to the Home screen.
//		TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
//		// Adds the back stack for the Intent (but not the Intent itself)
//		stackBuilder.addParentStack(MainActivity2.class);
//		// Adds the Intent that starts the Activity to the top of the stack
//		stackBuilder.addNextIntent(resultIntent);
//		PendingIntent resultPendingIntent =
//		        stackBuilder.getPendingIntent(
//		            0,
//		            PendingIntent.FLAG_UPDATE_CURRENT
//		        );
//		PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 100, new Intent(this, MainActivity2.class), PendingIntent.FLAG_ONE_SHOT);
//		
//		mBuilder.setContentIntent(resultPendingIntent);
//		NotificationManager mNotificationManager =
//		    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//		// mId allows you to update the notification later on.
//		mNotificationManager.notify(99, mBuilder.build());
	}

	@Override
	public void onLoginInputComplete(String username, String password) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "name:"+username+",pwd:"+password, Toast.LENGTH_SHORT).show();
	}
}
