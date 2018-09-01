package com.user.service;

import java.util.HashMap;

import org.apache.ibatis.annotations.Param;

import com.user.entity.User;

public interface UserService {
	public User findUserByOpenId(String openId);
	public User findUserByOpenIdWX(String openId);
	public String isExistUserId(String userId);
	
	
	public Integer findTID(Integer cId);
	public String findUserId(@Param("openId")String openId,@Param("tId")Integer tId);
	public void insertUser(HashMap<String, Object> map);
	public Integer findMoney(@Param("userId")String userId,@Param("tId")Integer tId);
}
