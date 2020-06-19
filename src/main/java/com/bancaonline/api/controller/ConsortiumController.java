package com.bancaonline.api.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

import com.bancaonline.api.model.AuthDevice;
import com.bancaonline.api.model.Consortium;
import com.bancaonline.api.model.ConsortiumToken;
import com.bancaonline.api.service.ConsortiumService;
import com.bancaonline.api.service.ConsortiumTokenService;
import com.bancaonline.api.util.Constants;
import com.bancaonline.api.util.EndpointConstants;

import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    private ConsortiumTokenService consortiumTokenService;

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

        LOGGER.info("trying save consortium");
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

        LOGGER.info("trying delete consortium");
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

        LOGGER.info("trying get All Consortiums");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(consortiumService.findAll());
    }

    @RequestMapping(value = "/token/consortium/{name}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ConsortiumToken> generateTokenByConsortiumName(@PathVariable("name") String name,
            @NotNull @RequestParam("tokenType") Long tokenType) {

        return ResponseEntity.status(HttpStatus.CREATED).body(consortiumTokenService.createToken(name, tokenType));

    }

    @RequestMapping(value = "/token/consortium", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ConsortiumToken>> findTokenByConsortiumId(@RequestParam("id") Long id) {
        List<ConsortiumToken> list = consortiumTokenService.findByConsortiumId(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(list);
    }

    @RequestMapping(value = "/token/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean deleteTokenById(@PathVariable("id") Long id, HttpServletRequest request) {

        Boolean deleted = false;
        consortiumTokenService.delete(id);
        deleted = true;

        return deleted;

    }

    @RequestMapping(value = Constants.VALIDATE_DEVICE_TOKEN, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean authDeviceResult(@PathVariable("token") String token, HttpServletRequest request) {

        String ip = request.getRemoteAddr();
        return consortiumTokenService.validateDevice(ip, token);

    }

    @GetMapping(value = Constants.FIND_FULL_URL, produces = MediaType.TEXT_PLAIN_VALUE)
    public String findFullUrl(@NotBlank @RequestParam("shortToken") String shortToken) {

        String fullUrl = consortiumTokenService.findFullUrl(shortToken);
        return fullUrl;
    }

    @RequestMapping(value = "/token/status/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ConsortiumToken>> findByTokenStatusId(@PathVariable("id") Long statusId) {
        List<ConsortiumToken> list = consortiumTokenService.findByStatusId(statusId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(list);
    }

    @RequestMapping(value = "/token/consortium/{name}/status/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ConsortiumToken>> findByConsortiumNameAndTokenStatusId(
            @PathVariable("name") String consortiumName, @PathVariable("id") Long statusId) {
        List<ConsortiumToken> list = consortiumTokenService.findByConsortiumNameAndTokenStatusId(consortiumName,
                statusId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(list);
    }

    @RequestMapping(value = "/token/consortium/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ConsortiumToken>> findTokenByConsortiumName(
            @PathVariable("name") String consortiumName) {
        List<ConsortiumToken> list = consortiumTokenService.findByConsortiumName(consortiumName);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(list);
    }

    @RequestMapping(value = "/token/consortium/{name}/used/{is_used}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ConsortiumToken>> findTokenByAvailabilityAndConsortiumName(
            @PathVariable("is_used") boolean isUsed, @PathVariable("name") String consortiumName) {
        List<ConsortiumToken> list = consortiumTokenService.findByIsUsedAndConsortiumName(isUsed, consortiumName);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(list);
    }

    @RequestMapping(value = "/token/used/{is_used}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ConsortiumToken>> findTokensByAvailability(@PathVariable("is_used") boolean isUsed) {
        List<ConsortiumToken> list = consortiumTokenService.findByIsUsed(isUsed);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(list);
    }

    @DeleteMapping(value = "/device/{token}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean cleanIpsByToken(@PathVariable("token") String token) {
        return consortiumTokenService.cleanIpsByToken(token);
    }

    @GetMapping(value = "/device/{token}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AuthDevice>> findAuthDevicesByToken(@PathVariable("token") String token) {

        List<AuthDevice> list = consortiumTokenService.findAuthDevicesByToken(token);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(list);
    }
}
