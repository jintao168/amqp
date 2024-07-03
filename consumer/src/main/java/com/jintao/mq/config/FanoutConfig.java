package com.jintao.mq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutConfig {

    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("jintao.fanout");
    }
    @Bean
    public Queue queue1(){
        return new Queue("fanout.que1");
    }

    //绑定队列到交换机
    @Bean
    public Binding queueBinding1(Queue queue1, FanoutExchange fanoutExchange){
        return BindingBuilder.bind(queue1).to(fanoutExchange);
    }
    @Bean
    public Queue queue2(){
        return new Queue("fanout.que2");
    }

    //绑定
    @Bean
    public Binding queueBinding2(Queue queue2,FanoutExchange fanoutExchange){
        return BindingBuilder.bind(queue2).to(fanoutExchange);
    }

//    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("jintao.direct");
    }

//    @Bean
    public Queue queue3(){
        return new Queue("direct.que3");
    }

//    @Bean
    public Queue queue4(){
        return new Queue("direct.que4");
    }

//    @Bean
    public Binding queueBinding3(Queue queue3,DirectExchange directExchange){
        return BindingBuilder.bind(queue3).to(directExchange).with("red");
    }

//    @Bean
    public Binding queueBinding4(Queue queue4,DirectExchange directExchange){
        return BindingBuilder.bind(queue4).to(directExchange).with("blue");
    }

}
