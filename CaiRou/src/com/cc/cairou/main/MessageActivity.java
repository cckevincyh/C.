package com.cc.cairou.main;

import java.util.Random;

import com.cc.cairou.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;


public class MessageActivity extends Activity{
	private SpacingTextView mSpacingTextView;
	private String[] mess = {
		"点进来啊!~",
	
	};
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        Random r = new Random();
        int nextInt = r.nextInt(mess.length);
        Toast.makeText(this, mess[nextInt], Toast.LENGTH_SHORT).show();
        String message = MessageDao.getMessage();
        mSpacingTextView = (SpacingTextView) findViewById(R.id.space_text);
        mSpacingTextView.setText(message);
        mSpacingTextView.setLetterSpacing(10);

	}
}
