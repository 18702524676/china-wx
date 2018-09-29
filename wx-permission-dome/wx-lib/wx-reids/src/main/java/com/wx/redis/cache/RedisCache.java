package com.wx.redis.cache;


import com.wx.core.exception.RedisOperationException;
import com.wx.core.utils.SpringUtils;
import com.wx.redis.RedisClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.Cache;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static com.wx.redis.constant.RedisConstant.REDIS_EXPIRE;
import static com.wx.redis.enums.RedisError.REDIS_ERROR_3;


/**
 * @ClassName RedisCache
 * @Author wx
 * @Description Mybatis Redis二级缓存(因为RedisCache并不是Spring容器里的bean，
 * 所以我门只能手动去getBenan获取，包括获取的对象内部里面也不能使用Spring注解获取，全部改成getBen)
 * @Date 2018-09-16-20:33
 */
@Slf4j
public class RedisCache implements Cache {
    /**
     * ReentrantReadWriteLock允许多个读线程同时访问，
     * 但不允许写线程和读线程、写线程和写线程同时访问。相对于排他锁，提高了并发性。
     * 在实际应用中，大部分情况下对共享数据（如缓存）的访问都是读操作远多于写操作，
     * 这时ReentrantReadWriteLock能够提供比排他锁更好的并发性和吞吐量。
     */
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final String id;
    private RedisClient redisClient = SpringUtils.getBean(RedisClient.class);

    public RedisCache(String id) {
        if (id == null) {
            throw new RedisOperationException(REDIS_ERROR_3);
        }
        this.id = id;
    }

    /**
     * @param
     * @methodName: getId
     * @author: wx
     * @description: mybatis缓存操作对象的标识符。一个mapper对应一个mybatis的缓存操作对象
     * @date: 2018/9/16
     * @return: java.lang.String
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * @param key   这里不使用key.toString() 会包缓存key无法转换为Sting的错误
     * @param value
     * @methodName: putObject
     * @author: wx
     * @description: 将查询结果塞入缓存
     * @date: 2018/9/16
     * @return: void
     */
    @Override
    public void putObject(Object key, Object value) {
        getRedisClient().set(key.toString(), value, REDIS_EXPIRE);
        log.debug("Put query result to redis");
    }

    /**
     * @param key key  这里不使用key.toString() 会包缓存key无法转换为Sting的错误
     * @methodName: getObject
     * @author: wx
     * @description: 从缓存中获取被缓存的查询结果
     * @date: 2018/9/16
     * @return: java.lang.Object
     */
    @Override
    public Object getObject(Object key) {
        log.debug("Get cached query result from redis");
        return getRedisClient().get(key.toString());

    }

    /**
     * @param key
     * @methodName: removeObject
     * @author: wx
     * @description: 从缓存中删除对应的key、value。只有在回滚时触发。一般我们也可以不用实现，
     * 具体使用方式请参考：org.apache.ibatis.cache.decorators.TransactionalCache。
     * @date: 2018/9/16
     * @return: java.lang.Object
     */
    @Override
    public Object removeObject(Object key) {
        redisClient.del(key.toString());
        log.debug("Remove cached query result from redis");
        return null;
    }

    /**
     * @param
     * @methodName: 发生更新时，清除缓存。
     * @author: wx
     * @description:
     * @date: 2018/9/16
     * @return: void
     */
    @Override
    public void clear() {
        redisClient.iBatisclear("*:" + this.id + "*");
        log.debug("Clear all the cached query result from redis");
    }

    /**
     * @param
     * @methodName: getSize
     * @author: wx
     * @description: 可选实现。返回缓存的数量。
     * @date: 2018/9/16
     * @return: int
     */
    @Override
    public int getSize() {
        return 0;
    }

    /**
     * @param
     * @methodName: getReadWriteLock
     * @author: wx
     * @description: 可选实现。用于实现原子性的缓存操作
     * @date: 2018/9/16
     * @return: java.util.concurrent.locks.ReadWriteLock
     */
    @Override
    public ReadWriteLock getReadWriteLock() {
        return this.readWriteLock;
    }


    private RedisClient getRedisClient() {
        if (redisClient == null) {
            redisClient = SpringUtils.getBean(RedisClient.class);
        }
        return redisClient;
    }

}
