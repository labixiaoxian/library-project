package com.wyu.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



/**
 * Created by XiaoXian on 2020/11/19.
 */
@Configuration
public class MsgConfig {

    @Bean
    public Queue directQueue(){
        return new Queue("directQueue");
    }

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("directExchange");
    }

    // 通过 路由键，将消息队列绑定到 路由 上
    @Bean
    public Binding binding3(Queue directQueue, DirectExchange directExchange){
        return BindingBuilder.bind(directQueue).to(directExchange).with("xian");
    }
}
