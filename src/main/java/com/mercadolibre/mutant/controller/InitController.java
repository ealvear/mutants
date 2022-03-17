package com.mercadolibre.mutant.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class InitController {
	
	 @RequestMapping(value = "/", method = RequestMethod.GET)
	    public ModelAndView inicioAplicacionVisor(Model model) {
	        return new ModelAndView("redirect:index.html#");
	    }

}
