package com.lib.view;

import android.app.Dialog;
import android.content.Context;

public abstract class BaseDialog extends Dialog {
	
	protected Context context;
	
	public BaseDialog(Context context) {
		super(context);
		this.context = context;
	}
	
	abstract public void init();

}
