=====================
OnClanSDK help you to connect your user with all the onClan user system

For more information, visit http://developer.appota.com

=====================

If you had integrated AppotaGameSDK before, see https://github.com/appota/android-onclan-sdk/blob/master/README_INTEGRATE_PAYMENT.md for guide to integrate both AppotaGameSDK and onClanSDK

=====================

# I. Install SDK

## 1. Download

Download the onClanSDK jar file in https://github.com/appota/android-onclan-sdk/tree/master/onClanSDKLibrary folder

## 2. Add the SDK to your project

Add the downloaded jar file to libs folder in your project

![add](https://github.com/appota/android-onclan-sdk/blob/master/docs/import2.png)

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
            android:configChanges="orientation|screenSize|keyboardHidden"
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
        <!--optional-->
        <meta-data
            android:name="com.onclan.gameId"
            android:value="YOUR ONCLAN GAMEID GOT FROM ONCLAN DEVELOPERS" />
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
		//if you want to disable logout in onClanSDK, add this
		onClanSDK.enableLogout(false);
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

To show onClan floating button manually, call:
``` java
	onClanSDK.showOnClanButton();
```

To hide onClan floating button manually, call:
``` java
	onClanSDK.hideOnClanButton();
```

To set onClan floating button draggable, call:
``` java
	onClanSDK.setOnClanButtonDraggable(boolean draggable);
```

To set onClan floating button at fixed position (onClanButton must be undraggable using setDraggable function above), call:
``` java
	//onClan button must be undraggable first
	onClanSDK.setOnClanButtonDraggable(false);
	onClanSDK.setOnClanButtonPosition(int posX, int posY);
```

For more details see sample code
	https://github.com/appota/android-onclan-sdk/tree/master/sample
