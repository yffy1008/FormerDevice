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

public class MyAdapter extends BaseAdapter {
	private ArrayList<Customer> al;
	private Context c;

	public MyAdapter(Context c ,ArrayList<Customer> al){
		this.c = c;
		this.al = al;
	}
	
	@Override
	public int getCount() {
		return al.size();
	}

	@Override
	public Object getItem(int arg0) {
		return al.get(arg0);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Customer customer = al.get(position);
		TextView name,count;
		if (convertView == null) {
			convertView = (View) LayoutInflater.from(c).inflate(R.layout.list_row, null);
		}
		name = (TextView)convertView.findViewById(R.id.row_text_name);
		count = (TextView)convertView.findViewById(R.id.row_text_count);
		name.setGravity(Gravity.CENTER);
		count.setGravity(Gravity.CENTER);
//		if (position == 0) {
//			name.setTextColor(android.graphics.Color.RED);
//			count.setTextColor(android.graphics.Color.RED);
//		}
		name.setText(customer.name);
		count.setText(customer.number + "位");
		
		return convertView;
	}

}