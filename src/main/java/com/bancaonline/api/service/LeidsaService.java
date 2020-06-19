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
public class LeidsaService {

    @Autowired
    private OperationsService operationsService;

    @Autowired
    private LotteryResultRepository lotteryResultRepository;

    /**
     * Update pega 3 mas results lottery result.
     *
     * @param date the date
     * @return the lottery result
     * @throws IOException the io exception
     */
    public LotteryResult updatePega3MasResults(String date) throws IOException {
        LotteryResult lotteryResult = null;

        if (date == null) {
            LotteryResult lastSavedLotteryResult = operationsService.verifyResult("00, 00, 00", 10L);
            lotteryResult = operationsService.UpdateResult(lastSavedLotteryResult, 10L);
        } else {
            lotteryResult = operationsService.UpdateResultByDate(10L, date);
        }
        return lotteryResult;
    }

    /**
     * Update quiniela leidsa results lottery result.
     *
     * @param date the date
     * @return the lottery result
     * @throws IOException the io exception
     */
    public LotteryResult updateQuinielaLeidsaResults(String date) throws IOException {
        LotteryResult lotteryResult = null;

        if (date == null) {
            LotteryResult lastSavedLotteryResult = operationsService.verifyResult("00, 00, 00", 12L);
            lotteryResult = operationsService.UpdateResult(lastSavedLotteryResult, 12L);
        } else {
            lotteryResult = operationsService.UpdateResultByDate(12L, date);
        }
        return lotteryResult;
    }

    /**
     * Update loto pool results lottery result.
     *
     * @param date the date
     * @return the lottery result
     * @throws IOException the io exception
     */
    public LotteryResult updateLotoPoolResults(String date) throws IOException {
        LotteryResult lotteryResult = null;

        if (date == null) {
            LotteryResult lastSavedLotteryResult = operationsService.verifyResult("00, 00, 00, 00, 00", 8L);
            lotteryResult = operationsService.UpdateResult(lastSavedLotteryResult, 8L);
        } else {
            lotteryResult = operationsService.UpdateResultByDate(8L, date);
        }
        return lotteryResult;
    }

    /**
     * Update super kino results lottery result.
     *
     * @param date the date
     * @return the lottery result
     * @throws IOException the io exception
     */
    public LotteryResult updateSuperKinoResults(String date) throws IOException {
        LotteryResult lotteryResult = null;

        if (date == null) {
            LotteryResult lastSavedLotteryResult = operationsService
                    .verifyResult("00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00", 9L);
            lotteryResult = operationsService.UpdateResult(lastSavedLotteryResult, 9L);
        } else {
            lotteryResult = operationsService.UpdateResultByDate(9L, date);
        }
        return lotteryResult;
    }

    /**
     * Update loto results lottery result.
     *
     * @param date the date
     * @return the lottery result
     * @throws IOException the io exception
     */
    public LotteryResult updateLotoResults(String date) throws IOException {
        LotteryResult lotteryResult = null;

        if (date == null) {
            LotteryResult lastSavedLotteryResult = operationsService.verifyResult("00, 00, 00, 00, 00, 00, 00, 00", 4L);
            lotteryResult = operationsService.UpdateResult(lastSavedLotteryResult, 4L);
        } else {
            lotteryResult = operationsService.UpdateResultByDate(4L, date);
        }
        return lotteryResult;
    }

    /**
     * Get pega 3 mas lottery result.
     *
     * @return the lottery result
     */
    public LotteryResult getPega3mas() {
        return lotteryResultRepository.findByLotteryTypeIdAndStatusId(10L, 1L);
    }

    /**
     * Get quiniela leidsa lottery result.
     *
     * @return the lottery result
     */
    public LotteryResult getQuinielaLeidsa() {
        return lotteryResultRepository.findByLotteryTypeIdAndStatusId(12L, 1L);
    }

    /**
     * Get loto pool lottery result.
     *
     * @return the lottery result
     */
    public LotteryResult getLotoPool() {
        return lotteryResultRepository.findByLotteryTypeIdAndStatusId(8L, 1L);
    }

    /**
     * Get super kino lottery result.
     *
     * @return the lottery result
     */
    public LotteryResult getSuperKino() {
        return lotteryResultRepository.findByLotteryTypeIdAndStatusId(9L, 1L);
    }

    /**
     * Get loto lottery result.
     *
     * @return the lottery result
     */
    public LotteryResult getLoto() {
        return lotteryResultRepository.findByLotteryTypeIdAndStatusId(4L, 1L);
    }

    /**
     * Gets loto bote.
     *
     * @throws IOException the io exception
     */
    public void updateLotoBote() throws IOException {
        operationsService.getLotoLeidsaBote();
    }


    /**
     * Update loto bote.
     *
     * @throws IOException the io exception
     */
    public void updateManualLotoBote() throws IOException {
        operationsService.getLotoLeidsaBote();
    }

}
