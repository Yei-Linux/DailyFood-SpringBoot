package pe.yeilinux.dailyfood.mapper;

import org.modelmapper.ModelMapper;
import pe.yeilinux.dailyfood.domain.IngredientsFood;
import pe.yeilinux.dailyfood.dto.IngredientsFoodDTO;
import pe.yeilinux.dailyfood.entity.FoodIngredientDetailEntity;

public class IngredientsFoodMapper {
    private ModelMapper modelMapper = new ModelMapper();

    public FoodIngredientDetailEntity convertToIngredientsFoodEntity(IngredientsFood ingredientsFood) {
        return this.modelMapper.map(ingredientsFood,FoodIngredientDetailEntity.class);
    }
    public FoodIngredientDetailEntity convertToIngredientsFoodEntity(IngredientsFoodDTO ingredientsFood) {
        return this.modelMapper.map(ingredientsFood,FoodIngredientDetailEntity.class);
    }
}
