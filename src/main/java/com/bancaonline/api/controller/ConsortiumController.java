package com.bancaonline.api.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.bancaonline.api.model.Consortium;
import com.bancaonline.api.service.ConsortiumService;
import com.bancaonline.api.util.EndpointConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Consortium Controller
 */
@Validated
@RestController
@RequestMapping(EndpointConstants.PATH_CONSORTIUM)
public class ConsortiumController {

    /**
     * The constant logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsortiumController.class);

    /** consortiumService */
    @Autowired
    private ConsortiumService consortiumService;


    /**
     * Gets consortium.
     *
     * @param id the id
     * @return the consortium
     * @throws IOException the io exception
     */
    @RequestMapping(value = "consortium", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Consortium> getConsortium(@RequestParam(name = "id") Long id) throws IOException {

        LOGGER.info("trying getConsortium");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(consortiumService.findById(id));
    }

    /**
     * Update consortium response entity.
     *
     * @param consortium the consortium
     * @return the response entity
     * @throws IOException the io exception
     */
    @RequestMapping(value = "consortium", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Consortium> updateConsortium(@RequestBody Consortium consortium) throws IOException {

        LOGGER.info("trying getConsortium");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(consortiumService.saveOrUpdate(consortium));
    }

    /**
     * Save consortium response entity.
     *
     * @param consortium the consortium
     * @return the response entity
     * @throws IOException the io exception
     */
    @RequestMapping(value = "consortium", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Consortium> saveConsortium(@RequestBody @NotNull Consortium consortium) throws IOException {

        LOGGER.info("trying getConsortium");
        consortium.setCreatedDate(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(consortiumService.saveOrUpdate(consortium));
    }

    /**
     * Delete consortium.
     *
     * @param id the id
     * @throws IOException the io exception
     */
    @RequestMapping(value = "consortium", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteConsortium(@RequestParam(name = "id") Long id) throws IOException {

        LOGGER.info("trying getConsortium");
        consortiumService.delete(id);
    }

    /**
     * Gets all consortium.
     *
     * @return the all consortium
     * @throws IOException the io exception
     */
    @RequestMapping(value = "consortium/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Consortium>> getAllConsortium() throws IOException {

        LOGGER.info("trying getConsortium");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(consortiumService.findAll());
    }
}
