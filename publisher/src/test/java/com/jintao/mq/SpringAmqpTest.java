package com.jintao.mq;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringAmqpTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSendMessage() throws InterruptedException {
        String queueName="simple.queue";
        String message="nihao";
        for (int i = 0; i < 50; i++) {
            rabbitTemplate.convertAndSend(queueName,i+message);
            Thread.sleep(20);
        }
    }

    @Test
    public void testSendFanout() {
        String exchange="jintao.fanout";
        String msd="hello";
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend(exchange,"",msd+i);
        }
    }

    @Test
    public void testSendDirect() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String exchange="jintao.direct";
        HashMap<String, String> map = new HashMap<>();
        map.put("name","jintao");
        map.put("age","26");
        String msg=objectMapper.writeValueAsString(map);
        rabbitTemplate.convertAndSend(exchange,"red",map);

//        for (int i = 0; i < 20; i++) {
//            if (i%2==0){
//                rabbitTemplate.convertAndSend(exchange,"red",msg+"red");
//            }else {
//                rabbitTemplate.convertAndSend(exchange,"blue",msg+"blue");
//            }
//        }
    }
}
