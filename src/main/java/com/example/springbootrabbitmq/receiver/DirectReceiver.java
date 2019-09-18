package com.example.springbootrabbitmq.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消费者
 */
@Component
public class DirectReceiver {

        @RabbitListener(queues = "hello.java")
        public void handler1(String msg){
            System.out.println("handler1》》》"+msg);
        }
}
