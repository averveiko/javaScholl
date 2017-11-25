package ru.sbt.averveyko.HibernateRecipeStore;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.sbt.averveyko.HibernateRecipeStore.model.Composition;
import ru.sbt.averveyko.HibernateRecipeStore.model.Ingredient;
import ru.sbt.averveyko.HibernateRecipeStore.model.Recipe;
import ru.sbt.averveyko.HibernateRecipeStore.model.Unit;
import ru.sbt.averveyko.HibernateRecipeStore.repository.CompositionRepository;
import ru.sbt.averveyko.HibernateRecipeStore.repository.IngredientRepository;
import ru.sbt.averveyko.HibernateRecipeStore.repository.RecipeRepository;
import ru.sbt.averveyko.HibernateRecipeStore.repository.UnitRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class App {

    private final IngredientRepository ingredientRepository;
    private final RecipeRepository recipeRepository;
    private final CompositionRepository compositionRepository;
    private final UnitRepository unitRepository;

    @Autowired
    public App(IngredientRepository ingredientRepository,
               RecipeRepository recipeRepository,
               CompositionRepository compositionRepository,
               UnitRepository unitRepository) {

        this.ingredientRepository = ingredientRepository;
        this.recipeRepository = recipeRepository;
        this.compositionRepository = compositionRepository;
        this.unitRepository = unitRepository;
    }

    public void test() {
        //ingredientRepository.save(new Ingredient("Мясо"));
        //ingredientRepository.save(new Ingredient("Картошка"));

        Unit unit1 = new Unit("г.");
        Unit unit2 = new Unit("шт.");
        unitRepository.save(Arrays.asList(unit1, unit2));

        Ingredient ingredient1 = new Ingredient("Фарш мясной");
        Ingredient ingredient2 = new Ingredient("Макароны");
        Ingredient ingredient3 = new Ingredient("Лук");
        Ingredient ingredient4 = new Ingredient("Томатная паста");
        ingredientRepository.save(Arrays.asList(ingredient1, ingredient2, ingredient3, ingredient4));

        Recipe recipe = new Recipe();
        recipe.setName("Макароны по-флотски");
        recipe.setDescription("Must have");
        recipeRepository.save(recipe);

        //Recipe recipe, Ingredient ingredient, Double amount, Unit unit
        Composition composition1 = new Composition(recipe, ingredient1, 500.0, unit1);
        Composition composition2 = new Composition(recipe, ingredient2, 1500.0, unit1);
        Composition composition3 = new Composition(recipe, ingredient3, 1.0, unit2);
        Composition composition4 = new Composition(recipe, ingredient4, 100.0, unit1);
        compositionRepository.save(Arrays.asList(composition1, composition2, composition3, composition4));


        //List<Composition> compositions = new ArrayList<>(Arrays.asList(composition1, composition2, composition3, composition4));
        //recipe.setCompositions(compositions);


    }
}
