package com.bancaonline.api.repository;

import com.bancaonline.api.model.CurrencyResult;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Currency result repository.
 */
@Repository
public interface CurrencyResultRepository extends CrudRepository<CurrencyResult, String> {

    /**
     * Find day of week list.
     *
     * @param date the date
     * @return the list
     */
    @Query("SELECT c FROM CurrencyResult c WHERE c.createdDate = :date OR c.createdDate = :yesterday ORDER BY c.currencyType")
    List<CurrencyResult> findCurrency(@Param("date") String date, @Param("yesterday") String yesterday);
}
