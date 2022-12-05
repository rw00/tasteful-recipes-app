package com.rw.test.tastefulapp.common.initializer;

import com.rw.test.tastefulapp.common.model.CookingRecipeBuilder;
import com.rw.test.tastefulapp.model.*;
import com.rw.test.tastefulapp.repository.CookingRecipesSearchRepository;
import com.rw.test.tastefulapp.service.TastefulService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
class BaseDataInitializer implements ApplicationRunner {
    private static final String GRAMS = "grams";
    private final TastefulService service;
    private final CookingRecipesSearchRepository searchRepository;

    BaseDataInitializer(TastefulService service, CookingRecipesSearchRepository searchRepository) {
        this.service = service;
        this.searchRepository = searchRepository;
    }

    private static CookingRecipe pepperoniPizzaRecipe() {
        Ingredient pepperoniIngredient = new Ingredient("pepperoni");
        Ingredient cheeseIngredient = new Ingredient("cheese");
        Ingredient tomatoSauceIngredient = new Ingredient("tomato sauce");
        CookingRecipeBuilder pepperoniPizzaRecipeBuilder = new CookingRecipeBuilder(new Recipe(null, "Pepperoni Pizza", DietType.MEAT, Set.of(pepperoniIngredient, cheeseIngredient, tomatoSauceIngredient)), 2);
        pepperoniPizzaRecipeBuilder.addCookingInstruction("Spread the tomato sauce on the dough");
        pepperoniPizzaRecipeBuilder.addCookingInstruction("Put lots of cheese");
        pepperoniPizzaRecipeBuilder.addCookingInstruction("Place the pepperoni slices as topping");
        pepperoniPizzaRecipeBuilder.addCookingInstruction("Heat the pizza in the oven for 10 minutes");
        pepperoniPizzaRecipeBuilder.setIngredientQuantity(tomatoSauceIngredient, new IngredientQuantity(300, GRAMS));
        pepperoniPizzaRecipeBuilder.setIngredientQuantity(cheeseIngredient, new IngredientQuantity(300, GRAMS));
        pepperoniPizzaRecipeBuilder.setIngredientQuantity(pepperoniIngredient, new IngredientQuantity(200, GRAMS));
        return pepperoniPizzaRecipeBuilder.build();
    }

    private static CookingRecipe bruschettaRecipe() {
        Ingredient baguetteIngredient = new Ingredient("baguette");
        Ingredient tomatoesIngredient = new Ingredient("tomatoes");
        Ingredient roculaIngredient = new Ingredient("rocula");
        CookingRecipeBuilder bruschettaRecipeBuilder = new CookingRecipeBuilder(new Recipe(null, "Bruschetta", DietType.VEGETARIAN, Set.of(baguetteIngredient, tomatoesIngredient, roculaIngredient)), 1);
        bruschettaRecipeBuilder.addCookingInstruction("Drizzle the baguette with oil and place it on the pan");
        bruschettaRecipeBuilder.addCookingInstruction("Cut the tomatoes in small slices and place on the baguette");
        bruschettaRecipeBuilder.addCookingInstruction("Put some rocula on top");
        bruschettaRecipeBuilder.setIngredientQuantity(baguetteIngredient, new IngredientQuantity(1, "baguette"));
        bruschettaRecipeBuilder.setIngredientQuantity(tomatoesIngredient, new IngredientQuantity(1, "tomato"));
        bruschettaRecipeBuilder.setIngredientQuantity(roculaIngredient, new IngredientQuantity(1, "bag"));
        return bruschettaRecipeBuilder.build();
    }

    private static CookingRecipe stroganoffRecipe() {
        Ingredient mushroomIngredient = new Ingredient("mushroom");
        Ingredient chickenIngredient = new Ingredient("chicken");
        Ingredient onionIngredient = new Ingredient("onion");
        Ingredient whiteSauceIngredient = new Ingredient("béchamel sauce");
        Ingredient riceIngredient = new Ingredient("rice");
        CookingRecipeBuilder stroganoffRecipeBuilder = new CookingRecipeBuilder(new Recipe(null, "Chicken Stroganoff", DietType.MEAT, Set.of(mushroomIngredient, chickenIngredient, onionIngredient, whiteSauceIngredient, riceIngredient)), 4);
        stroganoffRecipeBuilder.addCookingInstruction("Cut the onions and mushrooms");
        stroganoffRecipeBuilder.addCookingInstruction("Fry the onions and then add the mushrooms");
        stroganoffRecipeBuilder.addCookingInstruction("Cook the chicken separately and once cooked add it to the mushrooms");
        stroganoffRecipeBuilder.addCookingInstruction("Meanwhile prepare the rice");
        stroganoffRecipeBuilder.addCookingInstruction("Pour the sauce over the chicken and mushrooms adding salt and pepper to taste");
        stroganoffRecipeBuilder.setIngredientQuantity(mushroomIngredient, new IngredientQuantity(1, "kg"));
        stroganoffRecipeBuilder.setIngredientQuantity(onionIngredient, new IngredientQuantity(0.5, "kg"));
        stroganoffRecipeBuilder.setIngredientQuantity(chickenIngredient, new IngredientQuantity(1, "kg"));
        stroganoffRecipeBuilder.setIngredientQuantity(whiteSauceIngredient, new IngredientQuantity(250, "ml"));
        stroganoffRecipeBuilder.setIngredientQuantity(riceIngredient, new IngredientQuantity(3, "cups"));
        return stroganoffRecipeBuilder.build();
    }

