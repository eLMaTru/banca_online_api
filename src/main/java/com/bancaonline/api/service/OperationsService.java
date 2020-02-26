package com.bancaonline.api.service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.bancaonline.api.model.LotteryResult;
import com.bancaonline.api.model.LotteryType;
import com.bancaonline.api.model.Status;
import com.bancaonline.api.repository.LotteryResultRepository;
import com.bancaonline.api.repository.LotteryTypeRepository;
import com.bancaonline.api.util.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type OperationsService
 */
@Service
public class OperationsService {

    /**
     * The constant logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(OperationsService.class);

    @Autowired
    private LotteryResultRepository lotteryResultRepository;

    @Autowired
    LotteryTypeRepository lotteryTypeRepository;

    @Autowired
    private LotteryResultScrapingService lotteryResultScrapingService;

    @Autowired
    private EmailSender emailSender;

    /**
     * Verify result lottery result.
     *
     * @param result      the result
     * @param LotteryType the lottery type
     * @return the lottery result
     */
    public LotteryResult verifyResult(String result, Long lotteryType) {

        if (result == null)
            throw new IllegalArgumentException("result can not be null");

        if (lotteryType == null)
            throw new IllegalArgumentException("lotteryType can not be null");

        LotteryResult lastSavedLotteryResult = lotteryResultRepository.findByLotteryTypeIdAndStatusId(lotteryType,
                Status.Type.ENABLED.getId());

        if (lastSavedLotteryResult == null) {
            lastSavedLotteryResult = new LotteryResult();
            lastSavedLotteryResult.setWinningNumbers(result);
            lastSavedLotteryResult.setCreatedDate(LocalDateTime.now());
            lastSavedLotteryResult.setLotteryType(new LotteryType(lotteryType));
            lastSavedLotteryResult.setDrawingDate(LocalDate.now().minusDays(3).toString());
            lastSavedLotteryResult.setStatus(Status.Type.ENABLED.toStatus());

            lastSavedLotteryResult = lotteryResultRepository.save(lastSavedLotteryResult);

        } else {
            lastSavedLotteryResult.setLotteryType(new LotteryType(lotteryType));
        }

        return lastSavedLotteryResult;
    }

