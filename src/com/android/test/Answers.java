/**
 * 
 */
package com.android.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * @author rohit
 *
 */
public class Answers extends Activity {
	
	private EditText question;
	private RadioButton rbn1;
	private RadioButton rbn2;
	private RadioButton rbn3;
	private RadioButton rbn4;
	
	private Button button1;
	private Button button2;
	private Button button3;
	private Button button4;
	private RadioGroup rbg;


	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.answers);
		question=(EditText)findViewById(R.id.Question);
		
		rbn1=(RadioButton)findViewById(R.id.Choice1);
		rbn2=(RadioButton)findViewById(R.id.Choice2);
		rbn3=(RadioButton)findViewById(R.id.Choice3);
		rbn4=(RadioButton)findViewById(R.id.Choice4);
		
		rbg=(RadioGroup)findViewById(R.id.Choices);
		
		button1=(Button)findViewById(R.id.Prev);
		button1.setText("Prev");
		
		button2=(Button)findViewById(R.id.Next);
		button2.setText("Next");
		
		button3=(Button)findViewById(R.id.Confirm);
		button3.setText("Confirm");
		
		button4=(Button)findViewById(R.id.Submit);
		button4.setText("Submit");	
		button4.setEnabled(false);
	}
}