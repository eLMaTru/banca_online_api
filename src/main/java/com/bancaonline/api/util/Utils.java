package com.bancaonline.api.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * The type Utils.
 */
public class Utils {

    /**
     * Convert date us format string.
     *
     * @param date the date
     * @return the string
     */
    public static String convertDateUSFormat(String date) {

        String[] dates = date.split("-");
        String mon = dates[1];
        String day = dates[0];
        String year = dates[2];
        date = year + "-" + mon + "-" + day;
        return date;
    }

    /**
     * Gets month value int.
     *
     * @param mon the mon
     * @return the month value int
     */
    public static String getMonthValueInt(String mon) {
        String month = "0";
        switch (mon) {
            case "Ene":
                month = "01";
                break;
            case "Feb":
                month = "02";
                break;
            case "Mar":
                month = "03";
                break;
            case "Abr":
                month = "04";
                break;
            case "May":
            case "Mayo":
                month = "05";
                break;
            case "Jun":
                month = "06";
                break;
            case "Jul":
                month = "07";
                break;
            case "Ago":
                month = "08";
                break;
            case "Sep":
                month = "09";
                break;
            case "Oct":
                month = "10";
                break;
            case "Nov":
                month = "11";
                break;
            case "Dic":
                month = "12";
                break;
        }
        return month;
    }

    /**
     * Build string result string.
     *
     * @param results the results
     * @return the string
     */
    public static String buildStringResult(List<String> results) {

        String delimiter, result = "";
        if (results != null && results.size() > 0) {
            for (int i = 0; i < results.size() - 1; i++) {
                String string = results.get(i);
                delimiter = i == results.size() - 2 ? "" : ", ";
                result += string + delimiter;
                if(string == null || string.isEmpty()) {

                    result = "fail";
                    break;
                }

            }

        }
        return result;

    }

    /**
     * Is correct result length boolean.
     *
     * @param lastResult the result length
     * @param scrapingResult   the last result
     * @return the boolean
     */
    public static boolean isCorrectResultLength(String lastResult,List<String> scrapingResult){

            String strToday = new SimpleDateFormat("YYYY-MM-dd").format(new Date());

            String[] length = lastResult.split(",");

            if ((scrapingResult.size() - 1) == length.length && strToday.equals(scrapingResult.get(scrapingResult.size() - 1))){
                return false;
            }else{
                return true;
            }
    }

}
