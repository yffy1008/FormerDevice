package com.yffy.formerdevice.utils;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;


import android.util.Log;

public class NetUtils {
	
	public static String doPost(String url, ConcurrentHashMap<String, String> hm){
		String response = null;
		HttpPost post=new HttpPost(url);
		HttpClient client=new DefaultHttpClient();
	   	try {
	   		post.setEntity(new UrlEncodedFormEntity(TextUtils.getValuePairList(hm),"UTF-8"));
		    HttpResponse res=client.execute(post);
		    if(res.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
		    	response = EntityUtils.toString(res.getEntity());		    	
		    }
	   	} catch (Exception e) {
			e.printStackTrace();
		}
	   	return response;
	}
	
	public static String doGet(String url,ConcurrentHashMap<String, String> hm){
		String response = null;
		HttpClient client=new DefaultHttpClient();
		HttpGet get=new HttpGet(TextUtils.urlWithQueryString(url, TextUtils.getValuePairList(hm)));
	   	try {
		    HttpResponse res=client.execute(get);
		    if(res.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
		        response = EntityUtils.toString(res.getEntity());	
		    }
	   	} catch (Exception e) {
			e.printStackTrace();
		}
	   	return response;
	}
	
	
	/**
	 * 
	 * @param instream    服务器的流
	 * @return                    string 字符串
	 * @throws IOException
	 */
	public static String stream2String (final InputStream instream) throws IOException {
		final StringBuilder sb = new StringBuilder();
		try {
			final BufferedReader reader = new BufferedReader(new InputStreamReader(instream, "UTF-8"));
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			
		} finally {
			closeStream(instream);
		}
		return sb.toString();
	}

	public static void closeStream (Closeable stream) {
		if (stream != null) {
			try {
				stream.close();
			} catch (IOException e) {
				Log.e("IOUtils", "Could not close stream", e);
			}
		}
	}
	
//	public static boolean isNetAvailable(){
//		boolean flag = false;
//		ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//		if (manager.getActiveNetworkInfo() != null) {
//			if (manager.getActiveNetworkInfo().isAvailable()){ 
//				flag = true;
//			}
//		}
//		return flag;
//	}
	
}
