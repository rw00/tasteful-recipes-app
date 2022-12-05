package com.rw.test.tastefulapp.repository;

import com.rw.test.tastefulapp.repository.entity.CookingIngredientEntity;
import com.rw.test.tastefulapp.repository.entity.CookingRecipeEntity;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public interface CookingRecipesSearchRepository {
    List<CookingRecipeEntity> search(FilteringCriteria filteringCriteria);
}

@Repository
class CookingRecipesSearchRepositoryImpl implements CookingRecipesSearchRepository {
    private final SessionFactory sessionFactory;

    CookingRecipesSearchRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<CookingRecipeEntity> search(FilteringCriteria filteringCriteria) {
        try (Session session = sessionFactory.openSession()) {
            Filter servingsFilter = session.enableFilter("servingsFilter");
            servingsFilter.setParameter("servings", filteringCriteria.getServings());
            if (filteringCriteria.getDietType() != null) {
                Filter dietTypeFilter = session.enableFilter("dietTypeFilter");
                dietTypeFilter.setParameter("dietType", filteringCriteria.getDietType().name());
            }
            List<CookingRecipeEntity> cookingRecipes = session.createQuery("FROM CookingRecipeEntity", CookingRecipeEntity.class).getResultList();
            return furtherFiltering(cookingRecipes, filteringCriteria);
        }
    }

    private List<CookingRecipeEntity> furtherFiltering(List<CookingRecipeEntity> cookingRecipes, FilteringCriteria filter) {
        return cookingRecipes.stream()
                .map(CookingRecipeEntity::initializeCollections)
                .filter(i -> matchesFiltering(i, filter))
                .toList();
    }

    private boolean matchesFiltering(CookingRecipeEntity cookingRecipe, FilteringCriteria filter) {
        Set<String> ingredients = cookingRecipe.getIngredients().stream()
                .map(CookingIngredientEntity::getName)
                .collect(Collectors.toSet());
        if (filter.getIncludedIngredients() != null && !ingredients.containsAll(filter.getIncludedIngredients())) {
            return false;
        }
        if (filter.getExcludedIngredients() != null && ingredients.stream().anyMatch(i -> filter.getExcludedIngredients().contains(i))) {
            return false;
        }
        return filter.getInstructionsSearch() == null || cookingRecipe.getCookingInstructions().stream().anyMatch(i -> i.getInstruction().contains(filter.getInstructionsSearch()));
    }
}
