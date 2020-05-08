package com.bancaonline.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bancaonline.api.model.Loto;

@Repository
public interface LotoRepository extends CrudRepository<Loto, String> {

}
