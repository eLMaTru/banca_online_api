package com.bancaonline.api.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * The type Lottery result.
 */
@Entity
@Table(name = "lottery_result")
public class LotteryResult implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "lottery_result_id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(name = "winning_numbers")
    private String winningNumbers;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "lottery_type_id")
    private LotteryType lotteryType;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "drawing_date")
    private String drawingDate;

    @Transient
    private List<String> winningNumbersList;

    public LotteryResult() {
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
     * Gets winning numbers list.
     *
     * @return the winning numbers list
     */
    public List<String> getWinningNumbersList() {
        return winningNumbersList;
    }

    /**
     * Sets winning numbers list.
     *
     * @param winningNumbersList the winning numbers list
     */
    public void setWinningNumbersList(List<String> winningNumbersList) {
        this.winningNumbersList = winningNumbersList;
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

    @Override
    public String toString() {
        return "[winningNumbers=" + winningNumbers + " - drawingDate=" + drawingDate + "]";
    }
}
