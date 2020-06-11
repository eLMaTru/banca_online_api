package com.bancaonline.api.component;

import com.bancaonline.api.model.LotteryResult;
import com.bancaonline.api.repository.LotteryResultRepository;
import com.bancaonline.api.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * The type Result job.
 */
@Component
public class ResultJob {

    /**
     * The constant logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ResultJob.class);

    @Autowired
    private LeidsaService leidsaService;

    @Autowired
    private NacionalService nacionalService;

    @Autowired
    private LaPrimeraService laPrimeraService;

    @Autowired
    private LotekaService lotekaService;

    @Autowired
    private AmericanaService americanaService;

    @Autowired
    private RealService realService;

    @Autowired
    private CurrencyResultScrapingService currencyResultScrapingService;

    @Autowired
    private LotteryResultRepository lotteryResultRepository;

    /**
     * Update juega mas gana mas results.
     *
     * @throws IOException the io exception
     */
    @Scheduled(cron = "0 0 15 ? * SUN,MON,TUE,WED,THU,FRI,SAT")
    @Async
    protected void updateJuegaMasGanaMasResults() throws IOException {
        LotteryResult lotteryResult = nacionalService.updateJuegaMasGanaMasResults(null);
        String result = lotteryResult == null ? "failed" : lotteryResult.toString();
        String msj = "updateJuegaMasGanaMasResults job executed with result: ".concat(result);
        if (result.equals("failed")) {
            LOGGER.error(msj);
        } else {
            LOGGER.info(msj);
        }

    }

    /**
     * Update gana mas results.
     *
     * @throws IOException the io exception
     */
    @Scheduled(cron = "0 0 15 ? * 1-7")
    @Async
    protected void updateGanaMasResults() throws IOException {
        LotteryResult lotteryResult = nacionalService.updateGanaMasResults(null);
        String result = lotteryResult == null ? "failed" : lotteryResult.toString();
        String msj = "updateGanaMasResults job executed with result: ".concat(result);
        if (result.equals("failed")) {
            LOGGER.error(msj);
        } else {
            LOGGER.info(msj);
        }
    }

    /**
     * Update quiniela nacional noche results.
     *
     * @throws IOException the io exception
     */
    @Scheduled(cron = "0 5 21 ? * MON,TUE,WED,THU,FRI,SAT")
    @Scheduled(cron = "0 5 18 ? * SUN")
    @Async
    protected void updateQuinielaNacionalNocheResults() throws IOException {
        LotteryResult lotteryResult = nacionalService.updateQuinielaNacionalResults(null);
        String result = lotteryResult == null ? "failed" : lotteryResult.toString();
        String msj = "updateQuinielaNacionalNocheResults job executed with result: ".concat(result);
        if (result.equals("failed")) {
            LOGGER.error(msj);
        } else {
            LOGGER.info(msj);
        }
    }

    /**
     * Update pega 3 mas results.
     *
     * @throws IOException the io exception
     */
    @Scheduled(cron = "0 5 21 ? * MON,TUE,WED,THU,FRI,SAT")
    @Scheduled(cron = "0 5 18 ? * SUN")
    @Async
    protected void updatePega3MasResults() throws IOException {
        LotteryResult lotteryResult = leidsaService.updatePega3MasResults(null);
        String result = lotteryResult == null ? "failed" : lotteryResult.toString();
        String msj = "updatePega3MasResults job executed with result: ".concat(result);
        if (result.equals("failed")) {
            LOGGER.error(msj);
        } else {
            LOGGER.info(msj);
        }
    }

    /**
     * Update loto pool results.
     *
     * @throws IOException the io exception
     */
    @Scheduled(cron = "0 5 21 ? * MON,TUE,WED,THU,FRI,SAT")
    @Scheduled(cron = "0 5 18 ? * SUN")
    @Async
    protected void updateLotoPoolResults() throws IOException {
        LotteryResult lotteryResult = leidsaService.updateLotoPoolResults(null);
        String result = lotteryResult == null ? "failed" : lotteryResult.toString();
        String msj = "updateLotoPoolResults job executed with result: ".concat(result);
        if (result.equals("failed")) {
            LOGGER.error(msj);
        } else {
            LOGGER.info(msj);
        }
    }

