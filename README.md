=====================
OnClanSDK help you to connect your user with all the onClan user system

For more information, visit http://developer.appota.com

=====================

If you had integrated AppotaGameSDK before, see https://github.com/appota/android-onclan-sdk/blob/master/README_INTEGRATE_PAYMENT.md for guide to integrate both AppotaGameSDK and onClanSDK

=====================

# I. Install SDK

## 1. Download & unzip the SDK

## 2. Add the SDK to your project

import onClan SDK project to your IDE, the SDK project just contains libs folder, other folders are unused

![add](https://github.com/appota/android-onclan-sdk/blob/master/docs/import.png)

reference your project to onClanSDK project

![add](https://github.com/appota/android-onclan-sdk/blob/master/docs/reference.png)

## 3. Config your AndroidManifest.xml file

- onClan SDK will require these permissions to run:

``` xml
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
```
- Declare SDK Activities:

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
            android:screenOrientation="landscape"
            android:theme="@android:style/Theme.Dialog" >
        </activity>
```        
If you want to use onClan chat feature, declare this:
``` xml
    <service android:name="com.onclan.android.chat.mqtt.ChatService" />
```
Add these keys inside "```xml<application>```" tag
``` xml
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
```

# II. Using onClanSDK
## 1. Init SDK
Init onClan SDK on onCreate function of Activity, for example:
``` java
    @Override
	protected void onCreate(Bundle savedInstance) {
		super.onCreate(savedInstance);
		setContentView(R.layout.activity_sample);
		//To configure which feature of onClanSDK will be use in your app, game. Currenty the SDK support 2 feature to configure: Chat and Leaderboard
		String[] onClanFeatures = new String[] {OnClanSubType.TYPE_CHAT, OnClanSubType.TYPE_LEADERBOARD};
		OnClanSDK onClanSDK = OnClanSDK.getInstance().initialize(this, onClanFeatures);
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
	https://github.com/appota/android-onclan-sdk/tree/master/sample
