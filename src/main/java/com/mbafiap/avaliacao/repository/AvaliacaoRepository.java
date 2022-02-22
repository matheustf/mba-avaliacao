package com.mbafiap.avaliacao.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mbafiap.avaliacao.model.Avaliacao;


@Repository
public interface AvaliacaoRepository extends MongoRepository<Avaliacao, String> {

	Optional<Avaliacao> findById(String idAvaliacao);

}
