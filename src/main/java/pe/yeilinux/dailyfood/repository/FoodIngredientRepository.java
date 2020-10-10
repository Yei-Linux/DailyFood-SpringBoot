package pe.yeilinux.dailyfood.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.yeilinux.dailyfood.dto.IngredientsFoodDTO;
import pe.yeilinux.dailyfood.entity.FoodIngredientDetailEntity;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface FoodIngredientRepository extends JpaRepository<FoodIngredientDetailEntity,Long> {
    @Query("SELECT new pe.yeilinux.dailyfood.dto.IngredientsFoodDTO(foodDetail.foodIngredientId,foodDetail.food.foodId,ingredient.ingredientId,ingredient.ingredient,foodDetail.size) FROM FoodIngredientDetailEntity foodDetail INNER JOIN foodDetail.food food INNER JOIN foodDetail.ingredient ingredient WHERE food.foodId= ?1")
    List<IngredientsFoodDTO> getIngredientsFoodByFoodId(Long foodId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE food_ingredients_detail SET size= ?3 WHERE food_id= ?1 AND ingredient_id= ?2",nativeQuery = true)
    void updateIngredientsFoodById(Long foodId,Long ingredientId,String size);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM food_ingredients_detail WHERE food_id= ?1 AND ingredient_id= ?2",nativeQuery = true)
    void deleteIngredientsFoodByFoodIdAndIngredientId(Long foodId,Long ingredientId);
}
