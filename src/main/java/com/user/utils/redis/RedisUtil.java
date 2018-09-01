package com.user.utils.redis;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import redis.clients.jedis.Jedis;
import com.alibaba.fastjson.JSONObject;


public class RedisUtil {

	
	public  static void hset(String key, String field, String value,Integer timeout) {
		Jedis jedis = null;
		try {
			jedis = MyRedis.getRedisClient().getJedis();
			jedis.hset(key, field, value);
			if (timeout!=null) {
				jedis.expire(key, timeout);
			}
		} catch (Exception e) {
			e.printStackTrace();
			MyRedis.getRedisClient().returnBrokenJedis(jedis);
		} finally {
			if (jedis != null) {
				MyRedis.getRedisClient().returnJedis(jedis);
			}
		}
	}
	
	public  static void hmset(String key, Map<String,String> info,Integer timeout) {
		Jedis jedis = null;
		try {
			jedis = MyRedis.getRedisClient().getJedis();
			jedis.hmset(key, info);
			if (timeout!=null) {
				jedis.expire(key, timeout);
			}
		} catch (Exception e) {
			e.printStackTrace();
			MyRedis.getRedisClient().returnBrokenJedis(jedis);
		} finally {
			if (jedis != null) {
				MyRedis.getRedisClient().returnJedis(jedis);
			}
		}
	}
	
	public  static String hget(String key, String field) {
		String result = null;
		Jedis jedis = null;
		try {
			jedis = MyRedis.getRedisClient().getJedis();
            result = jedis.hget(key, field);
		} catch (Exception e) {
			e.printStackTrace();
			MyRedis.getRedisClient().returnBrokenJedis(jedis);
		} finally {
			if (jedis != null) {
				MyRedis.getRedisClient().returnJedis(jedis);
			}
		}
		return result;
	}
	
	public  static Boolean hexists(String key, String field) {
		Boolean result = false;
		Jedis jedis = null;
		try {
			jedis = MyRedis.getRedisClient().getJedis();
            result = jedis.hexists(key, field);
		} catch (Exception e) {
			e.printStackTrace();
			MyRedis.getRedisClient().returnBrokenJedis(jedis);
		} finally {
			if (jedis != null) {
				MyRedis.getRedisClient().returnJedis(jedis);
			}
		}
		return result;
	}
	

