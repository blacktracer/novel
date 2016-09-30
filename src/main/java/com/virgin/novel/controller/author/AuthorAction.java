package com.virgin.novel.controller.author;

import java.net.URL;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

public class AuthorAction {
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@Resource(name="redisTemplate")
	private ListOperations<String, String> listOps;
	
	public void testRedis(){
		System.out.println(redisTemplate);
		System.out.println(listOps);
	}
	
	public void addLink(String userId, URL url) {
        listOps.leftPush(userId, url.toExternalForm());
        redisTemplate.opsForList().rightPush("list", "bbb");
        stringRedisTemplate.opsForList().leftPush("list", "aaa");
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
