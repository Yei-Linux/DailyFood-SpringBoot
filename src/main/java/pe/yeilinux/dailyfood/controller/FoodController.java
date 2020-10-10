package pe.yeilinux.dailyfood.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.yeilinux.dailyfood.domain.request.FoodRequest;
import pe.yeilinux.dailyfood.domain.response.GeneralResponse;
import pe.yeilinux.dailyfood.service.FoodService;

@RestController
@RequestMapping("foods")
public class FoodController {
    @Autowired
    private FoodService foodService;

    @GetMapping
    @ApiOperation(value = "Get Foods")
    public ResponseEntity<?> getFoods() {
        try{
            return new ResponseEntity<>(new GeneralResponse(this.foodService.getFoods(),"Foods Successful"), HttpStatus.OK);
        }catch(Exception e) {
            throw new Error(e.getMessage());
        }
    }

    @GetMapping("user/{userId}")
    @ApiOperation(value="Get foods by userId")
    public ResponseEntity<?> getFoodsByUserId(@PathVariable Long userId) {
        try{
            return new ResponseEntity<>(new GeneralResponse(this.foodService.getFoodsByUserId(userId),"Foods of User Successful"), HttpStatus.OK);
        }catch(Exception e) {
            throw new Error(e.getMessage());
        }
    }

    @GetMapping("{foodId}")
    @ApiOperation(value = "Get Food by id")
    public ResponseEntity<?> getFoodById(@PathVariable Long foodId) {
        try{
            return new ResponseEntity<>(new GeneralResponse(this.foodService.getFoodById(foodId),"Food Successful"), HttpStatus.OK);
        }catch(Exception e) {
            throw new Error(e.getMessage());
        }
    }

    @PostMapping
    @ApiOperation(value = "Insert Food")
    public ResponseEntity<?> insertFood(@RequestBody FoodRequest foodRequest) {
        try{
            return new ResponseEntity<>(new GeneralResponse(this.foodService.insertFood(foodRequest),"Food inserted Successful"), HttpStatus.CREATED);
        }catch(Exception e) {
            throw new Error(e.getMessage());
        }
    }

    @PutMapping("{foodId}")
    @ApiOperation(value = "Update Food by id")
    public ResponseEntity<?> updateFood(@RequestBody FoodRequest foodRequest,@PathVariable Long foodId) {
        try{
            this.foodService.updateFoodById(foodId,foodRequest);
            return new ResponseEntity<>(new GeneralResponse(foodId,"Food inserted Successful"), HttpStatus.OK);
        }catch(Exception e) {
            throw new Error(e.getMessage());
        }
    }
}
