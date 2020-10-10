package pe.yeilinux.dailyfood.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.yeilinux.dailyfood.domain.request.IngredientRequest;
import pe.yeilinux.dailyfood.entity.IngredientEntity;
import pe.yeilinux.dailyfood.mapper.IngredientMapper;
import pe.yeilinux.dailyfood.repository.IngredientRepository;
import pe.yeilinux.dailyfood.service.IngredientService;

import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService {
    @Autowired
    private IngredientRepository ingredientRepository;
    private IngredientMapper ingredientMapper = new IngredientMapper();

    @Override
    public List<IngredientEntity> getIngredients() {
        try{
            return this.ingredientRepository.findAll();
        }catch (Exception e) {
            throw new Error(e.getMessage());
        }
    }

    @Override
    public IngredientEntity getIngredientById(Long id) {
        try{
            return this.ingredientRepository.findById(id).orElse(null);
        }catch (Exception e) {
            throw new Error(e.getMessage());
        }
    }

    @Override
    public IngredientEntity insertIngredient(IngredientRequest ingredientRequest) {
        try{
            return this.ingredientRepository.save(this.ingredientMapper.convertToIngredientEntity(ingredientRequest));
        }catch (Exception e) {
            throw new Error(e.getMessage());
        }
    }
}
