package com.virgin.novel.util;

import java.util.ArrayList;
import java.util.List;

public class GenericUtil {
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("aaa");
		for(int i=0; i<list.size();i++){
			System.out.println(i+":"+(String)list.get(i));
		}
		
	}
}
