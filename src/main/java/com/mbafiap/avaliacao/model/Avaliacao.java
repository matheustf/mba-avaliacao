package com.mbafiap.avaliacao.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Avaliacao implements Serializable{

	private static final long serialVersionUID = -3588012881987809662L;

	@Id
	private String id;
	
	private String email;
	
	private String nomeCliente;
	
	private String idProduto;
	
	private String idCompra;
	
	private int notaDeSatisfacao;
	
	private String titulo;

	private String descricao;
	
	private String dataDaAvaliacao;
	
	public Avaliacao update(Avaliacao avaliacao, Avaliacao detailsAvaliacao) {
		
		avaliacao.setEmail(detailsAvaliacao.getEmail());
		avaliacao.setNomeCliente(detailsAvaliacao.getNomeCliente());
		avaliacao.setIdProduto(detailsAvaliacao.getIdProduto());
		avaliacao.setIdCompra(detailsAvaliacao.getIdCompra());
		avaliacao.setNotaDeSatisfacao(avaliacao.getNotaDeSatisfacao());
		avaliacao.setTitulo(detailsAvaliacao.getTitulo());
		avaliacao.setDescricao(detailsAvaliacao.getDescricao());
		avaliacao.setDataDaAvaliacao(detailsAvaliacao.getDataDaAvaliacao());
		
		return avaliacao;
	}
	
}
