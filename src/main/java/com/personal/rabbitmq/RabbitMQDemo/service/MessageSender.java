package com.personal.rabbitmq.RabbitMQDemo.service;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personal.rabbitmq.RabbitMQDemo.config.RabbitConfig;
import com.personal.rabbitmq.RabbitMQDemo.vo.Message;

@Service
public class MessageSender {
	 
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
    public MessageSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
	
	public void sendMessage(Message message) {
		System.out.println("Message sending.");
		this.rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_MESSAGE, RabbitConfig.QUEUE_MESSAGE, message);
		
//		rabbitTemplate.convertAndSend("exchange", "routingKey", "message", new MessagePostProcessor() {
//			
//			@Override
//			public org.springframework.amqp.core.Message postProcessMessage(org.springframework.amqp.core.Message message)
//					throws AmqpException {
//				message.getMessageProperties().setPriority(5);
//		        return message;
//			}
//		}); 
	}
}