    /**
     * Update super kino tv results.
     *
     * @throws IOException the io exception
     */
    @Scheduled(cron = "0 5 21 ? * MON,TUE,WED,THU,FRI,SAT")
    @Scheduled(cron = "0 5 18 ? * SUN")
    @Async
    protected void updateSuperKinoTVResults() throws IOException {
        LotteryResult lotteryResult = leidsaService.updateSuperKinoResults(null);
        String result = lotteryResult == null ? "failed" : lotteryResult.toString();
        String msj = "updateSuperKinoTVResults job executed with result: ".concat(result);
        if (result.equals("failed")) {
            LOGGER.error(msj);
        } else {
            LOGGER.info(msj);
        }
    }

    /**
     * Update quiniela leidsa results.
     *
     * @throws IOException the io exception
     */
    @Scheduled(cron = "0 5 21 ? * MON,TUE,WED,THU,FRI,SAT")
    @Scheduled(cron = "0 5 18 ? * SUN")
    @Async
    protected void updateQuinielaLeidsaResults() throws IOException {
        LotteryResult lotteryResult = leidsaService.updateQuinielaLeidsaResults(null);
        String result = lotteryResult == null ? "failed" : lotteryResult.toString();
        String msj = "updateQuinielaLeidsaResults job executed with result: ".concat(result);
        if (result.equals("failed")) {
            LOGGER.error(msj);
        } else {
            LOGGER.info(msj);
        }
    }

    /**
     * Update loto mas results.
     *
     * @throws IOException the io exception
     */
    @Scheduled(cron = "0 5 21 ? * WED,SAT")
    @Async
    protected void updateLotoMasResults() throws IOException {
        LotteryResult lotteryResult = leidsaService.updateLotoResults(null);
        String result = lotteryResult == null ? "failed" : lotteryResult.toString();
        String msj = "updateLotoMasResults job executed with result: ".concat(result);
        if (result.equals("failed")) {
            LOGGER.error(msj);
        } else {
            LOGGER.info(msj);
        }
    }

    /**
     * Update quiniela real results.
     *
     * @throws IOException the io exception
     */
    @Scheduled(cron = "0 5 13 ? * 1-7")
    @Async
    protected void updateQuinielaRealResults() throws IOException {
        LotteryResult lotteryResult = realService.updateQuinielaRealResults(null);
        String result = lotteryResult == null ? "failed" : lotteryResult.toString();
        String msj = "updateQuinielaRealResults job executed with result: ".concat(result);
        if (result.equals("failed")) {
            LOGGER.error(msj);
        } else {
            LOGGER.info(msj);
        }
    }

    /**
     * Update loto real results.
     *
     * @throws IOException the io exception
     */
    @Scheduled(cron = "0 5 13 ? * TUE,FRI")
    @Async
    protected void updateLotoRealResults() throws IOException {
        LotteryResult lotteryResult = realService.updateLotoRealResults(null);
        String result = lotteryResult == null ? "failed" : lotteryResult.toString();
        String msj = "updateLotoRealResults job executed with result: ".concat(result);
        if (result.equals("failed")) {
            LOGGER.error(msj);
        } else {
            LOGGER.info(msj);
        }
    }

    /**
     * Update quiniela loteka results.
     *
     * @throws IOException the io exception
     */
    @Scheduled(cron = "0 5 19 ? * 1-7")
    @Async
    protected void updateQuinielaLotekaResults() throws IOException {
        LotteryResult lotteryResult = lotekaService.updateQuinielaLotekaResults(null);
        String result = lotteryResult == null ? "failed" : lotteryResult.toString();
        String msj = "updateQuinielaLotekaResults job executed with result: ".concat(result);
        if (result.equals("failed")) {
            LOGGER.error(msj);
        } else {
            LOGGER.info(msj);
        }
    }

    /**
     * Update mega chance results.
     *
     * @throws IOException the io exception
     */
    @Scheduled(cron = "0 5 19 ? * 1-7")
    @Async
    protected void updateMegaChanceResults() throws IOException {
        LotteryResult lotteryResult = lotekaService.updateMegaChanceResults(null);
        String result = lotteryResult == null ? "failed" : lotteryResult.toString();
        String msj = "updateMegaChanceResults job executed with result: ".concat(result);
        if (result.equals("failed")) {
            LOGGER.error(msj);
        } else {
            LOGGER.info(msj);
        }
    }

