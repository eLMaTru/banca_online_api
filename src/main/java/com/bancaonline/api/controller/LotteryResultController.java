package com.bancaonline.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bancaonline.api.response.GeneralResponse;
import com.bancaonline.api.service.LotteryResultService;
import com.bancaonline.api.util.Constants;
import com.bancaonline.api.util.EndpointConstants;

@Validated
@RestController
@RequestMapping(EndpointConstants.PATH_LOTTERY_RESULT)
public class LotteryResultController {


	@Autowired
	private LotteryResultService lotteryResultService;
	
	
	@RequestMapping(value = Constants.ENDPOINT_UPDATE_RESULT, method = RequestMethod.PUT , produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<GeneralResponse> updateResult(@PathVariable("lotteryType") int lotteryType, 
			@PathVariable("drawingNumber") String drawingNumber, @PathVariable("drawingDate") String drawingDate) {
		
		GeneralResponse response = lotteryResultService.updateResult(lotteryType, drawingNumber, drawingDate);
		
		return ResponseEntity.status(response.isSuccess() ? HttpStatus.NO_CONTENT : HttpStatus.BAD_REQUEST).body(response);
		   
	}	

}
