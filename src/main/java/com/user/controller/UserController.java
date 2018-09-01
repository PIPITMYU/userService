package com.user.controller;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import com.user.constant.Cnst;
import com.user.entity.User;
import com.user.service.UserService;
import com.user.utils.ServerUtils;
import com.user.utils.redis.RedisUtil;
@Controller
public class UserController {
	@Resource
	private UserService userService;
	 @RequestMapping("/returnUser")
	 @ResponseBody
	 public  synchronized String returnUser(String openId,Integer cId){
		 //����tid
		 Integer tId = userService.findTID(cId);
		 //ͨ��tid openId����userId
		 String userId = userService.findUserId(openId, tId);
		 User user = userService.findUserByOpenIdWX(openId);
		 JSONObject info = new JSONObject();
		 info.put("nickname", user.getNickname());
		 info.put("sex", user.getSex());
		 info.put("headimgurl", user.getHeadimgurl());
		 if(userId == null){
			 //������userId
			 while(true){
	                user.setUserId(String.valueOf(ServerUtils.getGivenRamdonNum(6)));//Ψһ��userId����Ҫȥ���ݿ����Ƿ���ڴ�id
	                String userOpenId = userService.isExistUserId(user.getUserId());
	                if (userOpenId==null){
	                    break;
	                }
	            }
			 HashMap<String,Object> map = new HashMap<String, Object>();
			 map.put("openId", openId);
			 map.put("tId", tId);
			 map.put("userId", user.getUserId());
			 map.put("updateTime", System.currentTimeMillis());
			 map.put("money", 100);
			 userService.insertUser(map);
			 info.put("userId", user.getUserId());
		 }else{
			 info.put("userId", userId);
		 }
		 System.out.println("�û�����"+info);
		 return JSONObject.toJSONString(info);
	 }
	 @RequestMapping("/openRoom")
	 @ResponseBody
	 /**
	  * 
	  * @param userId
	  * @param cId
	  * @param openUsed
	  * @return 0 ��������    1 ���سɹ�
	  */
	 public synchronized String openRoom(String userId,Integer cId,Integer openUsed){
		 JSONObject info = new JSONObject();
		 //����tid 
		 Integer tId = userService.findTID(cId);
		 //ͨ��tid userId ����money
		 Integer money = userService.findMoney(userId, tId);
		 if(money<openUsed){
			 info.put("state", 0);
			 return JSONObject.toJSONString(info);
		 }
		 Integer returnMoney = money - openUsed;
		 String key = Cnst.REDIS_FREEZE_MONEY.concat(String.valueOf(cId));
		 if(RedisUtil.hexists(key,userId)){
			 Integer hasFreeze = Integer.parseInt(RedisUtil.hget(key, userId));
			 if(hasFreeze + openUsed > money){
				 info.put("state", 0);
				 return JSONObject.toJSONString(info);
			 }
			 RedisUtil.hset(key, userId, String.valueOf(hasFreeze+openUsed), null);
			 returnMoney = returnMoney - hasFreeze;
		 }else{
			 RedisUtil.hset(key, userId, String.valueOf(openUsed), null);
		 }
		 info.put("state", 1);
		 info.put("money", returnMoney);
		 return JSONObject.toJSONString(info);
	 }
}
