package pe.yeilinux.dailyfood.service;

import pe.yeilinux.dailyfood.domain.request.IngredientRequest;
import pe.yeilinux.dailyfood.entity.IngredientEntity;

import java.util.List;

public interface IngredientService {
    List<IngredientEntity> getIngredients();
    IngredientEntity getIngredientById(Long id);
    IngredientEntity insertIngredient(IngredientRequest ingredientRequest);
}
