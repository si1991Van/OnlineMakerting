package com.lib;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class Debug {

	private static final boolean test = true;
	private static String fullClassName = "Debug";
	private static String className = "Deubg";
	private static String methodName = "null";
	private static int lineNumber = 0;

	public static void getBundleToString(Bundle bundle) {
		String string = "Bundle{\n";
		for (String key : bundle.keySet()) {
			string += " " + key + " => " + bundle.get(key) + ";\n";
		}
		string += " }Bundle\n";
		Debug.e(string);
	}

	public static void showAlert(final Context context, final String message) {
		((Activity) context).runOnUiThread(new Runnable() {

			@Override
			public void run() {
				if (test) {
					fullClassName = Thread.currentThread().getStackTrace()[3].getClassName();
					className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
					methodName = Thread.currentThread().getStackTrace()[3].getMethodName();
					lineNumber = Thread.currentThread().getStackTrace()[3].getLineNumber();
					Log.e("Tag " + className + "." + methodName + "():" + lineNumber, message);
				} else {
					Log.e("Tag ", message);
				}
				Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
			}
		});

	}

	public static void v(String message) {
		if (test) {
			fullClassName = Thread.currentThread().getStackTrace()[3].getClassName();
			className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
			methodName = Thread.currentThread().getStackTrace()[3].getMethodName();
			lineNumber = Thread.currentThread().getStackTrace()[3].getLineNumber();
			Log.v("Tag " + className + "." + methodName + "():" + lineNumber, message);
		} else {
			Log.v("Tag ", message);
		}
	}

	public static void d(String message) {
		if (test) {
			fullClassName = Thread.currentThread().getStackTrace()[3].getClassName();
			className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
			methodName = Thread.currentThread().getStackTrace()[3].getMethodName();
			lineNumber = Thread.currentThread().getStackTrace()[3].getLineNumber();
			Log.d("Tag " + className + "." + methodName + "():" + lineNumber, message);
		} else {
			Log.d("Tag ", message);
		}
	}

	public static void i(String message) {
		if (test) {
			fullClassName = Thread.currentThread().getStackTrace()[3].getClassName();
			className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
			methodName = Thread.currentThread().getStackTrace()[3].getMethodName();
			lineNumber = Thread.currentThread().getStackTrace()[3].getLineNumber();
			Log.i("Tag " + className + "." + methodName + "():" + lineNumber, message);
		} else {
			Log.i("Tag ", message);
		}
	}

	public static void w(String message) {
		if (test) {
			fullClassName = Thread.currentThread().getStackTrace()[3].getClassName();
			className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
			methodName = Thread.currentThread().getStackTrace()[3].getMethodName();
			lineNumber = Thread.currentThread().getStackTrace()[3].getLineNumber();
			Log.w("Tag " + className + "." + methodName + "():" + lineNumber, message);
		} else {
			Log.w("Tag ", message);
		}
	}

	public static void e(String message) {
		if (test) {
			fullClassName = Thread.currentThread().getStackTrace()[3].getClassName();
			className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
			methodName = Thread.currentThread().getStackTrace()[3].getMethodName();
			lineNumber = Thread.currentThread().getStackTrace()[3].getLineNumber();
			Log.e("Tag " + className + "." + methodName + "():" + lineNumber, message);
		} else {
			Log.e("Tag ", message);
		}
	}

}