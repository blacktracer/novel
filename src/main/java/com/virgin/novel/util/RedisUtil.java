package com.virgin.novel.util;

import java.net.URL;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service("redisUtil")
public class RedisUtil {
	private static Logger logger = Logger.getLogger(RedisUtil.class);
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	@Resource(name="redisTemplate")
	private ListOperations<String, String> listOps;
	@Resource(name="redisTemplate")
	private ValueOperations<String, String> valueOps;
	
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
		RedisUtil redisUtil = (RedisUtil) context.getBean("redisUtil");
		//redisUtil.testRedisTemplate();
		redisUtil.testStringRedisTemplate();
		//redisUtil.testValueOperations();
	}
	
	/**
	 * 测试RedisTemplate
	 */
	public void testRedisTemplate(){
		ValueOperations<String,Object> valueOperations = redisTemplate.opsForValue();
		valueOperations.set("userName","迪丽热巴");
		String userName = (String) valueOperations.get("userName");
		logger.debug(userName);
	}
	
	/**
	 * 测试StringRedisTemplate
	 */
	public void testStringRedisTemplate(){
		ValueOperations<String,String> valueOperations = stringRedisTemplate.opsForValue();
		valueOperations.set("userName","江疏影");
		String userName = valueOperations.get("userName");
		logger.debug(userName);
	}
	
	/**
	 * 测试ValueOperaions
	 */
	public void testValueOperations(){
		valueOps.set("userName","古力娜扎");
		String userName = valueOps.get("userName");
		logger.debug(userName);
	}
	
	public void addLink(String userId, URL url) {
        stringRedisTemplate.execute(new RedisCallback<Object>(){
			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				
				connection.subscribe(new MessageListener(){
					@Override
					public void onMessage(Message message, byte[] pattern) {
						System.out.println(new String(message.getBody()));
					}
				},"channel".getBytes());
				
				StringRedisConnection stringRedisConnection = (StringRedisConnection) connection;
				connection.set("name1".getBytes(), "boy1".getBytes());
				stringRedisConnection.set("age", "100");
				connection.publish("channel".getBytes(),"message from jedis client".getBytes());
				return null;
			}
        });
        redisTemplate.convertAndSend("channel","hi");
	}
	
	public void subscribe(final String channel) {
        redisTemplate.execute(new RedisCallback<Object>(){
			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				
				connection.subscribe(new MessageListener(){
					@Override
					public void onMessage(Message message, byte[] pattern) {
						System.out.println(new String(message.getBody()));
						System.out.println(new String(message.getChannel()));;
					}
				},channel.getBytes());
				return null;
			}
        });
	}
	
	public void publish(final String channel, final String message) {
        redisTemplate.execute(new RedisCallback<Object>(){
			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				connection.publish(channel.getBytes(),message.getBytes());
				return null;
			}
        });
	}
}
