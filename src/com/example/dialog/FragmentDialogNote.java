package com.example.dialog;

import java.util.zip.Inflater;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;

public class FragmentDialogNote extends DialogFragment implements OnClickListener{

//	@Override
//	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
////		return inflater.inflate(R.layout.frag_dialog, container);
//		View view = inflater.inflate(R.layout.fragment_edit_name, container);  
//        return view;  
//	}
	
	private EditText mUsername;  
    private EditText mPassword; 
	
	public interface LoginInputListener  
    {  
        void onLoginInputComplete(String username, String password);  
    }

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
//		return super.onCreateDialog(savedInstanceState);
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		
		
		LayoutInflater inflater = getActivity().getLayoutInflater(); 
		View view = inflater.inflate(R.layout.fragment_login_dialog, null);
		mUsername = (EditText) view.findViewById(R.id.id_txt_username);
		mPassword = (EditText) view.findViewById(R.id.id_txt_password);
		
		builder.setView(view)
				.setPositiveButton("ok", this)
				.setNegativeButton("cancel", null);
		
		return builder.create();
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub
		if(getActivity() instanceof LoginInputListener){
			LoginInputListener listener = (LoginInputListener) getActivity();
			listener.onLoginInputComplete(mUsername.getText().toString(),
						mPassword.getText().toString());
		}
	}
	
	/*	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
//		return super.onCreateView(inflater, container, savedInstanceState);

		getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
		return inflater.inflate(R.layout.fragment_login_dialog, container, false);
	}*/
	
/*
	private EditText mUsername;  
	private EditText mPassword;  

	public interface LoginInputListener  
	{  
		void onLoginInputComplete(String username, String password);  
	}  

	

	@Override  
	public Dialog onCreateDialog(Bundle savedInstanceState)  
	{  
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());  
		// Get the layout inflater  
		LayoutInflater inflater = getActivity().getLayoutInflater();  
		View view = inflater.inflate(R.layout.fragment_login_dialog, null);  
		mUsername = (EditText) view.findViewById(R.id.id_txt_username);  
		mPassword = (EditText) view.findViewById(R.id.id_txt_password);  
		// Inflate and set the layout for the dialog  
		// Pass null as the parent view because its going in the dialog layout  
		builder.setView(view)  
		// Add action buttons  
		.setPositiveButton("Sign in",  
				new DialogInterface.OnClickListener()  
		{  
			@Override  
			public void onClick(DialogInterface dialog, int id)  
			{  
				LoginInputListener listener = (LoginInputListener) getActivity();  
				listener.onLoginInputComplete(mUsername  
						.getText().toString(), mPassword  
						.getText().toString());  
			}  
		}).setNegativeButton("Cancel", null);  
		return builder.create();  
	}  
	*/
}
