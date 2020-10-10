package pe.yeilinux.dailyfood.dto;
import lombok.Data;

@Data
public class IngredientsFoodDTO {
    private Long foodIngredientId;
    private Long foodId;
    private Long ingredientId;
    private String ingredient;
    private String size;
    private Boolean isDeleted;
    private Boolean isUpdated;

    public IngredientsFoodDTO(Long foodIngredientId,Long foodId,Long ingredientId,String ingredient,String size) {
        this.foodIngredientId = foodIngredientId;
        this.foodId = foodId;
        this.ingredientId = ingredientId;
        this.ingredient = ingredient;
        this.size = size;
    }
}
