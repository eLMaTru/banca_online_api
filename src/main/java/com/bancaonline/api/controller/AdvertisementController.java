package com.bancaonline.api.controller;

import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;

import com.bancaonline.api.model.Advertisement;
import com.bancaonline.api.model.dto.AdvertisementDto;
import com.bancaonline.api.service.AdvertisementService;
import com.bancaonline.api.util.EndpointConstants;

@Validated
@RestController
@RequestMapping(EndpointConstants.PATH_GENERAL)
public class AdvertisementController {

    /**
     * The constant logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AdvertisementController.class);

    @Autowired
    private AdvertisementService advertisementService;

    /**
     * Gets all Advertisements by status.
     *
     * @param statusId the status id
     * @return all Advertisements by status id
     */
    @GetMapping(value = "/advertisements", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, String>> getAllAdvertisementsByStatusId(
            @RequestParam("statusId") Long statusId) {

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(advertisementService.getAllAdvertisementsByStatusId(statusId));
    }

    /**
     * Gets all Advertisements by status and consortium id.
     *
     * @param consortiumId the consortium Id
     * @param statusId     the status id
     * @return all Advertisements by status id and consortium id
     */
    @GetMapping(value = "/consortium/{consortiumId}/advertisements", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, String>> getAllAdvertisementsByStatusIdAndConsortiumId(
            @RequestParam("statusId") Long statusId, @PathVariable("consortiumId") Long consortiumId) {

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(advertisementService.getAllAdvertisementsByStatusIdAndConsortiumId(statusId, consortiumId));
    }

    /**
     * Update an Advertisement image url by consortium Id and advertisement Id .
     *
     * @param consortiumId    the consortium Id
     * @param advertisementId the advertisement Id
     * @param imageUrl        the imageUrl
     * @return empty
     */
    @PutMapping(value = "/consortium/{consortiumId}/advertisement/{advertisementId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, String>> updateAdvertisementImageUrl(
            @PathVariable("consortiumId") Long consortiumId, @PathVariable("advertisementId") Long advertisementId,
            @NotBlank(message = "image url can't be empty") @RequestParam("imageUrl") String imageUrl) {

        Advertisement advertisement = advertisementService.getAdvertisementById(advertisementId);
        HttpStatus status = HttpStatus.NO_CONTENT;
        HashMap<String, String> map = new HashMap<>();

        if (advertisement != null) {
            advertisement.setImageUrl(imageUrl);
            advertisementService.save(advertisement);
        } else {
            status = HttpStatus.BAD_REQUEST;
            map.put("Status", status.name());
            map.put("Details", "Advertisement don't exist");
        }
        return ResponseEntity.status(status).body(map);
    }

    /**
     * Save Advertisement.
     *
     * @param consortiumId     the consortium Id
     * @param advertisementDto the advertisement Dto
     * @return empty
     */
    @PostMapping(value = "/consortium/{consortiumId}/advertisement", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, String>> saveAdvertisement(@PathVariable("consortiumId") Long consortiumId,
            @RequestBody AdvertisementDto advertisementDto) {

        Advertisement advertisement = new Advertisement(advertisementDto);
        advertisement.setCreatedDate(LocalDateTime.now());
        Advertisement savedAdvertisement = advertisementService.saveEnabled(advertisement);
        HttpStatus status = HttpStatus.NO_CONTENT;
        HashMap<String, String> map = new HashMap<>();

        if (savedAdvertisement != null) {
            LOGGER.info("advertisement: " + savedAdvertisement.toString() + " Saved successfully");
        } else {
            LOGGER.error("Error saving advertisement: " + advertisement.toString());
        }

        return ResponseEntity.status(status).body(map);
    }
}