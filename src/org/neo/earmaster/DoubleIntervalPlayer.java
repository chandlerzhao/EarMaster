package org.neo.earmaster;

import android.content.Context;
import android.view.View;

public class DoubleIntervalPlayer extends IntervalPlayer {

	private int note3;
	
	public DoubleIntervalPlayer(Piano piano) {
		super(piano);
		// TODO Auto-generated constructor stub
	}
	public void setNotes(int note1, int note2, int note3){
		this.note1 = note1;
		this.note2 = note2;
		this.note3 = note3;
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Thread t;
		t = new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				piano.playNote(note1, 1000);
				piano.playNote(note2, 1500);
				piano.playNote(note1, 1000);
				piano.playNote(note3, 1000);
			}
			
		});
		t.start();
	}
	
}
