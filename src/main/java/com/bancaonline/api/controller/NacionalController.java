package com.bancaonline.api.controller;

import java.io.IOException;

import com.bancaonline.api.response.EntityResponse;
import com.bancaonline.api.service.NacionalService;
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
 * The type Nacional controller.
 */
@Validated
@RestController
@RequestMapping(EndpointConstants.PATH_NACIONAL)
public class NacionalController {

    /**
     * The constant logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(NacionalController.class);

    @Autowired
    private NacionalService nacionalService;


    /**
     * Gets quiniela nacional.
     *
     * @return the quiniela nacional
     * @throws IOException the io exception
     */
    @RequestMapping(value = Constants.ENDPOINT_QUINIELA_NACIONA, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityResponse> getQuinielaNacional( ) throws IOException {

        LOGGER.info("trying getQuinielaNacional");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new EntityResponse(nacionalService.getQuinielaNacional()));

    }

    /**
     * Gets gana mas.
     *
     * @return the gana mas
     * @throws IOException the io exception
     */
    @RequestMapping(value = Constants.ENDPOINT_GANA_MAS, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityResponse> getGanaMas( ) throws IOException {

        LOGGER.info("trying getGanaMas");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new EntityResponse(nacionalService.getGanaMas()));
    }

    /**
     * Gets juega mas gana mas.
     *
     * @return the juega mas gana mas
     * @throws IOException the io exception
     */
    @RequestMapping(value = Constants.ENDPOINT_JUEGA_MAS_GANA_MAS, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityResponse> getJuegaMasGanaMas( ) throws IOException {

        LOGGER.info("trying getJuegaMasGanaMas");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new EntityResponse(nacionalService.getJuegaMasGanaMas()));
    }

}
