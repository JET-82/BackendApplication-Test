package team2.test.food;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
public class FoodRequestDto {
  private String foodName;
  private String description;
  private Integer price;

  public FoodEntity toEntity() {
    return FoodEntity.of(foodName, description, price);
  }
}
