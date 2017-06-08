package com.virgin.novel.util;

import java.io.IOException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.virgin.novel.dao.UserDao;
import com.virgin.novel.po.User;

public class MyBatisUtil {
	private static Logger logger = Logger.getLogger(MyBatisUtil.class);
	public static void main(String[] args) {
		testSpringMyBatis();
	}
	
	/**
	 * 测试SqlSession.selectOne
	 */
	public static void testSelectOne(){
		try {
			SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
			SqlSession sqlSession = sessionFactory.openSession();
			User user = sqlSession.selectOne("com.virgin.novel.dao.UserDao.getUser","root");
			logger.debug(user.getUser());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 测试SqlSession.getMapper
	 */
	public static void testGetMapper(){
		try {
			SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
			SqlSession sqlSession = sessionFactory.openSession();
			UserDao userDao = sqlSession.getMapper(UserDao.class);
			User user = userDao.getUser("root");
			logger.debug(user.getUser());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void testSpringMyBatis(){
		ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
		UserDao userDao = (UserDao) context.getBean("userMapper");
		User user = userDao.getUser("root");
		logger.debug(user.getUser());
	}
}
