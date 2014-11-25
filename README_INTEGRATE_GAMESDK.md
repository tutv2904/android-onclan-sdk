# First you need to update AppotaGameSDK to the latest version here 
https://github.com/appota/android-game-sdk

# I. Install onClanSDK

## 1. Download & unzip onClanSDK

## 2. Add onClanSDK to your project

import onClanSDK project to your IDE, the SDK project just contains libs folder, other folders are unused

![add](https://github.com/appota/android-onclan-sdk/blob/master/docs/import.png)

reference your project to onClanSDK project

![add](https://github.com/appota/android-onclan-sdk/blob/master/docs/reference2.png)

## 3. Config your AndroidManifest.xml file

- GameSDK and onClan SDK will require these permissions to run:
``` xml
	    <!-- game SDK -->
	    <uses-permission android:name="android.permission.INTERNET" />
	    <uses-permission android:name="android.permission.WRITE_SETTINGS" />
	    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
	    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
	    <uses-permission android:name="android.permission.READ_PHONE_STATE" />
	    <uses-permission android:name="android.permission.GET_ACCOUNTS" />
	    <uses-permission android:name="android.permission.USE_CREDENTIALS" />
	    
	    <!-- onClan SDK -->
	    <uses-permission android:name="android.permission.CAMERA" />
	    <uses-permission android:name="android.permission.VIBRATE" />
	    <uses-permission android:name="android.permission.WAKE_LOCK" />
	    <uses-permission android:name="android.permission.RECORD_AUDIO" />
	    <uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED" />
	    <!-- use this for google payment -->
	    <uses-permission android:name="com.android.vending.BILLING" />
```    
- Declare additional onClanSDK Activities:
``` xml
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
            android:theme="@android:style/Theme.Dialog" >
        </activity>
```        
If you want to use onClan chat feature, declare this:
``` xml
    <service android:name="com.onclan.android.chat.mqtt.ChatService" />
```
Add these keys inside ```<application>``` tag
``` xml
        <meta-data
            android:name="com.appota.apiKey"
            android:value="YOUR API KEY GOT FROM ONCLAN DEVELOPERS" />
        <meta-data
            android:name="com.facebook.sdk.ApplicationId"
            android:value="YOUR FACEBOOK APP ID" />
        <meta-data
            android:name="com.appota.twitter.consumer.key"
            android:value="YOUR TWITTER CONSUMER KEY" />
        <meta-data
            android:name="com.appota.twitter.consumer.secret"
            android:value="YOUR TWITTER CONSUMER SECRET" />
```

# II. Using onClanSDK
## 1. Init SDK
Declare OnClanSDK as a global variable
``` java
  	OnClanSDK onClanSDK;
```  
Add this inside onCreate method of Activity
``` java
  	onClanSDK = OnClanSDK.getInstance();
```
Init onClanSDK after successfully logged in with AppotaGameSDK
``` java
  	private class MyReceiver extends AppotaReceiver {

		@Override
		public void onLoginSuccess(AppotaUser user) {
			//init onClanSDK after login success
			///To configure which feature of onClanSDK will be use in your app, game. Currenty the SDK support 2 feature to configure: Chat and Leaderboard
			String[] onClanFeatures = new String[] {OnClanSubType.TYPE_CHAT, OnClanSubType.TYPE_LEADERBOARD};
			onClanSDK.initOnClanSDK(MainActivity.this, onClanFeatures, user.getOnClanUser());
			//if you want to disable logout in onClanSDK, add this
        onClanSDK.enableLogout(false);
		}

		@Override
		public void onLogoutSuccess() {
			//logout onClan after logout success
			onClanSDK.logoutOnClan();
		}

		@Override
		public void onSwitchAccountSuccess(AppotaUser user) {
			//switch account
			onClanSDK.switchAccount(user.getOnClanUser());
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
```
You will also have to destroy the SDK when exit application, add this on onDestroy method of Activity:
``` java
	@Override
	protected void onDestroy() {
		super.onDestroy();
		onClanSDK.destroy();
	}
```
## 2. SDK functions	
	
To immediately show chat screen, call:
``` java
	onClanSDK.showChat();
```	
To show leaderboard, call:
``` java
	onClanSDK.showLeaderBoard();
```	
To show main sdk screen, call:
``` java
	onClanSDK.showHome();
```
For more details see sample code
	https://github.com/appota/android-onclan-sdk/tree/master/sample_payment
