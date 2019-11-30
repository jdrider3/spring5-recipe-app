package guru.springframework.bootstrap;

import com.sun.org.apache.regexp.internal.RE;
import guru.springframework.domain.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final RecipeRepository recipeRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository, RecipeRepository recipeRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeRepository = recipeRepository;
    }

    private List<Recipe> getRecipes(){
        List<Recipe> recipes = new ArrayList<>(2);

        //get UOMs
        Optional<UnitOfMeasure> eachUomOption = unitOfMeasureRepository.findByDescription("Each");

        if(!eachUomOption.isPresent()) {
            throw new RuntimeException("Expected UOM Not found - Each");
        }

        Optional<UnitOfMeasure> teaspoonUomOption = unitOfMeasureRepository.findByDescription("Teaspoon");

        if(!teaspoonUomOption.isPresent()) {
            throw new RuntimeException("Expected UOM Not found");
        }

        Optional<UnitOfMeasure> tablespoonUomOption = unitOfMeasureRepository.findByDescription("Tablespoon");

        if(!tablespoonUomOption.isPresent()) {
            throw new RuntimeException("Expected UOM Not found");
        }

        Optional<UnitOfMeasure> cupUomOption = unitOfMeasureRepository.findByDescription("Cup");

        if(!cupUomOption.isPresent()) {
            throw new RuntimeException("Expected UOM Not found");
        }

        Optional<UnitOfMeasure> pinchUomOption = unitOfMeasureRepository.findByDescription("Pinch");

        if(!pinchUomOption.isPresent()) {
            throw new RuntimeException("Expected UOM Not found");
        }

        Optional<UnitOfMeasure> ounceUomOption = unitOfMeasureRepository.findByDescription("Ounce");

        if(!ounceUomOption.isPresent()) {
            throw new RuntimeException("Expected UOM Not found");
        }

        Optional<UnitOfMeasure> ripeUomOption = unitOfMeasureRepository.findByDescription("Ripe");

        if(!ripeUomOption.isPresent()) {
            throw new RuntimeException("Expected UOM Not found");
        }

        Optional<UnitOfMeasure> dashUomOption = unitOfMeasureRepository.findByDescription("Dash");

        if(!dashUomOption.isPresent()) {
            throw new RuntimeException("Expected UOM Not found");
        }

        Optional<UnitOfMeasure> pintUomOption = unitOfMeasureRepository.findByDescription("Pint");

        if(!pintUomOption.isPresent()) {
            throw new RuntimeException("Expected UOM Not found");
        }

        //Get unit of measures
        UnitOfMeasure eachUom = eachUomOption.get();
        UnitOfMeasure teaspoonUom = teaspoonUomOption.get();
        UnitOfMeasure tablespoonUom = tablespoonUomOption.get();
        UnitOfMeasure dashUom = dashUomOption.get();
        UnitOfMeasure pintUom = pintUomOption.get();
        UnitOfMeasure ripeUom = ripeUomOption.get();
        UnitOfMeasure ounceUom = ounceUomOption.get();
        UnitOfMeasure pinchUom = pinchUomOption.get();
        UnitOfMeasure cupUom = cupUomOption.get();

        //get Categories
        Optional<Category> americanCategoryOption = categoryRepository.findByDescription("American");
        if(!americanCategoryOption.isPresent()) {
            throw new RuntimeException("Expected Category Not found");
        }

        Optional<Category> mexicanCategoryOption = categoryRepository.findByDescription("Mexican");
        if(!mexicanCategoryOption.isPresent()) {
            throw new RuntimeException("Expected Category Not found");
        }

        Category americanCategory = americanCategoryOption.get();
        Category mexicanCategory = mexicanCategoryOption.get();

        //Yummy Guac
        Recipe guacamole = new Recipe();
        guacamole.setDescription("Perfect Guacamole");
        guacamole.setPrepTime(10);
        guacamole.setCookTime(0);
        guacamole.setDifficulty(Difficulty.EASY);
        guacamole.setDirections("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of " +
                "the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) " +
                "Place in a bowl." +
                "\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky." +
                "\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste." +
                "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n" +
                "\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.");

        Notes guacNotes = new Notes();
        guacNotes.setRecipeNotes("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "\n" +
                "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries (see our Strawberry Guacamole).\n" +
                "\n" +
                "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n" +
                "\n" +
                "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\n" +
                "\n" +
                "For a deviled egg version with guacamole, try our Guacamole Deviled Eggs!");
        guacamole.setNotes(guacNotes);

        guacamole.addIngredient(new Ingredient("ripe avocados", new BigDecimal(2), eachUom))
                .addIngredient(new Ingredient("Kosher salt", new BigDecimal(".5"), teaspoonUom))
                .addIngredient(new Ingredient("fresh lime juice or lemon juice", new BigDecimal(2), tablespoonUom))
                .addIngredient(new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal(2), tablespoonUom))
                .addIngredient(new Ingredient("serrano chiles, stems and seed removed, minced", new BigDecimal(2), eachUom))
                .addIngredient(new Ingredient("Cilantro", new BigDecimal(2), tablespoonUom))
                .addIngredient(new Ingredient("freshly grated black pepper", new BigDecimal(2), dashUom))
                .addIngredient(new Ingredient("ripe tomato, seed and pulp removed, chopped", new BigDecimal(2), dashUom));
        
        guacamole.getCategories().add(americanCategory);
        guacamole.getCategories().add(mexicanCategory);

        recipes.add(guacamole);

        Recipe tacosRecipe = new Recipe();
        tacosRecipe.setDescription("Spicy grilled chicken tacos!");
        tacosRecipe.setCookTime(15);
        tacosRecipe.setPrepTime(20);
        tacosRecipe.setDifficulty(Difficulty.MODERATE);
        tacosRecipe.setServings(6);
        tacosRecipe.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat." +
                "\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings." +
                "\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes." +
                "\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side." +
                "\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.");

        Notes tacoNotes = new Notes();
        tacoNotes.setRecipeNotes("Look for ancho chile powder with the Mexican ingredients at your grocery store, on buy it online. (If you can't find ancho chili powder, you replace the ancho chili, the oregano, and the cumin with 2 1/2 tablespoons regular chili powder, though the flavor won't be quite the same.)");
        tacosRecipe.setNotes(tacoNotes);
        
        tacosRecipe.addIngredient(new Ingredient("ancho chili powder", new BigDecimal(2), tablespoonUom))
                .addIngredient(new Ingredient("dried oregano", new BigDecimal(1), teaspoonUom))
                .addIngredient(new Ingredient("dried cumin", new BigDecimal(1), teaspoonUom))
                .addIngredient(new Ingredient("sugar", new BigDecimal(1), teaspoonUom))
                .addIngredient(new Ingredient("clove garlic, finely chopped", new BigDecimal(1), eachUom))
                .addIngredient(new Ingredient("finely grated orange zest", new BigDecimal(1), tablespoonUom))
                .addIngredient(new Ingredient("fresh-squeezed orange juice", new BigDecimal(3), tablespoonUom))
                .addIngredient(new Ingredient("olive oil", new BigDecimal(2), tablespoonUom))
                .addIngredient(new Ingredient("skinless, boneless chicken thighs", new BigDecimal(4), eachUom));

        tacosRecipe.getCategories().add(americanCategory);
        tacosRecipe.getCategories().add(mexicanCategory);

        recipes.add(tacosRecipe);
        return recipes;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
    }
}
