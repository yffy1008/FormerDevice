package com.yffy.formerdevice.app;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import com.yffy.formerdevice.R;
import com.yffy.formerdevice.data.Customer;
import com.yffy.formerdevice.data.MyAdapter;
import com.yffy.formerdevice.data.Params;
import com.yffy.formerdevice.utils.TextUtils;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class QueensList extends Activity {
	private Customer deleteCustomer;
	
	private ListView[] lvs;
	private ArrayList<MyAdapter> mas;
	private ArrayList<ArrayList<Customer>> als;
	
	private ListBroadCastReceiver lReceiver;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.list_views);
		
		
		lvs = new ListView[] {(ListView) findViewById(R.id.listview_a),(ListView) findViewById(R.id.listview_b),(ListView) findViewById(R.id.listview_c),(ListView) findViewById(R.id.listview_d)};
		als = new ArrayList<ArrayList<Customer>>();
		mas = new ArrayList<MyAdapter>();
		
		for (int i = 0; i < lvs.length; i++) {
			als.add(new ArrayList<Customer>());
			mas.add(new MyAdapter(QueensList.this, als.get(i)));
			lvs[i].setAdapter(mas.get(i));
			lvs[i].setOnItemClickListener(oicl);
		}
		startService(new Intent(QueensList.this, GetListService.class).putExtra("Commond", 11));	
		lReceiver = new ListBroadCastReceiver();
	}

	@Override
	protected void onResume() {
		super.onResume();
		registerReceiver(lReceiver, getFilter());
	}
	
	private IntentFilter getFilter(){
		IntentFilter iFilter = new IntentFilter();
		iFilter.addAction(Params.Code.GET_QUEEN_LIST);
		return iFilter;
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		unregisterReceiver(lReceiver);
	}
	
	class ListBroadCastReceiver extends BroadcastReceiver{
		
		@Override
		public void onReceive(Context context, Intent intent) {
			if (intent.getAction().equals(Params.Code.GET_QUEEN_LIST)) {
				String list = intent.getStringExtra(Params.Code.THE_LIST);
				new GetListDataTask().execute(list);
			}
		}
	}
	
	class GetListDataTask extends AsyncTask<String, Void, ArrayList<ArrayList<Customer>>>{

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}
		
		@Override
		protected ArrayList<ArrayList<Customer>> doInBackground(String... params) {
			JSONArray ja = getJsonArray(params[0]);
			if (ja == null) return null;
			ArrayList<ArrayList<Customer>> al = new ArrayList<ArrayList<Customer>>();
			for (int i = 0; i < ja.length(); i++) {
				ArrayList<Customer> a = new ArrayList<Customer>();
				JSONArray ja1 = ja.optJSONArray(i);
				for (int j = 0; j < ja1.length(); j++){ 
					a.add(new Customer(ja1.optString(j)));
				}
				al.add(a);
			}
			return al;
		}
		
		@Override
		protected void onPostExecute(ArrayList<ArrayList<Customer>> result) {
			super.onPostExecute(result);
			for (int i = 0; i < result.size(); i++) {
				als.get(i).clear();
				als.get(i).addAll(result.get(i));
				mas.get(i).notifyDataSetChanged();
			}
		}
	}
	
	private JSONArray getJsonArray(String jsonString){
		try {
			return new JSONArray(jsonString);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private OnItemClickListener oicl = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
			switch (arg0.getId()) {
			case R.id.listview_a:
				deleteCustomer = (Customer) mas.get(0).getItem(arg2);
				break;
			case R.id.listview_b:
				deleteCustomer = (Customer) mas.get(1).getItem(arg2);
				break;
			case R.id.listview_c:
				deleteCustomer = (Customer) mas.get(2).getItem(arg2);
				break;
			case R.id.listview_d:
				deleteCustomer = (Customer) mas.get(3).getItem(arg2);
				break;
			}
			getDeleteDialog().show();
		}
	};
	
	private Dialog getDeleteDialog(){
		final String[] delete = {"                        查看客户详情"};
		Builder builder = new Builder(QueensList.this);
		builder.setItems(delete, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				TextUtils.showOnScreen(QueensList.this,"        " + deleteCustomer.printInformation());
			}
		});
		return builder.create();
	}
	
}
