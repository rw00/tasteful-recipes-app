package com.rw.test.tastefulapp.common.model;

import com.rw.test.tastefulapp.model.CookingRecipe;
import com.rw.test.tastefulapp.model.Ingredient;
import com.rw.test.tastefulapp.model.IngredientQuantity;
import com.rw.test.tastefulapp.model.Recipe;
import jakarta.annotation.Nonnull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CookingRecipeBuilder {
    private final Recipe recipe;
    private final int servings;
    private final Map<Ingredient, IngredientQuantity> ingredients = new HashMap<>();
    private final List<String> cookingInstructions = new ArrayList<>();

    public CookingRecipeBuilder(@Nonnull Recipe recipe, int servings) {
        this.recipe = recipe;
        this.servings = servings;
    }

    public CookingRecipeBuilder addCookingInstruction(@Nonnull String cookingInstruction) {
        this.cookingInstructions.add(cookingInstruction);
        return this;
    }

    public CookingRecipeBuilder setIngredientQuantity(@Nonnull Ingredient ingredient, @Nonnull IngredientQuantity quantity) {
        if (!recipe.ingredients().contains(ingredient)) {
            throw new IllegalArgumentException(String.format("Unknown ingredient %s", ingredient.name()));
        }
        this.ingredients.put(ingredient, quantity);
        return this;
    }

    @Nonnull
    public CookingRecipe build() {
        if (ingredients.size() != recipe.ingredients().size()) {
            throw new IllegalStateException("Missing quantity for some ingredients");
        }
        if (cookingInstructions.isEmpty()) {
            throw new IllegalStateException("Missing cooking instructions");
        }
        return new CookingRecipe(recipe.id(), recipe.name(), recipe.dietType(), servings, ingredients, cookingInstructions);
    }
}
