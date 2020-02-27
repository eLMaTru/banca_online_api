package com.bancaonline.api.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import com.bancaonline.api.model.dto.CurrencyDto;
import com.bancaonline.api.model.dto.DaysOfWeek;
import com.bancaonline.api.service.EmailSender;
import com.bancaonline.api.service.GeneralService;
import com.bancaonline.api.util.Constants;
import com.bancaonline.api.util.EndpointConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Americana controller.
 */
@Validated
@RestController
@RequestMapping(EndpointConstants.PATH_GENERAL)
public class GeneralController {

    /**
     * The constant logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AmericanaController.class);

    /** fuelService */
    @Autowired
    private GeneralService generalService;

    @Autowired
    private EmailSender emailSender;

    /**
     * Gets quiniela.
     *
     * @return the String
     * @throws IOException the io exception
     */
    @RequestMapping(value = Constants.ENDPOINT_FUEL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getFuels() throws IOException {

        LOGGER.info("trying getFuels");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(generalService.getFuels());
    }

    /**
     * Test email response entity.
     *
     * @return the response entity
     */
    @GetMapping(value = "/test/send/email", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> testEmail() {

        String subject = "Testing email sending";
        String message = "This is an email sending test from banca-online-api Rest Api. Please don't replay it.";
        emailSender.prepareAndSend("jorgeluisdelossantoslopez@gmail.com", subject, message);
        emailSender.prepareAndSend("anderson.ferreiras.mota@gmail.com", subject, message);
        emailSender.prepareAndSend("joelcontreras21@gmail.com", subject, message);

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body("{\"status\": \"send\", \"subject\": \"Testing email sending\","
                        + "\"message\": \"This is an email sending test from banca-online-api Rest Api. Please don't replay it.\"}");
    }

    /**
     * Gets all lottery by status.
     *
     * @param statusId the status id
     * @return the all lottery by status
     */
    @RequestMapping(value = "/all/lottery", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, String>> getAllLotteryByStatus(@RequestParam("statusId") Long statusId) {

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(generalService.getAllLotteryByStatus(statusId));
    }

    /**
     * Gets days of week.
     *
     * @return the days of week
     */
    @RequestMapping(value = "/days/of/week", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DaysOfWeek>> getDaysOfWeek() {

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(generalService.getDaysOfWeek());
    }

    /**
     * Gets currency.
     *
     * @return the currency
     * @throws IOException the io exception
     */
    @RequestMapping(value = "/currency", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CurrencyDto>> getCurrency() throws IOException {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(generalService.getCurrencies());
    }

    /**
     * Update all lotteries results.
     *
     * @param date the string date representation to filter the url (valid format:             12-02-2020)
     * @return successful message
     * @throws IOException    the io exception
     * @throws ParseException the parse exception
     */
    @PutMapping(value = "/all/lottery", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateAll(@RequestParam String date) throws IOException, ParseException {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(generalService.updateAll(date));
    }

    /**
     * Update currency response entity.
     *
     * @return the response entity
     * @throws IOException the io exception
     */
    @RequestMapping(value = "/currency", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateCurrency() throws IOException {
        generalService.updateCurrencies();
    }

}