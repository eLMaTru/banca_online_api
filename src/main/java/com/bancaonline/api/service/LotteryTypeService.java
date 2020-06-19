package com.bancaonline.api.service;

import java.util.List;

import com.bancaonline.api.model.LotteryType;
import com.bancaonline.api.repository.LotteryTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LotteryTypeService {

  @Autowired
  private LotteryTypeRepository lotteryTypeRepository;

  public List<LotteryType> findAll() {

    List<LotteryType> results = lotteryTypeRepository.findAll();

    return results;
  }

}