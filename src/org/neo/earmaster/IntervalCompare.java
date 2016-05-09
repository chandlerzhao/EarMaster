package org.neo.earmaster;


import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class IntervalCompare extends Activity {
	
	Button ABigger;
	Button BBigger;
	Button ABSame;
	Button play;
	Button playA;
	Button playB;
	TextView oldCombo, curCombo;
	ImageView sign;
	
	MediaPlayer mp;
	Piano piano;
	SharedPreferences mySharedPreferences;
	Editor editor;
	DoubleIntervalPlayer dIP;
	IntervalPlayer ipA, ipB;
	
	long oldMax = 0, combo = 0;
	int noteA, noteB, noteC;
	int answer;
	final String TAG = "IntervalCompare Activity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_interval_compare);
		ABigger = (Button) this.findViewById(R.id.button1);
		BBigger = (Button) this.findViewById(R.id.button2);
		ABSame = (Button) this.findViewById(R.id.button3);
		play = (Button) this.findViewById(R.id.button4);
		playA = (Button) this.findViewById(R.id.button5);
		playB = (Button) this.findViewById(R.id.button6);
		oldCombo = (TextView) this.findViewById(R.id.textView2);
		curCombo = (TextView) this.findViewById(R.id.textView4);
		sign = (ImageView) this.findViewById(R.id.imageView1);
		
		sign.setVisibility(ImageView.INVISIBLE);
		
		Context ctx = IntervalCompare.this;
		mySharedPreferences = ctx.getSharedPreferences("score", MODE_PRIVATE);
		editor = mySharedPreferences.edit();
		oldMax = mySharedPreferences.getLong("maxIntervalCompare",0);
		oldCombo.setText(String.valueOf(oldMax));
		curCombo.setText(String.valueOf(0));
		
		piano = new Piano(this);
		dIP = new DoubleIntervalPlayer(piano);
		ipA = new IntervalPlayer(piano);
		ipB = new IntervalPlayer(piano);
		genNotes();
		

		System.out.println(piano.getNoteName(noteA) + " " + piano.getNoteName(noteB) + " " + piano.getNoteName(noteC));
		Judger AJudger = new Judger(-1);
		Judger BJudger = new Judger(1);
		Judger CJudger = new Judger(0);
		ABigger.setOnClickListener(AJudger);
		BBigger.setOnClickListener(BJudger);
		ABSame.setOnClickListener(CJudger);
		
		play.setOnClickListener(dIP);
		playA.setOnClickListener(ipA);
		playB.setOnClickListener(ipB);
	}
	@Override
	protected void onDestroy(){
		super.onDestroy();
		editor.putLong("maxIntervalCompare", combo);
		editor.commit();
		Log.d(TAG, "----onDestroy----");
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.interval_compare, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void genNotes(){
		noteA = piano.genRandomNote();
		noteB = noteA + piano.genRandomInterval();
		noteC = noteA + piano.genRandomInterval();
		if(noteB > noteC)
			answer = -1;
		else if(noteB == noteC)
			answer = 0;
		else if(noteB < noteC)
			answer = 1;
		ipA.setNotes(noteA, noteB);
		ipB.setNotes(noteA, noteC);
		dIP.setNotes(noteA, noteB, noteC);
	}
	
	public void addCombo(){
		combo++;
		sign.setVisibility(ImageView.VISIBLE);
		sign.setImageDrawable(this.getResources().getDrawable(R.drawable.yes));
		curCombo.setText(String.valueOf(combo));
	}
	
	public void zeroCombo(){
		combo = 0;
		sign.setVisibility(ImageView.VISIBLE);
		sign.setImageDrawable(this.getResources().getDrawable(R.drawable.no));
		curCombo.setText(String.valueOf(combo));
	}
	
	
	class Judger implements OnClickListener{
		int userAnswer;
		
		public Judger(int userAnswer) {
			super();
			this.userAnswer = userAnswer;
		}

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			System.out.println(userAnswer);
			if(userAnswer == answer){
				addCombo();
			} else {
				zeroCombo();
			}
			genNotes();
		}
		
	}
}
