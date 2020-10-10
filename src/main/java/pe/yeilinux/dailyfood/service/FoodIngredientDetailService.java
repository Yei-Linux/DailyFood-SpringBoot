package pe.yeilinux.dailyfood.service;

import pe.yeilinux.dailyfood.domain.request.FoodDetailRequest;
import pe.yeilinux.dailyfood.dto.IngredientsFoodDTO;

import java.util.List;

public interface FoodIngredientDetailService {
    void insertFoodIngredientDetail(FoodDetailRequest foodDetailRequest);
    List<FoodDetailRequest> getFoodDetailOfAllWeek(Long userId);
    List<IngredientsFoodDTO> getIngredientsOfFoodByFoodId(Long foodId);
    void updateFoodIngredientsDetail(FoodDetailRequest foodDetailRequest);
}
