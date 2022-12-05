package com.rw.test.tastefulapp.repository;

import com.rw.test.tastefulapp.common.api.RestDocsExamples;
import com.rw.test.tastefulapp.model.DietType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

import java.util.Set;

@Schema(example = RestDocsExamples.SEARCH_REQUEST_EXAMPLE)
@Value
@Builder
public class FilteringCriteria {
    DietType dietType;
    int servings;
    Set<String> includedIngredients;
    Set<String> excludedIngredients;
    String instructionsSearch;
}
