package pe.yeilinux.dailyfood.domain;

import lombok.Data;

@Data
public class IngredientsFood {
    private Long foodIngredientId;
    private Long foodId;
    private Long ingredientId;
    private String size;
}
