package com.cummins.scd.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ScdErrorController implements ErrorController {

	
    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object requestError = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        int statusCode = Integer.parseInt(requestError.toString());
        requestError = request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
        String statusMsg = requestError == null ? "No Error Message" : requestError.toString();
        requestError = request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
        String statusException = requestError == null ? "No Error Exception" : requestError.toString();
        requestError = request.getAttribute(RequestDispatcher.ERROR_SERVLET_NAME);
        String errorServlet = requestError == null ? "No Error Exception" : requestError.toString();
        requestError = request.getAttribute(RequestDispatcher.ERROR_EXCEPTION_TYPE);
        String errorExeptionType = requestError == null ? "No Error Exception" : requestError.toString();

        model.addAttribute("statusCode", statusCode);
        model.addAttribute("statusMsg", statusMsg);
        model.addAttribute("statusExp", statusException);
        model.addAttribute("errorServlet", errorServlet);
        model.addAttribute("errorExeptionType", errorExeptionType);

        return "error";
    }
    
}