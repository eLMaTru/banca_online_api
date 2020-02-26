package com.bancaonline.api.service;

import java.io.IOException;

import com.bancaonline.api.model.LotteryResult;
import com.bancaonline.api.repository.LotteryResultRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Real service.
 */
@Service
public class RealService {

    @Autowired
    private OperationsService operationsService;

    @Autowired
    private LotteryResultRepository lotteryResultRepository;

    /**
     * Update loto real results lottery result.
     *
     * @return the lottery result
     * @throws IOException the io exception
     */
    public LotteryResult updateLotoRealResults(String date) throws IOException {
        LotteryResult lotteryResult = null;

        if (date == null) {
            LotteryResult lastSavedLotteryResult = operationsService.verifyResult("00, 00, 00, 00, 00, 00", 6L);
            lotteryResult = operationsService.UpdateResult(lastSavedLotteryResult, 6L);
        } else {
            lotteryResult = operationsService.UpdateResultByDate(6L, date);
        }
        return lotteryResult;
    }

    /**
     * Update quiniela real results lottery result.
     *
     * @return the lottery result
     * @throws IOException the io exception
     */
    public LotteryResult updateQuinielaRealResults(String date) throws IOException {
        LotteryResult lotteryResult = null;

        if (date == null) {
            LotteryResult lastSavedLotteryResult = operationsService.verifyResult("00, 00, 00", 21L);
            lotteryResult = operationsService.UpdateResult(lastSavedLotteryResult, 21L);
        } else {
            lotteryResult = operationsService.UpdateResultByDate(21L, date);
        }
        return lotteryResult;
    }

    /**
     * Get loto real lottery result.
     *
     * @return the lottery result
     */
    public LotteryResult getLotoReal() {
        return lotteryResultRepository.findByLotteryTypeIdAndStatusId(6L, 1L);
    }

    /**
     * Get quiniela real lottery result.
     *
     * @return the lottery result
     */
    public LotteryResult getQuinielaReal() {
        return lotteryResultRepository.findByLotteryTypeIdAndStatusId(21L, 1L);
    }

}
