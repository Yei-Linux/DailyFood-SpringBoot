package pe.yeilinux.dailyfood.mapper;

import org.modelmapper.ModelMapper;
import pe.yeilinux.dailyfood.domain.request.FoodRequest;
import pe.yeilinux.dailyfood.entity.FoodEntity;

import java.util.List;
import java.util.stream.Collectors;

public class FoodMapper {
    private ModelMapper modelMapper = new ModelMapper();

    public FoodEntity convertToFoodEntity(FoodRequest foodRequest) {
        return this.modelMapper.map(foodRequest, FoodEntity.class);
    }
    public List<FoodEntity> convertToFoodEntityList(List<FoodRequest> foodRequestList) {
        return foodRequestList.stream().map(foodRequest -> this.modelMapper.map(foodRequest, FoodEntity.class)).collect(Collectors.toList());
    }
    public FoodRequest convertToFoodRequest(FoodEntity foodEntity) {
        return this.modelMapper.map(foodEntity, FoodRequest.class);
    }
}
