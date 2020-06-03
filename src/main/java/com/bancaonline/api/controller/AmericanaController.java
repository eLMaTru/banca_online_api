package com.bancaonline.api.controller;

import com.bancaonline.api.response.EntityResponse;
import com.bancaonline.api.service.AmericanaService;
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

import java.io.IOException;

/**
 * The type Americana controller.
 */
@Validated
@RestController
@RequestMapping(EndpointConstants.PATH_AMERICANA)
public class AmericanaController {

    /**
     * The constant logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AmericanaController.class);

    /** americanaService */
    @Autowired
    private AmericanaService americanaService;

    /**
     * Gets nueva york tarde.
     *
     * @return the nueva york tarde
     * @throws IOException the io exception
     */
    @RequestMapping(value = Constants.ENDPOINT_NUEVA_YORK_TARDE, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityResponse> getNuevaYorkTarde() throws IOException {

        LOGGER.info("trying getNuevaYorkTarde");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new EntityResponse(americanaService.getNewYorkTarde()));
    }

    /**
     * Gets nueva york noche.
     *
     * @return the nueva york noche
     * @throws IOException the io exception
     */
    @RequestMapping(value = Constants.ENDPOINT_NUEVA_YORK_NOCHE, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityResponse> getNuevaYorkNoche() throws IOException {

        LOGGER.info("trying getNuevaYorkNoche");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new EntityResponse(americanaService.getNewYorkNoche()));

    }

    /**
     * Gets mega million.
     *
     * @return the mega million
     * @throws IOException the io exception
     */
    @RequestMapping(value = Constants.ENDPOINT_MEGA_MILLION, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityResponse> getMegaMillion() throws IOException {

        LOGGER.info("trying getMegaMillion");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new EntityResponse(americanaService.getMegaMillions()));

    }

    /**
     * Gets power ball.
     *
     * @return the power ball
     * @throws IOException the io exception
     */
    @RequestMapping(value = Constants.ENDPOINT_POWER_BALL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityResponse> getPowerBall() throws IOException {

        LOGGER.info("trying getPowerBall");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new EntityResponse(americanaService.getPowerBall()));

    }

    /**
     * Update manual mega milion.
     *
     * @throws IOException the io exception
     */
    @RequestMapping(value = "/mega-million/bote", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateManualMegaMilion( ) throws IOException {

        LOGGER.info("trying update bote");
        americanaService.updateManualMeagaMilionBote();
    }

    /**
     * Update manual power ball.
     *
     * @throws IOException the io exception
     */
    @RequestMapping(value = "/power-ball/bote", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateManualPowerBall( ) throws IOException {

        LOGGER.info("trying update bote");
        americanaService.updatePowerBallBote();
    }
}
