package com.bancaonline.api.repository;

import java.util.List;

import javax.transaction.Transactional;

import com.bancaonline.api.model.LotteryResult;
import com.bancaonline.api.model.LotteryType;
import com.bancaonline.api.model.Status;

import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * The interface Lottery result repository.
 */
@Repository
public interface LotteryResultRepository extends CrudRepository<LotteryResult, String> {

    /**
     * Find by lottery type lottery result.
     *
     * @param lotteryType the lottery type
     * @return the lottery result
     */
    @Transactional()
    LotteryResult findByLotteryType(LotteryType lotteryType);
    
    @Transactional
 
    List<LotteryResult> findByLotteryTypeId(long typeId);

    /**
     * Find by lottery type id and status id lottery result.
     *
     * @param lotteryTypeId the lottery type id
     * @param statusId      the status id
     * @return the lottery result
     */
    LotteryResult findByLotteryTypeIdAndStatusId(Long lotteryTypeId, Long statusId);

    /**
     * Find by lottery type id and status list.
     *
     * @param lotteryTypeId the lottery type id
     * @param status        the status
     * @return the list
     */
    List<LotteryResult> findByLotteryTypeIdAndStatus(Long lotteryTypeId, Status status);

    /**
     * Find by lottery type id and status id is not in list.
     *
     * @param lotteryTypeId the lottery type id
     * @param statusId      the status id
     * @return the list
     */
    List<LotteryResult> findByLotteryTypeIdAndStatusIdIsNotIn(Long lotteryTypeId, Long statusId);

    /**
     * Count lottery result by lottery type id.
     *
     * @param lotteryTypeId the lottery type id
     * @return the long
     */
    @Transactional
    @Query("SELECT COUNT(lotteryResult.id) FROM LotteryResult lotteryResult where lotteryResult.lotteryType.id = :lotteryTypeId")
    long countLotteryResultByLotteryTypeId(@Param("lotteryTypeId") Long lotteryTypeId);

    /**
     * Count lottery result by status id.
     *
     * @param statusId the lottery type id
     * @return the long
     */
    @Transactional
    @Query("SELECT COUNT(lotteryResult.id) FROM LotteryResult lotteryResult where lotteryResult.lotteryType.id = :lotteryTypeId and "+
    "lotteryResult.status.id = :statusId")
    long countLotteryResultByLotteryTypeIdAndStatusId(@Param("lotteryTypeId") Long lotteryTypeId, @Param("statusId") Long statusId);

    /**
     * Update status int.
     *
     * @param lotteryResultId the lottery result id
     * @param statusId        the status id
     * @return the int
     */
    @Transactional
    @Modifying
    @Query("update LotteryResult lotteryResult set lotteryResult.status.id = :statusId where lotteryResult.id = :lotteryResultId")
    int updateStatus(@Param("lotteryResultId") String lotteryResultId, @Param("statusId") Long statusId);

    /**
     * Find all lottery enabled hash map.
     *
     * @param statusId the status id
     * @return the hash map
     */
    @Transactional
    @Query("SELECT a.lotteryType.name,a.winningNumbers,a.drawingDate FROM LotteryResult a where a.status.id = :statusId")
    List<Object[]> findAllLotteryByStatus(@Param("statusId") Long statusId);

    @Query("SELECT a.lotteryType.name,a.winningNumbers,a.createdDate,a.drawingDate FROM LotteryResult a WHERE a.drawingDate BETWEEN :date1 AND :date2 AND ( a.status.id = 1 Or a.status.id = 2) ORDER BY a.createdDate DESC ")
    List<Object[]> findDayOfWeek(@Param("date1") String date1, @Param("date2") String date2);

    @Transactional
    @Modifying
    @Query("DELETE FROM LotteryResult a WHERE a.drawingDate = :date")
    void deleteByDate(@Param("date") String date);

    /**
     * Find by status id list.
     *
     * @param statusId the status id
     * @return the list
     */
    List<LotteryResult> findByStatusId(long statusId);

    /**
     * Format leidsa date string.
     *
     * @param date the date
     * @return the string
     */
    default String formatLeidsaDate(String date) {

        date = date.toLowerCase();
        int i = 0;
        // Skip past non-digits.
        while (i < date.length() && !Character.isDigit(date.charAt(i))) {
            ++i;
        }

        date = date.substring(i, date.length());
        date = date.replace("del", "");
        date = date.replace("de", "");
        date = date.replace(".", "");
        date = date.replace("  ", "-");

        if (date.contains("enero")) {
            date = date.replace("enero", "01");
        } else if (date.contains("febrero")) {
            date = date.replace("febrero", "02");
        } else if (date.contains("marzo")) {
            date = date.replace("marzo", "03");
        } else if (date.contains("abril")) {
            date = date.replace("abril", "04");
        } else if (date.contains("mayo")) {
            date = date.replace("mayo", "05");
        } else if (date.contains("junio")) {
            date = date.replace("junio", "06");
        } else if (date.contains("julio")) {
            date = date.replace("julio", "07");
        } else if (date.contains("agosto")) {
            date = date.replace("agosto", "08");
        } else if (date.contains("septiembre")) {
            date = date.replace("septiembre", "09");
        } else if (date.contains("octubre")) {
            date = date.replace("octubre", "10");
        } else if (date.contains("noviembre")) {
            date = date.replace("noviembre", "11");
        } else if (date.contains("diciembre")) {
            date = date.replace("diciembre", "12");
        }

        return date;
    }

}
