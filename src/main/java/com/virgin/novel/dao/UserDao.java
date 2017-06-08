package com.virgin.novel.dao;

import org.apache.ibatis.annotations.Select;

import com.virgin.novel.po.User;

public interface UserDao {
	@Select("SELECT user,host FROM user WHERE user=#{user}")
	public User getUser(String user);
}
