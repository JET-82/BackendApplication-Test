package team2.test.food;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
public class FoodResponseDto {
  private Long foodId;
  private String foodName;
  private String description;

  public static FoodResponseDto fromEntity(FoodEntity foodEntity) {
    return new FoodResponseDto(
        foodEntity.getFoodId(), foodEntity.getFoodName(), foodEntity.getDescription());
  }
}
