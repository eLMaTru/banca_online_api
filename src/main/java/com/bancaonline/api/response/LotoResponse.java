package com.bancaonline.api.response;

import com.bancaonline.api.model.Loto;
import com.bancaonline.api.model.LotteryType;
import com.bancaonline.api.model.Status;


/**
 * The type Loto response.
 */
public class LotoResponse {

    private String id;

    private String name;

    private String description;

    private String bote;

    private LotteryType lotteryType;

    private Status status;

    private String currencyCode;

    /**
     * Instantiates a new Loto response.
     *
     * @param loto the loto
     */
    public LotoResponse(Loto loto) {
        if(loto != null){
        this.id =loto.getId();
        this.name = loto.getName();
        this.description = loto.getDescription();
        this.bote = loto.getBote();
        this.lotteryType = loto.getLotteryType();
        this.status = loto.getStatus();
        this.currencyCode = loto.getCurrencyCode();
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
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets bote.
     *
     * @return the bote
     */
    public String getBote() {
        return bote;
    }

    /**
     * Sets bote.
     *
     * @param bote the bote
     */
    public void setBote(String bote) {
        this.bote = bote;
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
     * Gets currency code.
     *
     * @return the currency code
     */
    public String getCurrencyCode() {
        return currencyCode;
    }

    /**
     * Sets currency code.
     *
     * @param currencyCode the currency code
     */
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
}
