package com.rw.test.tastefulapp.repository;

import com.rw.test.tastefulapp.repository.entity.CookingRecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CookingRecipesRepository extends JpaRepository<CookingRecipeEntity, String> {
}
