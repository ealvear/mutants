package com.mercadolibre.mutant.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {
	
	
	@RequestMapping("/status")
    public String estadoServicio(HttpServletRequest request, HttpServletResponse response) {
    	
    	return "Server is running ... ";
    }

}

