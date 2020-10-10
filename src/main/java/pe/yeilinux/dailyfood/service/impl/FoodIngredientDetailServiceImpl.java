package pe.yeilinux.dailyfood.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.yeilinux.dailyfood.domain.request.DaySchemaRequest;
import pe.yeilinux.dailyfood.domain.request.FoodDetailRequest;
import pe.yeilinux.dailyfood.domain.request.FoodRequest;
import pe.yeilinux.dailyfood.dto.DayDTO;
import pe.yeilinux.dailyfood.dto.IngredientsFoodDTO;
import pe.yeilinux.dailyfood.entity.FoodEntity;
import pe.yeilinux.dailyfood.entity.FoodIngredientDetailEntity;
import pe.yeilinux.dailyfood.mapper.FoodMapper;
import pe.yeilinux.dailyfood.mapper.IngredientsFoodMapper;
import pe.yeilinux.dailyfood.repository.FoodIngredientRepository;
import pe.yeilinux.dailyfood.service.DayService;
import pe.yeilinux.dailyfood.service.FoodIngredientDetailService;
import pe.yeilinux.dailyfood.service.FoodService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class FoodIngredientDetailServiceImpl implements FoodIngredientDetailService {
    @Autowired
    private FoodService foodService;
    @Autowired
    private FoodIngredientRepository foodIngredientRepository;
    @Autowired
    private DayService dayService;
    private IngredientsFoodMapper ingredientsFoodMapper = new IngredientsFoodMapper();
    private FoodMapper foodMapper = new FoodMapper();

    @Override
    public void insertFoodIngredientDetail(FoodDetailRequest foodDetailRequest) {
        try {
            FoodEntity foodEntity = this.foodService.insertFood(foodDetailRequest.getFoodRequest());
            this.insertFoodIngredientListDetail(foodDetailRequest.getIngredientsFoodList(),foodEntity.getFoodId());

            DayDTO dayDTO = this.dayService.getDaySchemaByUserId(foodDetailRequest.getUserId());
            Map<String,Integer> weekSchema = this.dayService.updateWeekSchemaByDay((Map<String, Integer>) dayDTO.getWeekSchema(),foodDetailRequest.getDay(),foodEntity.getFoodId().intValue(),false);
            DaySchemaRequest daySchemaRequest = new DaySchemaRequest();
            daySchemaRequest.setWeekSchema(weekSchema);

            this.dayService.updateDaySchemaByUserId(foodDetailRequest.getUserId(),daySchemaRequest);
        }catch (Exception e){
            throw new Error(e.getMessage());
        }
    }

    @Override
    public List<FoodDetailRequest> getFoodDetailOfAllWeek(Long userId) {
        try{
            List<FoodDetailRequest> foodDetailRequests = new ArrayList<>();
            DayDTO dayDTO = this.dayService.getDaySchemaByUserId(userId);
            Map<String,Integer> weekSchema = (Map<String, Integer>) dayDTO.getWeekSchema();
            for(Map.Entry<String,Integer> entry : weekSchema.entrySet()) {
                FoodRequest foodRequest = entry.getValue() != 0 ? this.foodService.getFoodById((long)entry.getValue()) : null;
                List<IngredientsFoodDTO> ingredientsFoodDTOS = new ArrayList<>();
                if(foodRequest != null) {
                    ingredientsFoodDTOS = this.getIngredientsOfFoodByFoodId((long)entry.getValue());
                }

                FoodDetailRequest foodDetailRequest = new FoodDetailRequest();
                foodDetailRequest.setDay(entry.getKey());
                foodDetailRequest.setFoodRequest(foodRequest);
                foodDetailRequest.setIngredientsFoodList(ingredientsFoodDTOS);
                foodDetailRequests.add(foodDetailRequest);
            }

            return foodDetailRequests;
        }catch (Exception e){
            throw new Error(e.getMessage());
        }
    }

    @Override
    public List<IngredientsFoodDTO> getIngredientsOfFoodByFoodId(Long foodId) {
        try{
            return this.foodIngredientRepository.getIngredientsFoodByFoodId(foodId);
        }catch (Exception e){
            throw new Error(e.getMessage());
        }
    }

    @Override
    public void updateFoodIngredientsDetail(FoodDetailRequest foodDetailRequest) {
        try{
            this.foodService.updateFoodById(foodDetailRequest.getFoodRequest().getFoodId(),foodDetailRequest.getFoodRequest());
            DayDTO dayDTO = this.dayService.getDaySchemaByUserId(foodDetailRequest.getUserId());
            Map<String,Integer> weekSchema = this.dayService.updateWeekSchemaByDay((Map<String, Integer>) dayDTO.getWeekSchema(),foodDetailRequest.getDay(),foodDetailRequest.getFoodRequest().getFoodId().intValue(),true);
            DaySchemaRequest daySchemaRequest = new DaySchemaRequest();
            daySchemaRequest.setWeekSchema(weekSchema);
            this.dayService.updateDaySchemaByUserId(foodDetailRequest.getUserId(),daySchemaRequest);

            for (IngredientsFoodDTO ingredientsFood : foodDetailRequest.getIngredientsFoodList()) {
                if(!ingredientsFood.getIsDeleted()) {
                    FoodIngredientDetailEntity foodIngredientDetailEntity = this.ingredientsFoodMapper.convertToIngredientsFoodEntity(ingredientsFood);
                    if (ingredientsFood.getIsUpdated()) {
                        this.foodIngredientRepository.updateIngredientsFoodById(ingredientsFood.getFoodId(), ingredientsFood.getIngredientId(), ingredientsFood.getSize());
                    }
                    if(!ingredientsFood.getIsUpdated() && !ingredientsFood.getIsDeleted()) {
                        this.foodIngredientRepository.save(foodIngredientDetailEntity);
                    }
                }else {
                    this.foodIngredientRepository.deleteIngredientsFoodByFoodIdAndIngredientId(ingredientsFood.getFoodId(), ingredientsFood.getIngredientId());
                }
            }
        }catch (Exception e) {
            throw new Error(e.getMessage());
        }
    }

    private void insertFoodIngredientListDetail(List<IngredientsFoodDTO> ingredientsFoodList,Long foodId) {
        for (IngredientsFoodDTO ingredientsFood : ingredientsFoodList) {
            ingredientsFood.setFoodId(foodId);
            FoodIngredientDetailEntity foodIngredientDetailEntity = this.ingredientsFoodMapper.convertToIngredientsFoodEntity(ingredientsFood);
            this.foodIngredientRepository.save(foodIngredientDetailEntity);
        }
    }
}
