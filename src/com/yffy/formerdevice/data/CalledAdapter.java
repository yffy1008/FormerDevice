package com.yffy.formerdevice.data;

import java.util.ArrayList;

import com.yffy.formerdevice.R;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CalledAdapter extends BaseAdapter {
	private ArrayList<Customer> hm;
	private Context context;
	
	public CalledAdapter( ArrayList<Customer> hm,Context context){
		this.context = context;
		this.hm = hm;
	}

	@Override
	public int getCount() {
		return this.hm.size();
	}

	@Override
	public Object getItem(int arg0) {
		return hm.get(arg0);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Customer c = (Customer) this.getItem(position);
		TextView customer_id,table____id;
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.called_list_row,null);
		}
		customer_id = (TextView)convertView.findViewById(R.id.cusotmer_id);
		table____id = (TextView)convertView.findViewById(R.id.table_id);
		customer_id.setGravity(Gravity.RIGHT);
		table____id.setGravity(Gravity.LEFT);
		
		customer_id.setText("请"+c.name+"客户到");
		table____id.setText(c.tableID +"餐桌就餐");
		
		return convertView;
	}

}
