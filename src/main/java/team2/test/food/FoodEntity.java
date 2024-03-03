package team2.test.food;

import javax.persistence.*;
import lombok.*;
import team2.test.common.BaseEntity;

@Entity
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
public class FoodEntity extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long foodId;

  @Column(nullable = false, length = 100)
  @Setter
  private String foodName;

  @Column(length = 100)
  @Setter
  private String description;

  @Column(nullable = false, length = 100)
  @Setter
  private Integer price;

  public static FoodEntity of(String foodName, String description, Integer price) {
    return new FoodEntity(null, foodName, description, price);
  }
}
