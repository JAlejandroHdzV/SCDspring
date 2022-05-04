package com.cummins.scd.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/_api")
public class LocaleController {
	
	@GetMapping("/global/locale")
	public String locale(HttpServletRequest request){
		System.out.println("entra a localeController");
		String ultimaUrl= request.getHeader("referer");
		return "redirect:".concat(ultimaUrl);
	}
}
