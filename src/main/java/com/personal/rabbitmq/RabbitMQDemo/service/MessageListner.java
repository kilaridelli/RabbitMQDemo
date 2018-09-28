package com.personal.rabbitmq.RabbitMQDemo.service;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import com.personal.rabbitmq.RabbitMQDemo.config.RabbitConfig;
import com.personal.rabbitmq.RabbitMQDemo.vo.Message;
import com.rabbitmq.client.Channel;

@Component
public class MessageListner {

	private final CountDownLatch latch = new CountDownLatch(1);

	@Profile("Consumer")
	@RabbitListener(queues = RabbitConfig.QUEUE_MESSAGE)
	public void receiveMessage(Message payload, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag)
			throws IOException {
		try {
			System.out.println("Received payload" + payload);
			// Teradata
			// SQL 			
			channel.basicAck(deliveryTag, false);
			latch.countDown();
		} catch (Throwable ex) {
			System.out.println("Exception caught..");
			channel.basicReject(deliveryTag, true);
		}
		// Email
	}
}
