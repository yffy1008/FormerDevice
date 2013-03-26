package com.yffy.formerdevice.data;


import org.json.JSONException;
import org.json.JSONObject;

public class Customer {
	/**
	 * 
	 */
	public int time;
	public int number;
	public String name;
	public String tableID;
	public String table_number;
	
	public Customer() {

	}

	public Customer(int number) {
		this.number = number;
	}

	public Customer(String jsonString) {
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			this.time = jsonObject.optInt(Params.Customer.WAIT_TIME, 0);
			this.number = jsonObject.optInt(Params.Customer.NUMBER, 0);
			this.name = jsonObject.optString(Params.Customer.CUSTOMER_ID, "");
			this.tableID = jsonObject.optString(Params.Table.TABLE_ID,"");
			this.table_number = jsonObject.optString(Params.Table.TABLE_MAX_NUMBER,"");
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public JSONObject customer2Json(){
		JSONObject jo = new JSONObject();
		try {
			jo.put(Params.Table.TABLE_ID,this.tableID);
			jo.put(Params.Customer.NUMBER,this.number);
			jo.put(Params.Customer.WAIT_TIME,this.time);
			jo.put(Params.Customer.CUSTOMER_ID, this.name);
			jo.put(Params.Table.TABLE_MAX_NUMBER,this.table_number);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jo;
	}

	public String printInformation(){
		return "编号:" + this.name + ";人数" + this.number +";预计等待时间" + this.time;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		int location = this.time / 2;
		sb.append("欢迎光临\n");
		sb.append("您的编号是:"+this.name);
		if (location != 0) sb.append("\n在您前面的共有" + location + "位顾客\n" + "预计等待时间为" + this.time +"分钟");
		sb.append("\n请您耐心等候");
		return sb.toString();
	}
	
}
