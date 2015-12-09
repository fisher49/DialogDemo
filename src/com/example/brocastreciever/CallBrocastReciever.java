package com.example.brocastreciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class CallBrocastReciever extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		String result = getResultData();
		setResultData("17951"+result);
	}

}
