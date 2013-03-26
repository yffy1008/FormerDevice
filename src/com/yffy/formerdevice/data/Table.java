package com.yffy.formerdevice.data;

import org.json.JSONException;
import org.json.JSONObject;



public class Table {
	public String tableID;
	public int maxNumber;

	public Table(){
		
	}
	
	public Table(String tableID,int maxNumber){
		this.tableID = tableID;
		this.maxNumber = maxNumber;
	}
	
	@Override
	public String toString() {
		return tableID;
	}
	
	public JSONObject table2Json() throws JSONException{
		JSONObject jo = new JSONObject();
		jo.put(Params.Table.TABLE_ID,this.tableID);
		jo.put(Params.Table.TABLE_MAX_NUMBER,this.maxNumber);
		return jo;
	}
	
}
