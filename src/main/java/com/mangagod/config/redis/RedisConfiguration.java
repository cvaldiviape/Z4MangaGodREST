//package com.mangagod.config.redis;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
//import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
//import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
//import static io.lettuce.core.ReadFrom.REPLICA_PREFERRED;
//
//@Configuration
//public class RedisConfiguration {
//
//    @Bean
//    public LettuceConnectionFactory redisConnectionFactory(){
//        LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder()
//                .readFrom(REPLICA_PREFERRED)
//                .build();
//        RedisStandaloneConfiguration serverConfig = new RedisStandaloneConfiguration("localhost", 6379);
//
//        return new LettuceConnectionFactory(serverConfig);
//    }
//
//}