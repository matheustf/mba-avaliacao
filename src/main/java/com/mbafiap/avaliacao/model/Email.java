package com.mbafiap.avaliacao.model;
import java.util.HashMap;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Email {

	private String nomeDoUsuario;

	private String emailDestinatario;

	private String tipoDeEmail;

	private HashMap<String, String> contentReplace;

}