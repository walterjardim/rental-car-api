package br.org.rentalcarapi.infra.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.org.rentalcarapi.domain.exceptions.EmailAlreadyExistsException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
  private final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value 
      = { EmailAlreadyExistsException.class })
    protected ResponseEntity<Object> handleUserExistsException(Exception exception) {
      LOG.error(exception.getMessage(), exception);
      return ResponseEntity
          .status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(exception.getMessage());
    }

  @ExceptionHandler(RuntimeException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseEntity<Object> handleAllUncaughtException(RuntimeException exception, WebRequest request) {
    LOG.error(exception.getMessage(), exception);
    return ResponseEntity
          .status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("Unknow error");
  }
}
