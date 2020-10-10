package pe.yeilinux.dailyfood.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.yeilinux.dailyfood.domain.request.FoodDetailRequest;
import pe.yeilinux.dailyfood.domain.request.FoodRequest;
import pe.yeilinux.dailyfood.domain.response.GeneralResponse;
import pe.yeilinux.dailyfood.dto.IngredientsFoodDTO;
import pe.yeilinux.dailyfood.service.FoodIngredientDetailService;
import pe.yeilinux.dailyfood.service.FoodService;

import java.util.List;

@RestController
@RequestMapping("food-details")
public class FoodDetailController {
    @Autowired
    private FoodIngredientDetailService foodIngredientDetailService;
    @Autowired
    private FoodService foodService;

    @GetMapping("{userId}")
    @ApiOperation(value = "Get Food Detail of Week")
    public ResponseEntity<?> getFoodDetailOfWeekByUser(@PathVariable Long userId) {
        try{
            return new ResponseEntity<>(new GeneralResponse(this.foodIngredientDetailService.getFoodDetailOfAllWeek(userId),"WeekSchema Details Successful"), HttpStatus.OK);
        }catch(Exception e) {
            throw new Error(e.getMessage());
        }
    }

    @GetMapping("food/{foodId}")
    @ApiOperation(value = "Get Food Detail By FoodId")
    public ResponseEntity<?> getFoodDetailByFoodId(@PathVariable Long foodId) {
        try{
            FoodDetailRequest foodDetailRequest = new FoodDetailRequest();
            FoodRequest foodRequest = this.foodService.getFoodById(foodId);
            List<IngredientsFoodDTO> ingredientsFoodDTOList = this.foodIngredientDetailService.getIngredientsOfFoodByFoodId(foodId);
            foodDetailRequest.setIngredientsFoodList(ingredientsFoodDTOList);
            foodDetailRequest.setFoodRequest(foodRequest);
            foodDetailRequest.setDay("");
            return new ResponseEntity<>(new GeneralResponse(foodDetailRequest,"IngredientsFood Details by foodId Successful"), HttpStatus.OK);
        }catch(Exception e) {
            throw new Error(e.getMessage());
        }
    }

    @PostMapping("{userId}")
    @ApiOperation(value = "Insert Food Detail of Week by UserId")
    public ResponseEntity<?> insertFoodDetailOnWeekByUser(@PathVariable Long userId, @RequestBody FoodDetailRequest foodDetailRequest) {
        try{
            foodDetailRequest.setUserId(userId);
            this.foodIngredientDetailService.insertFoodIngredientDetail(foodDetailRequest);
            return new ResponseEntity<>(new GeneralResponse(userId,"WeekSchema Detail Inserted Successful"), HttpStatus.CREATED);
        }catch(Exception e) {
            throw new Error(e.getMessage());
        }
    }

    @PutMapping("{userId}")
    @ApiOperation(value = "Update Food Detail of Week by UserId")
    public ResponseEntity<?> updateFoodDetailOnWeekByUser(@PathVariable Long userId, @RequestBody FoodDetailRequest foodDetailRequest) {
        try{
            foodDetailRequest.setUserId(userId);
            this.foodIngredientDetailService.updateFoodIngredientsDetail(foodDetailRequest);
            return new ResponseEntity<>(new GeneralResponse(userId,"WeekSchema Detail Inserted Successful"), HttpStatus.CREATED);
        }catch(Exception e) {
            throw new Error(e.getMessage());
        }
    }
}
