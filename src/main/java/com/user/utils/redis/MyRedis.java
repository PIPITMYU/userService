package com.user.utils.redis;



public class MyRedis {
	
  	private static RedisClient client;
  	public static void initRedis(){
  		client = new RedisClient(null);
		System.out.println("redis ��ʼ���ɹ�");
  	}
  	
  	public synchronized static RedisClient getRedisClient(){
  		return client;
  	}
}
