package com.yffy.formerdevice.app;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

import com.yffy.formerdevice.data.Params;
import com.yffy.formerdevice.utils.NetUtils;
import com.yffy.formerdevice.utils.TextUtils;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class GetListService extends Service {
	private final int GET_QUEENS_LIST = 11;
	private final int GET_CALLED_LISET = 15;
	private int action = 0;
	private Timer timer;
	

	@Override
	public void onCreate() {
		super.onCreate();
		timer = new Timer();
		timer.schedule(tt, 0, 1000);
	}
	
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		action = intent.getIntExtra("Commond",0);
		return Service.START_STICKY;
	}
	
	private ConcurrentHashMap<String, String> cc = new ConcurrentHashMap<String, String>();
	
	private TimerTask tt = new TimerTask() {
		@Override
		public void run() {
			if (action == GET_QUEENS_LIST) {
				cc.clear();
				cc.put(Params.Code.REQUEST,Params.Code.GET_QUEEN_LIST);
				handleResult(NetUtils.doPost(Params.Url.GET_LIST_SERVLET_URL, cc));
			} else if (action == GET_CALLED_LISET) {
				cc.clear();
				cc.put(Params.Code.REQUEST,Params.Code.GET_CALLED_LIST);
				handleResult(NetUtils.doPost(Params.Url.GET_LIST_SERVLET_URL,cc));
			}
		}
	};
	
	private void handleResult(String response){
		String responseCode = TextUtils.json2String(response,Params.Code.RESPONSE,null);
	
		if (responseCode == null) return;
		
		if (responseCode.equals(Params.Code.GET_QUEEN_LIST)) {
			String list = TextUtils.json2String(response,Params.Code.THE_LIST,"");
			sendBroadcast(new Intent(Params.Code.GET_QUEEN_LIST).putExtra(Params.Code.THE_LIST,list));
	
		} else if (responseCode.equals(Params.Code.GET_CALLED_LIST)) {
			String customer = TextUtils.json2String(response,Params.Customer.CUSTOMER,"");
			sendBroadcast(new Intent(Params.Code.CALL_CUSTOMER).putExtra(Params.Customer.CUSTOMER,customer));
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (timer != null) {
			timer.cancel();
			timer = null;
		}
		if (tt!= null) {
			tt.cancel();
			tt = null;
		}
	}
	
}
