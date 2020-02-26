package com.bancaonline.api.service;

import com.bancaonline.api.model.CurrencyResult;
import com.bancaonline.api.model.CurrencyType;
import com.bancaonline.api.repository.CurrencyResultRepository;
import com.bancaonline.api.util.Constants;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * The type Currency result scraping service.
 */
@Service
public class CurrencyResultScrapingService {

    @Autowired
    private CurrencyResultRepository currencyResultRepository;

    /**
     * The constant logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyResultScrapingService.class);

    /**
     * currency results list.
     *
     * @return the list
     */
    public List<CurrencyResult> currencyResults() {
        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.DAY_OF_WEEK, -1);

        String strToday = new SimpleDateFormat("YYYY-MM-dd").format(today);
        String yestarday = new SimpleDateFormat("YYYY-MM-dd").format(calendar.getTime());

        return currencyResultRepository.findCurrency(strToday, yestarday);
    }

    /**
     * Update dollar.
     *
     * @throws IOException the io exception
     */
    public void updateDollar() throws IOException {
        String result = getCurrency(Constants.DIVISA_DOLLAR);

        currencyResultRepository.save(getCurrencyResult(result, 1L));
    }

    /**
     * Update euro.
     *
     * @throws IOException the io exception
     */
    public void updateEuro() throws IOException {
        String result = getCurrency(Constants.DIVISA_EURO);

        currencyResultRepository.save(getCurrencyResult(result, 2L));
    }

    /**
     * Update gourder.
     *
     * @throws IOException the io exception
     */
    public void updateGourder() throws IOException {
        String result = getCurrency(Constants.DIVISA_GOURDE);

        currencyResultRepository.save(getCurrencyResult(result, 3L));
    }

    /**
     * Updatebolivar.
     *
     * @throws IOException the io exception
     */
    public void updatebolivar() throws IOException {
        String result = getCurrency(Constants.DIVISA_BOLIVAR);

        currencyResultRepository.save(getCurrencyResult(result, 4L));
    }

    /**
     * Gets gourde.
     *
     * @return the gourde
     * @throws IOException the io exception
     */
    public String getGourde() throws IOException {
        return getCurrency(Constants.DIVISA_GOURDE);
    }

    /**
     * Gets bolivar.
     *
     * @return the bolivar
     * @throws IOException the io exception
     */
    public String getBolivar() throws IOException {
        return getCurrency(Constants.DIVISA_GOURDE);
    }

    /**
     * Gets currency.
     *
     * @param url the url
     * @return the currency
     * @throws IOException the io exception
     */
    private String getCurrency(String url) throws IOException {

        Document doc = Jsoup.connect(url).get();
        Elements currency = doc.select(".cc__source-to-target");

        Element scores = currency.select(".text-success").get(0);

        LOGGER.info("Scraping for ".concat(url));

        return scores.text();

    }

    private CurrencyResult getCurrencyResult(String price, Long id) {
        CurrencyType currencyType = new CurrencyType();
        currencyType.setId(id);

        CurrencyResult currencyResult = new CurrencyResult();
        currencyResult.setCreatedDate(new SimpleDateFormat("YYYY-MM-dd").format(new Date()));
        currencyResult.setCurrencyType(currencyType);
        currencyResult.setPrice(price);
        return currencyResult;

    }
}
