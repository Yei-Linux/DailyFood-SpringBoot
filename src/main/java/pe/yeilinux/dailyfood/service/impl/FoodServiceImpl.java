package pe.yeilinux.dailyfood.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.yeilinux.dailyfood.domain.request.FoodRequest;
import pe.yeilinux.dailyfood.entity.FoodEntity;
import pe.yeilinux.dailyfood.mapper.FoodMapper;
import pe.yeilinux.dailyfood.repository.FoodRepository;
import pe.yeilinux.dailyfood.service.FoodService;

import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {
    @Autowired
    private FoodRepository foodRepository;
    private FoodMapper foodMapper = new FoodMapper();

    @Override
    public List<FoodEntity> getFoodsByUserId(Long userId) {
        try{
            return this.foodMapper.convertToFoodEntityList(this.foodRepository.getFoodByUserId(userId));
        }catch (Exception e) {
            throw new Error(e.getMessage());
        }
    }

    @Override
    public FoodEntity insertFood(FoodRequest foodRequest) {
        try{
            return this.foodRepository.save(this.foodMapper.convertToFoodEntity(foodRequest));
        }catch (Exception e) {
            throw new Error(e.getMessage());
        }
    }

    @Override
    public List<FoodEntity> getFoods() {
        try{
            return this.foodRepository.findAll();
        }catch (Exception e) {
            throw new Error(e.getMessage());
        }
    }

    @Override
    public FoodRequest getFoodById(Long id) {
        try{
            return this.foodRepository.getFoodById(id);
        }catch (Exception e) {
            throw new Error(e.getMessage());
        }
    }

    @Override
    public void updateFoodById(Long id,FoodRequest foodRequest) {
        try{
            this.foodRepository.updateFood(id,foodRequest.getFood(),foodRequest.getUserId());
        }catch (Exception e) {
            throw new Error(e.getMessage());
        }
    }
}
