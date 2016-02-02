package com.smile.android.gsm.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.util.InetAddressUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.ByteArrayBuffer;

import com.lib.Debug;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.util.Base64;

@SuppressLint("DefaultLocale")
public class AndroidUtils {
	private static Pattern pattern;
	private static Matcher matcher;

	/**
	 * Tráº£ vá»� tÃªn á»©ng dá»¥ng chá»©a trong file AndroidManifest.xml
	 * 
	 * @param context
	 * @param defaultValues
	 * @return
	 */
	public static String getApplicationName(Context context,
			String defaultValues) {
		String str = null;
		Object localObject = context.getPackageManager();
		ApplicationInfo localApplicationInfo = null;
		try {
			if ((localApplicationInfo = ((PackageManager) localObject)
					.getApplicationInfo(context.getPackageName(), 0)) != null) {
				str = (localObject = ((PackageManager) localObject)
						.getApplicationLabel(localApplicationInfo)) != null ? (String) localObject
						: null;
			}
		} catch (PackageManager.NameNotFoundException localNameNotFoundException) {
			if ((localApplicationInfo = context.getApplicationInfo()) != null) {
				str = context.getString(localApplicationInfo.labelRes);
			}
		}
		if (str != null) {
			return str;
		}
		return defaultValues;
	}

	/**
	 * Tráº£ vá»� chuá»—i SHA1 tÆ°Æ¡ng á»©ng vá»›i keystore cho á»©ng dá»¥ng
	 * 
	 * @param context
	 * @return
	 */
	public static String getSHA1(Context context) {
		String str = null;
		try {
			for (Signature sig : context.getPackageManager().getPackageInfo(
					context.getPackageName(), 64).signatures) {
				MessageDigest localMessageDigest;
				(localMessageDigest = MessageDigest.getInstance("SHA1"))
						.update(sig.toByteArray());
				str = byte2HexFormatted(localMessageDigest.digest());
			}
		} catch (Exception e) {
			Debug.e(e.toString());
		}
		return str;
	}

	/**
	 * Tráº£ vá»� mÃ£ Hashkey tÆ°Æ¡ng á»©ng vá»›i keystore cho á»©ng dá»¥ng
	 * 
	 * @param context
	 * @return
	 */
	public static String getHashKey(Context context) {
		String str = null;
		try {
			for (Signature sig : context.getPackageManager().getPackageInfo(
					context.getPackageName(), 64).signatures) {
				MessageDigest localMessageDigest;
				(localMessageDigest = MessageDigest.getInstance("SHA1"))
						.update(sig.toByteArray());
				str = new String(Base64.encode(localMessageDigest.digest(), 0));
			}
		} catch (Exception e) {
			Debug.e(e.toString());
		}
		return str;
	}

	/**
	 * Tráº£ vá»� Ä‘á»‹a chá»‰ IP Address non-localhost tá»« giao diá»‡n máº¡ng
	 * NetworkInterface. YÃªu cáº§u cáº¥p quyá»�n sá»­ dá»¥ng
	 * Manifest.permission#ACCESS_NETWORK_STATE cho á»©ng dá»¥ng.
	 * 
	 * @param useIPv4
	 * @return
	 */
	public static String getIPAddress(boolean useIPv4) {
		try {
			List<NetworkInterface> interfaces = Collections
					.list(NetworkInterface.getNetworkInterfaces());
			for (NetworkInterface intf : interfaces) {
				List<InetAddress> addrs = Collections.list(intf
						.getInetAddresses());
				for (InetAddress addr : addrs) {
					if (!addr.isLoopbackAddress()) {
						String sAddr = addr.getHostAddress().toUpperCase();
						boolean isIPv4 = InetAddressUtils.isIPv4Address(sAddr);
						if (useIPv4) {
							if (isIPv4)
								return sAddr;
						} else {
							if (!isIPv4) {
								int delim = sAddr.indexOf('%');
								return delim < 0 ? sAddr : sAddr.substring(0,
										delim);
							}
						}
					}
				}
			}
		} catch (Exception ex) {
		} // for now eat exceptions
		return "";
	}

