package team2.test.exceptions;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import team2.test.utils.ResponseType;

@Slf4j
@Tag(name = "Errors", description = "에러 응답")
@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(EntityNotFoundException.class)
  @ApiResponse(
      responseCode = "400",
      description = "Invalid ID.",
      content = @Content(schema = @Schema(implementation = ResponseType.Error.class)))
  public ResponseEntity<ResponseType.Result<Object>> handleEntityNotFoundException(
      EntityNotFoundException e) {
    log.warn("{}", e.getMessage());
    return new ResponseEntity<>(
        ResponseType.error("Invalid ID", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  @ApiResponse(
      responseCode = "400",
      description = "Invalid Request Message.",
      content = @Content(schema = @Schema(implementation = ResponseType.Error.class)))
  public ResponseEntity<ResponseType.Result<Object>> handleHttpMessageNotReadableException(
      HttpMessageNotReadableException e) {
    log.warn("{}", e.getMessage());
    return new ResponseEntity<>(
        ResponseType.error("Invalid Request Message.", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(RuntimeException.class)
  @ApiResponse(responseCode = "500", description = "Internal Server Error")
  public ResponseEntity<ResponseType.Result<Object>> handleInternalServerError(RuntimeException e) {
    log.warn("{}", e.getMessage());
    return new ResponseEntity<>(
        ResponseType.error(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
