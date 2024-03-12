package com.maidao.edu.store.common.redis;

import com.maidao.edu.store.common.util.L;
import com.sunnysuperman.commons.config.Config;
import com.sunnysuperman.commons.config.PropertiesConfig;
import com.sunnysuperman.commons.util.JSONUtil;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

import java.util.concurrent.TimeUnit;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：homework
 * 类名称:RedisUtils
 * 类描述:redis工具类
 **/
public class RedisUtils {

    public static JedisPool createPool(String cfgString) {
        L.warn("Load redis pool: " + cfgString);
        Config cfg = new PropertiesConfig(JSONUtil.parseJSONObject(cfgString));
        JedisPoolConfig config = new JedisPoolConfig();
        config.setJmxEnabled(false);
        config.setMinIdle(cfg.getInt("minIdle"));
        config.setMaxIdle(cfg.getInt("maxIdle"));
        config.setMaxTotal(cfg.getInt("maxTotal"));
        config.setMinEvictableIdleTimeMillis(TimeUnit.SECONDS.toMillis(cfg.getLong("idleTime", 300)));
        config.setMaxWaitMillis(cfg.getLong("maxWait", 15000));
        return new JedisPool(config, cfg.getString("host"), cfg.getInt("port", 6379), Protocol.DEFAULT_TIMEOUT,
                cfg.getString("password", null), cfg.getInt("db"));
    }

}
