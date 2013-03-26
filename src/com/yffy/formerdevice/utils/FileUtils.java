package com.yffy.formerdevice.utils;

import java.io.File;

import com.yffy.formerdevice.data.Params;

public final class FileUtils {

	public static String getFilePath(String s) throws Exception {
		int length = s.length();
		StringBuffer sb = new StringBuffer(Params.Video.HOME_PATH);
		if (length == 1) {
			sb.append("00" + s + Params.Video.POSTFIX);
			return sb.toString();
		} else if (length == 2) {
			sb.append("0" + s + Params.Video.POSTFIX);
			return sb.toString();
		} else if (length == 3) {
			sb.append(s + Params.Video.POSTFIX);
			return sb.toString();
		} else throw new Exception();
	}

	public static boolean isNumberAvailible(String enteredNumber) {
		boolean flag = false;
		try {
			if (new File(getFilePath(enteredNumber)).exists()) {
				flag = true;
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public static boolean hasFiles(String dirPath) {
		boolean hasFiles = false;
		File dir = new File(dirPath);
		if (dir.isDirectory() && dir.listFiles() != null) {
			hasFiles = true;
		}
		return hasFiles;
	}

}
