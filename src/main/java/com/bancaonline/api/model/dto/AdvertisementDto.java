package com.bancaonline.api.model.dto;

import com.bancaonline.api.model.Advertisement;

import java.io.Serializable;

public class AdvertisementDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long advertisementId;
    private String advertisementInfo;
    private String imageUrl;
    private Long consortiumId;
    private Long advertisementTypeId;

    public AdvertisementDto() {

    }

    public AdvertisementDto(Advertisement advertisement) {
        this.advertisementId = advertisement.getId();
        this.advertisementInfo = advertisement.getAdvertisementInfo();
        this.imageUrl = advertisement.getImageUrl() == null ? "" : advertisement.getImageUrl();
        this.consortiumId = advertisement.getConsortium().getId();
        this.advertisementTypeId = advertisement.getAdvertisementType().getId();
    }

    public Long getAdvertisementId() {
        return advertisementId;
    }

    public void setAdvertisementId(Long advertisementId) {
        this.advertisementId = advertisementId;
    }

    public String getAdvertisementInfo() {
        return advertisementInfo;
    }

    public void setAdvertisementInfo(String advertisementInfo) {
        this.advertisementInfo = advertisementInfo;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getConsortiumId() {
        return consortiumId;
    }

    public void setConsortiumId(Long consortiumId) {
        this.consortiumId = consortiumId;
    }

    public Long getAdvertisementTypeId() {
        return advertisementTypeId;
    }

    public void setAdvertisementTypeId(Long advertisementTypeId) {
        this.advertisementTypeId = advertisementTypeId;
    }

}
