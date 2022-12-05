package com.rw.test.tastefulapp.common.model;

import com.rw.test.tastefulapp.model.CookingRecipe;
import com.rw.test.tastefulapp.model.Ingredient;
import com.rw.test.tastefulapp.model.IngredientQuantity;
import com.rw.test.tastefulapp.repository.entity.CookingIngredientEntity;
import com.rw.test.tastefulapp.repository.entity.CookingInstructionEntity;
import com.rw.test.tastefulapp.repository.entity.CookingRecipeEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class ModelMapper {
    public CookingRecipe mapFromRecipeEntity(CookingRecipeEntity entity) {
        return new CookingRecipe(entity.getId(), entity.getName(), entity.getDietType(), entity.getServings(), mapFromIngredientEntities(entity.getIngredients()), mapFromInstructionEntities(entity.getCookingInstructions()));
    }

    public Map<Ingredient, IngredientQuantity> mapFromIngredientEntities(Set<CookingIngredientEntity> ingredients) {
        return ingredients.stream()
                .collect(Collectors.toMap(i -> new Ingredient(i.getName()), i -> new IngredientQuantity(i.getAmount(), i.getQuantityUnit())));
    }

    public List<String> mapFromInstructionEntities(List<CookingInstructionEntity> instructions) {
        return instructions.stream()
                .map(CookingInstructionEntity::getInstruction)
                .toList();
    }

    public CookingRecipeEntity mapToRecipeEntity(CookingRecipe recipe) {
        return new CookingRecipeEntity(recipe.id(), recipe.name(), recipe.dietType(), recipe.servings(), mapToIngredientEntities(recipe.ingredients()), mapToInstructionEntities(recipe.cookingInstructions()));
    }

    public Set<CookingIngredientEntity> mapToIngredientEntities(Map<Ingredient, IngredientQuantity> ingredients) {
        return ingredients.entrySet().stream()
                .map(e -> new CookingIngredientEntity(UUID.randomUUID().toString(), e.getKey().name(), e.getValue().amount(), e.getValue().unit()))
                .collect(Collectors.toSet());
    }

    public List<CookingInstructionEntity> mapToInstructionEntities(List<String> cookingInstructions) {
        return cookingInstructions.stream()
                .map(i -> new CookingInstructionEntity(UUID.randomUUID().toString(), i))
                .toList();
    }
}
