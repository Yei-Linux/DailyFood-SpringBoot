package pe.yeilinux.dailyfood.domain.request;

import lombok.Data;

@Data
public class IngredientRequest {
    private Long ingredientId;
    private String ingredient;
}
