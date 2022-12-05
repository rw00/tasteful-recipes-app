package com.rw.test.tastefulapp;

import com.rw.test.tastefulapp.common.model.CookingRecipeBuilder;
import com.rw.test.tastefulapp.model.DietType;
import com.rw.test.tastefulapp.model.Ingredient;
import com.rw.test.tastefulapp.model.IngredientQuantity;
import com.rw.test.tastefulapp.model.Recipe;
import lombok.experimental.UtilityClass;

import java.util.Set;

@UtilityClass
public class MockTestData {
    static CookingRecipeBuilder cookingRecipeBuilder() {
        var ingredient = new Ingredient("cheese");
        return new CookingRecipeBuilder(new Recipe(null, "pizza", DietType.MEAT, Set.of(ingredient)), 2)
                .addCookingInstruction("Add cheese")
                .addCookingInstruction("Bake the pizza in the oven")
                .setIngredientQuantity(ingredient, new IngredientQuantity(0.5, "kg"));
    }
}
