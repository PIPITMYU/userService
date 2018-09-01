package com.user.dao;

import java.util.HashMap;

import org.apache.ibatis.annotations.Param;

import com.user.entity.User;

public interface UserDao {
	//查找玩家信息
	public User findUserByOpenId(String openId);
	//添加用户
	public void insertUser(HashMap<String, Object> map);
	//用户是否存在
	public String isExistUserId(String userId);
	//查找项目TID
	public Integer findTID(Integer cId);
	//根据 OPENID TID 查找USERID
	public String findUserId(@Param("openId")String openId,@Param("tId")Integer tId);
	//根据 USERID TID 查找money
	public Integer findMoney(@Param("userId")String userId,@Param("tId")Integer tId);
}
