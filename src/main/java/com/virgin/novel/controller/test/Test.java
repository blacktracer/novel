package com.virgin.novel.controller.test;

import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.virgin.novel.controller.author.AuthorAction;

public class Test {
	
	public static void main(String[] args) throws MalformedURLException {
		ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
		AuthorAction authorAction = (AuthorAction) context.getBean("authorAction");
		authorAction.publish("channel", "bbb");
	}
}
