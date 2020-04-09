package com.example.demo.domain.validation;

public class WebValidation {
	
	public static boolean numberCheck(String val) {
		try {
			Integer.parseInt(val);
			return true;
		} catch (NumberFormatException nfex) {
			System.out.println("---例外発生---");
			return false;
		}
	}
}
