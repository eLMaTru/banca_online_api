package com.bancaonline.api.service;

import java.util.List;

import com.bancaonline.api.model.Status;
import com.bancaonline.api.repository.StatusRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusService {

    @Autowired
    private StatusRepository statusRepository;

    public List<Status> findAll() {
        return statusRepository.findAll();
    }

}