package com.mbafiap.avaliacao.component;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.mbafiap.avaliacao.enums.TipoDeEmail;
import com.mbafiap.avaliacao.model.Avaliacao;
import com.mbafiap.avaliacao.model.Email;
import com.mbafiap.avaliacao.rabbitmq.RabbitMQComponent;

@Component
public class SendEmailComponent {
	
	RabbitMQComponent rabbitMQComponent;
	
	@Autowired
	public SendEmailComponent(RabbitMQComponent rabbitMQComponent) {
		this.rabbitMQComponent = rabbitMQComponent;
	}
	
	public void sendEmail(Avaliacao avaliacao) {
		System.out.println("Send Email -> Cliente: " + avaliacao.getNomeCliente() + "Email: " + avaliacao.getEmail());
		
		HashMap<String, String> contentReplace = new HashMap<>();
		contentReplace.put("**usuario**", avaliacao.getNomeCliente());
		
		Email email = Email.builder()
				.emailDestinatario(avaliacao.getEmail())
				.nomeDoUsuario(avaliacao.getNomeCliente())
				.tipoDeEmail(TipoDeEmail.OBRIGADO_AVALIACAO.name())
				.contentReplace(contentReplace)
				.build();
		try {
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(email);
		System.out.println(json);
		}catch (Exception e) {
			// TODO: handle exception
		}
		rabbitMQComponent.sendEmail(email);
	}

}