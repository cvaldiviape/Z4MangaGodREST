//package com.mangagod.config.redis;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.data.redis.cache.RedisCacheConfiguration;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import java.time.Duration;
//import java.time.temporal.ChronoUnit;
//import java.util.HashMap;
//import java.util.Map;
//
//public class CacheConfiguration {
//
//    public static final String USER_CACHE = "user-cache";
//
//    @Bean
//    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
//        Map<String, RedisCacheConfiguration> redisConfigurationMap = new HashMap<>();
//        redisConfigurationMap.put(USER_CACHE, createConfig(1, ChronoUnit.MINUTES));
//
//        return RedisCacheManager.builder(connectionFactory)
//                .withInitialCacheConfigurations(redisConfigurationMap)
//                .build();
//    }
//
//    private static RedisCacheConfiguration createConfig(long time, ChronoUnit temporalUnit) {
//        return RedisCacheConfiguration.defaultCacheConfig()
//                .entryTtl(Duration.of(time, temporalUnit))
//                .disableCachingNullValues();
//    }
//
//}