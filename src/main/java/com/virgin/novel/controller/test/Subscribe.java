package com.virgin.novel.controller.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.virgin.novel.controller.author.AuthorAction;

public class Subscribe {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
		AuthorAction authorAction = (AuthorAction) context.getBean("authorAction");
		//authorAction.subscribe("channel");
	}
}
