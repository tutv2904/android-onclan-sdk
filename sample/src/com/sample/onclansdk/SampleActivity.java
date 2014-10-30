package com.sample.onclansdk;


import android.app.Activity;
import android.os.Bundle;

import com.onclan.android.core.OnClanSDK;

public class SampleActivity extends Activity {
	
	OnClanSDK onClanSDK;
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_sample);
		onClanSDK = OnClanSDK.getInstance().initialize(this);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		onClanSDK.destroy();
	}
}