    private static CookingRecipe bakedSalmonRecipe() {
        Ingredient salmonIngredient = new Ingredient("salmon");
        Ingredient garlicIngredient = new Ingredient("garlic");
        Ingredient rosemaryIngredient = new Ingredient("rosemary");
        Ingredient roculaIngredient = new Ingredient("rocula");
        Ingredient cherryTomatoesIngredient = new Ingredient("cherry tomatoes");
        Ingredient lemonIngredient = new Ingredient("lemon");
        CookingRecipeBuilder salmonRecipeBuilder = new CookingRecipeBuilder(new Recipe(null, "Baked Salmon", DietType.MEAT, Set.of(salmonIngredient, garlicIngredient, rosemaryIngredient, lemonIngredient, cherryTomatoesIngredient, roculaIngredient)), 3);
        salmonRecipeBuilder.addCookingInstruction("Add salt, pepper, garlic and rosemary to the salmon on both side");
        salmonRecipeBuilder.addCookingInstruction("Cook the salmon in the oven");
        salmonRecipeBuilder.addCookingInstruction("Meanwhile prepare a side salad by mixing rocula, cherry tomatoes and a squeeze of lemon juice");
        salmonRecipeBuilder.addCookingInstruction("Serve the salmon with a slice of lemon and the salad");
        salmonRecipeBuilder.setIngredientQuantity(salmonIngredient, new IngredientQuantity(1.2, "kg"));
        salmonRecipeBuilder.setIngredientQuantity(garlicIngredient, new IngredientQuantity(1, "clove"));
        salmonRecipeBuilder.setIngredientQuantity(rosemaryIngredient, new IngredientQuantity(1, "rosemary"));
        salmonRecipeBuilder.setIngredientQuantity(cherryTomatoesIngredient, new IngredientQuantity(0.5, "kg"));
        salmonRecipeBuilder.setIngredientQuantity(roculaIngredient, new IngredientQuantity(0.3, "g"));
        salmonRecipeBuilder.setIngredientQuantity(lemonIngredient, new IngredientQuantity(1, "lemon"));
        return salmonRecipeBuilder.build();
    }

    private static CookingRecipe potatoSouffleRecipe() {
        Ingredient potatoesIngredient = new Ingredient("potatoes");
        Ingredient beefIngredient = new Ingredient("minced beef");
        Ingredient butterIngredient = new Ingredient("butter");
        Ingredient breadIngredient = new Ingredient("bread");
        Ingredient onionsIngredient = new Ingredient("onions");
        Ingredient garlicIngredient = new Ingredient("garlic");
        CookingRecipeBuilder potatoSouffleRecipeBuilder = new CookingRecipeBuilder(new Recipe(null, "Potato Soufflé", DietType.MEAT, Set.of(potatoesIngredient, beefIngredient, butterIngredient, garlicIngredient, onionsIngredient, breadIngredient)), 6);
        potatoSouffleRecipeBuilder.addCookingInstruction("Boil the potatoes for 15 minutes with salt");
        potatoSouffleRecipeBuilder.addCookingInstruction("Make mashed potatoes by adding some butter");
        potatoSouffleRecipeBuilder.addCookingInstruction("Cut the onions and the garlic");
        potatoSouffleRecipeBuilder.addCookingInstruction("Fry the onions, garlic and minced beef for 5 minutes");
        potatoSouffleRecipeBuilder.addCookingInstruction("Spread the mashed potatoes on the casserole");
        potatoSouffleRecipeBuilder.addCookingInstruction("Spread the beef and onion mix on top");
        potatoSouffleRecipeBuilder.addCookingInstruction("Add the buttered bread crumbs as a final layer");
        potatoSouffleRecipeBuilder.addCookingInstruction("Bake in the oven for 30 minutes");
        potatoSouffleRecipeBuilder.setIngredientQuantity(potatoesIngredient, new IngredientQuantity(2.25, "kg"));
        potatoSouffleRecipeBuilder.setIngredientQuantity(beefIngredient, new IngredientQuantity(1, "kg"));
        potatoSouffleRecipeBuilder.setIngredientQuantity(butterIngredient, new IngredientQuantity(100, "g"));
        potatoSouffleRecipeBuilder.setIngredientQuantity(onionsIngredient, new IngredientQuantity(0.5, "kg"));
        potatoSouffleRecipeBuilder.setIngredientQuantity(breadIngredient, new IngredientQuantity(1, "bread"));
        potatoSouffleRecipeBuilder.setIngredientQuantity(garlicIngredient, new IngredientQuantity(1, "clove"));
        return potatoSouffleRecipeBuilder.build();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        service.create(pepperoniPizzaRecipe());
        service.create(bruschettaRecipe());
        service.create(stroganoffRecipe());
        service.create(potatoSouffleRecipe());
        service.create(bakedSalmonRecipe());
    }
}
