package com.bancaonline.api.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bancaonline.api.model.Consortium;

/**
 * The interface Consortium result repository.
 */
@Repository
public interface ConsortiumRepository extends CrudRepository<Consortium, Long> {
	
	Optional<Consortium> findByName(String name);

}
