package com.onlinemarketing.object;

public class AccountVO {
	public static int id;
	public static String email = "";
	public static String address = "";
	public static String phone = "";
	public static String fullname = "";
	public static String session = "";
	public static String getSession() {
		return session;
	}

	public static void setSession(String session) {
		AccountVO.session = session;
	}

	public static int getId() {
		return id;
	}

	public static void setId(int id) {
		AccountVO.id = id;
	}

	public static String getEmail() {
		return email;
	}

	public static void setEmail(String email) {
		AccountVO.email = email;
	}

	public static String getAddress() {
		return address;
	}

	public static void setAddress(String address) {
		AccountVO.address = address;
	}

	public static String getPhone() {
		return phone;
	}

	public static void setPhone(String phone) {
		AccountVO.phone = phone;
	}

	public static String getFullname() {
		return fullname;
	}

	public static void setFullname(String fullname) {
		AccountVO.fullname = fullname;
	}
	

}
