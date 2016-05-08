package org.neo.earmaster;

import java.util.Random;

import android.content.Context;
import android.media.MediaPlayer;
import java.util.Date;

public class Piano {

	private final String[] group = {"0","1","2","3","4","5","6","7","8"};
	private final String[] scale = {"c","cs","d","ds","e","f","fs","g","gs","a","as","b"};
	public String[] notes;
	private Random ran;
	private Date date;
	private int seed;
	private int ranInt;
	private Context context;
	private MediaPlayer mp;
	
	public Piano(Context context){
		this.context = context;
		notes = new String[88];
		notes[0] = "a0";
		notes[1] = "as0";
		notes[2] = "b0";
		for(int i = 3; i < 88; i++){
			int groupid = (i - 3) / 12;
			int scaleid = (i - 3) % 12;
			notes[i] = scale[scaleid]+ group[groupid];
		}
		for(int i=0; i<88; i++){
			System.out.println(notes[i]);
		}
		ran = new Random();
		date = new Date();
		seed = date.getDay();
		ran.setSeed(seed);
	}
	public int genRandomNote(){
		this.ranInt = ran.nextInt(88);
		return ranInt;
	}
	public int genRandomInterval(){
		return ran.nextInt(13);
	}
	
	public int playNote(int noteId){
		try{
			String noteString = this.notes[noteId];
			int noteInt;
			System.out.println(noteString);
			noteInt = R.raw.class.getField(noteString).getInt(R.raw.class.getField(noteString));
			System.out.println(noteInt);
			mp = MediaPlayer.create(context, noteInt);
			mp.start();
		} catch (Exception e){
			e.printStackTrace();
		}
		System.out.println(mp);
		mp.release();
		return noteId;
	}
}
