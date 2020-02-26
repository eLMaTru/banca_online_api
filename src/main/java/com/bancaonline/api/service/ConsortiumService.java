package com.bancaonline.api.service;

import com.bancaonline.api.model.Consortium;
import com.bancaonline.api.repository.ConsortiumRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Consortium Service
 */
@Service
public class ConsortiumService {

    /**
     * The constant logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsortiumService.class);

    @Autowired
    private ConsortiumRepository consortiumRepository;


    /**
     * Find by id consortium.
     *
     * @param id the id
     * @return the consortium
     */
    public Consortium findById(Long id){
        LOGGER.info("getConsortium was completed");
        return consortiumRepository.findOne(id);
    }

    /**
     * Find all list.
     *
     * @return the list
     */
    public List<Consortium> findAll(){

        List<Consortium> list = new ArrayList<Consortium>();
        Iterable<Consortium> consortiums =  consortiumRepository.findAll();
        consortiums.forEach(list::add);
        LOGGER.info("getAllConsortium was completed");
        return list;
    }


    /**
     * Save or update.
     *
     * @param consortium the consortium
     * @return the consortium
     */
    public Consortium saveOrUpdate(Consortium consortium){
        LOGGER.info("saveOrUpdate consortium was completed");
        return consortiumRepository.save(consortium);
    }

    /**
     * Delete.
     *
     * @param id the id
     */
    public void delete(Long id){
        LOGGER.info("delete consortium was completed");
        consortiumRepository.delete(id);
    }

}
