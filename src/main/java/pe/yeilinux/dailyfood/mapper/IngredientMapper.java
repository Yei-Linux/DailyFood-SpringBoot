package pe.yeilinux.dailyfood.mapper;

import org.modelmapper.ModelMapper;
import pe.yeilinux.dailyfood.domain.request.IngredientRequest;
import pe.yeilinux.dailyfood.entity.IngredientEntity;

public class IngredientMapper {
    private ModelMapper modelMapper = new ModelMapper();

    public IngredientEntity convertToIngredientEntity(IngredientRequest ingredientRequest) {
        return this.modelMapper.map(ingredientRequest,IngredientEntity.class);
    }
}
