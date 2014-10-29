=====================
OnClanSDK help you to connect your user with all the onClan user system

For more information, visit http://developer.appota.com 

=====================

# I. Install SDK

## 1. Download & unzip the SDK

Make sure you are using the latest version of XCode (6.0+) and targeting iOS 5.1.1 or higher

## 2. Add the SDK to your project

Drag onClanSDK.framework, OnClanBundle.bundle, libssl.a, libcrypto.a to your Xcode project folder target.

Make sure the "Copy items to destination's group folder" checkbox is checked.

![add](https://github.com/appota/ios-onclan-sdk/blob/master/images/sc1.png)

## 3. Add dependencies

Click on Targets → Your app name → and then the 'Build Phases' tab.
Expand 'Link Binary With Libraries' as shown.

![add](https://github.com/appota/ios-onclan-sdk/blob/master/images/sc3.png)

Click the + button in the bottom left of the 'Link Binary With Libraries' section and add the following libraries:

Twitter.framework, 
Social.framework (optional), 
ImageIO.framework, 
StoreKit.framework, 
CoreGraphics.framework, 
Mapkit.framework, 
Accounts.framework (optional), 
CoreMedia.framework, 
Foundation.framework, 
CFNetwork.framework, 
SystemConfiguration.framework, 
Security.framework, 
AVFoundation.framework, 
AssetLibrary.framework, 
libsqlite3.dylib

For social login, add those framework to your project:

FacebookSDK.framework, 
GoogleOpenSource.framework, 
GooglePlus.bundle, 
GooglePlus.framework

## 4. Config your plist file
Add these keys to your plist file:

- AppotaClientId (Get Appota client ID from http://developer.appota.com) 
- FacebookAppID use your Facebook Application ID
- GOOGLE_CLIENT_ID use your Google Client ID
- TWITTER_CONSUMER_KEY
- TWITTER_CONSUMER_SECRET

Add url schemes key:

- appotaYOUR_CLIENT_ID											
- your_bundle_id (Use your bundle ID as scheme url for Google login SDK)
- fbYOUR_FACEBOOK_APPID

![add](https://github.com/appota/ios-onclan-sdk/blob/master/images/sc2.png)

# II. Using onClanSDK
## 1. Setting
Open up your AppDelegate.m file and add the following import to the top of the file:

`#import <OnClanSDK/OCSDK.h>`

To show onClan floating button on your app, paste the following inside the application:didFinishLaunchingWithOptions: function:

`[OCSDKConfigure configWithAPIKey:YOUR_APP_API_KEY];`

Add the following inside function application: openURL: sourceApplication: annotation: to handle onClan login & onClan communication

`- (BOOL) application:(UIApplication *)application openURL:(NSURL *)url sourceApplication:(NSString *)sourceApplication annotation:(id)annotation {`
`return [OCSDKConfigure handleOpenURL:url sourceApplication:sourceApplication annotation:annotation];`
`}`

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