	public  static Map<String,String> hmget(String key,String[] fields) {
		Map<String,String> result = null;
        List<String> list = null;
		Jedis jedis = null;
		try {
			jedis = MyRedis.getRedisClient().getJedis();
			list = jedis.hmget(key, fields);
			if (list!=null) {
				result = new HashMap<String, String>();
				for(int i=0;i<fields.length;i++){
					result.put(fields[i], list.get(i));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			MyRedis.getRedisClient().returnBrokenJedis(jedis);
		} finally {
			if (jedis != null) {
				MyRedis.getRedisClient().returnJedis(jedis);
			}
		}
		return result;
	}

	public  static List<String> hmgetList(String key,String[] fields) {
        List<String> list = null;
		Jedis jedis = null;
		try {
			jedis = MyRedis.getRedisClient().getJedis();
			list = jedis.hmget(key, fields);
		} catch (Exception e) {
			e.printStackTrace();
			MyRedis.getRedisClient().returnBrokenJedis(jedis);
		} finally {
			if (jedis != null) {
				MyRedis.getRedisClient().returnJedis(jedis);
			}
		}
		return list;
	}


	public  static Map<String,String> hgetAll(String key) {
        Map<String, String> result = null;
		Jedis jedis = null;
		try {
			jedis = MyRedis.getRedisClient().getJedis();
            result = jedis.hgetAll(key);
		} catch (Exception e) {
			e.printStackTrace();
			MyRedis.getRedisClient().returnBrokenJedis(jedis);
		} finally {
			if (jedis != null) {
				MyRedis.getRedisClient().returnJedis(jedis);
			}
		}
		return result;
	}
	
	/**
	 * 向key的list中加入元素，取的时�?�，按照后进先出的规则存入，对应底下的lrange方法
	 * @param key
	 * @param value
	 */
	public  static void lpush(String key,Integer timeout , String... value) {
		Jedis jedis = null;
		try {
			jedis = MyRedis.getRedisClient().getJedis();
			jedis.lpush(key, value);
			if (timeout!=null) {
				jedis.expire(key, timeout);
			}
		} catch (Exception e) {
			e.printStackTrace();
			MyRedis.getRedisClient().returnBrokenJedis(jedis);
		} finally {
			if (jedis != null) {
				MyRedis.getRedisClient().returnJedis(jedis);
			}
		}
	}
	
	/**
	 * 向key的list中加入元素，取的时�?�，按照后进先出的规则存入，对应底下的lrange方法
	 * @param key
	 * @param value
	 */
	public  static void rpush(String key,Integer timeout , String... value) {
		Jedis jedis = null;
		try {
			jedis = MyRedis.getRedisClient().getJedis();
			jedis.rpush(key, value);
			if (timeout!=null) {
				jedis.expire(key, timeout);
			}
		} catch (Exception e) {
			e.printStackTrace();
			MyRedis.getRedisClient().returnBrokenJedis(jedis);
		} finally {
			if (jedis != null) {
				MyRedis.getRedisClient().returnJedis(jedis);
			}
		}
	}
	
	/**
	 * 从开始位置，取到结束位置，包含两边；跟mysql的分页不�?�?
	 * 比如�?5�?10，取第五位到第十位，�?共六个结�?
	 * @param key
	 * @param start �?始位�?
	 * @param end   结束位置
	 * @return
	 */
	public  static List<String> lrange(String key,int start,int end) {
		List<String> result = null;
		Jedis jedis = null;
		try {
			jedis = MyRedis.getRedisClient().getJedis();
			result = jedis.lrange(key, start, end);
		} catch (Exception e) {
			e.printStackTrace();
			MyRedis.getRedisClient().returnBrokenJedis(jedis);
		} finally {
			if (jedis != null) {
				MyRedis.getRedisClient().returnJedis(jedis);
			}
		}
		return result;
	}
	
	/**
	 * 获取key的list的�?�长�?
	 * @param key
	 * @return
	 */
	public  static Long llen(String key) {
		Long length = 0l;
		Jedis jedis = null;
		try {
			jedis = MyRedis.getRedisClient().getJedis();
			length = jedis.llen(key);
		} catch (Exception e) {
			e.printStackTrace();
			MyRedis.getRedisClient().returnBrokenJedis(jedis);
		} finally {
			if (jedis != null) {
				MyRedis.getRedisClient().returnJedis(jedis);
			}
		}
		return length;
	}
	
	public  static String setObject(String key,Object value,Integer timeout) {
		String result = null;
		Jedis jedis = null;
		try {
			jedis = MyRedis.getRedisClient().getJedis();
			result = jedis.set(key, JSONObject.toJSONString(value));
			if (timeout!=null) {
				jedis.expire(key, timeout);
			}
		} catch (Exception e) {
			e.printStackTrace();
			MyRedis.getRedisClient().returnBrokenJedis(jedis);
		} finally {
			if (jedis != null) {
				MyRedis.getRedisClient().returnJedis(jedis);
			}
		}
		return result;
	}
	
	
	public  static <T> T getObject(String key,Class<T> T) {
		String result = null;
		Jedis jedis = null;
		try {
			jedis = MyRedis.getRedisClient().getJedis();
			result = jedis.get(key);
			if (result!=null) {
				return JSONObject.parseObject(result, T);
			}
		} catch (Exception e) {
			e.printStackTrace();
			MyRedis.getRedisClient().returnBrokenJedis(jedis);
		} finally {
			if (jedis != null) {
				MyRedis.getRedisClient().returnJedis(jedis);
			}
		}
		return null;
	}
	
	public  static Set<String> getKeys(String pattern) {
		Set<String> result = null;
		Jedis jedis = null;
		try {
			jedis = MyRedis.getRedisClient().getJedis();
			result = jedis.keys(pattern);
		} catch (Exception e) {
			e.printStackTrace();
			MyRedis.getRedisClient().returnBrokenJedis(jedis);
		} finally {
			if (jedis != null) {
				MyRedis.getRedisClient().returnJedis(jedis);
			}
		}
		return result;
	}
	
	public  static boolean deleteByKey(String... key){
    	boolean result = true;
    	Jedis jedis = null;
    	try {
    		jedis = MyRedis.getRedisClient().getJedis();
    		jedis.del(key);
		} catch (Exception e) {
			result = false;
			MyRedis.getRedisClient().returnBrokenJedis(jedis);
		}finally{
			if (jedis!=null) {
				MyRedis.getRedisClient().returnJedis(jedis);
			}
		}
    	return result;
    }
	
	public  static boolean hdel(String key,String... field){
    	boolean result = true;
    	Jedis jedis = null;
    	try {
    		jedis = MyRedis.getRedisClient().getJedis();
    		jedis.hdel(key,field);
		} catch (Exception e) {
			result = false;
			MyRedis.getRedisClient().returnBrokenJedis(jedis);
		}finally{
			if (jedis!=null) {
				MyRedis.getRedisClient().returnJedis(jedis);
			}
		}
    	return result;
    }
	
	public  static boolean exists(String key){
    	boolean result = true;
    	Jedis jedis = null;
    	try {
    		jedis = MyRedis.getRedisClient().getJedis();
    		result = jedis.exists(key);
		} catch (Exception e) {
			result = false;
			MyRedis.getRedisClient().returnBrokenJedis(jedis);
		}finally{
			if (jedis!=null) {
				MyRedis.getRedisClient().returnJedis(jedis);
			}
		}
    	return result;
    }
	
	
	
	/**
	 * 从列表末尾移除并返回�?后一个元�?
	 * @param key
	 * @return
	 */
	public  static String rpop(String key){
		String result = null;
    	Jedis jedis = null;
    	try {
    		jedis = MyRedis.getRedisClient().getJedis();
    		result = jedis.rpop(key);
		} catch (Exception e) {
			MyRedis.getRedisClient().returnBrokenJedis(jedis);
		}finally{
			if (jedis!=null) {
				MyRedis.getRedisClient().returnJedis(jedis);
			}
		}
    	return result;
    }
      
    
    /************************通过对应key值get对应对象***************************/
    
    public synchronized static String getStringByKey(String key){
    	String value = null;
    	Jedis jedis = null;
    	try {
    		jedis = MyRedis.getRedisClient().getJedis();
    		value = jedis.get(key);
		} catch (Exception e) {
			e.printStackTrace();
			MyRedis.getRedisClient().returnBrokenJedis(jedis);
		}finally{
			if (jedis!=null) {
				MyRedis.getRedisClient().returnJedis(jedis);
			}
		}
    	return value;
    }

    
    public synchronized static Set<String> getSameKeys(String patten){
    	Set<String> keys = null;
    	Jedis jedis = null;
    	try {
    		jedis = MyRedis.getRedisClient().getJedis();
    		keys = jedis.keys(patten.concat("*"));
		} catch (Exception e) {
			e.printStackTrace();
			MyRedis.getRedisClient().returnBrokenJedis(jedis);
		}finally{
			if (jedis!=null) {
				MyRedis.getRedisClient().returnJedis(jedis);
			}
		}
    	Set<String> result = null;
    	if (keys!=null&&keys.size()>0) {
    		result = new HashSet<String>();
			for(String str:keys){
				str = str.replace(patten, "");
				result.add(str);
			}
		}
    	return result;
    }
    
    
  
    public synchronized static boolean deleteByKey(String key){
    	boolean result = true;
    	Jedis jedis = null;
    	try {
    		jedis = MyRedis.getRedisClient().getJedis();
    		jedis.del(key);
		} catch (Exception e) {
			result = false;
			MyRedis.getRedisClient().returnBrokenJedis(jedis);
		}finally{
			if (jedis!=null) {
				MyRedis.getRedisClient().returnJedis(jedis);
			}
		}
    	return result;
    }
    
}
