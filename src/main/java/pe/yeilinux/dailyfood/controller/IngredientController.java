package pe.yeilinux.dailyfood.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.yeilinux.dailyfood.domain.request.IngredientRequest;
import pe.yeilinux.dailyfood.domain.response.GeneralResponse;
import pe.yeilinux.dailyfood.service.IngredientService;

@RestController
@RequestMapping("ingredients")
public class IngredientController {
    @Autowired
    private IngredientService ingredientService;

    @GetMapping
    @ApiOperation(value = "Get all ingredients")
    public ResponseEntity<?> getIngredients() {
        return new ResponseEntity<>(new GeneralResponse(this.ingredientService.getIngredients(),"Ingredients successful"), HttpStatus.OK);
    }

    @GetMapping("{ingredientId}")
    @ApiOperation(value = "Get ingredient by id")
    public ResponseEntity<?> updateIngredientById(@PathVariable Long ingredientId) {
        return new ResponseEntity<>(new GeneralResponse(this.ingredientService.getIngredientById(ingredientId),"Ingredient successful"), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "Insert ingredient")
    public ResponseEntity<?> insertIngredient(@RequestBody IngredientRequest ingredientRequest) {
        return new ResponseEntity<>(new GeneralResponse(this.ingredientService.insertIngredient(ingredientRequest),"Ingredient inserted successful"), HttpStatus.OK);
    }
}
