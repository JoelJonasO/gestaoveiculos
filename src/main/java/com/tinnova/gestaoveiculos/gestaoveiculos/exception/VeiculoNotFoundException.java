package com.tinnova.gestaoveiculos.gestaoveiculos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class VeiculoNotFoundException extends RuntimeException {
  public VeiculoNotFoundException(String msg) {
    super(msg);
  }
}
