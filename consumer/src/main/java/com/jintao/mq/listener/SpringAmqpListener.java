package com.jintao.mq.listener;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class SpringAmqpListener {

    @RabbitListener(queues = "simple.queue")
    public void listenSimpleQueue1(String msg) throws InterruptedException {
        System.out.println("消费者1接受到simple.queue:"+msg);
        Thread.sleep(20);
    }

    @RabbitListener(queues = "simple.queue")
    public void listenSimpleQueue2(String msg) throws InterruptedException {
        System.err.println("消费者2接受到simple.queue:"+msg);
        Thread.sleep(200);
    }

    @RabbitListener(queues = "fanout.que1")
    public void listenFanoutQueue1(String msg){
        System.out.println("消费者接受到了fanoutQueue1的消息"+msg);
    }

    @RabbitListener(queues = "fanout.que2")
    public void listenFanoutQueue2(String msg){
        System.err.println("消费者接受到了fanoutQueue2的消息"+msg);
    }

//    @RabbitListener(queues = "direct.que3")
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.que3"),
            exchange = @Exchange(name = "jintao.direct",type = "direct"),
            key = "red"
    ))
    public void listenFanoutQueue3(String msg){
        System.out.println("消费者接受到了fanoutQueue3的消息"+msg);
    }

//    @RabbitListener(queues = "direct.que4")
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.que4"),
            exchange = @Exchange(name = "jintao.direct",type = ExchangeTypes.DIRECT),
            key = "blue"
    ))
    public void listenFanoutQueue4(String msg){
        System.err.println("消费者接受到了fanoutQueue4的消息"+msg);
    }
}
