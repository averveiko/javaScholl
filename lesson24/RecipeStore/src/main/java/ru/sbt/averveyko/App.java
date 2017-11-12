package ru.sbt.averveyko;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.sbt.averveyko.dao.CompositionDao;
import ru.sbt.averveyko.dao.IngredientDao;
import ru.sbt.averveyko.dao.RecipeDao;
import ru.sbt.averveyko.dao.UnitDao;
import ru.sbt.averveyko.model.*;
import ru.sbt.averveyko.ui.console.ConsoleUI;

import java.util.List;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

        ConsoleUI consoleUI = context.getBean("consoleUI", ConsoleUI.class);
        consoleUI.start();

//        IngredientDao ingredientDaoImpl = context.getBean("ingredientDaoImpl", IngredientDao.class);
//        RecipeDao recipeDao = context.getBean("recipeDaoImpl", RecipeDao.class);
//        UnitDao unitDao = context.getBean("unitDaoImpl", UnitDao.class);
//
//
//        System.out.println("list all");
//        List<Ingredient> ingredients = ingredientDaoImpl.getAll();
//        for (Ingredient ingredient1 : ingredients) {
//            System.out.println(ingredient1);
//        }

//        //Test composition
//        Recipe recipe = context.getBean("recipe", Recipe.class);
//        recipe.setName("Бутерброд царский");
//        recipe.setDescription("Бутер с колбаской и сыром");
//
//        Unit unit = context.getBean("unit", Unit.class);
//        unit.setName("г");


//        System.out.println("\nСохраняем состав рецепта\n");
//
//        CompositionEntry entry1 = context.getBean("compositionEntry", CompositionEntry.class);
//        entry1.setIngredientRef(new Ingredient(null, "Колбаса докторская"));
//        entry1.setAmount(100.0);
//        entry1.setUnitRef(unit);
//
//        CompositionEntry entry2 = context.getBean("compositionEntry", CompositionEntry.class);
//        entry2.setIngredientRef(new Ingredient(null, "Сыр"));
//        entry2.setAmount(50.0);
//        entry2.setUnitRef(unit);
//
//        CompositionEntry entry3 = context.getBean("compositionEntry", CompositionEntry.class);
//        entry3.setIngredientRef(new Ingredient(null, "Хлеб"));
//        entry3.setAmount(200.0);
//        entry3.setUnitRef(unit);
//
//
//        Composition composition = context.getBean("composition", Composition.class);
//        composition.setRecipeRef(recipe);
//        composition.addEntry(entry1);
//        composition.addEntry(entry2);
//        composition.addEntry(entry3);
//
//        CompositionDao compositionDao = context.getBean("compositionDaoImpl", CompositionDao.class);
//        compositionDao.insert(composition);


//        CompositionDao compositionDao = context.getBean("compositionDaoImpl", CompositionDao.class);
//
////        compositionDao.deleteByRecipeID(1);
//
//        List<Composition> compositionList = compositionDao.searchByName("Бутер");
//
//        System.out.println("Найдено " + compositionList.size() + " рецептов");
//
//        for (Composition composition : compositionList) {
//            System.out.println(composition);
//        }

    }
}
