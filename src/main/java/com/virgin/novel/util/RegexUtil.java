package com.virgin.novel.util;

import java.util.regex.Pattern;

public class RegexUtil {
	public static void main(String[] args) {
		Pattern p = Pattern.compile("^1\\d{10}$");
		System.out.println(p.matcher("15255599326").matches());
	}
}
