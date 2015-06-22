package com.school.jazy;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

public class SplashDisplayerActivity extends Activity {
	// Display my little logo for 3 seconds
	boolean done = false;
	MediaPlayer mp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);

		mp = MediaPlayer.create(this, R.raw.splash);
		mp.start();

		Thread timer = new Thread() {
			public void run() {
				try {
					Thread.sleep(6000);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					done = true;
					startActivity(new Intent("com.school.jazy.SCHOOLXMLGETTERACTIVITY"));
				}
			}
		};
		timer.start();
	}

	@Override
	protected void onPause() {
		super.onPause();
		mp.release();
		finish();
	}

}
