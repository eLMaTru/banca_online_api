package com.bancaonline.api.repository;

import javax.transaction.Transactional;

import com.bancaonline.api.model.LotteryType;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Lottery result repository.
 */
@Repository
public interface LotteryTypeRepository extends CrudRepository<LotteryType, Long> {

    /**
     * Find by id
     *
     * @param lotteryTypeId the lottery type ID
     * @return the lottery type
     */
    @Transactional
    LotteryType findById(Long lotteryTypeId);

}
