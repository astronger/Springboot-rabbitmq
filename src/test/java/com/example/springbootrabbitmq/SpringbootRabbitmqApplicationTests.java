package com.example.springbootrabbitmq;

import com.example.springbootrabbitmq.config.RabbitFanoutConfig;
import com.example.springbootrabbitmq.config.RabbitHeaderConfig;
import com.example.springbootrabbitmq.config.RabbitTopicConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.MessageDigest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRabbitmqApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;

    //direct
    @Test
    public void contextLoads() {
        rabbitTemplate.convertAndSend("hello.java","hello java!!!hahaha");//routingKey要与directConfig配置的queue一样
    }

    //fanout
    @Test
    public void test1() {
        rabbitTemplate.convertAndSend(RabbitFanoutConfig.FANOUTNAME,null,"hello fanout!");//
    }

    //topic
    @Test
    public void test2() {
        rabbitTemplate.convertAndSend(RabbitTopicConfig.TOPICNAME,"xiaomi.news","小米新闻!");//
        rabbitTemplate.convertAndSend(RabbitTopicConfig.TOPICNAME,"vivo.phone","vivo手机!");
        rabbitTemplate.convertAndSend(RabbitTopicConfig.TOPICNAME,"huawei.phone","华为手机！");
    }

    //header
    @Test
    public void test3() {
        Message nameMsg = MessageBuilder.withBody("hello java!".getBytes()).setHeader("name", "java").build();//"name"/"java"要与config文件中map.put的值一样，否则获取不到
        Message ageMsg = MessageBuilder.withBody("hello 99!".getBytes()).setHeader("age", "99").build();
        rabbitTemplate.send(RabbitHeaderConfig.HEADERNAME,null,nameMsg);
        rabbitTemplate.send(RabbitHeaderConfig.HEADERNAME,null,ageMsg);
    }
}
