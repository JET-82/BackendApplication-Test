package team2.test.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import team2.test.utils.ResponseType;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ResponseType.Result<Object>> unknownServerError(Exception e) {
    ResponseType.Result<Object> apiResult =
        ResponseType.error(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    return new ResponseEntity<>(apiResult, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
