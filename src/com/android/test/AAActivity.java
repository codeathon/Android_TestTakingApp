package com.android.test;

//import junit.framework.Test;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

//import android.R;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


public class AAActivity extends Activity implements RadioGroup.OnCheckedChangeListener{
	/**private Button btnlogin;
	private EditText txtuname;
	private EditText txtpswd;
	**/

	int count_correct=0;
	int count_question=0;
	String rbnString;
	String corrString;
		
	private EditText question;
	private TextView qno;
	private TextView cnum;
	private RadioButton rbn1;
	private RadioButton rbn2;
	private RadioButton rbn3;
	private RadioButton rbn4;
	private int rbnSelected;
	private String[] selectedAnswers;
	private Button button1;
	private Button button2;
	private Button button3;
	private Button button4;
	private RadioGroup rbg;
	private String key;
	
	private DataBaseHelper dbh;
	private SQLiteDatabase myDataBase;
	
	private Cursor c;	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        question=(EditText)findViewById(R.id.Question);
        
        qno=(TextView)findViewById(R.id.q_no);
        
        cnum=(TextView)findViewById(R.id.cnum);
 		
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
 		
 		rbn1.setOnClickListener(new RadioButton.OnClickListener() { public void onClick(View v) { rbtn1OnClick();}});
 		rbn2.setOnClickListener(new RadioButton.OnClickListener() { public void onClick(View v) { rbtn2OnClick();}});
 		rbn3.setOnClickListener(new RadioButton.OnClickListener() { public void onClick(View v) { rbtn3OnClick();}});
 		rbn4.setOnClickListener(new RadioButton.OnClickListener() { public void onClick(View v) { rbtn4OnClick();}});	
 	//	rbg.setOnCheckedChangeListener(this);
 		
 		button1.setOnClickListener(new Button.OnClickListener() { public void onClick(View v) { prevOnClick(); }});
 		button2.setOnClickListener(new Button.OnClickListener() { public void onClick(View v) { nextOnClick(); }});
 		button3.setOnClickListener(new Button.OnClickListener() { public void onClick(View v) { confirmOnClick(); }});
 		button4.setOnClickListener(new Button.OnClickListener() { public void onClick(View v) { submitOnClick(); }});
        
        initcontrol();
    }
 
    private void initcontrol() {
    	
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
    
    
    
    private void rbtn1OnClick()	{
    	rbnString = (String) rbn1.getText();
    	if(rbnString.equals(corrString))
			++count_correct;
    	button1.setEnabled(true);
		button2.setEnabled(false);
		button3.setEnabled(true);
	}
	
    private void rbtn2OnClick()	{
    	rbnString = (String) rbn2.getText();
    	if(rbnString.equals(corrString))
			++count_correct;
    	button1.setEnabled(true);
		button2.setEnabled(false);
		button3.setEnabled(true);
	}
    
    private void rbtn3OnClick()	{
    	rbnString = (String) rbn3.getText();
    	if(rbnString.equals(corrString))
			++count_correct;
    	button1.setEnabled(true);
		button2.setEnabled(false);
		button3.setEnabled(true);
	}
    
    private void rbtn4OnClick()	{
    	rbnString = (String) rbn4.getText();
    	if(rbnString.equals(corrString))
			++count_correct;
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
		
			
			cnum.setText(""+count_correct);
			if(c.isLast()){
				button1.setEnabled(true);
				button2.setEnabled(false);
				button3.setEnabled(false);
				button4.setEnabled(true);
			}
			else {
				c.moveToNext();
				setNewQuestion();
				setValues();
			}
	}
	
	private void confirmOnClick()	{
		if(button3.getText().toString() == "Confirm") {

			resetRadio();
			button1.setEnabled(false);
			button2.setEnabled(true);
			button3.setText("Cancel");
		}
		else
			setNewQuestion();
	}
	
	// Submit the result and open the "View Answers" intent.
	private void submitOnClick()	{
		Intent myIntent = new Intent();
		myIntent.setClass(this, Result.class);
		Bundle bundle = new Bundle();
		bundle.putInt("key", count_correct);
		
		Intent myIntent2 = new Intent();
		myIntent2.setClass(this, Answers.class);
		Bundle bundle2 = new Bundle();
		bundle2.putStringArray("selected_answer", selectedAnswers);
		myIntent.putExtras(bundle);
		startActivityForResult(myIntent,0);
	}
	
	
	// Set the values of the display elements
	private void setValues()	{
		
		rbnString = "";
		corrString = "";
		count_question++;
		qno.setText(""+count_question);
		question.setText(c.getString(c.getColumnIndex("Question")).toString());
		rbn1.setText(c.getString(c.getColumnIndex("Choice1")).toString());
		rbn2.setText(c.getString(c.getColumnIndex("Choice2")).toString());
		rbn3.setText(c.getString(c.getColumnIndex("Choice3")).toString());
		rbn4.setText(c.getString(c.getColumnIndex("Choice4")).toString());
		corrString =(c.getString(c.getColumnIndex("Correct")).toString());
	}
	
	// Set a new Question
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

	public void onCheckedChanged(RadioGroup arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

    /**
    private void login() {
    	//System.out.println("In login, outside if");
    //	if ((txtuname.getText().toString()) == ("mainapure") && (txtpswd.getText().toString()) == ("mainapure")) {
			System.out.println("In login");
    		Intent myIntent = new Intent();
    		myIntent.setClass(this, Test.class);
    		startActivity(myIntent);
    	//}
			//AAActivity.this.startActivity(myIntent);
    	
    	
    }**/
}