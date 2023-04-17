package com.example.demo;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.StringKeySerializer;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties.ClientType;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties.Cluster;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration.JedisClientConfigurationBuilder;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @date: 2022/9/27
 */
@Slf4j
public class RedisEvalDemo {

    @Test
    public void evalTest() {
        String nodeStr = "192.168.101.140:7000,192.168.101.140:7001,192.168.101.140:7002,192.168.101.140:7003,192.168.101.140:7004,192.168.101.140:7005";

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(1);
        jedisPoolConfig.setMaxIdle(1);
        jedisPoolConfig.setMaxWait(Duration.ofMillis(100));
        jedisPoolConfig.setMinIdle(0);
        jedisPoolConfig.setTestOnBorrow(true);
        jedisPoolConfig.setTestOnReturn(true);
        jedisPoolConfig.setTestWhileIdle(true);
        JedisClientConfiguration jedisClientConfiguration = JedisClientConfiguration.builder().usePooling().poolConfig(jedisPoolConfig).and()
                .readTimeout(Duration.ofMillis(100)).build();

        Collection<RedisNode> nodes = new ArrayList<>();
        List<String> nodeList = Arrays.asList(nodeStr.split(","));
        for (int i = 0; i < nodeList.size(); i++) {
            String[] split = nodeList.get(i).split(":");
            RedisNode node = new RedisNode(split[0], Integer.parseInt(split[1]));
            nodes.add(node);
        }
        
        RedisClusterConfiguration clusterConfiguration = new RedisClusterConfiguration();
        clusterConfiguration.setClusterNodes(nodes);
        clusterConfiguration.setMaxRedirects(3);
        clusterConfiguration.setPassword("123456");

        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(clusterConfiguration, jedisClientConfiguration);
        jedisConnectionFactory.afterPropertiesSet();

        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate(jedisConnectionFactory);


        log.info("1: {}", stringRedisTemplate.opsForValue().get("kk"));
        stringRedisTemplate.opsForValue().set("kk", "123", Duration.ofSeconds(10));
        log.info("2: {}", stringRedisTemplate.opsForValue().get("kk"));

        String script = "if (redis.call('EXISTS', KEYS[1]) == 1) then return -1 end;"
                + "local valueCtl = redis.call('incr',KEYS[1]);"
                + "redis.call('expire', KEYS[1], ARGV[1]);"
                + "return valueCtl";

        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>(script, Long.class);
        Long hahaha = stringRedisTemplate.execute(redisScript, Arrays.asList("HAHAHA"), "10");
        log.info("3: {}", hahaha);
        hahaha = stringRedisTemplate.execute(redisScript, Arrays.asList("HAHAHA"), "10");
        log.info("4: {}", hahaha);

        script = "local current = redis.call('incr',KEYS[1]); "
                + "local t = redis.call('ttl',KEYS[1]); "
                + "if t == -1 then  redis.call('expire',KEYS[1],ARGV[1]) end;"
                + "return current";
        redisScript = new DefaultRedisScript<>(script, Long.class);
        hahaha = stringRedisTemplate.execute(redisScript, Arrays.asList("HAHAHA"), "10");
        log.info("5: {}", hahaha);
        hahaha = stringRedisTemplate.execute(redisScript, Arrays.asList("HAHAHA"), "10");
        log.info("6: {}", hahaha);
    }

}
