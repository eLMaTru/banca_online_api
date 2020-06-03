package com.bancaonline.api.controller;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.bancaonline.api.model.LotteryType;
import com.bancaonline.api.model.dto.CurrencyDto;
import com.bancaonline.api.model.dto.DaysOfWeek;
import com.bancaonline.api.response.EntityResponse;
import com.bancaonline.api.response.GeneralResponse;
import com.bancaonline.api.response.LotoResponse;
import com.bancaonline.api.service.EmailSender;
import com.bancaonline.api.service.GeneralService;
import com.bancaonline.api.util.Constants;
import com.bancaonline.api.util.EndpointConstants;
import com.bancaonline.api.util.ValidationPattern;

import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
     * @param date the string date representation to filter the url (valid format:
     *             12-02-2020)
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

    /**
     * Gets currency.
     *
     * @param viewHour the view hour
     * @return the currency
     * @throws IOException the io exception
     */
    @RequestMapping(value = "/verify/date", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean verifyDate(@RequestParam("viewDate") Integer viewHour) {

        LocalDateTime now = LocalDateTime.now();
        int minorHour = now.plusHours(-1).getHour();
        int mayorHour = now.plusHours(1).getHour();

        if (viewHour == now.getHour()) {
            return true;
        } else if (viewHour > minorHour && viewHour < mayorHour) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Update result response entity.
     *
     * @param lotteryType   the lottery type
     * @param drawingNumber the drawing number
     * @param drawingDate   the drawing date
     * @return the response entity
     */
    @RequestMapping(value = Constants.ENDPOINT_UPDATE_RESULT, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GeneralResponse> updateResult(@PathVariable("lotteryType") Integer lotteryType,
            @PathVariable("drawingNumber") String drawingNumber, @PathVariable("drawingDate") String drawingDate) {

        GeneralResponse response = generalService.updateResult(lotteryType, drawingNumber, drawingDate);

        return ResponseEntity.status(response.isSuccess() ? HttpStatus.NO_CONTENT : HttpStatus.BAD_REQUEST)
                .body(response);

    }

    @RequestMapping(value = Constants.VALIDATE_DEVICE_TOKEN, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean authDeviceResult(@PathVariable("token") String token, HttpServletRequest request) {

        String ip = request.getRemoteAddr();
        return generalService.validateDevice(ip, token);

    }

    @RequestMapping(value = "/generate/token/consortium_name/{name}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean generateToken(@PathVariable("name") String name,
            @NotNull @RequestParam("tokenType") Long tokenType) {

        return generalService.createToken(name, tokenType);

    }

    @GetMapping(value = Constants.FIND_FULL_URL, produces = MediaType.TEXT_PLAIN_VALUE)
    public String findFullUrl(@NotBlank @RequestParam("shortToken") String shortToken) {

        String fullUrl = generalService.findFullUrl(shortToken);
        return fullUrl;

    @RequestMapping(value = "bote", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LotoResponse> getBoteByType(@RequestParam( "LotteryTypeId") Long id) throws IOException {

        LOGGER.info("trying getBote");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new LotoResponse(generalService.getBoteByLotteryType(new LotteryType(id))));
    }

}
