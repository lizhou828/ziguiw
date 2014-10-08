package com.arj.ziguiw.common;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Observable;
import java.util.Observer;
import java.util.logging.Logger;

import static java.lang.System.currentTimeMillis;

/**
 * Created by IntelliJ IDEA.
 * User: pengchangguo
 * Date: 12-1-11
 * Time: P.M 2:26
 */
public class JedisTest {

    private static final Logger logger = Logger.getLogger(JedisTest.class.getName());

    JedisPoolSource jedisPool;
    long beginTime;

    @Before
    public void init() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxActive(150);
        jedisPoolConfig.setMaxIdle(50);
        jedisPoolConfig.setMaxWait(6000);
        jedisPool = new JedisPoolSource(jedisPoolConfig, "10.0.1.22");
        beginTime = currentTimeMillis();
    }

    @Test
    public void singleJedis() {
        Jedis jedis = jedisPool.getJedis();
        Long beginTime = currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            jedis.set(String.format("singletonTest_%s", i), String.format("singletonTest_%s", i));
        }
        logger.info(String.format("execute set : %s", currentTimeMillis() - beginTime));
        beginTime = currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            jedis.get(String.format("singletonTest_%s", i));
        }
        logger.info(String.format("execute get : %s", currentTimeMillis() - beginTime));
    }

   @Test
    public void multiThreadJedis() throws InterruptedException {
        logger.info("exec multiThread");
        for (int i = 0; i < 10; i++) {
            JedisThread jedisThread = new JedisThread(i);
            JedisObserver jedisObserver = new JedisObserver();
            jedisThread.addObserver(jedisObserver);
            new Thread(jedisThread).start();
        }
        while (true) {
            Thread.sleep(500);
            if (counts > 10) {
                break;
            }
        }
    }

    @Test
    public void rpush() {
        for (int i = 0; i < 100; i++) {
            System.out.println(jedisPool.rpush("point", i + ""));
        }
    }

    @Test
    public void lpop() {
        for (int i = 0; i < 100; i++) {
            System.out.println(jedisPool.lpop("point"));
        }
    }

    class JedisThread extends Observable implements Runnable {
        int num;

        JedisThread(int num) {
            this.num = num;
        }

        @Override
        public void run() {
            int index = num * 1000;
            for (int i = index; i < index + 1000; i++) {
                Jedis jedis = jedisPool.getJedis();
                try{
                    jedis.set(String.format("multiThreadTest_%s", i), String.format("multiThreadTest_%s", i));
                } finally {
                    jedisPool.returnResource(jedis);
                }
            }
            setChanged();
            notifyObservers();
        }

    }

    private static int counts = 1;

    static class JedisObserver implements Observer {

        @Override
        public void update(Observable o, Object arg) {
            counts++;
        }
    }

    @After
    public void destroy() {
        logger.info(String.format("total time is %s", currentTimeMillis() - beginTime));
        //jedisPool.getJedis().flushAll();
        jedisPool.destroy();
    }
}
