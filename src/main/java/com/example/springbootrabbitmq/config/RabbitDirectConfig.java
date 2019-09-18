package com.example.springbootrabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitDirectConfig {
        public final static String DIRBCTNAME="JAVA-direct";

        /**
         * 配置一个队列
         * 如果是DirectExchange，下面两个bean可省略
         * @return
         */
        @Bean
        Queue queue(){
                return new Queue("hello.java");
            }

        @Bean
        DirectExchange directExchange(){
            return new DirectExchange(DIRBCTNAME,true,false);
        }

        @Bean
        Binding binding(){
            return BindingBuilder.bind(queue()).to(directExchange()).with("direct");
        }
}
