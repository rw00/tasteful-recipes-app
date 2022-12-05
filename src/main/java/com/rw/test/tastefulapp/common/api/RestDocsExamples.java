package com.rw.test.tastefulapp.common.api;

import lombok.experimental.UtilityClass;

@UtilityClass
public class RestDocsExamples {
    public final String PAGEABLE_EXAMPLE = """
            {
                "page": 1,
                "size": 3
            }""";
    public static final String COOKING_RECIPE_REQUEST_BODY_EXAMPLE = """
            {
                "id": null,
                "name": "Pepperoni Pizza",
                "dietType": "MEAT",
                "servings": 2,
                "ingredients": {
                    "Ingredient[name=tomato sauce]": {
                        "amount": 300,
                        "unit": "grams"
                    },
                    "Ingredient[name=pepperoni]": {
                        "amount": 200,
                        "unit": "grams"
                    },
                    "Ingredient[name=cheese]": {
                        "amount": 300,
                        "unit": "grams"
                    }
                },
                "cookingInstructions": [
                    "Spread the tomato sauce on the dough",
                    "Put lots of cheese",
                    "Place the pepperoni slices as topping",
                    "Heat the pizza in the oven for 10 minutes"
                ]
            }""";

    public static final String SEARCH_REQUEST_EXAMPLE = """
            {
              "dietType": "MEAT",
              "servings": 2,
              "includedIngredients": [
                "potatoes"
              ]
            }
            """;
}
