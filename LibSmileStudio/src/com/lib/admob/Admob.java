package com.lib.admob;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.ViewGroup;

@SuppressLint("InflateParams")
public class Admob {

	private final String deviceID = "987710727ACFAB0C0B00E82DEFAD0085";
	protected AdRequest request = null;
	protected InterstitialAd interstitialAd = null;
	private AdView adView = null;
	/**
	 * ajshdaksd
	 * @param isTest - true: đang test; false: đã chạy thật
	 */
	public Admob(boolean isTest) {
		AdRequest.Builder Builder = new AdRequest.Builder();
		if (isTest) {
			Builder.addTestDevice(AdRequest.DEVICE_ID_EMULATOR);
			Builder.addTestDevice(deviceID);
		}
		request = Builder.build();
	}

	public void BannerAdmob(Context context, ViewGroup view, String adUnitId, AdListener adListener) {
		this.adView = new AdView(context);
		this.adView.setAdUnitId(adUnitId);
		this.adView.setAdSize(AdSize.SMART_BANNER);
		this.adView.loadAd(request);
		this.adView.setAdListener(adListener);
		view.addView(adView);
	}

	public void InterstitialAdmob(Context context, String adUnitId, AdListener adListener) {
		interstitialAd = new InterstitialAd(context);
		interstitialAd.setAdUnitId(adUnitId);
		interstitialAd.setAdListener(adListener);
		interstitialAd.loadAd(request);
	}

	public void InterstitialShow() {
		if (interstitialAd.isLoaded()) {
			interstitialAd.show();
		}
	}

}
