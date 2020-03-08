package com.bancaonline.api.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bancaonline.api.model.ConsortiumToken;

@Repository
public interface ConsortiumTokenRepository extends CrudRepository<ConsortiumToken, Long>{
	
	Optional<ConsortiumToken> findByToken(String token);

}
