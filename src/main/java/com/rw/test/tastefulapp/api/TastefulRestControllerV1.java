package com.rw.test.tastefulapp.api;

import com.rw.test.tastefulapp.common.api.RestControllerUtil;
import com.rw.test.tastefulapp.common.api.RestDocsExamples;
import com.rw.test.tastefulapp.common.validation.CreateValidationGroup;
import com.rw.test.tastefulapp.common.validation.ValidationUtil;
import com.rw.test.tastefulapp.model.CookingRecipe;
import com.rw.test.tastefulapp.repository.FilteringCriteria;
import com.rw.test.tastefulapp.service.TastefulService;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/recipes")
class TastefulRestControllerV1 {

    private final TastefulService service;

    TastefulRestControllerV1(TastefulService service) {
        this.service = service;
    }

    @PostMapping
    ResponseEntity<CookingRecipe> create(@Validated(CreateValidationGroup.class) @RequestBody CookingRecipe recipe) {
        CookingRecipe createdRecipe = service.create(recipe);
        URI location = RestControllerUtil.createdResourceLocation(createdRecipe.id());
        return ResponseEntity.created(location).body(createdRecipe);
    }

    @GetMapping("{id}")
    CookingRecipe get(@PathVariable @Pattern(regexp = ValidationUtil.UUID_REGEX) String id) {
        return service.get(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void update(@PathVariable @Pattern(regexp = ValidationUtil.UUID_REGEX) String id, @Valid @RequestBody CookingRecipe recipe) {
        service.update(id, recipe);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable @Pattern(regexp = ValidationUtil.UUID_REGEX) String id) {
        service.delete(id);
    }

    @GetMapping
    Page<CookingRecipe> getAll(@Parameter(example = RestDocsExamples.PAGEABLE_EXAMPLE) Pageable pageable) {
        return service.getAll(pageable);
    }

    @GetMapping("search")
    List<CookingRecipe> search(FilteringCriteria filteringCriteria) {
        return service.search(filteringCriteria);
    }
}