	/**
	 * Tráº£ vá»� Ä‘á»‹a chá»‰ MAC cá»§a thiáº¿t bá»‹ CHÃš Ã�: YÃªu cáº§u API LEVEL 9 trá»Ÿ lÃªn
	 * 
	 * @param interfaceName
	 *            - eth0, wlan0 or NULL=use first interface
	 * @return Tráº£ vá»� má»™t chuá»—i chá»©a Ä‘á»‹a chá»‰ mac náº¿u tÃ¬m tháº¥y. NgÆ°á»£c láº¡i tráº£ vá»�
	 *         chuá»—i trá»‘ng náº¿u khÃ´ng tÃ¬m tháº¥y
	 */
	public static String getMACAddress(String interfaceName) {
		try {
			List<NetworkInterface> interfaces = Collections
					.list(NetworkInterface.getNetworkInterfaces());
			for (NetworkInterface intf : interfaces) {
				if (interfaceName != null) {
					if (!intf.getName().equalsIgnoreCase(interfaceName))
						continue;
				}
				byte[] mac = intf.getHardwareAddress();
				if (mac == null)
					return "";
				StringBuilder buf = new StringBuilder();
				for (int idx = 0; idx < mac.length; idx++)
					buf.append(String.format("%02X:", mac[idx]));
				if (buf.length() > 0)
					buf.deleteCharAt(buf.length() - 1);
				return buf.toString();
			}
		} catch (Exception e) {
			Debug.e(e.toString());
		}
		return "";
	}

