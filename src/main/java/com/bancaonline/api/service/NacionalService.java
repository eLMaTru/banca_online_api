package com.bancaonline.api.service;

import java.io.IOException;

import com.bancaonline.api.model.LotteryResult;
import com.bancaonline.api.repository.LotteryResultRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Leidsa service.
 */
@Service
public class NacionalService {

    @Autowired
    private OperationsService operationsService;

    @Autowired
    private LotteryResultRepository lotteryResultRepository;

    /**
     * Update gana mas results lottery result.
     *
     * @return the lottery result
     * @throws IOException the io exception
     */
    public LotteryResult updateGanaMasResults(String date) throws IOException {
        LotteryResult lotteryResult = null;

        if (date == null) {
            LotteryResult lastSavedLotteryResult = operationsService.verifyResult("00, 00, 00", 33L);
            lotteryResult = operationsService.UpdateResult(lastSavedLotteryResult, 33L);
        } else {
            lotteryResult = operationsService.UpdateResultByDate(33L, date);
        }
        return lotteryResult;
    }

    /**
     * Update quiniela nacional results lottery result.
     *
     * @return the lottery result
     * @throws IOException the io exception
     */
    public LotteryResult updateQuinielaNacionalResults(String date) throws IOException {
        LotteryResult lotteryResult = null;

        if (date == null) {
            LotteryResult lastSavedLotteryResult = operationsService.verifyResult("00, 00, 00", 15L);
            lotteryResult = operationsService.UpdateResult(lastSavedLotteryResult, 15L);
        } else {
            lotteryResult = operationsService.UpdateResultByDate(15L, date);
        }
        return lotteryResult;
    }

    /**
     * Update juega mas gana mas results lottery result.
     *
     * @return the lottery result
     * @throws IOException the io exception
     */
    public LotteryResult updateJuegaMasGanaMasResults(String date) throws IOException {
        LotteryResult lotteryResult = null;

        if (date == null) {
            LotteryResult lastSavedLotteryResult = operationsService.verifyResult("00, 00, 00, 00, 00", 34L);
            lotteryResult = operationsService.UpdateResult(lastSavedLotteryResult, 34L);
        } else {
            lotteryResult = operationsService.UpdateResultByDate(34L, date);
        }
        return lotteryResult;
    }

    /**
     * Get gana mas lottery result.
     *
     * @return the lottery result
     */
    public LotteryResult getGanaMas() {
        return lotteryResultRepository.findByLotteryTypeIdAndStatusId(33L, 1L);
    }

    /**
     * Get quiniela nacional lottery result.
     *
     * @return the lottery result
     */
    public LotteryResult getQuinielaNacional() {
        return lotteryResultRepository.findByLotteryTypeIdAndStatusId(15L, 1L);
    }

    /**
     * Get juega mas gana mas lottery result.
     *
     * @return the lottery result
     */
    public LotteryResult getJuegaMasGanaMas() {
        return lotteryResultRepository.findByLotteryTypeIdAndStatusId(34L, 1L);
    }

}
