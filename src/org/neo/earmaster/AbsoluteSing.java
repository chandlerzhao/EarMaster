package org.neo.earmaster;

import java.io.IOException;

import android.app.Activity;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class AbsoluteSing extends Activity {
	
	private int audioSource = MediaRecorder.AudioSource.MIC;
	private static int sampleRateInHz = 44100;
	private static int channelConfig = AudioFormat.CHANNEL_IN_MONO;
	private static int audioFormat = AudioFormat.ENCODING_PCM_16BIT;
	private int bufferSizeInBytes = 0;
	private AudioRecord audioRecord;
	private String ACTIVITY_TAG = "AbsoluteSing";
	private boolean isRecord = false;
	private double currfreq;
	
	private void startRecord(){
		audioRecord.startRecording();
		isRecord = true;
		new Thread(new PitchDetector()).start();
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_absolute_sing);
		bufferSizeInBytes = AudioRecord.getMinBufferSize(sampleRateInHz, channelConfig, audioFormat);
		Log.v(ACTIVITY_TAG, "Buffer Size: " +  Integer.toString(bufferSizeInBytes));
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.absolute_sing, menu);
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
	
	class PitchDetector implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			int readsize = 0;
			byte[] audiodata = new byte[bufferSizeInBytes];
			while(isRecord == true){
				readsize = audioRecord.read(audiodata, 0, bufferSizeInBytes);
				if (AudioRecord.ERROR_INVALID_OPERATION != readsize) {  
	                try {  
	                	
	                } catch (Exception e) {  
	                    e.printStackTrace();  
	                }  
	            } 
			}
		}
		
	}
}
