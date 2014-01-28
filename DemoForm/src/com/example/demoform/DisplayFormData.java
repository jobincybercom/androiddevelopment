package com.example.demoform;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DisplayFormData extends Activity {

	
	private TextView textView=null;
	private TextView textView1=null;
	private TextView textView2=null;
	private TextView textView3=null;
	private TextView textView4=null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent intent = getIntent();
		
		LinearLayout layout = new LinearLayout(this);
		
		textView =  new TextView(this);
		textView1 = new TextView(this);
		textView2 = new TextView(this);
		textView3 = new TextView(this);
		textView4 = new TextView(this);
		
		textView.setText("Welcome "+intent.getStringExtra(MainForm.EXTRA_MESSAGE1));
		textView.setGravity(Gravity.CENTER);
		textView1.setText(intent.getStringExtra(MainForm.EXTRA_MESSAGE1));
		textView1.setGravity(Gravity.LEFT);
		textView2.setText(intent.getStringExtra(MainForm.EXTRA_MESSAGE2));
		textView2.setGravity(Gravity.LEFT);
		textView3.setText(intent.getStringExtra(MainForm.EXTRA_MESSAGE3));
		textView3.setGravity(Gravity.LEFT);
		textView4.setText(intent.getStringExtra(MainForm.EXTRA_MESSAGE4));
		textView4.setGravity(Gravity.LEFT);
		
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));  
		layout.addView(textView);
		layout.addView(textView1);
		layout.addView(textView2);
		layout.addView(textView3);
		layout.addView(textView4);
		layout.setGravity(Gravity.LEFT);
		
		setContentView(layout);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_form_data, menu);
		return true;
	}

}
