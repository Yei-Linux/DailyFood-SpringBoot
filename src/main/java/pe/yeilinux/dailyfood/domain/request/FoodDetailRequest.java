package pe.yeilinux.dailyfood.domain.request;

import lombok.Data;
import pe.yeilinux.dailyfood.dto.IngredientsFoodDTO;

import java.util.List;

@Data
public class FoodDetailRequest {
    private FoodRequest foodRequest;
    private List<IngredientsFoodDTO> ingredientsFoodList;
    private String day;
    private Long userId;
}
