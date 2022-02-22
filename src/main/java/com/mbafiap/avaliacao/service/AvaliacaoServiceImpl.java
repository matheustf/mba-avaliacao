package com.mbafiap.avaliacao.service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.gson.reflect.TypeToken;
import com.mbafiap.avaliacao.component.SendEmailComponent;
import com.mbafiap.avaliacao.consts.Constants;
import com.mbafiap.avaliacao.dtos.AvaliacaoDTO;
import com.mbafiap.avaliacao.exceptions.AvaliacaoException;
import com.mbafiap.avaliacao.model.Avaliacao;
import com.mbafiap.avaliacao.repository.AvaliacaoRepository;
import com.mbafiap.avaliacao.utils.Util;

@Service
public class AvaliacaoServiceImpl implements AvaliacaoService {

	private final AvaliacaoRepository avaliacaoRepository;
	
	private final SendEmailComponent sendEmailComponent;
	
	@Autowired
	public AvaliacaoServiceImpl(AvaliacaoRepository avaliacaoRepository, SendEmailComponent sendEmailComponent) {
		this.avaliacaoRepository = avaliacaoRepository;
		this.sendEmailComponent = sendEmailComponent;
	}

	@Override
	public AvaliacaoDTO consultar(String id) throws AvaliacaoException {
		
		Optional<Avaliacao> optional = avaliacaoRepository.findById(id);
		Avaliacao avaliacao = validarAvaliacao(optional);
		
		AvaliacaoDTO avaliacaoDTO = modelMapper().map(avaliacao, AvaliacaoDTO.class);
		
		return avaliacaoDTO;
	}

	@Override
	public List<AvaliacaoDTO> buscarTodos() {

		List<Avaliacao> avaliacoes = (List<Avaliacao>) avaliacaoRepository.findAll();

		Type listType = new TypeToken<List<AvaliacaoDTO>>(){}.getType();
		List<AvaliacaoDTO> avaliacoesDTO = modelMapper().map(avaliacoes, listType);

		return avaliacoesDTO;
	}

	@Override
	public AvaliacaoDTO incluir(AvaliacaoDTO avaliacaoDTO) throws AvaliacaoException {
		Avaliacao avaliacao = modelMapper().map(avaliacaoDTO, Avaliacao.class);
		
		avaliacao.setDataDaAvaliacao(Util.dataNow());
		
		avaliacao = avaliacaoRepository.save(avaliacao);
		sendEmailComponent.sendEmail(avaliacao);
		
		return modelMapper().map(avaliacao, AvaliacaoDTO.class);
	}

	@Override
	public AvaliacaoDTO atualizar(String id, AvaliacaoDTO avaliacaoDTODetails) throws AvaliacaoException {
		
		Optional<Avaliacao> optional = avaliacaoRepository.findById(id);
		Avaliacao avaliacao = validarAvaliacao(optional);
		
		Avaliacao avaliacaoDetails = modelMapper().map(avaliacaoDTODetails, Avaliacao.class);
		
		avaliacao = avaliacao.update(avaliacao, avaliacaoDetails);

		avaliacao.setDataDaAvaliacao(Util.dataNow());
		
		avaliacao = avaliacaoRepository.save(avaliacao);

		AvaliacaoDTO avaliacaoDTO = modelMapper().map(avaliacao, AvaliacaoDTO.class);

		return avaliacaoDTO;
	}

	@Override
	public ResponseEntity<AvaliacaoDTO> deletar(String id) throws AvaliacaoException {
		
		Optional<Avaliacao> optional = avaliacaoRepository.findById(id);
		validarAvaliacao(optional);
		
		avaliacaoRepository.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	private Avaliacao validarAvaliacao(Optional<Avaliacao> optional) throws AvaliacaoException {
		return Optional.ofNullable(optional).get()
		.orElseThrow(() -> new AvaliacaoException(HttpStatus.NOT_FOUND, Constants.ITEM_NOT_FOUND));
	}
	
}
