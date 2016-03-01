package com.smile.android.gsm.utils;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class RemoveUTF8 {

	public RemoveUTF8() {
	}

	public static String getCharacterAscii() {
		return "";
	}

	public static String remove(String string) {
		return string;
	}
	public static String unAccent(String string) {
		  String temp = Normalizer.normalize(string, Normalizer.Form.NFD);
		  Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		  return pattern.matcher(temp).replaceAll("").replace("Đ", "D").replace("đ", "d");
		 }
}
