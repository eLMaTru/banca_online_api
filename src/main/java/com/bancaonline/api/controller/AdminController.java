package com.bancaonline.api.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bancaonline.api.util.EndpointConstants;

@Validated
@RestController
@RequestMapping(EndpointConstants.PATH_ADMIN)
public class AdminController {

}
