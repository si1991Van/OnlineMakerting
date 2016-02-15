package com.onlinemarketing.config;

import com.onlinemarketing.object.OutputProduct;

public class SystemConfig {

	public static final String API = "http://localhost:8000/api/";

	public static final String Login = "login?";
	public static final String Register = "register?";
	public static final int statusLogin = 1;
	public static final int statusRegister = 2;
	public static String device_id;
	public static String user_id = "";
	public static String session_id = "";
	public static String Email, Pass;
	public static  OutputProduct oOputproduct ;
	// method
	public static int httpget = 1;
	public static int httppost = 2;
	public static int httpDelete = 3;

	public static final String apiConfirmPass = "http://abc.vn/altp/rsPass.jsp?";
	public static final String apiGetQuestion = "http://abc.vn/altp/getQuestion.jsp?";
	public static final String apigetAnswer = "http://abc.vn/altp/getAnswer.jsp?";
	public static final String apiGetScore = "http://abc.vn/altp/getScore.jsp?";
	public static final String apiGetCharge = "http://abc.vn/altp/getCharge.jsp?";
	public static final String apiGetTopScore = "http://abc.vn/altp/getTopScore.jsp?";
	public static final String apiGetUserInfo = "http://abc.vn/altp/getUserInfo.jsp?";
	public static final String apiGetCartTopUp = "http://abc.vn/altp/getCardTopup.jsp?";
	public static final String apiGetSendGift = "http://abc.vn/altp/getSendGift.jsp?";
	public static final String apiGetStop = "http://abc.vn/altp/getStop.jsp?";
	public static final String apiChangePass = "http://abc.vn/altp/changePass.jsp?";
	public static final String apiInbox = "http://abc.vn/altp/getMailList.jsp?";
	public static final String apiInboxDetail = "http://abc.vn/altp/getMailDetail.jsp?";
	public static final String apiSender = "http://abc.vn/altp/getFeedBack.jsp?";
}
