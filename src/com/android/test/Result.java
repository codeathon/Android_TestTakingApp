/**
 * 
 */
package com.android.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/*
 * @author rohit
 */

public class Result extends Activity {
	private EditText txt1;
	private Button vAnswers;
	private Button exit;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.outcome);
		initcontrol();
	}
	
	private void initcontrol()	{
	
		exit=(Button)findViewById(R.id.exit);
		
		vAnswers=(Button)findViewById(R.id.vans);
		
		vAnswers.setOnClickListener(new Button.OnClickListener() { public void onClick(View v) { vansOnClick(); }});
	
		exit.setOnClickListener(new Button.OnClickListener() { public void onClick(View v) { exitOnClick(); }});
		
		Bundle bundle = this.getIntent().getExtras();
		int count = bundle.getInt("key");
		txt1=(EditText)findViewById(R.id.editText2);
		txt1.setText(""+count+"/10");
	}

	private void vansOnClick() {
		Bundle bundle2 = this.getIntent().getExtras();
		String[] answers = new String[20];
		answers = bundle2.getStringArray("selected_answer");
		
		Intent myIntent = new Intent();
		myIntent.setClass(this, Answers.class);
		Bundle bundle = new Bundle();
		bundle.putStringArray("answers", answers);
		myIntent.putExtras(bundle);
		startActivityForResult(myIntent,0);
	}
	
	private void exitOnClick() {
		
	}

}