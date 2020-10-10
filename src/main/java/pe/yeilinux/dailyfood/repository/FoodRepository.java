package pe.yeilinux.dailyfood.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.yeilinux.dailyfood.domain.request.FoodRequest;
import pe.yeilinux.dailyfood.entity.FoodEntity;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<FoodEntity,Long> {
    @Transactional
    @Modifying
    @Query(value = "UPDATE foods SET food= ?2,user_id= ?3 WHERE food_id= ?1",nativeQuery = true)
    void updateFood(Long foodId,String food,Long userId);

    @Query("SELECT new pe.yeilinux.dailyfood.domain.request.FoodRequest(food.foodId,user.userId,food.food) FROM FoodEntity food INNER JOIN food.user user WHERE food.foodId= ?1")
    FoodRequest getFoodById(Long foodId);

    @Query("SELECT new pe.yeilinux.dailyfood.domain.request.FoodRequest(food.foodId,user.userId,food.food) FROM FoodEntity food INNER JOIN food.user user WHERE user.userId= ?1")
    List<FoodRequest> getFoodByUserId(Long userId);
}
