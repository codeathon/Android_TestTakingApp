/**
 * 
 */
package com.android.test;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * @author rohit
 *
 */
public class Answers extends Activity {
	

	private DataBaseHelper dbh2;
	private SQLiteDatabase myDataBase;
	
	private EditText question;
	
	private TextView msg;
	
	private RadioButton rbn1;
	private RadioButton rbn2;
	private RadioButton rbn3;
	private RadioButton rbn4;
	
	private Button button1;
	private Button button2;
	private Button button3;
	
	private RadioGroup rbg;
	private Cursor c;	
	private String[] answers;
	

	int count_correct=0;
	int count_question=1;
	String rbnString;
	String corrString;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.answers);
		question=(EditText)findViewById(R.id.a_Question);

		msg=(TextView)findViewById(R.id.a_msg);

		rbn1=(RadioButton)findViewById(R.id.a_Choice1);
		rbn2=(RadioButton)findViewById(R.id.a_Choice2);
		rbn3=(RadioButton)findViewById(R.id.a_Choice3);
		rbn4=(RadioButton)findViewById(R.id.a_Choice4);
		
		rbg=(RadioGroup)findViewById(R.id.a_Choices);
		
		button1=(Button)findViewById(R.id.a_Prev);
		
		button2=(Button)findViewById(R.id.a_Next);
		
		button3=(Button)findViewById(R.id.a_Exit);
		
		button1.setOnClickListener(new Button.OnClickListener() { public void onClick(View v) { prevOnClick(); }});
 		button2.setOnClickListener(new Button.OnClickListener() { public void onClick(View v) { nextOnClick(); }});
 		button3.setOnClickListener(new Button.OnClickListener() { public void onClick(View v) { exitOnClick(); }});
 		
 		Bundle bundle3 = this.getIntent().getExtras();
		answers = bundle3.getStringArray("answers");
 		
 		initcontrol();
	}
	
	private void initcontrol() {
    	
    	dbh2 = new DataBaseHelper(this);
 		try {			
 			dbh2.createDataBase();
 		} catch (IOException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
 		
 		dbh2.openDataBase();
 		
 		c = dbh2.getQuestion();
 		c.moveToFirst();
 		setValues();
    }
    
	
	private void prevOnClick()	{
//		if(!c.isFirst())	{
			c.moveToPrevious();
			setValues();
//		}
//		else	{
//			button1.setEnabled(false);
		}
	

	private void nextOnClick()	{		
//		if(c.isLast()){
//			button1.setEnabled(true);
//			button2.setEnabled(false);
//		}
//		else {
		++count_question;

			c.moveToNext();
			setValues();
		
	}
	
	private void exitOnClick() {
		
	}
	private void setValues()	{
		
		rbn1.setTextColor(Color.BLACK);
		rbn2.setTextColor(Color.BLACK);
		rbn3.setTextColor(Color.BLACK);
		rbn4.setTextColor(Color.BLACK);

		question.setText(c.getString(c.getColumnIndex("Question")).toString());
		rbn1.setText(c.getString(c.getColumnIndex("Choice1")).toString());
		rbn2.setText(c.getString(c.getColumnIndex("Choice2")).toString());
		rbn3.setText(c.getString(c.getColumnIndex("Choice3")).toString());
		rbn4.setText(c.getString(c.getColumnIndex("Choice4")).toString());
		corrString =(c.getString(c.getColumnIndex("Correct")).toString());
		
		
			if(rbn1.getText().toString().equals(answers[count_question])) {
				rbn1.setTextColor(Color.RED);
				if(rbn1.getText().toString().equals(corrString)) {
					rbn1.setTextColor(Color.GREEN);
					msg.setText("Congratulations ! You got this one right !");
				}	
				if(rbn2.getText().toString().equals(corrString)) {
					rbn2.setTextColor(Color.GREEN);
					msg.setText("Oops ! Sorry, you got this one wrong !");
				}
				if(rbn3.getText().toString().equals(corrString)) {
					rbn3.setTextColor(Color.GREEN);
					msg.setText("Oops ! Sorry, you got this one wrong !");
				}	
				if(rbn4.getText().toString().equals(corrString)) {
					rbn4.setTextColor(Color.GREEN);
					msg.setText("Oops ! Sorry, you got this one wrong !");
				}
			}
			if(rbn2.getText().toString().equals(answers[count_question])) {
				rbn2.setTextColor(Color.RED);
				if(rbn1.getText().toString().equals(corrString)) {
					rbn1.setTextColor(Color.GREEN);
					msg.setText("Oops ! Sorry, you got this one wrong !");
				}
					
				if(rbn2.getText().toString().equals(corrString)) {
					rbn2.setTextColor(Color.GREEN);
					msg.setText("Congratulations ! You got this one right !");
				}	
				if(rbn3.getText().toString().equals(corrString)) {
					rbn3.setTextColor(Color.GREEN);
					msg.setText("Oops ! Sorry, you got this one wrong !");
				}
				if(rbn4.getText().toString().equals(corrString)) {
					rbn4.setTextColor(Color.GREEN);
					msg.setText("Oops ! Sorry, you got this one wrong !");
				}
			}
			if(rbn3.getText().toString().equals(answers[count_question])) {
				rbn3.setTextColor(Color.RED);
				if(rbn1.getText().toString().equals(corrString)) {
					rbn1.setTextColor(Color.GREEN);
					msg.setText("Oops ! Sorry, you got this one wrong !");
				}
				if(rbn2.getText().toString().equals(corrString)) {
					rbn2.setTextColor(Color.GREEN);
					msg.setText("Oops ! Sorry, you got this one wrong !");
				}
				if(rbn3.getText().toString().equals(corrString)) {
					rbn3.setTextColor(Color.GREEN);
					msg.setText("Congratulations ! You got this one right !");
				}
				if(rbn4.getText().toString().equals(corrString)) {
					rbn4.setTextColor(Color.GREEN);
					msg.setText("Oops ! Sorry, you got this one wrong !");
				}
			}
			if(rbn4.getText().toString().equals(answers[count_question])) {
				rbn4.setTextColor(Color.RED);
				if(rbn1.getText().toString().equals(corrString)) {
					rbn1.setTextColor(Color.GREEN);
					msg.setText("Oops ! Sorry, you got this one wrong !");
				}
				if(rbn2.getText().toString().equals(corrString)) {
					rbn2.setTextColor(Color.GREEN);
					msg.setText("Oops ! Sorry, you got this one wrong !");
				}
				if(rbn3.getText().toString().equals(corrString)) {
					rbn3.setTextColor(Color.GREEN);
					msg.setText("Oops ! Sorry, you got this one wrong !");
				}
				if(rbn4.getText().toString().equals(corrString)) {
					rbn4.setTextColor(Color.GREEN);
					msg.setText("Congratulations ! You got this one right !");
				}	
			}
		}
	}
