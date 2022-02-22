package com.mbafiap.avaliacao.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.mbafiap.avaliacao.dtos.AvaliacaoDTO;
import com.mbafiap.avaliacao.exceptions.AvaliacaoException;

public interface AvaliacaoService {
	
	AvaliacaoDTO consultar(String id) throws AvaliacaoException;
	
	AvaliacaoDTO incluir(AvaliacaoDTO avaliacaoDTO) throws AvaliacaoException;
	
	AvaliacaoDTO atualizar(String id, AvaliacaoDTO avaliacaoDTODetails) throws AvaliacaoException;
	
	ResponseEntity<AvaliacaoDTO> deletar(String id) throws AvaliacaoException;

	List<AvaliacaoDTO> buscarTodos() throws AvaliacaoException;

}
