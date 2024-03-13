package team2.test.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
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
  @Schema(description = "응답 객체")
  public static class Result<T> {
    @Schema(description = "상태 코드")
    private int status;

    @Schema(description = "성공 여부")
    private boolean success;

    @Schema(description = "응답 데이터")
    private T response;

    @Schema(description = "에러 발생시 에러 객체 포함")
    private Error error;
  }

  @JsonInclude(JsonInclude.Include.NON_NULL)
  @AllArgsConstructor
  @Getter
  @Schema(description = "Error")
  public static class Error {
    @Schema(description = "에러 메세지")
    private String message;
  }
}
