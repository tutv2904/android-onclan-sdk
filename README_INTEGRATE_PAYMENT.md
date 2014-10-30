# First you need to update AppotaGameSDK to the lastest version here 
https://github.com/appota/android-game-sdk

# I. Install SDK

## 1. Download & unzip the SDK

## 2. Add the SDK to your project

import onClan SDK project to your IDE, the SDK project just contains libs folder, other folders are unused

![add](https://github.com/appota/android-onclan-sdk/blob/master/docs/import.png)

reference your project to onClanSDK project

![add](https://github.com/appota/android-onclan-sdk/blob/master/docs/reference.png)

## 3. Config your AndroidManifest.xml file

- onClan SDK will require these permissions to run:

        <uses-permission android:name="android.permission.INTERNET" />
        <uses-permission android:name="android.permission.WRITE_SETTINGS" />
        <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
        <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
        <uses-permission android:name="android.permission.READ_PHONE_STATE" />
        <uses-permission android:name="android.permission.GET_ACCOUNTS" />
        <uses-permission android:name="android.permission.USE_CREDENTIALS" />
        <uses-permission android:name="android.permission.CAMERA" />
        <uses-permission android:name="android.permission.VIBRATE" />
        <uses-permission android:name="android.permission.WAKE_LOCK" />
        <uses-permission android:name="android.permission.RECORD_AUDIO" />
        <uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED" />
    
- Declare SDK Activities:

        <activity android:name="com.onclan.android.home.LoginActivity" >
            <intent-filter>
                <action android:name="com.onclan.android.sdk.login" />
                <category android:name="android.intent.category.DEFAULT" />
                <data
                    android:host="sdk"
                    android:scheme="onclan" />
            </intent-filter>
        </activity>
        <activity
            android:name="com.appota.facebook.LoginActivity"
            android:label="@string/app_name"
            android:theme="@android:style/Theme.Translucent.NoTitleBar" />
        <activity
            android:name="com.onclan.android.core.OnClanActivity"
            android:screenOrientation="landscape"
            android:theme="@android:style/Theme.Dialog" >
        </activity>
        
If you want to use onClan chat feature, declare this:

    <service android:name="com.onclan.android.chat.mqtt.ChatService" />

Add these keys inside "application" tag

        <meta-data
            android:name="com.appota.apiKey"
            android:value="YOUR API KEY GOT FROM ONCLAN DEVELOPERS" />
        <meta-data
            android:name="com.facebook.sdk.ApplicationId"
            android:value="YOUR FACEBOOK APP ID" />
        <meta-data
            android:name="com.onclan.gameId"
            android:value="YOUR ONCLAN GAME ID GOT FROM ONCLAN DEVELOPERS" />
        <meta-data
            android:name="com.appota.twitter.consumer.key"
            android:value="YOUR TWITTER CONSUMER KEY" />
        <meta-data
            android:name="com.appota.twitter.consumer.secret"
            android:value="YOUR TWITTER CONSUMER SECRET" />

<!--![add](https://github.com/appota/ios-onclan-sdk/blob/master/images/sc2.png)-->

# II. Using onClanSDK
## 1. Init SDK
Declare OnClanSDK as a global variable

  	OnClanSDK onClanSDK;
  
Add this inside onCreate method of Activity

  	onClanSDK = OnClanSDK.getInstance();
  	
Init onClanSDK after successfully logged in with AppotaGameSDK

  	private class MyReceiver extends AppotaReceiver {

		@Override
		public void onLoginSuccess(AppotaUser user) {
			//init onClanSDK after login success
			onClanSDK.initOnClanSDK(MainActivity.this, user.getOnClanUser());
		}

		@Override
		public void onLogoutSuccess() {
			//logout onClan after logout success
			onClanSDK.logoutOnClan();
		}

		@Override
		public void onSwitchAccountSuccess(AppotaUser user) {

		}

		// payment success callback
		@Override
		public void onPaymentSuccess(TransactionResult paymentResult) {

		}

		@Override
		public void onLoginFail() {
			// TODO Auto-generated method stub
			
		}
	}

You will also have to destroy the SDK when exit application, add this on onDestroy method of Activity:

	@Override
	protected void onDestroy() {
		super.onDestroy();
		onClanSDK.destroy();
	}
