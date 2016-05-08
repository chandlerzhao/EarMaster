package org.neo.earmaster;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;

public class IntervalPlayer extends Piano implements OnClickListener, Runnable{
	protected int note1, note2, note3;

	public void setInterval(int note1, int note2){
		this.note1 = note1;
		this.note2 = note2;
	}
	
	public void setInterval(int note1, int note2, int note3){
		this.note1 = note1;
		this.note2 = note2;
		this.note3 = note3;
	}
	
	public IntervalPlayer(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public IntervalPlayer(Context context, int note1, int note2) {
		super(context);
		// TODO Auto-generated constructor stub
		setInterval(note1, note2);
	}
	
	public IntervalPlayer(Context context, int note1, int note2, int note3) {
		super(context);
		// TODO Auto-generated constructor stub
		setInterval(note1, note2, note3);
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		this.run();
	}
	
	public void run(){
		this.playNote(note1);
		this.playNote(note2);
	}
}
