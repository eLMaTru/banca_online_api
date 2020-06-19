package com.bancaonline.api.repository;

import java.util.List;

import com.bancaonline.api.model.Status;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends CrudRepository<Status, Long> {

    List<Status> findAll();
}