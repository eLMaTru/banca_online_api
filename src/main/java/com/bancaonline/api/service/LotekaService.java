package com.bancaonline.api.service;

import java.io.IOException;

import com.bancaonline.api.model.LotteryResult;
import com.bancaonline.api.repository.LotteryResultRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Loteka service.
 */
@Service
public class LotekaService {

    @Autowired
    private OperationsService operationsService;

    @Autowired
    private LotteryResultRepository lotteryResultRepository;

    /**
     * Update mega loto result lottery result.
     *
     * @return the lottery result
     * @throws IOException the io exception
     */
    public LotteryResult updateMegaLotoResult(String date) throws IOException {
        LotteryResult lotteryResult = null;

        if (date == null) {
            LotteryResult lastSavedLotteryResult = operationsService.verifyResult("00, 00, 00, 00, 00, 00", 7L);
            lotteryResult = operationsService.UpdateResult(lastSavedLotteryResult, 7L);
        } else {
            lotteryResult = operationsService.UpdateResultByDate(7L, date);
        }
        return lotteryResult;
    }

    /**
     * Update mega chance results lottery result.
     *
     * @return the lottery result
     * @throws IOException the io exception
     */
    public LotteryResult updateMegaChanceResults(String date) throws IOException {
        LotteryResult lotteryResult = null;

        if (date == null) {
            LotteryResult lastSavedLotteryResult = operationsService.verifyResult("00, 00, 00, 00, 00", 32L);
            lotteryResult = operationsService.UpdateResult(lastSavedLotteryResult, 32L);
        } else {
            lotteryResult = operationsService.UpdateResultByDate(32L, date);
        }
        return lotteryResult;
    }

    /**
     * Update quiniela loteka results lottery result.
     *
     * @return the lottery result
     * @throws IOException the io exception
     */
    public LotteryResult updateQuinielaLotekaResults(String date) throws IOException {
        LotteryResult lotteryResult = null;
        if (date == null) {
            LotteryResult lastSavedLotteryResult = operationsService.verifyResult("00, 00, 00", 30L);
            lotteryResult = operationsService.UpdateResult(lastSavedLotteryResult, 30L);
        } else {
            lotteryResult = operationsService.UpdateResultByDate(30L, date);
        }
        return lotteryResult;
    }

    /**
     * Get quiniela loteka lottery result.
     *
     * @return the lottery result
     */
    public LotteryResult getQuinielaLoteka() {
        return lotteryResultRepository.findByLotteryTypeIdAndStatusId(30L, 1L);
    }

    /**
     * Get mega chance lottery result.
     *
     * @return the lottery result
     */
    public LotteryResult getMegaChance() {
        return lotteryResultRepository.findByLotteryTypeIdAndStatusId(32L, 1L);
    }

    /**
     * Get mega loto lottery result.
     *
     * @return the lottery result
     */
    public LotteryResult getMegaLoto() {
        return lotteryResultRepository.findByLotteryTypeIdAndStatusId(7L, 1L);
    }

}
