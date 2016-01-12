package com.smile.android.gsm.utils;

public class SmileUtils {

	public static boolean isEmpty(String string) {
		return string.equals("");
	}

	public static int parseInt(String string, int defaultValues) throws NumberFormatException {
		int value = defaultValues;
		value = Integer.parseInt(string);
		return value;
	}

	public static long parseLong(String string, long defaultValues) throws NumberFormatException {
		long value = defaultValues;
		value = Long.parseLong(string);
		return value;
	}

	public static double parseDouble(String string, double defaultValues) throws NumberFormatException {
		double value = defaultValues;
		value = Double.parseDouble(string);
		return value;
	}

}
