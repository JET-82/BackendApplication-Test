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
  @Schema(name = "음식 DB ID", example = "100", description = "음식 ID")
  private String id;

  @Schema(name = "음식 이름", example = "불닭볶음면", description = "음식 이름")
  private String name;

  @Schema(name = "음식 설명", example = "굉장히 매운 불닭볶음면", description = "음식 정보")
  private String description;

  @Schema(name = "음식 가격", example = "₩10,000", description = "음식 가격")
  private String price;

  @Schema(name = "음식 이미지", example = "https://test.link", description = "음식 이미지 링크")
  private String image;

  public static FoodResponseDto fromEntity(FoodEntity foodEntity) {
    return new FoodResponseDto(
        String.format("%d", foodEntity.getFoodId()),
        foodEntity.getFoodName(),
        foodEntity.getDescription(),
        foodEntity.getPrice(),
        foodEntity.getImageLink());
  }

  @Override
  public String toString() {
    return "FoodResponseDto{"
        + "foodId="
        + id
        + ", foodName='"
        + name
        + '\''
        + ", description='"
        + description
        + '\''
        + ", price='"
        + price
        + '\''
        + ", imageLink='"
        + image
        + '\''
        + '}';
  }
}
