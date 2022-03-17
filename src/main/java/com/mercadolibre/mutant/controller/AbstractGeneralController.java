package com.mercadolibre.mutant.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public abstract class AbstractGeneralController {
	
	@Autowired
	private Environment varEntorno;

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private HttpServletResponse response;

	public abstract Logger getLog();

	public static final String TXT_ERROR_DATOS = "Por favor ingresar la informacion correctamente";

	private static final Logger LOG = LoggerFactory.getLogger(AbstractGeneralController.class);
	
    public Environment getVarEntorno() {
        return varEntorno;
    }

}
