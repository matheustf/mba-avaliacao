package com.mbafiap.avaliacao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mbafiap.avaliacao.dtos.AvaliacaoDTO;
import com.mbafiap.avaliacao.exceptions.AvaliacaoException;
import com.mbafiap.avaliacao.service.AvaliacaoService;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {
	
	private AvaliacaoService avaliacaoService;

	@Autowired
	public AvaliacaoController(AvaliacaoService avaliacaoService) {
		this.avaliacaoService = avaliacaoService;
	}

	@GetMapping("")
	public ResponseEntity<List<AvaliacaoDTO>> buscarTodos() throws AvaliacaoException {
		
		List<AvaliacaoDTO> avaliacoesDTO = avaliacaoService.buscarTodos();

		return new ResponseEntity<List<AvaliacaoDTO>>(avaliacoesDTO, HttpStatus.OK);
	}


	@PostMapping("") 
	public ResponseEntity<AvaliacaoDTO> incluir(@RequestBody AvaliacaoDTO avaliacaoDTO) throws AvaliacaoException {
		AvaliacaoDTO responseAvaliacaoDTO = avaliacaoService.incluir(avaliacaoDTO);
		return new ResponseEntity<AvaliacaoDTO>(responseAvaliacaoDTO, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<AvaliacaoDTO> atualizar(@PathVariable(value = "id") String id, @RequestBody AvaliacaoDTO avaliacaoDTODetails) throws AvaliacaoException {
		
		AvaliacaoDTO avaliacaoDTO = avaliacaoService.atualizar(id, avaliacaoDTODetails);

		return new ResponseEntity<AvaliacaoDTO>(avaliacaoDTO, HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AvaliacaoDTO> consultar(@PathVariable(value = "id") String idAvaliacao) throws AvaliacaoException {
		
		AvaliacaoDTO avaliacaoDTO = avaliacaoService.consultar(idAvaliacao);

		return new ResponseEntity<AvaliacaoDTO>(avaliacaoDTO, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<AvaliacaoDTO> deletar(@PathVariable(value = "id") String id) throws AvaliacaoException {
		
		ResponseEntity<AvaliacaoDTO> response = avaliacaoService.deletar(id);
		
		return response;
	}

}