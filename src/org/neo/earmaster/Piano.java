package org.neo.earmaster;

import java.util.Random;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import java.util.Date;
import java.util.HashMap;

public class Piano {

	private final String[] group = {"0","1","2","3","4","5","6","7","8"};
	private final String[] scale = {"c","cs","d","ds","e","f","fs","g","gs","a","as","b"};
	private String[] noteName;
	private int[] noteResId;
	private HashMap<Integer, Integer> soundMap;
	private Random ran = new Random();
	private Date date = new Date();;
	private int seed;
	private int ranInt;
	private Context context;
	private SoundPool soundPool;
	
	public Piano(Context context){
		this.context = context;
		seed = date.getSeconds();
		ran.setSeed(seed);
		soundMap = new HashMap<Integer, Integer>();
		try {
			genNotes();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		soundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
		for(int i = 0;i < 88; i++){
			soundMap.put(i, soundPool.load(context, noteResId[i], 1));
		}
	}
	public int genRandomNote(){
		this.ranInt = ran.nextInt(88);
		return ranInt;
	}
	public int genRandomInterval(){
		return ran.nextInt(13);
	}
	
	public void genNotes() throws IllegalAccessException, IllegalArgumentException, NoSuchFieldException{
		int i;
		noteName = new String[88];
		noteResId = new int[88];
		noteName[0] = "a0";
		noteName[1] = "as0";
		noteName[2] = "b0";
		for(i = 3; i < 88; i++){
			int groupid = (i - 3) / 12;
			int scaleid = (i - 3) % 12;
			noteName[i] = scale[scaleid]+ group[groupid];
		}
		for(i = 0;i < 88;i++){
			noteResId[i] = R.raw.class.getField(this.noteName[i]).getInt(R.raw.class.getField(this.noteName[i]));
			System.out.format("%d %s %d\n", i, noteName[i], noteResId[i]);
		}
	}
	
	public void playNote(int noteId){
		soundPool.play(soundMap.get(noteId), 1, 1, 0, 0, 1);
	}
}
