package com.stage.sprint3.controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ModelAndView handleValidationErrors(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<ObjectError> errors = result.getAllErrors();

        // You can customize the error handling logic here, such as logging errors or displaying error messages to the user

        ModelAndView modelAndView = new ModelAndView("your-error-view");
        modelAndView.addObject("errors", errors);
        return modelAndView;
    }
}
