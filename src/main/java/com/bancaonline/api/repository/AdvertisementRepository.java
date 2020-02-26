package com.bancaonline.api.repository;

import java.util.List;

import com.bancaonline.api.model.Advertisement;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Advertisement result repository.
 */
@Repository
public interface AdvertisementRepository extends CrudRepository<Advertisement, Long> {

    List<Advertisement> findByStatusId(Long statusId);
    List<Advertisement> findByStatusIdAndConsortiumId(Long statusId, Long consortiumId);

}