	/**
	 * Kiá»ƒm tra xem Ä‘iá»‡n thoáº¡i cÃ³ thá»ƒ truy cáº­p Internet theo Ä‘Æ°á»�ng nÃ o, vÃ­ nhÆ°
	 * thiáº¿t bá»‹ cÃ³ káº¿t ná»‘i internet thÃ´ng qua Ä‘Æ°á»�ng truyá»�n wifi hay mobile hay
	 * bluetooth...
	 * 
	 * @param context
	 * @return
	 */
	public static int getTypeConnectedToIntenet(Context context) {
		ConnectivityManager cm;
		NetworkInfo networkInfo[];
		if (((cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE)) != null)
				&& ((networkInfo = cm.getAllNetworkInfo()) != null)) {
			for (int i = 0; i < networkInfo.length; i++) {
				if ((networkInfo[i] != null)
						&& (networkInfo[i].isAvailable())
						&& (networkInfo[i].getState() == NetworkInfo.State.CONNECTED)) {
					return networkInfo[i].getType();
				}
			}
		}
		return -1;
	}

	/**
	 * HÃ m láº¥y Ä‘á»‹a chá»‰ MAC cá»§a wifi. YÃªu cáº§u cáº¥p quyá»�n sá»­ dá»¥ng
	 * Manifest.permission#ACCESS_NETWORK_STATE cho á»©ng dá»¥ng.
	 * 
	 * @param context
	 * @return
	 */
	public static String getWifiMacAddress(Context context) {
		WifiManager wifiManager = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		WifiInfo wInfo = wifiManager.getConnectionInfo();
		return wInfo.getMacAddress();
	}

	/**
	 * Kiá»ƒm tra Ä‘iá»‡n thoáº¡i Ä‘Ã£ káº¿t
	 * 
	 * ná»‘i máº¡ng (Internet) hay chÆ°a?
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isConnectedToInternet(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo wifiNetwork = cm
				.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		if (wifiNetwork != null && wifiNetwork.isConnected()) {
			return true;
		}

		NetworkInfo mobileNetwork = cm
				.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		if (mobileNetwork != null && mobileNetwork.isConnected()) {
			return true;
		}

		NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
		if (activeNetwork != null && activeNetwork.isConnected()) {
			return true;
		}
		return false;
	}

	/**
	 * Kiá»ƒm tra xem Ä‘iá»‡n thoáº¡i cÃ³ thá»ƒ truy cáº­p Internet qua sÃ³ng cá»§a nhÃ  máº¡ng
	 * (GPRS, 3G, HIPRI, UTMS, etc.) hay khÃ´ng?
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isConnectedToInternetMobile(Context context) {
		int value = 0;
		return ((value = getTypeConnectedToIntenet(context)) == 0)
				|| (value == 4) || (value == 5) || (value == 2) || (value == 3);
	}

	/**
	 * Kiá»ƒm tra xem á»©ng dá»¥ng Ä‘Ã£ cáº¥p quyá»�n truy cáº­p nÃ o Ä‘Ã³ hay chÆ°a? Náº¿u á»©ng dá»¥ng
	 * Ä‘Æ°á»£c cáº¥p quyá»�n thÃ¬ cÃ³ thá»ƒ láº¥y cÃ¡c thÃ´ng tin hoáº·c sá»­ dá»¥ng cÃ¡c thÃ´ng tin Ä‘Ã³
	 * trÃªn thiáº¿t bá»‹ cá»§a ngÆ°á»�i dÃ¹ng.
	 * 
	 * @param context
	 * @param permission
	 * @return
	 * @throws NullPointerException
	 */
	public static boolean checkPermission(Context context, String permission)
			throws NullPointerException {
		return context.getPackageManager().checkPermission(permission,
				context.getPackageName()) != 0;
	}

	/**
	 * Kiá»ƒm tra má»™t Activity cÃ³ trong file manifest hay chÆ°a?
	 * 
	 * @param context
	 * @param mClass
	 * @return
	 */
	public static boolean isExsistActivity(Context context, Class<?> mClass)
			throws NameNotFoundException {
		return context.getPackageManager().getActivityInfo(
				new ComponentName(context, mClass),
				PackageManager.GET_META_DATA) != null;
	}

	/**
	 * Kiá»ƒm tra má»™t Service cÃ³ trong file manifest hay chÆ°a?
	 * 
	 * @param context
	 * @param mClass
	 * @return
	 */
	public static boolean isExsistServices(Context context, Class<?> mClass)
			throws NameNotFoundException {
		return context.getPackageManager().getServiceInfo(
				new ComponentName(context, mClass),
				PackageManager.GET_META_DATA) != null;
	}

	/**
	 * Kiá»ƒm tra Ä‘ang cháº¡y mÃ¡y áº£o hay mÃ¡y tháº­t
	 * 
	 * @return false lÃ  mÃ¡y tháº­t | true lÃ  mÃ¡y áº£o
	 */
	public static boolean isRunningOnEmulator() {
		String model = Build.MODEL;
		Debug.d("model=" + model);
		String product = Build.PRODUCT;
		Debug.d("product=" + product);
		boolean isEmulator = false;
		if (product != null) {
			isEmulator = product.equals("sdk") || product.contains("_sdk")
					|| product.contains("sdk_");
		}
		Debug.d("isEmulator=" + isEmulator);
		return isEmulator;
	}

	private static String byte2HexFormatted(byte[] arr) {
		StringBuilder localStringBuilder = new StringBuilder(arr.length * 2);
		for (int i = 0; i < arr.length; i++) {
			String str;
			int j;
			if ((j = (str = Integer.toHexString(arr[i])).length()) == 1) {
				str = "0" + str;
			}
			if (j > 2) {
				str = str.substring(j - 2, j);
			}
			localStringBuilder.append(str.toUpperCase());
			if (i < arr.length - 1) {
				localStringBuilder.append(':');
			}
		}
		return localStringBuilder.toString();
	}

	private static String convertToHex(byte[] data) {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < data.length; i++) {
			int halfbyte = (data[i] >>> 4) & 0x0F;
			int two_halfs = 0;
			do {
				if ((0 <= halfbyte) && (halfbyte <= 9))
					buf.append((char) ('0' + halfbyte));
				else
					buf.append((char) ('a' + (halfbyte - 10)));
				halfbyte = data[i] & 0x0F;
			} while (two_halfs++ < 1);
		}
		return buf.toString();
	}

	/**
	 * MÃ£ hÃ³a MD5
	 * 
	 * @param text
	 *            chuá»—i String Chuyá»�n vÃ o
	 * @return tráº£ vá»� MD5
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public static String hash(String text) throws NoSuchAlgorithmException,
			UnsupportedEncodingException {
		MessageDigest md;
		md = MessageDigest.getInstance("MD5");
		byte[] md5hash = new byte[32];
		md.update(text.getBytes("iso-8859-1"), 0, text.length());
		md5hash = md.digest();
		return convertToHex(md5hash);
	}

	// Email Pattern
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";

	/**
	 * Validate Email with regular expression
	 * 
	 * @param email
	 * @return true for Valid Email and false for Invalid Email
	 */
	public static boolean validate(String email) {
		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(email);
		return matcher.matches();

	}

	/**
	 * Checks for Null String object
	 * 
	 * @param txt
	 * @return true for not null and false for null String object
	 */
	public static boolean isNotNull(String txt) {
		return txt != null && txt.trim().length() > 0 ? true : false;
	}
	public static HttpGet httpget;
	public static HttpPost httpPost;
	public static HttpDelete httpDelete;
	public static HttpResponse response ;
	// ham connecet webservice.
	
	public static String getjSonUrl(String link, int status) {
		StringBuffer objbuffer = new StringBuffer();

		HttpClient httpcliend = new DefaultHttpClient();
		if (status == 1) {
		 	 httpget = new HttpGet(link);
		}
		if (status == 2) {
			 httpPost = new HttpPost(link);
		}
		if (status == 3) {
			 httpDelete = new HttpDelete(link);
		}
		try {
			switch (status) {
			case 1:
				response = httpcliend.execute(httpget);
				break;
			case 2:
				response =httpcliend.execute(httpPost);
				break;
			case 3:
				response =httpcliend.execute(httpDelete);
				break;
			}
			
			StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			if (statusCode == 200) {
				HttpEntity entity = response.getEntity();
				InputStream coInputStream = entity.getContent();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(coInputStream));
				String line;
				while ((line = reader.readLine()) != null) {
					objbuffer.append(line);
				}
			} else {
				Debug.e("Fiale to jdklfajlkd");
			}
		} catch (Exception e) {
			Debug.e(e.toString());
		}
		return objbuffer.toString();
	}

}
