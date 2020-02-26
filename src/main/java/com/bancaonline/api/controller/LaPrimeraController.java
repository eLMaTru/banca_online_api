package com.bancaonline.api.controller;

import com.bancaonline.api.response.EntityResponse;
import com.bancaonline.api.service.LaPrimeraService;
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
 * The type La primera controller.
 */
@Validated
@RestController
@RequestMapping(EndpointConstants.PATH_PRIMERA)
public class LaPrimeraController {

    /**
     * The constant logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(LaPrimeraController.class);

    /**  laPrimeraService  */
    @Autowired
    private LaPrimeraService laPrimeraService;

    /**
     * Gets quiniela.
     *
     * @return the quiniela
     * @throws IOException the io exception
     */
    @RequestMapping(value = Constants.ENDPOINT_QUINIELA_LA_PRIMERA, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityResponse> getQuiniela( ) throws IOException {

        LOGGER.info("trying getQuiniela");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new EntityResponse(laPrimeraService.getLaPrimeraQuiniela()));
    }
}
