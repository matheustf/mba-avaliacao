package com.mbafiap.avaliacao.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AvaliacaoDTO {

	private String id;
	
	private String email;
	
	private String nomeCliente;
	
	private String idProduto;
	
	private String idCompra;
	
	private int notaDeSatisfacao;
	
	private String titulo;

	private String descricao;
	
	private String dataDaAvaliacao;
	
}