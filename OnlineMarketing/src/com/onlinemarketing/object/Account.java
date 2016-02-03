package com.onlinemarketing.object;

public class Account {

	public static final String ID = "ID";
	public static final String NAME = "name";

	private String usearname = null;
	private String email = null;
	private boolean bActive;
	private boolean bTelesale;
	private boolean bCSKH;
	private boolean bReplyInbox;

	public Account() {
	}

	public Account(String usearname, String email, boolean bActive, boolean bTelesale, boolean bCSKH,
			boolean bReplyInbox) {
		super();
		this.usearname = usearname;
		this.email = email;
		this.bActive = bActive;
		this.bTelesale = bTelesale;
		this.bCSKH = bCSKH;
		this.bReplyInbox = bReplyInbox;
	}

	public String getUsearname() {
		return usearname;
	}

	public String getEmail() {
		return email;
	}

	public boolean isbActive() {
		return bActive;
	}

	public boolean isbTelesale() {
		return bTelesale;
	}

	public boolean isbCSKH() {
		return bCSKH;
	}

	public boolean isbReplyInbox() {
		return bReplyInbox;
	}

}
