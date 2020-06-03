package com.bancaonline.api.service;

import java.io.IOException;

import com.bancaonline.api.model.LotteryResult;
import com.bancaonline.api.repository.LotteryResultRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Americana service.
 */
@Service
public class AmericanaService {

    @Autowired
    private OperationsService operationsService;

    @Autowired
    private LotteryResultRepository lotteryResultRepository;

    /**
     * Update new york 1230 results lottery result.
     *
     * @param date the date
     * @return the lottery result
     * @throws IOException the io exception
     */
    public LotteryResult updateNewYork1230Results(String date) throws IOException {
        LotteryResult lotteryResult = null;

        if (date == null) {
            LotteryResult lastSavedLotteryResult = operationsService.verifyResult("00, 00, 00", 24L);
            lotteryResult = operationsService.UpdateResult(lastSavedLotteryResult, 24L);
        } else {
            lotteryResult = operationsService.UpdateResultByDate(24L, date);
        }
        return lotteryResult;
    }

    /**
     * Update new york 730 results lottery result.
     *
     * @param date the date
     * @return the lottery result
     * @throws IOException the io exception
     */
    public LotteryResult updateNewYork730Results(String date) throws IOException {
        LotteryResult lotteryResult = null;

        if (date == null) {
            LotteryResult lastSavedLotteryResult = operationsService.verifyResult("00, 00, 00", 27L);
            lotteryResult = operationsService.UpdateResult(lastSavedLotteryResult, 27L);
        } else {
            lotteryResult = operationsService.UpdateResultByDate(27L, date);
        }
        return lotteryResult;
    }

    /**
     * Update mega millions result lottery result.
     *
     * @param date the date
     * @return the lottery result
     * @throws IOException the io exception
     */
    public LotteryResult updateMegaMillionsResult(String date) throws IOException {
        LotteryResult lotteryResult = null;

        if (date == null) {
            LotteryResult lastSavedLotteryResult = operationsService.verifyResult("00, 00, 00, 00, 00, 00, 00", 1L);
            lotteryResult = operationsService.UpdateResult(lastSavedLotteryResult, 1L);
        } else {
            lotteryResult = operationsService.UpdateResultByDate(1L, date);
        }
        return lotteryResult;
    }

    /**
     * Update power ball result lottery result.
     *
     * @param date the date
     * @return the lottery result
     * @throws IOException the io exception
     */
    public LotteryResult updatePowerBallResult(String date) throws IOException {
        LotteryResult lotteryResult = null;
        if (date == null) {
            LotteryResult lastSavedLotteryResult = operationsService.verifyResult("00, 00, 00, 00, 00, 00, 00", 2L);
            lotteryResult = operationsService.UpdateResult(lastSavedLotteryResult, 2L);
        } else {
            lotteryResult = operationsService.UpdateResultByDate(2L, date);
        }
        return lotteryResult;
    }

    /**
     * Get new york tarde lottery result.
     *
     * @return the lottery result
     */
    public LotteryResult getNewYorkTarde() {
        return lotteryResultRepository.findByLotteryTypeIdAndStatusId(24L, 1L);
    }

    /**
     * Get new york noche lottery result.
     *
     * @return the lottery result
     */
    public LotteryResult getNewYorkNoche() {
        return lotteryResultRepository.findByLotteryTypeIdAndStatusId(27L, 1L);
    }

    /**
     * Get mega millions lottery result.
     *
     * @return the lottery result
     */
    public LotteryResult getMegaMillions() {
        return lotteryResultRepository.findByLotteryTypeIdAndStatusId(1L, 1L);
    }

    /**
     * Get power ball lottery result.
     *
     * @return the lottery result
     */
    public LotteryResult getPowerBall() {
        return lotteryResultRepository.findByLotteryTypeIdAndStatusId(2L, 1L);
    }


    /**
     * Update power ball bote.
     *
     * @throws IOException the io exception
     */
    public void updatePowerBallBote() throws IOException {
        operationsService.getPowerBallBote();
    }

    /**
     * Update mega million bote.
     *
     * @throws IOException the io exception
     */
    public void updateMegaMillionBote() throws IOException {
        operationsService.getMegaMillionBote();
    }


    /**
     * Update manual meaga milion bote.
     *
     * @throws IOException the io exception
     */
    public void updateManualMeagaMilionBote() throws IOException {
        operationsService.getMegaMillionBote();
    }

    /**
     * Update manual power ball bote.
     *
     * @throws IOException the io exception
     */
    public void updateManualPowerBallBote() throws IOException {
        operationsService.getMegaMillionBote();
    }
}
