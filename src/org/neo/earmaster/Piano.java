package org.neo.earmaster;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import android.R.raw;
import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class Piano {

	private final String[] group = {"0","1","2","3","4","5","6","7","8"};
	private final String[] scale = {"c","cs","d","ds","e","f","fs","g","gs","a","as","b"};

	private Random ran = new Random();
	private Date date = new Date();;
	private int seed;
	private int ranInt;
	private Context context;
	private SoundPool soundPool;

	private String[] noteName;
	private int[] noteResId;
	private HashMap<Integer, Integer> soundMap;
	
	public Piano(Context context){
		this.context = context;
		seed = date.getSeconds();
		ran.setSeed(seed);
		noteResId = new int[88];
		noteName = new String[88];
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
		noteName[0] = "a0";
		noteName[1] = "as0";
		noteName[2] = "b0";
		for(i = 3; i < 88; i++){
			int groupid = (i - 3) / 12 + 1;
			int scaleid = (i - 3) % 12;
			noteName[i] = scale[scaleid]+ group[groupid];
		}
		Class rawC = R.raw.class;
		R.raw raw = new R.raw();
		for(i = 0;i < 88;i++){
			Field field = rawC.getField(noteName[i]);			 
			noteResId[i] = field.getInt(raw);
			System.out.println();
//			System.out.format("%d %s %d\n", i, noteName[i], noteResId[i]);
		}
	}
	
	public String getNoteName(int i) {
		return noteName[i];
	}
	
	public void playNote(int noteId){	
		NotePlayer notePlayer = new NotePlayer(soundPool,soundMap, noteId);
		notePlayer.play();
	}
	
	public void playNote(int noteId, int delay){
		NotePlayer notePlayer = new NotePlayer(soundPool,soundMap, noteId);
		notePlayer.play(delay);
	}
	
	
	public class NotePlayer extends Thread{

		private SoundPool soundPool;
		private HashMap<Integer, Integer> soundMap;
		private int noteId;
		
		public NotePlayer(SoundPool soundPool, HashMap<Integer,Integer> soundMap, int noteId) {
			// TODO Auto-generated constructor stub
			this.soundPool = soundPool;
			this.soundMap = soundMap;
			this.noteId = noteId;
		}

		public void play(){
			this.start();
		}
		
		public void play(int delay){
			this.start();
			try {
				this.sleep(delay);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			soundPool.play(soundMap.get(noteId), 1, 1, 0, 0, 1);
			System.out.println(noteId);
		}
		
		
		
	}
}
