package team2.test.food;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import team2.test.utils.ResponseType;

@Slf4j
@OpenAPIDefinition(
    info = @Info(title = "Foods", description = "Food 도메인 API"),
    tags = {@Tag(name = "Foods", description = "Food 도메인 태그")})
@RestController
@RequestMapping("/foods")
@RequiredArgsConstructor
public class FoodRestController {
  private final FoodService foodService;

  @Operation(
      method = "GET",
      summary = "음식 리스트 조회.",
      description = "음식 리스트 반환.",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "리스트 조회 성공",
            content =
                @Content(
                    array = @ArraySchema(schema = @Schema(implementation = FoodResponseDto.class))))
      })
  @GetMapping
  public ResponseType.Result<List<FoodResponseDto>> getFoodList(HttpServletRequest request) {

    logClientInfo(request);

    List<FoodResponseDto> responseDtos = foodService.getFoods();

    return ResponseType.success(responseDtos);
  }

  @Operation(
      method = "GET",
      summary = "음식 상세 조회.",
      description = "음식 상세 정보 반환.",
      parameters = {@Parameter(in = ParameterIn.PATH)},
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "상세 조회 성공",
            content = @Content(schema = @Schema(implementation = FoodResponseDto.class))),
        @ApiResponse(
            responseCode = "400",
            description = "존재하지 않는 음식 ID",
            content = @Content(schema = @Schema(implementation = ResponseType.Result.class)))
      })
  @GetMapping("/{foodId}")
  public ResponseType.Result<FoodResponseDto> getFoodDetail(
      @Parameter(
              name = "foodId",
              in = ParameterIn.PATH,
              description = "상세 조회 음식 정보 ID",
              required = true)
          @PathVariable
          Long foodId,
      HttpServletRequest request) {

    logClientInfo(request, foodId);

    FoodResponseDto responseDto = foodService.getFoodDetail(foodId);

    log.info("Response With             : {}", responseDto);

    return ResponseType.success(responseDto);
  }

  @Operation(
      summary = "음식 정보 추가.",
      description = "음식명, 설명, 가격 정보 추가.",
      responses = {@ApiResponse(responseCode = "200", description = "추가된 음식 정보.")})
  @PostMapping
  public ResponseType.Result<FoodResponseDto> register(
      @RequestBody FoodRequestDto requestDto, HttpServletRequest request) {

    logClientInfo(request, requestDto);

    FoodResponseDto responseDto = foodService.registerFood(requestDto);

    return ResponseType.success(responseDto);
  }

  @Operation(
      method = "DELETE",
      summary = "음식 정보 삭제.",
      description = "음식 ID 에 해당하는 엔트리를 삭제한다.",
      parameters = {@Parameter(in = ParameterIn.PATH)},
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "삭제 성공",
            content = @Content(schema = @Schema(implementation = FoodResponseDto.class))),
        @ApiResponse(
            responseCode = "400",
            description = "존재하지 않는 음식 ID",
            content = @Content(schema = @Schema(implementation = ResponseType.Result.class)))
      })
  @DeleteMapping("/{foodId}")
  public ResponseType.Result<Long> deleteFood(
      @Parameter(
              name = "foodId",
              in = ParameterIn.PATH,
              description = "삭제할 음식 정보 ID",
              required = true)
          @PathVariable
          Long foodId,
      HttpServletRequest request) {

    logClientInfo(request);

    foodService.deleteFood(foodId);

    return ResponseType.success(foodId);
  }

  private void logClientInfo(HttpServletRequest request) {
    String clientIp = getClientIP(request);

    log.info("Client IP                : {}", clientIp);
    log.info("Request from Remote Host : {}", request.getRemoteHost());
    log.info("Request URI              : {}", request.getRequestURI());
    log.info("Request Method           : {}", request.getMethod());
  }

  private void logClientInfo(HttpServletRequest request, Object object) {
    String clientIp = getClientIP(request);

    log.info("Client IP                : {}", clientIp);
    log.info("Request from Remote Host : {}", request.getRemoteHost());
    log.info("Request URI              : {}", request.getRequestURI());
    log.info("Request Method           : {}", request.getMethod());
    log.info("Request With             : {}", object);
  }

  private String getClientIP(HttpServletRequest request) {
    final String[] headerNames = {
      "X-Forwarded-For",
      "Proxy-Client-IP",
      "WL-Proxy-Client-IP",
      "HTTP_CLIENT_IP",
      "HTTP_X_FORWARDED_FOR"
    };

    for (String header : headerNames) {
      String ipAddress = request.getHeader(header);
      if (ipAddress != null && !ipAddress.isEmpty() && !"unknown".equalsIgnoreCase(ipAddress)) {
        // 일부 프록시들은 쉼표로 구분된 IP 주소 목록을 사용하며, 실제 클라이언트 IP는 첫 번째로 옵니다.
        if (ipAddress.contains(",")) {
          return ipAddress.split(",")[0].trim();
        }
        return ipAddress;
      }
    }

    return request.getRemoteAddr();
  }
}
