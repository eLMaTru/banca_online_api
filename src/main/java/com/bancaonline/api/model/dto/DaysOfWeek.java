package com.bancaonline.api.model.dto;


/**
 * The type Days of week.
 */
public class DaysOfWeek {

    private String lottery;

    private String winningNumbers;

    private String createdDate;

    private String drawingDate;

    private String Day;

    /**
     * Instantiates a new Days of week.
     */
    public DaysOfWeek(){}

    /**
     * Gets lottery.
     *
     * @return the lottery
     */
    public String getLottery() {
        return lottery;
    }

    /**
     * Sets lottery.
     *
     * @param lottery the lottery
     */
    public void setLottery(String lottery) {
        this.lottery = lottery;
    }

    /**
     * Gets winning numbers.
     *
     * @return the winning numbers
     */
    public String getWinningNumbers() {
        return winningNumbers;
    }

    /**
     * Sets winning numbers.
     *
     * @param winningNumbers the winning numbers
     */
    public void setWinningNumbers(String winningNumbers) {
        this.winningNumbers = winningNumbers;
    }

    /**
     * Gets created date.
     *
     * @return the created date
     */
    public String getCreatedDate() {
        return createdDate;
    }

    /**
     * Sets created date.
     *
     * @param createdDate the created date
     */
    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * Gets day.
     *
     * @return the day
     */
    public String getDay() {
        return Day;
    }

    /**
     * Sets day.
     *
     * @param day the day
     */
    public void setDay(String day) {
        Day = day;
    }


    /**
     * Gets drawing date.
     *
     * @return the drawing date
     */
    public String getDrawingDate() {
        return drawingDate;
    }

    /**
     * Sets drawing date.
     *
     * @param drawingDate the drawing date
     */
    public void setDrawingDate(String drawingDate) {
        this.drawingDate = drawingDate;
    }
}
