package team2.test.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import team2.test.utils.ResponseType;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(Exception.class)
  public ResponseType.Result<Object> unknownServerError(Exception e) {

    return ResponseType.error(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
