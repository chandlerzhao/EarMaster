package org.neo.earmaster;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;

public class IntervalPlayer implements OnClickListener{
	protected int note1, note2;
	protected Piano piano;
	
	

	public IntervalPlayer(Piano piano) {
		this.piano = piano;
	}
	
	public void setNotes(int note1, int note2){
		this.note1 = note1;
		this.note2 = note2;
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
				piano.playNote(note2, 1000);
			}
			
		});
		t.start();
	}
	
}
