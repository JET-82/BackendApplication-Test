package team2.test.food;

import java.util.List;

/** 음식 조회 */
public interface FoodService {
  List<FoodResponseDto> getFoods();

  FoodResponseDto registerFood(FoodRequestDto requestDto);

  FoodResponseDto getFoodDetail(Long foodId);
}
