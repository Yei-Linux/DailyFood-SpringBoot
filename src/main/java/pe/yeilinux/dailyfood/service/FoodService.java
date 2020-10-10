package pe.yeilinux.dailyfood.service;

import pe.yeilinux.dailyfood.domain.request.FoodRequest;
import pe.yeilinux.dailyfood.entity.FoodEntity;

import java.util.List;

public interface FoodService {
    List<FoodEntity> getFoodsByUserId(Long userId);
    FoodEntity insertFood(FoodRequest foodRequest);
    List<FoodEntity> getFoods();
    FoodRequest getFoodById(Long id);
    void updateFoodById(Long id,FoodRequest foodRequest);
}
