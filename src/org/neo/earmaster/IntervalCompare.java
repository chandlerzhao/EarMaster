package org.neo.earmaster;


import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;

public class IntervalCompare extends Activity {
	
	Button ABigger;
	Button BBigger;
	Button ABSame;
	Button play;
	Button playA;
	Button playB;
	MediaPlayer mp;
	Piano piano;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_interval_compare);
		piano = new Piano(this);
		ABigger = (Button) this.findViewById(R.id.button1);
		BBigger = (Button) this.findViewById(R.id.button2);
		ABSame = (Button) this.findViewById(R.id.button3);
		play = (Button) this.findViewById(R.id.button4);
		playA = (Button) this.findViewById(R.id.button5);
		playB = (Button) this.findViewById(R.id.button6);
		int noteA = piano.genRandomNote();
		int noteB, noteC;
		noteB = noteA + piano.genRandomInterval();
		noteC = noteA + piano.genRandomInterval();
		play.setOnClickListener(new DoubleIntervalPlayer(this,noteA,noteB,noteC));
		
		playA.setOnClickListener(new IntervalPlayer(this, noteA, noteB));
		playB.setOnClickListener(new IntervalPlayer(this, noteA, noteC));
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
	
	
}
