package com.bancaonline.api.response;

import com.bancaonline.api.model.LotteryResult;
import java.util.List;

/**
 * The type Entity response.
 */
public class EntityResponse {

    private String id;

    private String winnigNumbers;

    private long statusId;

    private long lotteryTypeId;

    private String createdDate;

    private String drawingDate;

    private List<String> winnigNumbersList;

    public EntityResponse(LotteryResult lotteryResult) {

        if (lotteryResult != null) {
            this.id = lotteryResult.getId();
            this.winnigNumbers = lotteryResult.getWinningNumbers();
            this.statusId = lotteryResult.getStatus().getId();
            this.lotteryTypeId = lotteryResult.getLotteryType().getId();
            this.createdDate = lotteryResult.getCreatedDate().toString();
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
    public long getStatusId() {
        return statusId;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatusId(long statusId) {
        this.statusId = statusId;
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

    public long getLotteryTypeId() {
        return lotteryTypeId;
    }

    public void setLotteryTypeId(long lotteryTypeId) {
        this.lotteryTypeId = lotteryTypeId;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getWinnigNumbers() {
        return winnigNumbers;
    }

    public List<String> getWinnigNumbersList() {
        return winnigNumbersList;
    }

}
