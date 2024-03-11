package team2.test.food;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {
  private final FoodJpaRepository foodJpaRepository;

  @Override
  public List<FoodResponseDto> getFoods() {
    return foodJpaRepository.findAll().stream()
        .map(FoodResponseDto::fromEntity)
        .collect(Collectors.toList());
  }

  @Override
  public FoodResponseDto getFoodDetail(Long foodId) {
    return foodJpaRepository
        .findById(foodId)
        .map(FoodResponseDto::fromEntity)
        .orElseThrow(
            () ->
                new EntityNotFoundException(
                    String.format("Entity not found with id : %d", foodId)));
  }

  @Override
  public FoodResponseDto registerFood(FoodRequestDto requestDto) {
    FoodEntity newEntity = requestDto.toEntity();
    foodJpaRepository.save(newEntity);
    return FoodResponseDto.fromEntity(newEntity);
  }
}
