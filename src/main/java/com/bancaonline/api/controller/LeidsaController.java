package com.bancaonline.api.controller;

import java.io.IOException;

import com.bancaonline.api.response.EntityResponse;
import com.bancaonline.api.service.LeidsaService;
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
 * <p>
 *
 * @version 1.0
 * @since 1.0
 */
@Validated
@RestController
@RequestMapping(EndpointConstants.PATH_LEIDSA)
public class LeidsaController {

    /**
     * The constant logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(LeidsaController.class);

    @Autowired
    private LeidsaService leidsaService;

    /**
     * Gets quiniela.
     *
     * @return the quiniela
     * @throws IOException the io exception
     */
    @RequestMapping(value = Constants.ENDPOINT_QUINIELA_REAL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityResponse> getQuiniela() throws IOException {

        LOGGER.info("trying getQuinielaLeidsa");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new EntityResponse(leidsaService.getQuinielaLeidsa()));
    }

    /**
     * Gets pega 3 mas.
     *
     * @return the pega 3 mas
     * @throws IOException the io exception
     */
    @RequestMapping(value = Constants.ENDPOINT_PEGA3MAS_LEIDA, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityResponse> getPega3Mas() throws IOException {

        LOGGER.info("trying getPega3Mas");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new EntityResponse(leidsaService.getPega3mas()));

    }

    /**
     * Gets loto mas.
     *
     * @return the loto mas
     * @throws IOException the io exception
     */
    @RequestMapping(value = Constants.ENDPOINT_LOTOMAS_LEIDA, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityResponse> getLotoMas() throws IOException {

        LOGGER.info("trying getLotoMas");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new EntityResponse(leidsaService.getLoto()));

    }

    /**
     * Gets loto pool.
     *
     * @return the loto pool
     * @throws IOException the io exception
     */
    @RequestMapping(value = Constants.ENDPOINT_LOTOPOOL_LEIDA, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityResponse> getLotoPool() throws IOException {

        LOGGER.info("trying getLotoPool");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new EntityResponse(leidsaService.getLotoPool()));

    }

    /**
     * Gets super quino.
     *
     * @return the super quino
     * @throws IOException the io exception
     */
    @RequestMapping(value = Constants.ENDPOINT_SUPER_QUINO_LEIDA, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityResponse> getSuperQuino() throws IOException {

        LOGGER.info("trying getSuperKino");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new EntityResponse(leidsaService.getSuperKino()));

    }

    /**
     * Update manual loto bote.
     *
     * @throws IOException the io exception
     */
    @RequestMapping(value = "/loto/bote", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateManualLotoBote() throws IOException {

        LOGGER.info("trying getSuperKino");
        leidsaService.updateLotoBote();
    }

}