    /**
     * Update result lottery result.
     *
     * @param lastSavedLotteryResult the last saved lottery result
     * @param lotteryType            the lottery type
     * @return the lottery result
     * @throws IOException the io exception
     */
    public LotteryResult UpdateResult(LotteryResult lastSavedLotteryResult, Long lotteryTypeId) throws IOException {
        if (lastSavedLotteryResult == null)
            throw new IllegalArgumentException("lastSavedLotteryResult result can not be null");

        if (lotteryTypeId == null)
            throw new IllegalArgumentException("lotteryType result can not be null");

        String drawDate = "";
        String resultsToString = "";

        LotteryResult lotteryResult = new LotteryResult();
        List<String> results = new ArrayList<>();

        long time = 0;

        int updated = 0;
        int retry = 0;

        if (lastSavedLotteryResult != null && lotteryTypeId != null
                && !lastSavedLotteryResult.getDrawingDate().equals(LocalDate.now().toString())) {

            do {
                results = getScrapingByLotteryType(lotteryTypeId, null);
                drawDate = results.get(results.size() - 1);

                if (!validateScrapingData(results, drawDate) || lastSavedLotteryResult.getDrawingDate().equals(drawDate)
                        || Utils.buildStringResult(results).equals("fail")) {
                    time = 300000L;
                } else {
                    time = 0L;
                    resultsToString = "[winnigNumbers=" + Utils.buildStringResult(results) + " - drawingDate="
                            + drawDate + "]";
                }
                try {
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                LOGGER.info(
                        "Lottery type id: " + lotteryTypeRepository.findById(lotteryTypeId).getName() + " Reintento: "
                                + (retry + 1) + " Resultados: " + drawDate + " = " + Utils.buildStringResult(results));

                if (retry == 15) {
                    break;
                }
                retry++;
            } while (Utils.buildStringResult(results).equals("fail")
                    || lastSavedLotteryResult.getDrawingDate().equals(drawDate)
                    || lastSavedLotteryResult.toString().equals(resultsToString)
                    || Utils.isCorrectResultLength(lastSavedLotteryResult.getWinningNumbers(), results));

            LOGGER.info("Fin de intentos");

            if (!lastSavedLotteryResult.getDrawingDate().equals(results.get(results.size() - 1))) {
                updated = lotteryResultRepository.updateStatus(lastSavedLotteryResult.getId(),
                        Status.Type.DISABLED.getId());
            } else {
                String msj = "No fue posible actualizar automaticamente los resultados de la loteria: "
                        + lotteryTypeRepository.findById(lotteryTypeId).getName() + " en " + (retry + 1)
                        + " intentos con intervalos de " + (time / 60000) + " minutos para el dia . " + LocalDate.now()
                        + "Por favor actualizar manualmente";

                LOGGER.error(msj);
                sendEmailFailUpdatingResults(retry, time, lastSavedLotteryResult, lotteryTypeId);

            }
        }

        if (updated == 1 && results != null && results.size() > 0 && !Utils.buildStringResult(results).equals("fail")) {
            lotteryResult.setLotteryType(new LotteryType(lotteryTypeId));
            lotteryResult.setWinningNumbers(Utils.buildStringResult(results));
            lotteryResult.setStatus(Status.Type.ENABLED.toStatus());
            lotteryResult.setDrawingDate(drawDate);
            lotteryResult.setCreatedDate(LocalDateTime.now());
            lotteryResult = lotteryResultRepository.save(lotteryResult);

        } else if (results != null && results.size() > 0) {
            lotteryResult.setLotteryType(new LotteryType(lotteryTypeId));
            lotteryResult.setWinningNumbers(Utils.buildStringResult(results));
            lotteryResult.setStatus(Status.Type.ENABLED.toStatus());
            lotteryResult.setDrawingDate(results.get(results.size() - 1));
            lotteryResult.setCreatedDate(LocalDateTime.now());

        }

        if (countLotteryResultByLotteryTypeId(lotteryTypeId) == 0 && !lotteryResult.getWinningNumbers().isEmpty()) {
            lotteryResult = lotteryResultRepository.save(lotteryResult);
        }

        if (lotteryResultRepository.countLotteryResultByLotteryTypeIdAndStatusId(lotteryTypeId,
                Status.Type.DELETED.getId()) == 0 && updated == 1) {
            LotteryResult defaultLotteryResult = lotteryResultRepository.findByLotteryTypeIdAndStatusId(lotteryTypeId,
                    Status.Type.DISABLED.getId());
            lotteryResultRepository.updateStatus(defaultLotteryResult.getId(), Status.Type.DELETED.getId());
        }

        return lotteryResult;

    }

    /**
     * Update All lotteries results.
     *
     * @param lastSavedLotteryResult the last saved lottery result
     * @param lotteryType            the lottery type
     * @return the lottery result
     * @throws IOException the io exception
     */
    public LotteryResult UpdateResultByDate(Long lotteryTypeId, String date) throws IOException {


        if (lotteryTypeId == null)
            throw new IllegalArgumentException("lotteryType result can not be null");

        String drawDate = "";
        String resultsToString = "";

        LotteryResult lotteryResult = new LotteryResult();
        List<String> results = new ArrayList<>();

        if (lotteryTypeId != null) {

            results = getScrapingByLotteryType(lotteryTypeId, date);
            drawDate = results.get(results.size() - 1);

            if (!validateScrapingData(results, drawDate) || Utils.buildStringResult(results).equals("fail")) {
            } else {
                resultsToString = "[winnigNumbers=" + Utils.buildStringResult(results) + " - drawingDate=" + drawDate
                        + "]";
                LOGGER.info(resultsToString);
            }

        }

        if (results != null && results.size() > 0 && !Utils.buildStringResult(results).equals("fail")) {
            lotteryResult.setLotteryType(new LotteryType(lotteryTypeId));
            lotteryResult.setWinningNumbers(Utils.buildStringResult(results));
            lotteryResult.setStatus(Status.Type.DISABLED.toStatus());
            lotteryResult.setDrawingDate(drawDate);

            String strDateTime = drawDate + " 11:30:22";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime dateTime = LocalDateTime.parse(strDateTime, formatter);
            lotteryResult.setCreatedDate(dateTime);
            lotteryResult = lotteryResultRepository.save(lotteryResult);

        }

        return lotteryResult;

    }

    private List<String> getScrapingByLotteryType(long lotteryType, String date) throws IOException {

        List<String> results = new ArrayList<>();
        switch ((int) lotteryType) {
        case 1:
            results = lotteryResultScrapingService.megaMillionsWebScrapingResult(date);
            break;
        case 2:
            results = lotteryResultScrapingService.powerBallWebScrapingResult(date);
            break;
        case 4:
            results = lotteryResultScrapingService.lotomasScrapingResult(date);
            break;
        case 6:
            results = lotteryResultScrapingService.lotoRealScrapingResult(date);
            break;
        case 7:
            results = lotteryResultScrapingService.megaLottoScrapingResult(date);
            break;
        case 8:
            results = lotteryResultScrapingService.lotoPoolWebScrapingResults(date);
            break;
        case 9:
            results = lotteryResultScrapingService.superkinoScrapingResult(date);
            break;
        case 10:
            results = lotteryResultScrapingService.pega3MasWebScrapingResults(date);
            break;
        case 12:
            results = lotteryResultScrapingService.quinielaLeidsaScrapingResult(date);
            break;
        case 15:
            results = lotteryResultScrapingService.quinielaNacionalNocheWebScrapingResults(date);
            break;
        case 21:
            results = lotteryResultScrapingService.quinielaRealScrapingResult(date);
            break;
        case 24:
            results = lotteryResultScrapingService.nuevaYorkTardeScrapingResult(date);
            break;
        case 27:
            results = lotteryResultScrapingService.nuevaYorkNocheScrapingResult(date);
            break;
        case 30:
            results = lotteryResultScrapingService.lotekaQuinielaScrapingResult(date);
            break;
        case 32:
            results = lotteryResultScrapingService.megaChanceScrapingResult(date);
            break;
        case 33:
            results = lotteryResultScrapingService.quinielaNacionalTardeWebScrapingResult(date);
            break;
        case 34:
            results = lotteryResultScrapingService.juegaMasGanaMas(date);
            break;
        case 35:
            results = lotteryResultScrapingService.laPrimeraQuinielaScrapingResult(date);
            break;

        }

        return results;

    }

    private long countLotteryResultByLotteryTypeId(Long lotteryTypeId) {

        long count = lotteryResultRepository.countLotteryResultByLotteryTypeId(lotteryTypeId);
        return count;
    }

    private void sendEmailFailSemiAutomaticUpdatingResults(LotteryResult lotteryResult) {

        String subject = "La Banquera: Resultados no actualizados";
        String message = "No fue posible actualizar semi-automaticamente los resultados de la loteria: "
                + lotteryResult.getLotteryType().getName() + " para el dia: " + LocalDateTime.now()
                + " Por favor actualizar manualmente. Causa: Resultados con valores vacios o null";
        emailSender.prepareAndSend("jorgeluisdelossantoslopez@gmail.com", subject, message);
        emailSender.prepareAndSend("anderson.ferreiras.mota@gmail.com", subject, message);
        emailSender.prepareAndSend("joelcontreras21@gmail.com", subject, message);
    }

    private void sendEmailFailUpdatingResults(int retry, long time, LotteryResult lotteryResult, long lotteryTypeId) {

        String subject = "banca-online-api web-service: Resultados no actualizados";
        String message = "No fue posible actualizar automaticamente los resultados de la loteria: "
                + lotteryTypeRepository.findById(lotteryTypeId).getName() + " en " + (retry + 1)
                + " intentos con intervalos de " + (time / 60000) + " minutos para el dia: " + LocalDateTime.now()
                + " Por favor actualizar manualmente";

        LOGGER.error(subject + "\n" + message);

        emailSender.prepareAndSend("jorgeluisdelossantoslopez@gmail.com", subject, message);
        emailSender.prepareAndSend("anderson.ferreiras.mota@gmail.com", subject, message);
        emailSender.prepareAndSend("joelcontreras21@gmail.com", subject, message);
    }

    private boolean validateScrapingData(List<String> balls, String date) {

        boolean isValid = true;

        if (balls == null || balls.isEmpty()) {
            isValid = false;
        } else {
            for (String ball : balls) {
                if (!ball.matches("^\\d\\d")) {
                    isValid = false;
                    break;
                }
            }
        }

        if (date == null || date.isBlank()) {
            isValid = false;
        } else {
            if (!date.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
                isValid = false;
            }
        }
        return isValid;

    }

}
