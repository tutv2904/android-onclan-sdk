package com.sample.onclansdk;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.onclan.android.core.OnClanSDK;
import com.onclan.android.core.OnClanSubType;

public class SampleActivity extends Activity {
	
	private OnClanSDK onClanSDK;
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_sample);
		String[] onClanFeatures = new String[] {OnClanSubType.TYPE_CHAT, OnClanSubType.TYPE_LEADERBOARD};
		onClanSDK = OnClanSDK.getInstance().initialize(this, onClanFeatures);
	}
	
	public void showHome(View v){
		onClanSDK.showHome();
	}
	
	public void showChat(View v){
		onClanSDK.showChat();
	}
	
	public void showLeaderboard(View v){
		onClanSDK.showLeaderBoard();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		onClanSDK.destroy();
	}
}
