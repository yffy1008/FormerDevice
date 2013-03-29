package com.yffy.formerdevice.data;

public final class Params {
	public static final class Video {
		public static final String FILE_NAME = "Number";
		public static final String HOME_PATH = "data/ads/";
		public static final String POSTFIX = ".avi";
		public static final String DISPLAY = "TV";
		public static final String PDA = "PDA";
	}

	public static final class Url {
		public static String IP_ADDRESS = "192.168.1.111";
		public static final String HTTP = "http://";
		public static final String MIDDLE = ":8080/TestServlet/servlet/";
		public static final String Add_SERVLET_URL = HTTP + IP_ADDRESS + MIDDLE + "AddCustomerServlet";
		public static final String CONTORL_SERVLET_URL = HTTP + IP_ADDRESS + MIDDLE  + "ControlServlet";
		public static final String GET_LIST_SERVLET_URL = HTTP + IP_ADDRESS + MIDDLE + "GetListServlet";
	}

	public static final class Code {
		public static final String REQUEST = "RequestCode";
		public static final String RESPONSE = "ResponseCode";
		public static final String MESSAGE = "Message";
		
		public static final String CHANGE_VIDEO = "ChangeVideo";
		public static final String KEEP_CONNECTION = "KeepConnection";
		
		public static final String GET_CALLED_LIST = "GetCalledList"; 
		public static final String ADD_TO_QUEEN = "AddToQueen";
		public static final String GET_EMPTY_TABLE = "EmptyTable";
		public static final String TABLE_IS_SEATED = "OnTheTable";
		public static final String DELETE_SUCCEED = "DeleteSuccess";
		public static final String SEARCH_SUCCEED = "AnEmptyTable";
		public static final String MISSED_CUSTOMER = "MissedCustomer";
		public static final String CALL_CUSTOMER = "CallCustomer";
		public static final String GET_QUEEN_LIST = "GetQueenList";
		public static final String ADD_SUCCEED = "AddSuccess";
		public static final String CHANGE_PAGER = "Change_pager";
		public static final String PAGER_NUMBER = "Pager_number";
		public static final String SUCCESS = "Success";
		public static final String FAILED = "Failed";
		public static final String THE_LIST = "List";
	}

	public final class KeyValue {
		public static final int NUMBER_0 = 144;
		public static final int NUMBER_1 = 145;
		public static final int NUMBER_2 = 146;
		public static final int NUMBER_3 = 147;
		public static final int NUMBER_4 = 148;
		public static final int NUMBER_5 = 149;
		public static final int NUMBER_6 = 150;
		public static final int NUMBER_7 = 151;
		public static final int NUMBER_8 = 152;
		public static final int NUMBER_9 = 153;
		public static final int NUMBER_DEL = 158;
		public static final int NUMBER_PLUS = 157;
		public static final int NUMBER_BACK = 67;
		public static final int NUMBER_ENTER = 66;
		public static final int NUMBER_REDUCE = 156;
	}

	public static final class Customer {
		public static final String WAIT_TIME = "Wait_time";
		public static final String CUSTOMER = "Customer";
		public static final String NUMBER = "Number";
		public static final String GROUP_INDEX = "Index";
		public static final String CAN_COMBINE = "CanCombine";
		public static final String CUSTOMER_ID = "CustomerID";
	}

	public static final class Table {
		public static final String TABLE = "Table";
		public static final String TABLE_ID = "TableID";
		public static final String TABLE_MAX_NUMBER = "MaxNumber";
	}
	public static final class Sqlist{
		public static final String DATABASE_NAME="LinkedDB";
		public static final int DATABASE_VERSION=1;
		public static final String TABLE_NAME="linktohost";
		public static final String PHONE="phonenumber";
		public static final String CUSTOMER_PHONE="customer_phone";
		public static  final String CUSTOMER_ID="customerId";
		public static final String CUSTOMER_COUNT="customer_count";
		
	}
	
}
