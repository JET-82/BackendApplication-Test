package team2.test.food;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/foods")
@RequiredArgsConstructor
public class FoodRestController {
  private final FoodService foodService;

  @GetMapping
  public ResponseEntity<List<FoodResponseDto>> getFoodList() {
    List<FoodResponseDto> responseDtos = foodService.getFoods();
    return ResponseEntity.ok(responseDtos);
  }

  @PostMapping
  public ResponseEntity<FoodResponseDto> register(@RequestBody FoodRequestDto requestDto) {
    FoodResponseDto responseDto = foodService.registerFood(requestDto);
    return ResponseEntity.ok(responseDto);
  }
}
