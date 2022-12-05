package com.rw.test.tastefulapp.common.model;

import com.rw.test.tastefulapp.model.CookingRecipe;
import com.rw.test.tastefulapp.model.DietType;
import com.rw.test.tastefulapp.repository.entity.CookingIngredientEntity;
import com.rw.test.tastefulapp.repository.entity.CookingInstructionEntity;
import com.rw.test.tastefulapp.repository.entity.CookingRecipeEntity;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


class ModelMapperTest {
    private final ModelMapper modelMapper = new ModelMapper();

    @Test
    void test_model_mapper() {
        CookingRecipe cookingRecipe = modelMapper.mapFromRecipeEntity(new CookingRecipeEntity("recipe1", "pizza", DietType.MEAT, 2, Set.of(new CookingIngredientEntity("ingredient1", "cheese", 0.5, "kg")), List.of(new CookingInstructionEntity("instruction1", "Cook"))));
        assertThat(cookingRecipe)
                .hasFieldOrPropertyWithValue("id", "recipe1")
                .hasFieldOrPropertyWithValue("name", "pizza")
                .hasFieldOrPropertyWithValue("dietType", DietType.MEAT)
                .hasFieldOrPropertyWithValue("servings", 2);
        assertThat(cookingRecipe.ingredients()).hasSize(1);
        assertThat(cookingRecipe.cookingInstructions()).hasSize(1);
    }
}
