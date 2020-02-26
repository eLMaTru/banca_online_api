package com.bancaonline.api.repository;

import com.bancaonline.api.model.Consortium;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Consortium result repository.
 */
@Repository
public interface ConsortiumRepository extends CrudRepository<Consortium, Long> {

}
