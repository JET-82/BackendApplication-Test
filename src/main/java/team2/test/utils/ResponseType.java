package team2.test.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseType {
  public static <T> Result<T> success() {
    return new Result<>(HttpStatus.NO_CONTENT.value(), true, null, null);
  }

  public static <T> Result<T> success(T response) {
    return new Result<>(HttpStatus.OK.value(), true, response, null);
  }

  public static Result<Object> error(String message, HttpStatus status) {
    return new Result<>(status.value(), false, null, new Error(message));
  }

  @JsonInclude(JsonInclude.Include.NON_NULL)
  @AllArgsConstructor
  @Getter
  public static class Result<T> {
    private int status;
    private boolean success;
    private T response;
    private Error error;
  }

  @AllArgsConstructor
  @Getter
  public static class Error {
    private String message;
  }
}
