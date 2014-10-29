=====================
OnClanSDK help you to connect your user with all the onClan user system

For more information, visit http://developer.appota.com 

=====================

# I. Install SDK

## 1. Download & unzip the SDK

## 2. Add the SDK to your project

import onClan SDK project to your IDE

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

Add these keys to your plist file:

        <meta-data
            android:name="com.appota.apiKey"
            android:value="YOUR API KEY GOT FROM ONCLAN DEVELOPERS" />
        <meta-data
            android:name="com.facebook.sdk.ApplicationId"
            android:value="YOUR FACEBOOK APP ID" />
        <meta-data
            android:name="onClanGameId"
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
Init onClan SDK on onCreate function of Activity, for example:
        @Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_sample);
		onClanSDK = OnClanSDK.getInstance().initialize(this);
	}

## 2. SDK functions
To post your score to onClan leaderboard, use the following function:

[OCSDKLeaderboard submitScore:USER_SCORE];

If you have many leaderboard categories in your game, such as Best score, best timing... you can pass your LEADERBOARD_ID to the second parameter:

[OCSDKLeaderboard submitScore:USER_SCORE inCategory:LEADERBOARD_ID];

By default, LEADERBOARD_ID = 1 if you have only 1 category. You can create leaderboard at http://developer.appota.com

To show onClan leaderboard, using following function:

[OCSDKConfigure showLeaderBoard];

To show onClan user profile, using following function:
[OCSDKConfigure showUser];

To show Chat, using following function:

[OCSDKConfigure showChat];
