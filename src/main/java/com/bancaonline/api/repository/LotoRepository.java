package com.bancaonline.api.repository;

import com.bancaonline.api.model.LotteryType;
import com.bancaonline.api.model.Status;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bancaonline.api.model.Loto;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface LotoRepository extends CrudRepository<Loto, String> {

    @Query("SELECT d FROM Loto d where d.lotteryType = :lotteryType and d.status = :status")
    Loto findByType(@Param("lotteryType") LotteryType lotteryType, @Param("status") Status status);

    @Transactional
    @Modifying
    @Query("update Loto loto set loto.status.id = :statusId where loto.lotteryType.id= :lotteryTypeId")
    void updateByLoteryType(@Param("lotteryTypeId") Long lotteryTypeId, @Param("statusId") Long statusId);

    @Query("SELECT d FROM Loto d where d.status.id = :status")
    List<Loto> findAllLottoByStatus(@Param("status") Long status);

}
