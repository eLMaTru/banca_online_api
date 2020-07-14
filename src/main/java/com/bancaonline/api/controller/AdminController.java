package com.bancaonline.api.controller;

import com.bancaonline.api.model.AuthDevice;
import com.bancaonline.api.service.ConsortiumTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.bancaonline.api.model.LotteryType;
import com.bancaonline.api.model.Status;
import com.bancaonline.api.service.LotteryTypeService;
import com.bancaonline.api.service.StatusService;
import com.bancaonline.api.util.EndpointConstants;

@Validated
@RestController
public class AdminController {

    @Autowired
    private LotteryTypeService lotteryTypeService;

    @Autowired
    private StatusService statusService;

    @Autowired
    private ConsortiumTokenService consortiumTokenService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = EndpointConstants.PATH_ADMIN + "/lottery-types")
    public ResponseEntity<List<LotteryType>> fetchAllLoteryTypes() {

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(lotteryTypeService.findAll());
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = EndpointConstants.PATH_ADMIN + "/status")
    public ResponseEntity<List<Status>> findAllStatus() {

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(statusService.findAll());
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = EndpointConstants.PATH_ADMIN + "/ips")
    public ResponseEntity<List<AuthDevice>> findAllIP() {

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(consortiumTokenService.findAllIP());
    }

}
