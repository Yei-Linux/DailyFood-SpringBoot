package pe.yeilinux.dailyfood.domain.request;

import lombok.Data;

@Data
public class FoodRequest {
    private Long foodId;
    private Long userId;
    public String food;
    public FoodRequest(Long foodId,Long userId,String food) {
        this.foodId = foodId;
        this.userId = userId;
        this.food = food;
    }
}
