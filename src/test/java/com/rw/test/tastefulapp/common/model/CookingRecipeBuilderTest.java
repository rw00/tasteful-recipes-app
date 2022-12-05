package com.rw.test.tastefulapp.common.model;

import com.rw.test.tastefulapp.model.DietType;
import com.rw.test.tastefulapp.model.Ingredient;
import com.rw.test.tastefulapp.model.IngredientQuantity;
import com.rw.test.tastefulapp.model.Recipe;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThatThrownBy;


class CookingRecipeBuilderTest {

    @Test
    void builder_validates_ingredients() {
        var ingredient = new Ingredient("cheese");
        var cookingRecipeBuilder = new CookingRecipeBuilder(new Recipe(null, "pizza", DietType.MEAT, Set.of(ingredient)), 2);
        assertThatThrownBy(() -> cookingRecipeBuilder.setIngredientQuantity(new Ingredient("chocolate"), new IngredientQuantity(0.3, "kg")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Unknown ingredient chocolate");
    }

    @Test
    void builder_validates_input() {
        var ingredient = new Ingredient("cheese");
        var cookingRecipeBuilder = new CookingRecipeBuilder(new Recipe(null, "pizza", DietType.MEAT, Set.of(ingredient)), 2);
        cookingRecipeBuilder.setIngredientQuantity(ingredient, new IngredientQuantity(0.5, "kg"));
        assertThatThrownBy(cookingRecipeBuilder::build)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Missing cooking instructions");
    }
}
