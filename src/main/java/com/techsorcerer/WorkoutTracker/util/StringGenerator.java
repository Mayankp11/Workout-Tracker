package com.techsorcerer.WorkoutTracker.util;

import java.util.Random;

public class StringGenerator {
	
	
	public static String userIdGenerator(String name) {
		StringBuilder sb = new StringBuilder();
		Random  random = new Random();
		String alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String digits = "0123456789";
		
		if(name.length() >= 3) {
			String upper = name.substring(0, 3).toUpperCase();
			sb.append(upper);
		} else {
			sb.append(name.toUpperCase());
			for (int i = 0; i < 3 - name.length(); i++) {
				sb.append(alphabets.charAt(random.nextInt(alphabets.length())));
			}
		}
		
		for(int i = 0; i < 4; i++) {
			sb.append(digits.charAt(random.nextInt(digits.length())));
		}
		
		return sb.toString();
	}

}
