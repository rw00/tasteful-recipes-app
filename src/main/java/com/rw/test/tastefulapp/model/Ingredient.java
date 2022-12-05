package com.rw.test.tastefulapp.model;

import com.rw.test.tastefulapp.common.validation.ValidationUtil;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record Ingredient(@NotNull @Pattern(regexp = ValidationUtil.NAME_REGEX) String name) {
}
