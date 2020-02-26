package com.bancaonline.api.controller;

import java.io.IOException;

import com.bancaonline.api.response.EntityResponse;
import com.bancaonline.api.service.LotekaService;
import com.bancaonline.api.util.Constants;
import com.bancaonline.api.util.EndpointConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Loteka controller.
 */
@Validated
@RestController
@RequestMapping(EndpointConstants.PATH_LOTEKA)
public class LotekaController {

    /**
     * The constant logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(LotekaController.class);

    /**  for test */
    @Autowired
    private LotekaService lotekaService;

    /**
     * Gets quiniela loteka.
     *
     * @return the quiniela loteka
     * @throws IOException the io exception
     */
    @RequestMapping(value = Constants.ENDPOINT_QUINIELA_LOTEKA, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityResponse> getQuinielaLoteka( ) throws IOException {

        LOGGER.info("trying getQuinielaLoteka");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new EntityResponse(lotekaService.getQuinielaLoteka()));
    }

    /**
     * Gets mega chance.
     *
     * @return the mega chance
     * @throws IOException the io exception
     */
    @RequestMapping(value = Constants.ENDPOINT_MEGA_CHANCE, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityResponse> getMegaChance( ) throws IOException {

        LOGGER.info("trying getMegaChance");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new EntityResponse(lotekaService.getMegaChance()));
    }

    /**
     * Gets mega lotto.
     *
     * @return the mega lotto
     * @throws IOException the io exception
     */
    @RequestMapping(value = Constants.ENDPOINT_MEGA_LOTTO, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityResponse> getMegaLotto( ) throws IOException {

        LOGGER.info("trying getMegaLotto");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new EntityResponse(lotekaService.getMegaLoto()));
    }

}
