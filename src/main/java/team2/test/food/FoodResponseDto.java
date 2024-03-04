package team2.test.food;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "음식 정보 응답 DTO")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
public class FoodResponseDto {
  @Schema(description = "음식 ID")
  private Long foodId;

  @Schema(description = "음식 이름")
  private String foodName;

  @Schema(description = "음식 정보")
  private String description;

  @Schema(description = "음식 가격")
  private String price;

  @Schema(description = "음식 이미지 링크")
  private String imageLink;

  public static FoodResponseDto fromEntity(FoodEntity foodEntity) {
    return new FoodResponseDto(
        foodEntity.getFoodId(),
        foodEntity.getFoodName(),
        foodEntity.getDescription(),
        foodEntity.getPrice(),
        foodEntity.getImageLink());
  }
}
