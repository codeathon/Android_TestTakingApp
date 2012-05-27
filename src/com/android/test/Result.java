package com.android.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/*
 * @author rohit
 */

public class Result extends Activity {
	private EditText txt1;
	private TextView msg;
	private Button vAnswers;
	private Button exit;

	String[] answers;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.outcome);
		initcontrol();
	}
	
	private void initcontrol()	{
	
		exit=(Button)findViewById(R.id.exit);
		
		vAnswers=(Button)findViewById(R.id.vans);
		
		msg=(TextView)findViewById(R.id.res_msg);
		
		vAnswers.setOnClickListener(new Button.OnClickListener() { public void onClick(View v) { vansOnClick(); }});
	
		exit.setOnClickListener(new Button.OnClickListener() { public void onClick(View v) { exitOnClick(); }});
		
		Bundle bundle = this.getIntent().getExtras();
		int count = bundle.getInt("key");
		txt1=(EditText)findViewById(R.id.editText2);
		txt1.setText(""+count+"/10");
		
		if(7<=count && count<10)
			msg.setText("Excellent Score");
		if(count==10)
			msg.setText("Perfect Score");
		if(count >=5 && count< 7)
			msg.setText("You could do better than that");
		if(count < 5)
			msg.setText("You need to improve");
	}

	private void vansOnClick() {
		Bundle bundle2 = this.getIntent().getExtras();
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