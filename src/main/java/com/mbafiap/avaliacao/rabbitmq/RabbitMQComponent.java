package com.mbafiap.avaliacao.rabbitmq;

import com.mbafiap.avaliacao.model.Avaliacao;
import com.mbafiap.avaliacao.model.Email;

public interface RabbitMQComponent {

	void sendEmail(Email email);

}
