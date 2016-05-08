package org.neo.earmaster;

import android.content.Context;
import android.view.View;

public class DoubleIntervalPlayer extends IntervalPlayer {

	public DoubleIntervalPlayer(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public DoubleIntervalPlayer(Context context, int note1, int note2) {
		super(context, note1, note2);
		// TODO Auto-generated constructor stub
	}

	public DoubleIntervalPlayer(Context context, int note1, int note2, int note3) {
		super(context, note1, note2, note3);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		this.run();
	}
	
	@Override
	public void run(){
		this.playNote(note1);
		this.playNote(note2);
		this.playNote(note1);
		this.playNote(note3);
	}
}
