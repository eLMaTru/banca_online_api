package com.bancaonline.api.response;

import com.bancaonline.api.model.LotteryResult;
import com.bancaonline.api.model.LotteryType;
import com.bancaonline.api.model.Status;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The type Entity response.
 */
public class EntityResponse {

    private String id;

    private String winnigNumbers;

    private Status status;

    private LotteryType lotteryType;

    private LocalDateTime createdDate;

    private String drawingDate;

    private List<String> winnigNumbersList;

    public EntityResponse(LotteryResult lotteryResult) {

        if (lotteryResult != null) {
            this.id = lotteryResult.getId();
            this.winnigNumbers = lotteryResult.getWinningNumbers();
            this.status = lotteryResult.getStatus();
            this.lotteryType = lotteryResult.getLotteryType();
            this.createdDate = lotteryResult.getCreatedDate();
            this.drawingDate = lotteryResult.getDrawingDate();
            this.winnigNumbersList = lotteryResult.getWinningNumbersList();
        }

    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets winnig numbers.
     *
     * @return the winnig numbers
     */
    public String getWinningNumbers() {
        return winnigNumbers;
    }

    /**
     * Sets winnig numbers.
     *
     * @param winnigNumbers the winnig numbers
     */
    public void setWinnigNumbers(String winnigNumbers) {
        this.winnigNumbers = winnigNumbers;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Gets lottery type.
     *
     * @return the lottery type
     */
    public LotteryType getLotteryType() {
        return lotteryType;
    }

    /**
     * Sets lottery type.
     *
     * @param lotteryType the lottery type
     */
    public void setLotteryType(LotteryType lotteryType) {
        this.lotteryType = lotteryType;
    }

    /**
     * Gets created date.
     *
     * @return the created date
     */
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    /**
     * Sets created date.
     *
     * @param createdDate the created date
     */
    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
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

    /**
     * Gets winnig numbers list.
     *
     * @return the winnig numbers list
     */
    public List<String> getWinningNumbersList() {
        return winnigNumbersList;
    }

    /**
     * Sets winnig numbers list.
     *
     * @param winnigNumbersList the winnig numbers list
     */
    public void setWinnigNumbersList(List<String> winnigNumbersList) {
        this.winnigNumbersList = winnigNumbersList;
    }
}
