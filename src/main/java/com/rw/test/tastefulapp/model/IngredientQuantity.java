package com.rw.test.tastefulapp.model;

import com.rw.test.tastefulapp.common.validation.ValidationUtil;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

/**
 * @param unit unit of quantity measurement such as grams, tablespoon...
 */
public record IngredientQuantity(@Positive double amount,
                                 @NotNull @Pattern(regexp = ValidationUtil.UNIT_REGEX) String unit) {
}
