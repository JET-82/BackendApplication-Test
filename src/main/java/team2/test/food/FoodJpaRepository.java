package team2.test.food;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodJpaRepository extends JpaRepository<FoodEntity, Long> {}
