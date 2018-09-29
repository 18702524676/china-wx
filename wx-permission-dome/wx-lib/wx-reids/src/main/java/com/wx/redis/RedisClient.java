package com.wx.redis;


import com.wx.core.exception.RedisOperationException;
import com.wx.core.utils.SpringUtils;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisUtil
 * @Author wx
 * @Description Redis客户端
 * @Date 2018-08-26-23:17
 */
@Component
public class RedisClient {

	private RedisTemplate<Object,Object> redisTemplate = null;


	// =============================common============================

	/**
	 * @methodName: expire
	 * @author: wx
	 * @description: 指定缓存失效时间
	 * @param key
	 * @param time
	 * @date: 2018/8/26
	 * @return: boolean
	 */
	public boolean expire(Object key, long time) {
		return getRedisTemplate().expire(key, time, TimeUnit.SECONDS);
	}

	/**
	 * @methodName: getExpire
	 * @author: wx
	 * @description: 根据key 获取过期时间
	 * @param key
	 * @date: 2018/8/26
	 * @return: long 时间(秒) 返回0代表为永久有效
	 */
	public long getExpire(Object key) {
		return getRedisTemplate().getExpire(key, TimeUnit.SECONDS);
	}
    
	/**
     * @methodName: hasKey
     * @author: wx
     * @description: 判断key是否存在
     * @param key
     * @date: 2018/8/26
     * @return: boolean
     */
    public boolean hasKey(Object key) {
		try {
			return getRedisTemplate().hasKey(key);
		} catch (Exception e) {
			throw new RedisOperationException(e.getMessage());
		}
	}
    
	/**
     * @methodName: del
     * @author: wx
     * @description: 根据key删除缓存 可以传一个或多个
     * @param key
     * @date: 2018/8/26
     * @return: void
     */
    public void del(Object... key){
        try {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
				getRedisTemplate().delete(key[0]);
            } else {
				getRedisTemplate().delete(CollectionUtils.arrayToList(key));
            }
        }
        } catch (Exception e) {
            throw new RedisOperationException(e.getMessage());
        }
    }

    /**
	 * @methodName: clear
	 * @author: wx
	 * @description: MyBatis清除缓存
	 * @param
	 * @date: 2018/9/16
	 * @return: void
	 */
	public void iBatisclear(String keys) {
		redisTemplate = getRedisTemplate();
		Set<Object> keysSet = redisTemplate.keys(keys);
		if (!CollectionUtils.isEmpty(keysSet)){
			redisTemplate.delete(keysSet);
		}

	}

	/**
	 * @methodName: clear
	 * @author: wx
	 * @description: 清除所有缓存
	 * @param
	 * @date: 2018/9/16
	 * @return: void
	 */
	public void clear() {
		getRedisTemplate().execute((RedisCallback) connection -> {
			connection.flushAll();
			return null;
		});
	}

	// ============================数据结构-String=============================
	/**
	 * @methodName: set
	 * @author: wx
	 * @description: 添加缓存
	 * @param key
	 * @param data
	 * @date: 2018/8/26
	 * @return: boolean
	 */
	public boolean set(Object key, Object data) {
		try {
			getRedisTemplate().opsForValue().set(key, data);
			return true;
		} catch (Exception e) {
			throw new RedisOperationException(e.getMessage());
		}
	}


	/**
	 * @methodName: set
	 * @author: wx
	 * @description: 添加缓存并设置过期时间
	 * @param key
	 * @param data
	 * @param time
	 * @date: 2018/8/26
	 * @return: boolean
	 */
	public boolean set(Object key, Object data, long time) {
		try {
			getRedisTemplate().opsForValue().set(key, data, time, TimeUnit.SECONDS);
			return true;
		} catch (Exception e) {
			throw new RedisOperationException(e.getMessage());
		}
	}


	/**
	 * @methodName: get
	 * @author: wx
	 * @description: 获取缓存数据
	 * @param key
	 * @date: 2018/9/16
	 * @return: java.lang.Object
	 */
	public Object get(Object key){
		if (key == null) {
			return null;
		}
		try {
			return getRedisTemplate().opsForValue().get(key);
		} catch (Exception e) {
			throw new RedisOperationException(e.getMessage());
		}
	}


	private RedisTemplate getRedisTemplate() {
		if (redisTemplate == null) {
			redisTemplate = (RedisTemplate) SpringUtils.getBean("redisTemplate");
		}
		return redisTemplate;
	}
}
