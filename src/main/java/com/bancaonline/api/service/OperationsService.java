package com.bancaonline.api.service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.bancaonline.api.model.*;
import com.bancaonline.api.repository.LotoRepository;
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

    /**
     * The Lottery type repository.
     */
    @Autowired
    LotteryTypeRepository lotteryTypeRepository;

    @Autowired
    private LotteryResultScrapingService lotteryResultScrapingService;

    @Autowired
    private EmailSender emailSender;

    @Autowired
    private LotoRepository lotoRepository;

    /**
     * Verify result lottery result.
     *
     * @param result      the result
     * @param lotteryType the lottery type
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
     * @param lotteryTypeId          the lottery type id
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
     * @param lotteryTypeId the lottery type id
     * @param date          the date
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
         case 39:
            results = lotteryResultScrapingService.floridaDayResult(date);
             break;
         case 40:
             results = lotteryResultScrapingService.floridaNightResult(date);
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

    /**
     * Gets loto leidsa bote.
     *
     * @throws IOException the io exception
     */
    public void getLotoLeidsaBote() throws IOException {
        try {
           List<String> result = lotteryResultScrapingService.getLotoLeidsaBote();
           if (result.isEmpty()){
               throw new IllegalArgumentException("Error con el scraping");
           }

           Loto loto = lotoRepository.findByType(new LotteryType(4L),new Status(1L));
           Loto LotoMas = lotoRepository.findByType(new LotteryType(36L),new Status(1L));
           Loto SuperMas = lotoRepository.findByType(new LotteryType(37L),new Status(1L));

           /***/

           if (loto != null){
               if (!loto.getBote().equals(result.get(0).trim())){
                   lotoRepository.updateByLoteryType(4L,2L);
                   lotoRepository.save(createLotoObject(result.get(0),
                           new LotteryType(4L),
                           "RD",
                           "Acumulado de la loto"));
               }

           }else{
               lotoRepository.save(createLotoObject(result.get(0),
                       new LotteryType(4L),
                       "RD",
                       "Acumulado de la loto"));
           }

            if (LotoMas != null){
                if (!LotoMas.getBote().equals(result.get(1).trim())){
                    lotoRepository.updateByLoteryType(36L,2L);
                    lotoRepository.save(createLotoObject(result.get(1),
                            new LotteryType(36L),
                            "RD",
                            "Acumulado del loto mas"));
                }

            }else{
                lotoRepository.save(createLotoObject(result.get(1),
                        new LotteryType(36L),
                        "RD",
                        "Acumulado de la loto mas"));
            }

            if (SuperMas != null){
                if (!SuperMas.getBote().equals(result.get(2).trim())){
                    lotoRepository.updateByLoteryType(37L,2L);
                    lotoRepository.save(createLotoObject(result.get(2),
                            new LotteryType(37L),
                            "RD",
                            "Acumulado del super loto mas"));
                }

            }else{
                lotoRepository.save(createLotoObject(result.get(2),
                        new LotteryType(37L),
                        "RD",
                        "Acumulado del super loto mas"));
            }



        }catch (Exception ex){
            ex.fillInStackTrace();
        }
    }

    private Loto createLotoObject(String bote, LotteryType lotteryType, String currencyCode,String description){
        Loto loto = new Loto();
        loto.setBote(bote);
        loto.setCurrencyCode(currencyCode);
        loto.setDescription(description);
        loto.setName(lotteryType.getName());
        loto.setLotteryType(lotteryType);
        loto.setStatus(new Status(1L));
        return loto;
    }

    /**
     * Gets loto real bote.
     *
     * @throws IOException the io exception
     */
    public void getLotoRealBote() throws IOException {
        try {
            List<String> result = lotteryResultScrapingService.getLotoRealBote();
            if (result.isEmpty()){
                throw new IllegalArgumentException("Error con el scraping");
            }

            Loto lotoReal = lotoRepository.findByType(new LotteryType(6L),new Status(1L));
            Loto LotoReal25 = lotoRepository.findByType(new LotteryType(38L),new Status(1L));

            /***/

            if (lotoReal != null){
                if (!lotoReal.getBote().equals(result.get(0).trim())){
                    lotoRepository.updateByLoteryType(6L,2L);
                    lotoRepository.save(createLotoObject(result.get(0).trim(),
                            new LotteryType(6L),
                            "RD",
                            "Acumulado de la loto Real 10 pesos"));
                }

            }else{
                lotoRepository.save(createLotoObject(result.get(0).trim(),
                        new LotteryType(6L),
                        "RD",
                        "Acumulado de la loto Real 10 pesos"));
            }



            if (LotoReal25 != null){
                if (!LotoReal25.getBote().equals(result.get(1).trim())){
                    lotoRepository.updateByLoteryType(38L,2L);
                    lotoRepository.save(createLotoObject(result.get(2).trim(),
                            new LotteryType(38L),
                            "RD",
                            "Acumulado de la loto Real 25 pesos"));
                }

            }else{
                lotoRepository.save(createLotoObject(result.get(1).trim(),
                        new LotteryType(38L),
                        "RD",
                        "Acumulado de la loto Real 25 pesos"));
            }


        }catch (Exception ex){
            ex.fillInStackTrace();
        }
    }


    /**
     * Gets mega million bote.
     *
     * @throws IOException the io exception
     */
    public void getMegaMillionBote() throws IOException {
        try {
            List<String> result = lotteryResultScrapingService.getMegaMillionBote();
            if (result.isEmpty()){
                throw new IllegalArgumentException("Error con el scraping");
            }

            Loto megaMillion = lotoRepository.findByType(new LotteryType(1L),new Status(1L));

            /***/

            if (megaMillion != null){
                if (!megaMillion.getBote().equals(result.get(0).trim())){
                    lotoRepository.updateByLoteryType(1L,2L);
                    lotoRepository.save(createLotoObject(result.get(0).trim(),
                            new LotteryType(1L),
                            "US",
                            "Acumulado del mega millions"));
                }

            }else{
                lotoRepository.save(createLotoObject(result.get(0).trim(),
                        new LotteryType(1L),
                        "US",
                        "Acumulado del mega millions"));
            }




        }catch (Exception ex){
            ex.fillInStackTrace();
        }
    }

    /**
     * Gets power ball bote.
     *
     * @throws IOException the io exception
     */
    public void getPowerBallBote() throws IOException {
        try {
            List<String> result = lotteryResultScrapingService.getPowerBallBote();
            if (result.isEmpty()){
                throw new IllegalArgumentException("Error con el scraping");
            }

            Loto powerBall = lotoRepository.findByType(new LotteryType(2L),new Status(1L));

            /***/

            if (powerBall != null){
                if (!powerBall.getBote().equals(result.get(0).trim())){
                    lotoRepository.updateByLoteryType(2L,2L);
                    lotoRepository.save(createLotoObject(result.get(0).trim(),
                            new LotteryType(2L),
                            "US",
                            "Acumulado del power ball"));
                }

            }else{
                lotoRepository.save(createLotoObject(result.get(0).trim(),
                        new LotteryType(2L),
                        "US",
                        "Acumulado de power ball"));
            }




        }catch (Exception ex){
            ex.fillInStackTrace();
        }
    }


}
