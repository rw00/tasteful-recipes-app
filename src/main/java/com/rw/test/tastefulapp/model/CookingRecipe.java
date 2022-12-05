package com.rw.test.tastefulapp.model;

import com.rw.test.tastefulapp.common.api.RestDocsExamples;
import com.rw.test.tastefulapp.common.validation.CreateValidationGroup;
import com.rw.test.tastefulapp.common.validation.ValidationUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.util.List;
import java.util.Map;

@Schema(example = RestDocsExamples.COOKING_RECIPE_REQUEST_BODY_EXAMPLE)
public record CookingRecipe(
        @Null(groups = CreateValidationGroup.class) @Pattern(regexp = ValidationUtil.UUID_REGEX) String id,
        @NotNull @Pattern(regexp = ValidationUtil.NAME_REGEX) String name,
        @NotNull DietType dietType,
        @Positive int servings,
        @NotNull @Size(min = 1) Map<Ingredient, IngredientQuantity> ingredients,
        @NotNull @Size(min = 1) List<String> cookingInstructions
) {
}
