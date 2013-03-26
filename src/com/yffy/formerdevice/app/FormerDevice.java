package com.yffy.formerdevice.app;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import org.json.JSONArray;


import com.yffy.formerdevice.R;
import com.yffy.formerdevice.data.CalledAdapter;
import com.yffy.formerdevice.data.Customer;
import com.yffy.formerdevice.data.Params;
import com.yffy.formerdevice.utils.HttpTask;
import com.yffy.formerdevice.utils.TextUtils;
import com.yffy.formerdevice.utils.HttpTask.TaskListener;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class FormerDevice extends Activity {
	private int positon_;
	private ConcurrentHashMap<String, String> cc;
	private ProgressBar progressBar;
	private ListView called_list;
	private ArrayList<Customer> called_alist;
	
	private TextView info_tv;
	private EditText customer_count;
	private CalledAdapter mCalledAdapter;
	private CalledReceiver mCalledReceiver;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		cc = new ConcurrentHashMap<String, String>();
		progressBar = (ProgressBar) findViewById(R.id.progress);
		progressBar.setVisibility(View.INVISIBLE);
		
		info_tv = (TextView)findViewById(R.id.textview_paidui_info);
		customer_count = (EditText)findViewById(R.id.edittext_paidui_count);
		
		called_list = (ListView)findViewById(R.id.called_list);
		called_alist = new ArrayList<Customer>();
		mCalledAdapter = new CalledAdapter(called_alist, FormerDevice.this);
		called_list.setAdapter(mCalledAdapter);
		called_list.setOnItemClickListener(oicl);
		
		mCalledReceiver = new CalledReceiver();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.settings:
			startActivity(new Intent(FormerDevice.this,PreferenceActivity.class));
			break;
		case R.id.show_list:
			startActivity(new Intent(FormerDevice.this,QueensList.class));
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		registerReceiver(mCalledReceiver,getIntentFilter());
		startService(new Intent(FormerDevice.this,GetListService.class).putExtra("Commond",15));
	}
	
	private IntentFilter getIntentFilter(){
		IntentFilter mFilter = new IntentFilter();
		mFilter.addAction(Params.Code.CALL_CUSTOMER);
		return mFilter;
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		unregisterReceiver(mCalledReceiver);
	}
	
	public void onClick(View view) throws NumberFormatException{
		TextUtils.hideInputMethod(FormerDevice.this);
		String number = customer_count.getText().toString().trim();
		switch (view.getId()) {
		case R.id.button_paidui_send:
			if(number.equals("")) break;
			cc.clear();
			cc.put(Params.Customer.NUMBER,number);
			cc.put(Params.Code.REQUEST,Params.Code.ADD_TO_QUEEN);
			new HttpTask(FormerDevice.this, progressBar,Params.Url.Add_SERVLET_URL, cc, mTaskListener).execute();				
//			new SocketTarsk().execute(number);
			break;
		}
	}
	
	private TaskListener mTaskListener = new TaskListener() {
		@Override
		public void successed(String code, String result) {
			customer_count.setText("");
			
			if (code.equals(Params.Code.ADD_SUCCEED)) {
				String id = TextUtils.json2String(result,Params.Customer.CUSTOMER,"");
				Customer c = new Customer(id);
				info_tv.setText(c.toString());
			
			} else if (code.equals(Params.Code.DELETE_SUCCEED)) {
				TextUtils.showOnScreen(FormerDevice.this,"已经成功删除");
		
			} else if (code.equals(Params.Code.SEARCH_SUCCEED)) {
				String meg = TextUtils.json2String(result,Params.Code.MESSAGE,"");
				info_tv.setText(meg);
			}
		}
		
		@Override
		public void failed() {
			customer_count.setText("");
			TextUtils.showOnScreen(FormerDevice.this,"数据有误");
		}
	};

	@Override
	protected void onDestroy() {
		super.onDestroy();
		try {
			stopService(new Intent(FormerDevice.this,GetListService.class));			
		} catch (Exception e) {
			TextUtils.showOnLog(e.toString());
		}
	}
	
	private class CalledReceiver extends BroadcastReceiver{
		
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			String content = intent.getStringExtra(Params.Customer.CUSTOMER);
			if (action.equals(Params.Code.CALL_CUSTOMER)) {
				refreshCalledList(content);
			}
		}
	}
	
	private void refreshCalledList(String jsonString){
		try {
			JSONArray ja = new JSONArray(jsonString);
			called_alist.clear();
			for (int i = 0; i < ja.length(); i++)called_alist.add(new Customer(ja.getString(i)));
			mCalledAdapter.notifyDataSetChanged();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private OnItemClickListener oicl = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
			positon_ = arg2;
			getItemClickDialog().show();
		}
	};
	
	private Dialog getItemClickDialog(){
		final String[] choices = {"                         客户已安排","                         客户已离开"};
		Builder builder = new Builder(FormerDevice.this);
		builder.setItems(choices, new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				switch (which) {
				case 0:
					Customer c = (Customer) mCalledAdapter.getItem(positon_);
					cc.clear();
					cc.put(Params.Code.REQUEST,Params.Code.TABLE_IS_SEATED);
					cc.put(Params.Customer.CUSTOMER,c.customer2Json().toString());
					new HttpTask(FormerDevice.this,progressBar,Params.Url.CONTORL_SERVLET_URL, cc, mTaskListener).execute();
					break;
				case 1:
					Customer c_ = (Customer) mCalledAdapter.getItem(positon_);
					cc.clear();
					cc.put(Params.Code.REQUEST,Params.Code.MISSED_CUSTOMER);
					cc.put(Params.Customer.CUSTOMER,c_.customer2Json().toString());
					new HttpTask(FormerDevice.this,progressBar,Params.Url.CONTORL_SERVLET_URL, cc, mTaskListener).execute();
					break;
				}
			}
		});
		return builder.create();
	}
	
//	private void sendBySorcket(String msg){
//		try {
//			Socket s = new Socket("192.168.1.112", 5678);
//			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
//			out.write(msg);
//			out.flush();
//			out.close();
//			s.close();
//		} catch (UnknownHostException e) {
//			e.printStackTrace();
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		} 
//	}
//	
//	private class SocketTarsk extends AsyncTask<String, Void, Void>{
//
//		@Override
//		protected Void doInBackground(String... params) {
//			sendBySorcket(params[0]);
//			return null;
//		}
//		
//	}
}
