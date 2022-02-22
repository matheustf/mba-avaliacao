package com.mbafiap.avaliacao.rabbitmq;

import java.util.Date;

import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.stereotype.Component;

import com.mbafiap.avaliacao.model.Email;
import com.mbafiap.avaliacao.rabbitmq.config.RobbitMqConfig;


@Component
public class RabbitMQComponentImpl implements RabbitMQComponent{
	
	@Autowired
	private RabbitMessagingTemplate rabbitMessagingTemplate;
	
	@Autowired
	private MappingJackson2MessageConverter mappingJackson2MessageConverter;
	
	@Override
	public void sendEmail(Email email) {
		this.rabbitMessagingTemplate.setMessageConverter(this.mappingJackson2MessageConverter);

		System.out.println(new Date());
		rabbitMessagingTemplate.convertAndSend(RobbitMqConfig.ROUTING_EMAIL, email);
	    System.out.println("Is listener returned OK - Email::: ");
	}

}
