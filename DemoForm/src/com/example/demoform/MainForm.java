package com.example.demoform;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainForm extends Activity {

	public static final String EXTRA_MESSAGE1 = "com.example.demoform.MESSAGE1";
	public static final String EXTRA_MESSAGE2 = "com.example.demoform.MESSAGE2";
	public static final String EXTRA_MESSAGE3 = "com.example.demoform.MESSAGE3";
	public static final String EXTRA_MESSAGE4 = "com.example.demoform.MESSAGE4";
	
	private EditText editText1 = null;
	private EditText editText2 = null;
	private EditText editText3 = null;
	private EditText editText4 = null;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_form);
	}
	
	public void clearData(View view){
		
		editText1  = (EditText) findViewById(R.id.etxt_firstName);
		editText2 = (EditText) findViewById(R.id.etxt_lastName);
		editText3 = (EditText) findViewById(R.id.etxt_email);
		editText4 = (EditText) findViewById(R.id.etxt_phone);
		editText1.setText("");
		editText2.setText("");
		editText3.setText("");
		editText4.setText("");
		
	}
	
	public void sendData(View view){
		
		System.out.println(view.getId());
		Intent intent = new Intent(this, DisplayFormData.class);
		
		editText1 = (EditText) findViewById(R.id.etxt_firstName);
		editText2 = (EditText) findViewById(R.id.etxt_lastName);
		editText3 = (EditText) findViewById(R.id.etxt_email);
		editText4 = (EditText) findViewById(R.id.etxt_phone);
		
		intent.putExtra(EXTRA_MESSAGE1, editText1.getText().toString());
		intent.putExtra(EXTRA_MESSAGE2, editText2.getText().toString());
		intent.putExtra(EXTRA_MESSAGE3, editText3.getText().toString());
		intent.putExtra(EXTRA_MESSAGE4, editText4.getText().toString());
		
		startActivity(intent);
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_form, menu);
		return true;
	}

}
