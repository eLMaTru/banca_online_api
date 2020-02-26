package com.bancaonline.api.service;

import java.io.IOException;

import com.bancaonline.api.model.LotteryResult;
import com.bancaonline.api.repository.LotteryResultRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type La primera service.
 */
@Service
public class LaPrimeraService {

    @Autowired
    private OperationsService operationsService;

    @Autowired
    private LotteryResultRepository lotteryResultRepository;

    /**
     * La primera quiniela results lottery result.
     *
     * @return the lottery result
     * @throws IOException the io exception
     */
    public LotteryResult updateLaPrimeraQuinielaResults(String date) throws IOException {
        LotteryResult lotteryResult = null;

        if (date == null) {
            LotteryResult lastSavedLotteryResult = operationsService.verifyResult("00, 00, 00", 35L);
            lotteryResult = operationsService.UpdateResult(lastSavedLotteryResult, 35L);
        } else {
            lotteryResult = operationsService.UpdateResultByDate(35L, date);
        }
        return lotteryResult;
    }

    /**
     * Get la primera quiniela lottery result.
     *
     * @return the lottery result
     */
    public LotteryResult getLaPrimeraQuiniela() {
        return lotteryResultRepository.findByLotteryTypeIdAndStatusId(35L, 1L);
    }

}
