package com.bancaonline.api.model.dto;

/**
 * The type Currency dto.
 */
public class CurrencyDto {

    private String dollar;
    private String euro;
    private String gourde;
    private String date;

    /**
     * Instantiates a new Currency dto.
     *
     * @param dollar the dollar
     * @param euro   the euro
     * @param gourde the gourde
     */
    public CurrencyDto(String dollar, String euro, String gourde, String date) {
        this.dollar = dollar;
        this.euro = euro;
        this.gourde = gourde;
        this.date = date;

    }

    /**
     * Gets dollar.
     *
     * @return the dollar
     */
    public String getDollar() {
        return dollar;
    }

    /**
     * Sets dollar.
     *
     * @param dollar the dollar
     */
    public void setDollar(String dollar) {
        this.dollar = dollar;
    }

    /**
     * Gets euro.
     *
     * @return the euro
     */
    public String getEuro() {
        return euro;
    }

    /**
     * Sets euro.
     *
     * @param euro the euro
     */
    public void setEuro(String euro) {
        this.euro = euro;
    }

    /**
     * Gets gourde.
     *
     * @return the gourde
     */
    public String getGourde() {
        return gourde;
    }

    /**
     * Sets gourde.
     *
     * @param gourde the gourde
     */
    public void setGourde(String gourde) {
        this.gourde = gourde;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(String date) {
        this.date = date;
    }
}
