package com.bancaonline.api.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

import java.time.LocalDateTime;

/**
 * The Advertisement class.
 */
@Entity
@Table(name = "advertisement")
public class Advertisement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "advertisement_id")
    private Long id;

    @Column(name = "advertisement_info", length = 3000)
    private String advertisementInfo;

    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "consortium_id")
    @NotNull
    private Consortium consortium;

    @ManyToOne
    @JoinColumn(name = "status_id")
    @NotNull
    private Status status;

    @ManyToOne
    @JoinColumn(name = "advertisement_type_id")
    @NotNull
    private AdvertisementType advertisementType;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    public String getAdvertisementInfo() {
        return advertisementInfo;
    }

    public void setAdvertisementInfo(String advertisementInfo) {
        this.advertisementInfo = advertisementInfo;
    }

    public Consortium getConsortium() {
        return consortium;
    }

    public void setConsortium(Consortium consortium) {
        this.consortium = consortium;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public AdvertisementType getAdvertisementType() {
        return advertisementType;
    }

    public void setAdvertisementType(AdvertisementType advertisementType) {
        this.advertisementType = advertisementType;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

}
