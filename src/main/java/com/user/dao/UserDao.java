package com.user.dao;

import java.util.HashMap;

import org.apache.ibatis.annotations.Param;

import com.user.entity.User;

public interface UserDao {
	//���������Ϣ
	public User findUserByOpenId(String openId);
	//����û�
	public void insertUser(HashMap<String, Object> map);
	//�û��Ƿ����
	public String isExistUserId(String userId);
	//������ĿTID
	public Integer findTID(Integer cId);
	//���� OPENID TID ����USERID
	public String findUserId(@Param("openId")String openId,@Param("tId")Integer tId);
	//���� USERID TID ����money
	public Integer findMoney(@Param("userId")String userId,@Param("tId")Integer tId);
}
