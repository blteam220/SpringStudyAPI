package com.example.demo.domain.validation;

public class WebValidation {
	
	public static boolean numberCheck(String val) {
		// Integer型に変換できた場合 trueを返す
		try {
			Integer.parseInt(val);
			return true;
		}
		//　Integer型に変換できない場合 falseを返す
		catch (NumberFormatException nfex) {
			return false;
		}
	}
}
