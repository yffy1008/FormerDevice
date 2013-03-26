package com.yffy.formerdevice.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

public final class TextUtils{
	public static final String LOG_TAG = "xxccll";
	public static final String ENCODING = "UTF-8";
	
	/**
	 * 取结果
	 * @param jsonString          传过来的json string值
	 * @param key                    需要取值的key
	 * @return                           相应key的value 没有该key返回 null
	 * @throws JSONException
	 */
	public static String json2String (String jsonString,String key,String defaultValue) {
		String result = defaultValue;
		JSONObject jsonObject = null;
		try {
			jsonObject = new JSONObject(jsonString);
			result = jsonObject.optString(key,defaultValue);
		} catch (JSONException e) {
			
		} catch (Exception e) {
		
		}
		return result;
	}

	/**
	 * @param url            接口url
	 * @param params    参数封装好的 ArrayList<BasicNameValuePair>
	 * @return                 拼接完成的 geturl
	 */
	public static String urlWithQueryString (String url,List<BasicNameValuePair> params) {
		if (params != null) {
			String paramString = URLEncodedUtils.format(params, ENCODING);
			url += "?" + paramString;
		}
		return url;
	}
	
	/**
	 * ConcurrentHashMap转换成 ArrayList<BasicNameValuePair>；
	 * @param hm          参数键值对
	 * @return
	 */
	public static ArrayList<BasicNameValuePair> getValuePairList (ConcurrentHashMap<String, String> hm) {
		ArrayList<BasicNameValuePair> al = new ArrayList<BasicNameValuePair>();
		for (ConcurrentHashMap.Entry<String, String> entry : hm.entrySet()) {
			al.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		return al;
	}
	
	public static String getLocalTime(String pattern){
		long time = System.currentTimeMillis();
		SimpleDateFormat sdf;
		try {
			sdf = new SimpleDateFormat(pattern);
		} catch (Exception e) {
			sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
		}
		return sdf.format(time);
	}
	
	public static void showOnScreen(Context context, String message) {
		Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
	}
	
	public static void showOnLog(String message){
		Log.d(LOG_TAG,message);
	}

	public static void hideInputMethod(Context context){
		((InputMethodManager) ((Activity) context).getSystemService(Context.INPUT_METHOD_SERVICE)).
		hideSoftInputFromWindow(((Activity) context).getCurrentFocus().getWindowToken(),
		InputMethodManager.HIDE_NOT_ALWAYS);
	}
	
}
