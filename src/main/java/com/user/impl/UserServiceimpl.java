package com.user.impl;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.user.dao.UserDao;
import com.user.entity.User;
import com.user.service.UserService;
import com.user.wxdao.WXUserDao;
@Service("userService")
public class UserServiceimpl implements UserService{
	@Resource
	private UserDao userDao;
	@Resource
	private WXUserDao wxuserDao;
	public User findUserByOpenId(String openId) {
		return userDao.findUserByOpenId(openId);
	}

	public void insertUser(HashMap<String, Object> map) {
		userDao.insertUser(map);
	}

	public User findUserByOpenIdWX(String openId) {
		return wxuserDao.findUserByOpenId(openId);
	}

	public String isExistUserId(String userId) {
		return userDao.isExistUserId(userId);
	}

	public Integer findTID(Integer cId) {
		
		return userDao.findTID(cId);
	}

	public String findUserId(String openId, Integer tId) {

		return userDao.findUserId(openId, tId);
	}

	@Override
	public Integer findMoney(String userId, Integer tId) {
		
		return userDao.findMoney(userId, tId);
	}


}
