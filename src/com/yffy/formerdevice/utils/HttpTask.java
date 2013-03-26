package com.yffy.formerdevice.utils;

import java.util.concurrent.ConcurrentHashMap;

import com.yffy.formerdevice.data.Params;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

public class HttpTask extends AsyncTask<Void, Void, String> {
	private String url;
	private Context context;
	private TaskListener mListener;
	private ProgressBar progressBar;
	private ProgressDialog progressDialog;
	private ConcurrentHashMap<String, String> cc;

	public interface TaskListener{
		void successed(String code,String result);
		void failed();
	}

	public HttpTask(Context context, ProgressBar progressBar ,String url,ConcurrentHashMap<String, String> cc,TaskListener mTaskListener){
		this.cc = cc;
		this.url = url;
		this.context = context;
		this.mListener = mTaskListener;
		this.progressBar = progressBar;
	}
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		if (this.progressBar == null) {
			progressDialog = ProgressDialog.show(context,"","Sending Request...");
		} else {
			progressBar.setVisibility(View.VISIBLE);
		}
	}

	@Override
	protected String doInBackground(Void... params) {
		return NetUtils.doPost(url, cc);
	}

	@Override
	protected void onPostExecute(String responseString) {
		super.onPostExecute(responseString);
		if (this.progressBar == null) {
			progressDialog.dismiss();
		} else {
			progressBar.setVisibility(View.INVISIBLE);
		}
		String code = TextUtils.json2String(responseString,Params.Code.RESPONSE,null);
		if (code == null) {
			TextUtils.showOnLog("网络问题");
		} else if(code.equals(Params.Code.FAILED)){
			mListener.failed();
		} else if (!code.equals(Params.Code.FAILED)) {
			mListener.successed(code,responseString);
		}
	}
	
}

