package com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception.customexceptions;


import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotFoundException extends RuntimeException {
  private String path;

  public NotFoundException(String message, String path) {
    super(message);
    this.path = path;
  }
}
