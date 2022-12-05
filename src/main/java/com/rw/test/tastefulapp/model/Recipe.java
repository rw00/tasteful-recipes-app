package com.rw.test.tastefulapp.model;

import com.rw.test.tastefulapp.common.validation.CreateValidationGroup;
import com.rw.test.tastefulapp.common.validation.ValidationUtil;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record Recipe(@Null(groups = CreateValidationGroup.class) @Pattern(regexp = ValidationUtil.UUID_REGEX) String id,
                     @NotNull @Pattern(regexp = ValidationUtil.NAME_REGEX) String name,
                     @NotNull DietType dietType,
                     @NotNull @Size(min = 1) Set<Ingredient> ingredients) {
}
