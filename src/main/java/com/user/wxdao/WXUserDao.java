package com.user.wxdao;

import com.user.entity.User;

public interface WXUserDao {
	public User findUserByOpenId(String openId);
}
