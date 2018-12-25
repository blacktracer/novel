package com.virgin.novel.util;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegexUtil {
	public static void main(String[] args) {
		// *：0个或者多个字符 
		// +：1个或者多个字符
		// ?：0个或者1个
		//System.out.println("abcd".matches("..."));
		//System.out.println("a12dd?".replaceAll("\\d{3}","-"));
		//Pattern p = Pattern.compile("[a-z]{3,5}");
		//Matcher m = p.matcher("dfs-dd-weef-fddsd-a23-2");
		//System.out.println(m.matches());
		//m.reset();
		/*System.out.println(m.find());
		System.out.println(m.start()+"-"+m.end());
		System.out.println(m.find());
		System.out.println(m.start()+"-"+m.end());
		System.out.println(m.find());
		System.out.println(m.start()+"-"+m.end());
		System.out.println(m.find());
		System.out.println(m.start()+"-"+m.end());*/
		/*Pattern p = Pattern.compile("java",Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher("java jAVA Java hate love JAVA tail");
		while(m.find()){
			System.out.println(m.group());
		}
		System.out.println(m.replaceAll("JAVA"));
		m.reset();
		StringBuffer sb = new StringBuffer();
		int i = 0;
		while(m.find()){
			i++;
			if(i%2==0){
				m.appendReplacement(sb,"java");
			}else{
				m.appendReplacement(sb,"JAVA");
			}
		}
		m.appendTail(sb);
		System.out.println(sb.toString());*/
		/*
		Pattern p = Pattern.compile("(\\d{3,5})([a-z]{2})");
		Matcher m = p.matcher("123abc-45679cdf-1012er-jkd");
		while(m.find()){
			System.out.println(m.group());
			System.out.println(m.group(0));
			System.out.println(m.group(1));
			System.out.println(m.group(2));
		}
		*/
		try {
			InputStream inputStream = new FileInputStream("D:/job/广东中旅电子合同/补充协议/aaa.html");
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] b = new byte[1024];
			int len;
			while((len = inputStream.read(b))!=-1){
				baos.write(b, 0, len);
			}
			String html = new String(baos.toByteArray(),"GBK");
			inputStream.close();
			baos.close();
			String bodyHtml = "";
			Pattern p = Pattern.compile("<body[\\s\\S]*?</body>",Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(html);
			if(m.find()){
				bodyHtml = m.group();
			}
			bodyHtml = bodyHtml.replaceAll("<body","<div").replaceAll("</body>","</div>");
			//获取class CSS样式
			Map<String,String> classCssMap = new HashMap<String,String>();
			String styleHtml = "";
			p = Pattern.compile("<style[\\s\\S]*?</style>",Pattern.CASE_INSENSITIVE);
			m = p.matcher(html);
			if(m.find()){
				styleHtml = m.group();
			}
			p = Pattern.compile("\\.(.*?)\\{(.*?)\\}",Pattern.CASE_INSENSITIVE);
			m = p.matcher(styleHtml);
			while(m.find()){
				classCssMap.put(m.group(1),m.group(2));
			}
			StringBuffer body = new StringBuffer();
			String[] classCssNameArray = null;
			StringBuilder innerStyle = null;
			p = Pattern.compile("class=\"(.*?)\"",Pattern.CASE_INSENSITIVE);
			m = p.matcher(bodyHtml);
			while(m.find()){
				classCssNameArray = m.group(1).split(" ",0);
				innerStyle = new StringBuilder("style=\"");
				for(String classCssName : classCssNameArray){
					innerStyle.append(classCssMap.get(classCssName));
				}
				innerStyle.append("\"");
				m.appendReplacement(body,innerStyle.toString());
			}
			m.appendTail(body);
			bodyHtml = body.toString();
			OutputStream outputStream = new FileOutputStream("D:/job/广东中旅电子合同/补充协议/bbb.html");
			outputStream.write(bodyHtml.getBytes("GBK"));
			outputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
