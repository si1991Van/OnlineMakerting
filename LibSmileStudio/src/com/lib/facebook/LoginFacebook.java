package com.lib.facebook;

import java.util.Arrays;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import android.app.Activity;

public class LoginFacebook {

	public static void onActionLoginFacebook(Activity activity, CallbackManager callbackManager, FacebookCallback<LoginResult> callback) {
		LoginManager.getInstance().registerCallback(callbackManager, callback);
		LoginManager.getInstance().logInWithReadPermissions(activity, Arrays.asList("email", "public_profile", "user_friends"));
	}

}
