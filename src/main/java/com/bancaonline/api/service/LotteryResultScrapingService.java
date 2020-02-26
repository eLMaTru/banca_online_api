package com.bancaonline.api.service;

import com.bancaonline.api.util.Constants;
import com.bancaonline.api.util.Utils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The type Lottery result scraping service.
 */
@Service
public class LotteryResultScrapingService {

    /**
     * The constant logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(LotteryResultScrapingService.class);

    /**
     * Mega millions web scraping result list.
     *
     * @return the list
     * @throws IOException the io exception
     */
    public List<String> megaMillionsWebScrapingResult(String date) throws IOException {

        return getLotteryByURL(Constants.MEGA_MILLIONS_URL, date);

    }

    /**
     * Power ball web scraping result list.
     *
     * @return the list
     * @throws IOException the io exception
     */
    public List<String> powerBallWebScrapingResult(String date) throws IOException {

        return getLotteryByURL(Constants.POWER_BALL_URL, date);
    }

    /**
     * Quiniela nacional tarde web scraping result list.
     *
     * @return the list
     * @throws IOException the io exception
     */
    public List<String> quinielaNacionalTardeWebScrapingResult(String date) throws IOException {

        return getLotteryByURL(Constants.GANA_MAS_URL, date);
    }

    /**
     * Update quiniela nacional noche web scraping results list.
     *
     * @return the list
     * @throws IOException the io exception
     */
    public List<String> quinielaNacionalNocheWebScrapingResults(String date) throws IOException {

        return getLotteryByURL(Constants.LOTERIA_NACIONA_URL, date);

    }

    /**
     * Juega mas gana mas list.
     *
     * @return the list
     * @throws IOException the io exception
     */
    public List<String> juegaMasGanaMas(String date) throws IOException {

        return getLotteryByURL(Constants.JUEGA_MAS_PEGA_MAS_URL, date);

    }

    /**
     * Pega 3 mas web scraping results list.
     *
     * @return the list
     * @throws IOException the io exception
     */
    public List<String> pega3MasWebScrapingResults(String date) throws IOException {

        return getLotteryByURL(Constants.PEGA_3_MAS_URL, date);
    }

    /**
     * Superkino scraping result list.
     *
     * @return the list
     * @throws IOException the io exception
     */
    public List<String> superkinoScrapingResult(String date) throws IOException {

        return getLotteryByURL(Constants.SUPER_QUINO_URL, date);

    }

    /**
     * Loto pool web scraping results list.
     *
     * @return the list
     * @throws IOException the io exception
     */
    public List<String> lotoPoolWebScrapingResults(String date) throws IOException {

        return getLotteryByURL(Constants.LOTO_POOL_URL, date);

    }

    /**
     * Lotomas scraping result list.
     *
     * @return the list
     * @throws IOException the io exception
     */
    public List<String> lotomasScrapingResult(String date) throws IOException {

        return getLotteryByURL(Constants.LOTO_MAS_URL, date);

    }

    /**
     * Quiniela leidsa scraping result list.
     *
     * @return the list
     * @throws IOException the io exception
     */
    public List<String> quinielaLeidsaScrapingResult(String date) throws IOException {

        return getLotteryByURL(Constants.QUINIELA_LEIDSA_URL, date);

    }

    /**
     * Quiniela real scraping result list.
     *
     * @return the list
     * @throws IOException the io exception
     */
    public List<String> quinielaRealScrapingResult(String date) throws IOException {

        return getLotteryByURL(Constants.REAL_QUINIELA_URL, date);

    }

    /**
     * Loto real scraping result list.
     *
     * @return the list
     * @throws IOException the io exception
     */
    public List<String> lotoRealScrapingResult(String date) throws IOException {

        return getLotteryByURL(Constants.LOTO_REAL_URL, date);

    }

    /**
     * Loteka quiniela scraping result list.
     *
     * @return the list
     * @throws IOException the io exception
     */
    public List<String> lotekaQuinielaScrapingResult(String date) throws IOException {

        return getLotteryByURL(Constants.LOTEKA_QUINIELA_URL, date);

    }

    /**
     * Mega chance scraping result list.
     *
     * @return the list
     * @throws IOException the io exception
     */
    public List<String> megaChanceScrapingResult(String date) throws IOException {

        return getLotteryByURL(Constants.MEGA_CHANCE_URL, date);

    }

    /**
     * Mega lotto scraping result list.
     *
     * @return the list
     * @throws IOException the io exception
     */
    public List<String> megaLottoScrapingResult(String date) throws IOException {

        return getLotteryByURL(Constants.MEGA_LOTTO_URL, date);

    }

    /**
     * Nueva york tarde scraping result list.
     *
     * @return the list
     * @throws IOException the io exception
     */
    public List<String> nuevaYorkTardeScrapingResult(String date) throws IOException {

        return getLotteryByURL(Constants.NUEVA_YORK_TARDE_URL, date);

    }

    /**
     * Nueva york noche scraping result list.
     *
     * @return the list
     * @throws IOException the io exception
     */
    public List<String> nuevaYorkNocheScrapingResult(String date) throws IOException {

        return getLotteryByURL(Constants.NUEVA_YORK_NOCHE_URL, date);

    }

    public List<String> laPrimeraQuinielaScrapingResult(String date) throws IOException {

        return getLotteryByURL(Constants.QUINIELA_LA_PRIMERA_URL, date);

    }

    private List<String> getLotteryByURL(String url, String date) throws IOException {
        List<String> results = new ArrayList<>();
        if (date != null) {
            url += "?date=";
            url += date;
        }
        Document doc = Jsoup.connect(url).get();
        Elements normalBalls = doc.select(".game-block");

        Element scores = normalBalls.select(".game-scores").get(0);

        Elements dates = normalBalls.select(".game-details .session-details span");
        String lastDate = dates.get(0).text();

        List<String> values = Arrays.asList(scores.text().split(" "));

        values.forEach((k) -> results.add(k));

        try {
            results.add(Utils.convertDateUSFormat(lastDate));
        } catch (Exception ex) {
            LOGGER.error("Error with the cast in the date");
            results.add("0000-00-00");
        }

        LOGGER.info("Scraping for ".concat(url));
        return results;
    }

}
