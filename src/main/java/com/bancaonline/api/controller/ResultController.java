package com.bancaonline.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bancaonline.api.response.EntityResponse;
import com.bancaonline.api.service.ResultService;
import com.bancaonline.api.util.EndpointConstants;

@Validated
@RestController
@RequestMapping(EndpointConstants.PATH_RESULT)
public class ResultController {
	
	@Autowired
	ResultService resultService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
     public ResponseEntity<List<EntityResponse>> fetchLotteryResultByStatus(@RequestParam("statusId") long statusId, @RequestParam("typeId") long typeId){
		
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(resultService.changeResultListToEntityResponseList(typeId == 0 ? resultService.findResultByStatus(statusId) : resultService.findResultByStatusIdAndTypeId(statusId, typeId)));
	}
}
