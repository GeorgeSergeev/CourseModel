package com.rstyle.softlab.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CustomErrorController implements ErrorController{
 
    @RequestMapping("/error")
    @ResponseBody
    public String handleError(HttpServletRequest request) {
    	Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        
        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
        
            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                return "404";
            }else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            	System.out.println("500!!!!!!!!!!!!!!!!!!!!!!!!");
                return "500";
            }
        }
    	
        return "404";
    }
 
    @Override
    public String getErrorPath() {
        return null;
    }
}