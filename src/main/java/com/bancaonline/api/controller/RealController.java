package com.bancaonline.api.controller;

import com.bancaonline.api.response.EntityResponse;
import com.bancaonline.api.service.RealService;
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
 * <p>
 * RealController class.
 *
 * @version 1.0
 * @since 1.0
 */
@Validated
@RestController
@RequestMapping(EndpointConstants.PATH_REAL)
public class RealController {

    /**
     * The constant logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RealController.class);

    /**  realService */
    @Autowired
    private RealService realService;


    /**
     * Gets quiniela.
     *
     * @return the quiniela
     * @throws IOException the io exception
     */
    @RequestMapping(value = Constants.ENDPOINT_QUINIELA_REAL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityResponse> getQuiniela( ) throws IOException {

        LOGGER.info("trying getQuinielaReal");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new EntityResponse(realService.getQuinielaReal()));

    }

    /**
     * Gets loto real.
     *
     * @return the loto real
     * @throws IOException the io exception
     */
    @RequestMapping(value = Constants.ENDPOINT_LOTO_REAL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityResponse> getLotoReal( ) throws IOException {

        LOGGER.info("trying getLotoReal");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new EntityResponse(realService.getLotoReal()));

    }

    /**
     * Update manual loto bote.
     *
     * @throws IOException the io exception
     */
    @RequestMapping(value = "/real/bote", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateManualLotoBote( ) throws IOException {

        LOGGER.info("trying update bote");
        realService.updateLotoRealBote();
    }


}
