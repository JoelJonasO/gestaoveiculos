package com.tinnova.gestaoveiculos.gestaoveiculos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class MarcaNotFoundException extends RuntimeException {
  public MarcaNotFoundException(String msg) {
    super(msg);
  }
}