    /**
     * Update la primera quinela results.
     *
     * @throws IOException the io exception
     */
    @Scheduled(cron = "0 05 12 ? * 1-7")
    @Async
    protected void updateLaPrimeraQuinelaResults() throws IOException {
        LotteryResult lotteryResult = laPrimeraService.updateLaPrimeraQuinielaResults(null);
        String result = lotteryResult == null ? "failed" : lotteryResult.toString();
        String msj = "updateLaPrimeraQuinelaResults job executed with result: ".concat(result);
        if (result.equals("failed")) {
            LOGGER.error(msj);
        } else {
            LOGGER.info(msj);
        }
    }

    /**
     * Update new york 1230 results.
     *
     * @throws IOException the io exception
     */
    @Scheduled(cron = "0 35 12 ? * 1-7")
    @Async
    protected void updateNewYork1230Results() throws IOException {
        LotteryResult lotteryResult = americanaService.updateNewYork1230Results(null);
        String result = lotteryResult == null ? "failed" : lotteryResult.toString();
        String msj = "updateNewYork1230Results job executed with result: ".concat(result);
        if (result.equals("failed")) {
            LOGGER.error(msj);
        } else {
            LOGGER.info(msj);
        }
    }

    /**
     * Update new york 730 results.
     *
     * @throws IOException the io exception
     */
    @Scheduled(cron = "0 35 19 ? * 1-7")
    @Async
    protected void updateNewYork730Results() throws IOException {
        LotteryResult lotteryResult = americanaService.updateNewYork730Results(null);
        String result = lotteryResult == null ? "failed" : lotteryResult.toString();
        String msj = "updateNewYork730Results job executed with result: ".concat(result);
        if (result.equals("failed")) {
            LOGGER.error(msj);
        } else {
            LOGGER.info(msj);
        }
    }

    /**
     * Mega millions result job.
     *
     * @throws IOException the io exception
     */
    @Scheduled(cron = "0 1 4 ? * WED,SAT")
    @Async
    protected void mega_millions_result_job() throws IOException {

        LotteryResult result = americanaService.updateMegaMillionsResult(null);
        String res = result == null ? "failed" : result.toString();
        String msj = "updateMegaMillionsResult job executed with result: ".concat(res);
        if (res.equals("failed")) {
            LOGGER.error(msj);
        } else {
            LOGGER.info(msj);
        }

    }

    /**
     * Power ball result job.
     *
     * @throws IOException the io exception
     */
    @Scheduled(cron = "0 2 2 ? * THU,SUN")
    @Async
    protected void powerBallResultJob() throws IOException {

        LotteryResult result = americanaService.updatePowerBallResult(null);
        String res = result == null ? "failed" : result.toString();
        String msj = "updatePowerBallResult job executed with result: ".concat(res);
        if (res.equals("failed")) {
            LOGGER.error(msj);
        } else {
            LOGGER.info(msj);
        }

    }

    /**
     * Mega loto job.
     *
     * @throws IOException the io exception
     */
    @Scheduled(cron = "0 5 19 ? * MON,THU")
    @Async
    protected void megaLotoJob() throws IOException {

        LotteryResult result = lotekaService.updateMegaLotoResult(null);
        String res = result == null ? "failed" : result.toString();
        String msj = "updateMegaLotoResult job executed with result: ".concat(res);
        if (res.equals("failed")) {
            LOGGER.error(msj);
        } else {
            LOGGER.info(msj);
        }

    }

    /**
     * Update currency results.
     *
     * @throws IOException the io exception
     */
    @Scheduled(cron = "0 30 8 ? * 1-7")
    @Async
    protected void updateCurrencyResults() throws IOException {
        currencyResultScrapingService.updateEuro();
        LOGGER.info("updateEuro");
        currencyResultScrapingService.updateDollar();
        LOGGER.info("updateDollar");
        ;

        currencyResultScrapingService.updateGourder();
        LOGGER.info("updateGourde");

    }

    /**
     * Update all.
     *
     * @param date the date
     * @throws IOException    the io exception
     * @throws ParseException the parse exception
     */
    public void updateAll(String date) throws IOException, ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateFromService = sdf.parse(date);

