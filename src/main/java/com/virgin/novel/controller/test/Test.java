package com.virgin.novel.controller.test;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.virgin.novel.controller.author.AuthorAction;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

public class Test {
	
	public static void main(String[] args) throws MalformedURLException {
		//ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
		//AuthorAction authorAction = (AuthorAction) context.getBean("authorAction");
		//authorAction.publish("channel", "bbb");
		/**
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Person person = new Person();
		//person.setId("");
		//person.setName("123");
		Set<ConstraintViolation<Person>> constraintViolationSet = validator.validate(person);
		Set<ConstraintViolation<Person>> constraintViolationSet1 = validator.validateProperty(person,"id");
		if (constraintViolationSet1.size()>0){
			ConstraintViolation<Person> constraintViolation = constraintViolationSet1.iterator().next();
			System.out.println(constraintViolation.getPropertyPath() + constraintViolation.getMessage());
		}
		System.out.println(constraintViolationSet.size());
		 */
		try{
			SimpleDateFormat simpleDateFormat =  new SimpleDateFormat("yyyy-MM-dd");
			Date startDate = simpleDateFormat.parse("2016-12-383");
			Date endDate = simpleDateFormat.parse("2017-01-34");
			Calendar startCalendar =  Calendar.getInstance();
			startCalendar.setTime(startDate);
			Calendar endCalendar = Calendar.getInstance();
			endCalendar.setTime(endDate);
			System.out.println(startCalendar.compareTo(endCalendar));
			while (startCalendar.compareTo(endCalendar)<=0){
				System.out.println(simpleDateFormat.format(startCalendar.getTime()));
				startCalendar.add(Calendar.DAY_OF_YEAR,1);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
}
