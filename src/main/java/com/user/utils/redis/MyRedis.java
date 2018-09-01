package com.user.utils.redis;



public class MyRedis {
	
  	private static RedisClient client;
  	public static void initRedis(){
  		client = new RedisClient(null);
		System.out.println("redis 初始化成功");
  	}
  	
  	public synchronized static RedisClient getRedisClient(){
  		return client;
  	}
}
