package com.rw.test.tastefulapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rw.test.tastefulapp.common.model.CookingRecipeBuilder;
import com.rw.test.tastefulapp.common.validation.ValidationUtil;
import com.rw.test.tastefulapp.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TastefulApplicationIT extends IntegrationTestBase {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void when_creating_recipe_input_is_validated() throws Exception {
        var ingredient = new Ingredient("cheese");
        var cookingRecipe = new CookingRecipeBuilder(new Recipe(null, " ", DietType.MEAT, Set.of(ingredient)), 0)
                .addCookingInstruction("Add cheese")
                .addCookingInstruction("Bake the pizza in the oven")
                .setIngredientQuantity(ingredient, new IngredientQuantity(0.5, "kg"))
                .build();

        MvcResult mvcResult = mockMvc.perform(post("/v1/recipes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cookingRecipe)))
                .andExpect(status().isBadRequest())
                .andReturn();

        Exception resolvedException = mvcResult.getResolvedException();
        assertThat(resolvedException).isInstanceOf(MethodArgumentNotValidException.class);
        List<FieldError> unsortedFieldErrors = ((MethodArgumentNotValidException) resolvedException).getFieldErrors();
        assertThat(unsortedFieldErrors).hasSize(2);
        List<FieldError> fieldErrors = unsortedFieldErrors.stream().sorted(Comparator.comparing(FieldError::getField)).toList();
        FieldError nameFieldError = fieldErrors.get(0);
        assertThat(nameFieldError.getField()).isEqualTo("name");
        assertThat(nameFieldError.getDefaultMessage()).contains("must match ");
        assertThat(fieldErrors.get(1))
                .hasFieldOrPropertyWithValue("field", "servings")
                .hasFieldOrPropertyWithValue("defaultMessage", "must be greater than 0");
    }

    @Test
    void service_creates_and_returns_recipe() throws Exception {
        CookingRecipeBuilder cookingRecipeBuilder = MockTestData.cookingRecipeBuilder();

        MvcResult mvcResult = mockMvc.perform(post("/v1/recipes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cookingRecipeBuilder.build())))
                .andExpect(status().isCreated())
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        CookingRecipe cookingRecipe = objectMapper.readValue(contentAsString, CookingRecipe.class);
        assertThat(cookingRecipe.id()).matches(ValidationUtil.UUID_REGEX);
        assertThat(cookingRecipe.name()).isEqualTo("pizza");
        assertThat(cookingRecipe.cookingInstructions()).hasSize(2);
    }

    @Test
    void service_creates_and_deletes_recipe() throws Exception {
        CookingRecipeBuilder cookingRecipeBuilder = MockTestData.cookingRecipeBuilder();

        MvcResult mvcResult = mockMvc.perform(post("/v1/recipes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cookingRecipeBuilder.build())))
                .andExpect(status().isCreated())
                .andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        CookingRecipe cookingRecipe = objectMapper.readValue(contentAsString, CookingRecipe.class);

        String recipeUri = "/v1/recipes/" + cookingRecipe.id();
        mockMvc.perform(get(recipeUri))
                .andExpect(status().isOk());

        mockMvc.perform(delete(recipeUri))
                .andExpect(status().isNoContent());

        mockMvc.perform(get(recipeUri))
                .andExpect(status().isNotFound());
    }
}