        Date toDay = new Date();

        if (dateFromService.compareTo(toDay) >= 0) {
            throw new IllegalArgumentException("Param date can not be mayor or equal that today ");
        }

        Calendar calendarToday = Calendar.getInstance();
        calendarToday.setTime(toDay);
        calendarToday.add(Calendar.DAY_OF_WEEK, -1);
        toDay = calendarToday.getTime();

        while (dateFromService.compareTo(toDay) < 0) {

            lotteryResultRepository.deleteByDate(sdf.format(dateFromService));

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateFromService);

            date = sdf.format(dateFromService);

            nacionalService.updateJuegaMasGanaMasResults(date);
            nacionalService.updateGanaMasResults(date);
            nacionalService.updateQuinielaNacionalResults(date);
            leidsaService.updatePega3MasResults(date);
            leidsaService.updateLotoPoolResults(date);
            leidsaService.updateSuperKinoResults(date);
            leidsaService.updateQuinielaLeidsaResults(date);
            leidsaService.updateLotoResults(date);
            realService.updateQuinielaRealResults(date);
            realService.updateLotoRealResults(date);
            lotekaService.updateQuinielaLotekaResults(date);
            lotekaService.updateMegaChanceResults(date);
            laPrimeraService.updateLaPrimeraQuinielaResults(date);
            americanaService.updateNewYork1230Results(date);
            americanaService.updateNewYork730Results(date);
            americanaService.updateMegaMillionsResult(date);
            americanaService.updatePowerBallResult(date);
            lotekaService.updateMegaLotoResult(date);

            calendar.add(Calendar.DAY_OF_WEEK, 1);
            dateFromService = calendar.getTime();
        }
    }

    /**
     * Update power ball bote.
     *
     * @throws IOException the io exception
     */
    @Scheduled(cron = "0 5 21 ? * THU,SUN")
    @Async
    protected void updatePowerBallBote() throws IOException {
        LOGGER.info("update powerBall bote");
        americanaService.updatePowerBallBote();

    }

    /**
     * Update mega million bote.
     *
     * @throws IOException the io exception
     */
    @Scheduled(cron = "0 5 21 ? * WED,SAT")
    @Async
    protected void updateMegaMillionBote() throws IOException {
        LOGGER.info("update mega million bote");
        americanaService.updateMegaMillionBote();

    }

    /**
     * Update loto leidsa bote.
     *
     * @throws IOException the io exception
     */
    @Scheduled(cron = "0 5 21 ? * THU,SUN")
    @Async
    protected void updateLotoLeidsaBote() throws IOException {
        LOGGER.info("update loto leidsa bote");
        leidsaService.updateLotoBote();
    }

    /**
     * Update loto real bote.
     *
     * @throws IOException the io exception
     */
    @Scheduled(cron = "0 5 21 ? * WED,SAT")
    @Async
    protected void updateLotoRealBote() throws IOException {
        LOGGER.info("update loto real bote");
        realService.updateLotoRealBote();

    }

    /**
     * Update florida nigth results.
     *
     * @throws IOException the io exception
     */
    @Scheduled(cron = "0 15 20 ? * 1-7")
    @Async
    protected void updateFloridaNigthResults() throws IOException {
        LotteryResult lotteryResult = americanaService.updateFloridaNight(null);
        String result = lotteryResult == null ? "failed" : lotteryResult.toString();
        String msj = "floridaNoche job executed with result: ".concat(result);
        if (result.equals("failed")) {
            LOGGER.error(msj);
        } else {
            LOGGER.info(msj);
        }
    }

    /**
     * Update florida day results.
     *
     * @throws IOException the io exception
     */
    @Scheduled(cron = "0 5 14 ? * 1-7")
    @Async
    protected void updateFloridaDayResults() throws IOException {
        LotteryResult lotteryResult = americanaService.updateFloridaDay(null);
        String result = lotteryResult == null ? "failed" : lotteryResult.toString();
        String msj = "floridaDia job executed with result: ".concat(result);
        if (result.equals("failed")) {
            LOGGER.error(msj);
        } else {
            LOGGER.info(msj);
        }
    }

}
