package com.bancaonline.api.service;

import java.util.HashMap;
import java.util.List;

import com.bancaonline.api.model.Advertisement;
import com.bancaonline.api.model.dto.AdvertisementDto;
import com.bancaonline.api.repository.AdvertisementRepository;
import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bancaonline.api.model.Status;

@Service
public class AdvertisementService {

    @Autowired
    private AdvertisementRepository advertisementRepository;

    public HashMap<String, String> getAllAdvertisementsByStatusId(Long statusId) {
        HashMap<String, String> hashMap = new HashMap<>();

        List<Advertisement> Advertisements = advertisementRepository.findByStatusId(statusId);

        Gson gson = new Gson();

        Advertisements.stream().forEach(x -> hashMap.put("" + x.getId(), gson.toJson(new AdvertisementDto(x))));
        return hashMap;
    }

    public HashMap<String, String> getAllAdvertisementsByStatusIdAndConsortiumId(Long statusId, Long consortiumId) {
        HashMap<String, String> hashMap = new HashMap<>();

        List<Advertisement> Advertisements = advertisementRepository.findByStatusIdAndConsortiumId(statusId,
                consortiumId);

        Gson gson = new Gson();

        Advertisements.stream().forEach(x -> hashMap.put("" + x.getId(), gson.toJson(new AdvertisementDto(x))));
        return hashMap;
    }

    public Advertisement save(Advertisement advertisement) {
        Advertisement savedAdvertisement = advertisementRepository.save(advertisement);
        return savedAdvertisement;
    }

    public Advertisement saveEnabled(Advertisement advertisement) {

        advertisement.setStatus(Status.Type.ENABLED.toStatus());
        Advertisement savedAdvertisement = advertisementRepository.save(advertisement);
        return savedAdvertisement;
    }

    public Advertisement saveDisabled(Advertisement advertisement) {

        advertisement.setStatus(Status.Type.DISABLED.toStatus());
        Advertisement savedAdvertisement = advertisementRepository.save(advertisement);
        return savedAdvertisement;
    }

    public Advertisement getAdvertisementById(Long advertisementId) {

        Advertisement advertisement = advertisementRepository.findOne(advertisementId);
        return advertisement;
    }

}
