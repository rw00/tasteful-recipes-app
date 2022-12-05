package com.rw.test.tastefulapp.service;

import com.rw.test.tastefulapp.common.exception.InvalidRequestException;
import com.rw.test.tastefulapp.common.exception.RecipeNotFoundException;
import com.rw.test.tastefulapp.common.model.ModelMapper;
import com.rw.test.tastefulapp.model.CookingRecipe;
import com.rw.test.tastefulapp.repository.CookingRecipesRepository;
import com.rw.test.tastefulapp.repository.CookingRecipesSearchRepository;
import com.rw.test.tastefulapp.repository.FilteringCriteria;
import com.rw.test.tastefulapp.repository.entity.CookingRecipeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class TastefulService {
    private final CookingRecipesRepository repository;
    private final CookingRecipesSearchRepository searchRepository;
    private final ModelMapper modelMapper;

    public TastefulService(CookingRecipesRepository repository, CookingRecipesSearchRepository searchRepository, ModelMapper modelMapper) {
        this.repository = repository;
        this.searchRepository = searchRepository;
        this.modelMapper = modelMapper;
    }

    public Page<CookingRecipe> getAll(Pageable pageable) {
        Page<CookingRecipeEntity> page = repository.findAll(pageable);
        List<CookingRecipe> cookingRecipes = page
                .stream()
                .map(modelMapper::mapFromRecipeEntity)
                .toList();
        return new PageImpl<>(cookingRecipes, page.getPageable(), page.getTotalElements());
    }

    @Transactional
    public CookingRecipe create(CookingRecipe recipe) {
        CookingRecipeEntity recipeEntity = modelMapper.mapToRecipeEntity(recipe);
        return modelMapper.mapFromRecipeEntity(repository.save(recipeEntity));
    }

    public CookingRecipe get(String id) {
        return repository.findById(id)
                .map(modelMapper::mapFromRecipeEntity)
                .orElseThrow(() -> new RecipeNotFoundException(String.format("No recipe with id=%s", id)));
    }

    @Transactional
    public void update(String id, CookingRecipe recipe) {
        if (recipe.id() != null && !recipe.id().equals(id)) {
            throw new InvalidRequestException("Invalid request to update recipe id");
        }
        if (!repository.existsById(id)) {
            throw new RecipeNotFoundException(String.format("No recipe with id=%s", id));
        }
        CookingRecipeEntity recipeEntity = modelMapper.mapToRecipeEntity(recipe);
        recipeEntity.setId(id);
        repository.save(recipeEntity);
    }

    @Transactional
    public void delete(String id) {
        repository.deleteById(id);
    }

    public List<CookingRecipe> search(FilteringCriteria filteringCriteria) {
        return searchRepository.search(filteringCriteria)
                .stream()
                .map(modelMapper::mapFromRecipeEntity)
                .toList();
    }
}
