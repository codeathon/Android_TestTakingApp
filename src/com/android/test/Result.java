/**
 * 
 */
package com.android.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

/*
 * @author rohit
 */

public class Result extends Activity {
	private EditText txt1;

public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.outcome);
    initcontrol();
}
	
private void initcontrol()	{
	Bundle bundle = this.getIntent().getExtras();
    int count = bundle.getInt("key");
    txt1=(EditText)findViewById(R.id.editText2);
    txt1.setText(""+count+"/10");
}


}