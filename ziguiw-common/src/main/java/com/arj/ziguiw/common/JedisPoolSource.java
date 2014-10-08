package com.arj.ziguiw.common;

import com.arj.ziguiw.common.utils.ObjectSerializer;
import org.apache.commons.pool.impl.GenericObjectPool;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA <br/>
 * User: pengchangguo <br/>
 * Date: 12-1-11 <br/>
 * Time: P.M 4:06 <br/>
 * <p>
 * the pool of jedis object instance
 * </p>
 * <p/>
 * Here is a simple Example how to use:
 * <pre>
 *     import redis.clients.jedis.Jedis;
 *     import redis.clients.jedis.JedisPoolConfig;
 *     import com.arj.ziguiw.common.JedisPoolSource;
 *     class Example {
 *
 *         public static void main(String[] args) {
 *             JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
 *             jedisPoolConfig.setMaxActive(150);
 *             jedisPoolConfig.setMaxIdle(50);
 *             jedisPoolConfig.setMaxWait(60000);
 *             JedisPoolSource jedisPoolSource = new JedisPoolSource(jedisPoolConfig, "172.16.112.12");
 *             Jedis jedis = jedisPoolSource.getJedis();
 *             //then you can operate redis by a jedis object
 *         }
 *
 *     }
 *
 * <pre>
 *
 *  or u can use like this:
 *     import com.arj.ziguiw.common.JedisPoolSource;
 *     class Example {
 *
 *         public static void main(String[] args) {
 *             JedisPoolSource jedisPoolSource = new JedisPoolSource("127.0.0.1", 6379); //the redis host is "127.0.0.1", the redis port is 6379
 *             Jedis jedis = jedisPoolSource.getJedis();
 *             //then you can operate redis by a jedis object
 *             // also, we supply the api to operate redis in JedisPoolSource, for Example: push, pop, u can see in UnitTest
 *             jedisPoolSource.rpush(queueName, elementValue);
 *             jedisPoolSource.lpop(queueName);
 *         }
 *
 *     }
 *
 * <pre>
 *
 * @author pengchangguo
 * @version 1.0
 */
public class JedisPoolSource extends JedisPool {

    /* the alias name of app for distinguish the key when multi-apps use redis cache */
    private String alias = "";

    public static String DEFAULT_HOST = "127.0.0.1";

    public static int DEFAULT_PORT = Protocol.DEFAULT_PORT;

    private JedisPoolConfig jedisPoolConfig;
    
    private static JedisPoolSource jedisPoolSource = new JedisPoolSource(DEFAULT_HOST, Protocol.DEFAULT_PORT);

    public JedisPoolSource(String host, Integer port) {
        super(host == null ? DEFAULT_HOST : host, port == null ? DEFAULT_PORT : port);
    }

    public JedisPoolSource(GenericObjectPool.Config poolConfig, String host) {
        this(poolConfig, host, null, null, null);
    }

    public JedisPoolSource(GenericObjectPool.Config poolConfig, String host, Integer port, Integer timeout, String password) {
        super(poolConfig, host, port == null ? Protocol.DEFAULT_PORT : port, timeout == null ? Protocol.DEFAULT_TIMEOUT : timeout,
                password != null && password.trim().equals("") ? null : password);
    }
    
    public static JedisPoolSource getInstance() {
        return jedisPoolSource;
    }

    /**
     * u can operate the redis by the api
     *
     * @param callBack the interface to do something
     * @return result
     */
    private Object exec(JedisCallBack callBack) {
        Jedis jedis = getJedis();
        try{
            return callBack.doInJedis(jedis);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (null != jedis) returnResource(jedis);
        }
    }

    /**
     * get a jedis object instance from pool
     *
     * @return jedis
     */
    public Jedis getJedis() {
        return getResource();
    }

    /**
     * push the message into redis queue
     * @param queueName queue name
     * @param message message
     * @return the count of queue elements
     */
    public Long rpush(final String queueName, final Object message) {
        return (Long) exec(new JedisCallBack() {
            @Override
            public Object doInJedis(Jedis jedis) {
                try {
                    return jedis.rpush(queueName.getBytes(), ObjectSerializer.serialize(message));
                } catch (IOException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        });
    }

    /**
     * receive the head element and remove it
     * @param queueName queue name
     * @return the head element
     */
    public Object lpop(final String queueName) {
        return exec(new JedisCallBack() {
            @Override
            public Object doInJedis(Jedis jedis) {
                try {
                    byte [] bytes = jedis.lpop(queueName.getBytes());
                    if (bytes == null) return null;
                    return ObjectSerializer.reSerialize(bytes);
                } catch (IOException e) {
                    throw new IllegalStateException("can't serialize the message!", e);
                } catch (ClassNotFoundException e) {
                    throw new IllegalStateException("can't serialize the message! class not found!", e);
                }
            }
        });
    }

    public interface JedisCallBack {

        Object doInJedis(Jedis jedis);
    }
}
