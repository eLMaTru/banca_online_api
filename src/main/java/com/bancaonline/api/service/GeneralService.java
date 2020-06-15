package com.bancaonline.api.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import com.bancaonline.api.model.*;
import com.bancaonline.api.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bancaonline.api.component.ResultJob;
import com.bancaonline.api.model.dto.CurrencyDto;
import com.bancaonline.api.model.dto.DaysOfWeek;
import com.bancaonline.api.response.GeneralResponse;
import com.bancaonline.api.util.Constants;

/**
 * The type General service.
 */
@Service
public class GeneralService {

    /**
     * The constant logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(GeneralService.class);

    @Autowired
    private LotteryResultRepository lotteryResultRepository;

    @Autowired
    private CurrencyResultScrapingService currencyResultScrapingService;

    @Autowired
    private ResultJob resultJob;

    @Autowired
    private LotoRepository lotoRepository;

    /**
     * Gets fuels.
     *
     * @return the fuels
     */
    public String getFuels() {

        try {

            HttpURLConnection connection = conectHttpUrl(Constants.FUEL);
            connection.setConnectTimeout(30000);
            connection.setReadTimeout(30000);
            BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));

            String output;
            String values = "";
            while ((output = br.readLine()) != null) {
                values += output;
            }
            disconectHttpUrl(connection);
            return values;
        } catch (Exception ex) {
            ex.getStackTrace();
            return null;
        }
    }

    /**
     * Get all lottery by status hash map.
     *
     * @param statusId the status id
     * @return the hash map
     */
    public HashMap<String, String> getAllLotteryByStatus(Long statusId) {
        LOGGER.info("getting all lottery by status :" + statusId);

        HashMap<String, String> stringStringHashMap = new HashMap<>();

        List<Object[]> allResult = lotteryResultRepository.findAllLotteryByStatus(statusId);

        allResult.stream().forEach(x -> stringStringHashMap.put((String) x[0], (String) x[1] + ", " + x[2]));

        return stringStringHashMap;
    }

    public HashMap<String, String> getALLBoteByStatus(Long statusId) {
        LOGGER.info("getting all bote by status :" + statusId);

        HashMap<String, String> stringStringHashMap = new HashMap<>();

        List<Loto> allResult = lotoRepository.findAllLottoByStatus(statusId);

        allResult.stream().forEach(x -> stringStringHashMap.put(x.getLotteryType().getName(), x.getBote() + "," + x.getCurrencyCode()));

        return stringStringHashMap;
    }

    /**
     * Gets currencies.
     *
     * @return the currencies
     */
    public List<CurrencyDto> getCurrencies() {
        LOGGER.info("getting currencies");

        List<CurrencyDto> currencyDtoList = new ArrayList<>();

        List<CurrencyResult> allResult = currencyResultScrapingService.currencyResults();

        if (allResult != null && !allResult.isEmpty()) {

            try {
                CurrencyDto currencyDto1 = new CurrencyDto(allResult.get(0).getPrice(), allResult.get(2).getPrice(),
                        allResult.get(4).getPrice(), allResult.get(0).getCreatedDate());
                CurrencyDto currencyDto2 = new CurrencyDto(allResult.get(1).getPrice(), allResult.get(3).getPrice(),
                        allResult.get(5).getPrice(), allResult.get(1).getCreatedDate());
                currencyDtoList.add(currencyDto1);
                currencyDtoList.add(currencyDto2);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return currencyDtoList;
    }

    /**
     * Get days of week list.
     *
     * @return the list
     */
    public List<DaysOfWeek> getDaysOfWeek() {

        String d1 = new SimpleDateFormat("YYYY-MM-dd").format(new Date());

        List<DaysOfWeek> daysOfWeeks = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.WEEK_OF_MONTH, -1);

        String d2 = new SimpleDateFormat("YYYY-MM-dd").format(calendar.getTime());

        List<Object[]> objects = lotteryResultRepository.findDayOfWeek(d2, d1);

        objects.sort((Object[] ob1, Object[] ob2) -> ((LocalDateTime) ob1[2]).getDayOfWeek().getValue()
                - ((LocalDateTime) ob2[2]).getDayOfWeek().getValue());

        objects.stream().forEach((x) -> {
            DaysOfWeek daysOfWeek = new DaysOfWeek();
            daysOfWeek.setLottery((String) x[0]);
            daysOfWeek.setWinningNumbers((String) x[1]);
            daysOfWeek.setCreatedDate(
                    ((LocalDateTime) x[2]).format(DateTimeFormatter.ofPattern("YYYY-MM-dd", Locale.ENGLISH)));
            daysOfWeek.setDay(((LocalDateTime) x[2]).getDayOfWeek().name());
            daysOfWeek.setDrawingDate((String) x[3]);
            daysOfWeeks.add(daysOfWeek);

        });

        return daysOfWeeks;

    }

    /**
     * Update currencies.
     *
     * @throws IOException the io exception
     */
    public void updateCurrencies() throws IOException {
        currencyResultScrapingService.updateDollar();
        currencyResultScrapingService.updateEuro();
        currencyResultScrapingService.updateGourder();
    }

    private static HttpURLConnection conectHttpUrl(String urlValue) {
        try {
            URL url = new URL(urlValue);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(25000);
            connection.setRequestProperty("Acceptcharset", "en-us");
            connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            connection.setRequestProperty("charset", "EN-US");
            connection.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");

            return connection;

        } catch (MalformedURLException ex) {
            ex.getStackTrace();
        } catch (Exception ex) {
            ex.getStackTrace();
        }

        return null;
    }

    private static void disconectHttpUrl(HttpURLConnection conection) {

        try {
            conection.disconnect();
        } catch (Exception ex) {
            ex.getStackTrace();
        }
    }

    /**
     * Update all string.
     *
     * @param date the date
     * @return the string
     * @throws IOException    the io exception
     * @throws ParseException the parse exception
     */
    public String updateAll(String date) throws IOException, ParseException {
        LOGGER.warn("Updating all lotteries results for date: {} ...", date);
        resultJob.updateAll(date);
        LOGGER.info("All lotteries results are updated for date: {}", date);

        return "{Info: All lotteries updated}";
    }

    /**
     * Update result general response.
     *
     * @param lotteryType   the lottery type
     * @param drawingNumber the drawing number
     * @param drawingDate   the drawing date
     * @return the general response
     */
    public GeneralResponse updateResult(long lotteryType, String drawingNumber, String drawingDate) {

        LotteryResult lastResult = lotteryResultRepository.findByLotteryTypeIdAndStatusId(lotteryType, 1L);
        LotteryResult lotteryResult = null;
        GeneralResponse response;

        if (lastResult == null) {
            throw new IllegalArgumentException("LotteryType dont have any object with statusId = 1");
        }

        if (lastResult.getDrawingDate().equals(drawingDate)
                && lastResult.getWinningNumbers().length() == drawingNumber.length()) {

            lastResult.setWinningNumbers(drawingNumber);
            lotteryResultRepository.save(lastResult);

            response = new GeneralResponse(true, 204, "No Content");

        } else if (!lastResult.getDrawingDate().equals(drawingDate)
                && lastResult.getWinningNumbers().length() == drawingNumber.length()) {

            lastResult.setStatus(Status.Type.DISABLED.toStatus());
            lotteryResultRepository.save(lastResult);

            lotteryResult = new LotteryResult();
            lotteryResult.setDrawingDate(drawingDate);
            lotteryResult.setCreatedDate(LocalDateTime.now());
            lotteryResult.setWinningNumbers(drawingNumber);
            lotteryResult.setStatus(Status.Type.ENABLED.toStatus());
            lotteryResult.setLotteryType(new LotteryType(lotteryType));

            lotteryResultRepository.save(lotteryResult);

            response = new GeneralResponse(true, 204, "No Content");

        } else {

            response = new GeneralResponse(false, 400, "Bad Request");
        }

        return response;
    }

    /**
     * Get bote by lottery type loto.
     *
     * @param lotteryType the lottery type
     * @return the loto
     */
    public Loto getBoteByLotteryType(LotteryType lotteryType) {
        Status status = new Status(1L);
        return lotoRepository.findByType(lotteryType, status);
    }




}
