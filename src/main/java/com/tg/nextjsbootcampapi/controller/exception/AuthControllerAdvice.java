package com.tg.nextjsbootcampapi.controller.exception;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.security.SignatureException;

@ControllerAdvice
public class AuthControllerAdvice {



  @ExceptionHandler(SignatureException.class)
  public void expHandler() {
    System.out.println("HA OCURIDO UNA EXCEPCIOOOOOON");
  }


}
