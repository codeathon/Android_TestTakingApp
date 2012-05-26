/**
 * 
 */
package com.android.test;

import java.io.FileOutputStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
/**
 * @author rohit
 *
 */
public class Test extends Activity {
	private EditText question;
	private RadioButton rbn1;
	private RadioButton rbn2;
	private RadioButton rbn3;
	private RadioButton rbn4;
	private int checkedRB=0;
	private String rbnSelected;
	private String corrAnswer;
	private Button button1;
	private Button button2;
	private Button button3;
	private Button button4;
	private RadioGroup rbg;
	private String key;
	private static int count=1;
	
	private DataBaseHelper dbh;
	private SQLiteDatabase myDataBase;
	
	private Cursor c;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        this.initcontrol();
    }
	
/**	
	public void onCheckedChanged(RadioGroup rbg1, int checkedID) {
		checkedRB = rbg.getCheckedRadioButtonId();
		RadioButton rb = (RadioButton)findViewById(checkedRB);
		rbnSelected = rb.getText().toString();
	}
	**/
	private void initcontrol() {
		
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
			
			rbn1.setOnClickListener(new RadioButton.OnClickListener() { public void onClick(View v) { rbtnOnClick();}});
			rbn2.setOnClickListener(new RadioButton.OnClickListener() { public void onClick(View v) { rbtnOnClick();}});
			rbn3.setOnClickListener(new RadioButton.OnClickListener() { public void onClick(View v) { rbtnOnClick();}});
			rbn4.setOnClickListener(new RadioButton.OnClickListener() { public void onClick(View v) { rbtnOnClick();}});	
				
		//	rbg.setOnCheckedChangeListener(this);
			
			button1.setOnClickListener(new Button.OnClickListener() { public void onClick(View v) { prevOnClick(); }});
			button2.setOnClickListener(new Button.OnClickListener() { public void onClick(View v) { nextOnClick(); }});
			button3.setOnClickListener(new Button.OnClickListener() { public void onClick(View v) { confirmOnClick(); }});
			button4.setOnClickListener(new Button.OnClickListener() { public void onClick(View v) { submitOnClick(); }});
	
        dbh = new DataBaseHelper(this);

		try {			
			dbh.createDataBase();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dbh.openDataBase();
		
		c = dbh.getQuestion();
		c.moveToFirst();
		
		setNewQuestion();
		setValues();
	}
	
	private void rbtnOnClick()	{
	//	RadioButton rb = (RadioButton)v;
	//	rbnSelected = rb.getText().toString();
		button1.setEnabled(true);
		button2.setEnabled(false);
		button3.setEnabled(true);
	}
	
	private void prevOnClick()	{
		if(!c.isFirst())	{
			c.moveToPrevious();
			setValues();
		}
		else	{
			button1.setEnabled(false);
		}
	}
	
	private void nextOnClick()	{
		if(!c.isLast()) {
		//	if(rbnSelected == corrAnswer)
				++count;
			c.moveToNext();
			setNewQuestion();
			setValues();
		}
		else	{
			button1.setEnabled(true);
			button2.setEnabled(false);
			button3.setEnabled(false);
			button4.setEnabled(true);
		}
	}
	
	private void confirmOnClick()	{
		if(button3.getText().toString() == "Confirm") {
			//resetRadio();
			button1.setEnabled(false);
			button2.setEnabled(true);
			button3.setText("Cancel");
		}
		else
			setNewQuestion();
	}
	
	private void submitOnClick()	{

		
		Intent myIntent = new Intent();
		myIntent.setClass(this, Result.class);
		Bundle bundle = new Bundle();
		bundle.putInt("key", count);
		myIntent.putExtras(bundle);
		startActivityForResult(myIntent,0);
		c.close();
		dbh.close();
	}
	
	private void setValues()	{
		question.setText(c.getString(c.getColumnIndex("Question")).toString());
		rbn1.setText(c.getString(c.getColumnIndex("Choice1")).toString());
		rbn2.setText(c.getString(c.getColumnIndex("Choice2")).toString());
		rbn3.setText(c.getString(c.getColumnIndex("Choice3")).toString());
		rbn4.setText(c.getString(c.getColumnIndex("Choice4")).toString());
	//	corrAnswer = c.getString(c.getColumnIndex("Correct")).toString();
	}
	
	private void setNewQuestion()	{
		
		rbn1.setEnabled(true);
		rbn1.setChecked(false);
		
		rbn2.setEnabled(true);
		rbn2.setChecked(false);
		
		rbn3.setEnabled(true);
		rbn3.setChecked(false);
		
		rbn4.setEnabled(true);
		rbn4.setChecked(false);
		
		button1.setEnabled(true);
		button2.setEnabled(false);
		button3.setText("Confirm");
		button3.setEnabled(true);
	}
	
	private void resetRadio()	{
		rbn1.setEnabled(false);
		rbn2.setEnabled(false);
		rbn3.setEnabled(false);
		rbn4.setEnabled(false);
	}
}
