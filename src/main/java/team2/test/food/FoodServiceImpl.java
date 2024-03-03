package team2.test.food;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
  public FoodResponseDto registerFood(FoodRequestDto requestDto) {
    FoodEntity newEntity = requestDto.toEntity();
    foodJpaRepository.save(newEntity);
    return FoodResponseDto.fromEntity(newEntity);
  }
}
