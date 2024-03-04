package team2.test.food;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
  public ResponseEntity<List<FoodResponseDto>> getFoodList() {
    List<FoodResponseDto> responseDtos = foodService.getFoods();
    return ResponseEntity.ok(responseDtos);
  }

  @Operation(
      summary = "음식 정보 추가.",
      description = "음식명, 설명, 가격 정보 추가.",
      responses = {@ApiResponse(responseCode = "200", description = "추가된 음식 정보.")})
  @PostMapping
  public ResponseEntity<FoodResponseDto> register(@RequestBody FoodRequestDto requestDto) {
    FoodResponseDto responseDto = foodService.registerFood(requestDto);
    return ResponseEntity.ok(responseDto);
  }
}
