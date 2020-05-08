package com.bancaonline.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bancaonline.api.repository.LotoRepository;

@Service
public class LotoService {

	
	@Autowired
	LotoRepository lotoRepository;
	
}
