package com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoSuchUserException extends RuntimeException{

  public NoSuchUserException(String message) {
    super(message);
  }
}
