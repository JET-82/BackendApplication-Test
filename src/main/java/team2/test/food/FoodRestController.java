package team2.test.food;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team2.test.utils.ResponseType;

@Slf4j
@Tag(name = "Foods", description = "음식 정보 API")
@Api(
    tags = {"Foods"},
    value = "Food Controller")
@RestController
@RequestMapping("/foods")
@RequiredArgsConstructor
public class FoodRestController {
  private final FoodService foodService;

  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "게시글 조회 성공",
            content = @Content(schema = @Schema(implementation = FoodResponseDto.class))),
      })
  @Operation(
      summary = "음식 리스트 조회.",
      description = "음식 리스트 반환.",
      responses = {@ApiResponse(responseCode = "200", description = "음식 리스트 정보.")})
  @GetMapping
  public ResponseEntity<ResponseType.Result<List<FoodResponseDto>>> getFoodList(
      HttpServletRequest request) {

    String clientIp = getClientIP(request);

    log.info("Client IP                : {}", clientIp);
    log.info("Request from Remote Host : {}", request.getRemoteHost());
    log.info("Request URI              : {}", request.getRequestURI());
    log.info("Request Method           : {}", request.getMethod());

    List<FoodResponseDto> responseDtos = foodService.getFoods();

    return ResponseEntity.ok(ResponseType.success(responseDtos));
  }

  @Operation(
      summary = "음식 정보 추가.",
      description = "음식명, 설명, 가격 정보 추가.",
      responses = {@ApiResponse(responseCode = "200", description = "추가된 음식 정보.")})
  @PostMapping
  public ResponseType.Result<FoodResponseDto> register(
      @RequestBody FoodRequestDto requestDto, HttpServletRequest request) {

    String clientIp = getClientIP(request);

    log.info("Client IP                : {}", clientIp);
    log.info("Request from Remote Host : {}", request.getRemoteHost());
    log.info("Request URI              : {}", request.getRequestURI());
    log.info("Request Method           : {}", request.getMethod());
    log.info("Request Body             : {}", requestDto);

    FoodResponseDto responseDto = foodService.registerFood(requestDto);
    return ResponseType.success(responseDto);
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
