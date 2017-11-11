package ru.sbt.averveyko;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.sbt.averveyko.dao.IngredientDao;
import ru.sbt.averveyko.model.Ingredient;

import java.util.List;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

        IngredientDao ingredientDaoImpl = context.getBean("ingredientDaoImpl", IngredientDao.class);


//        Ingredient ingredient = context.getBean("ingredient", Ingredient.class);
//        ingredient.setName("Соль");
//
//        ingredientDaoImpl.insert(ingredient);
//        System.out.println(ingredient);

//        Ingredient ingredient = ingredientDaoImpl.getByPK(2);
//        System.out.println(ingredient);
//
//        System.out.println("delete ingredient 2");
//        ingredientDaoImpl.deleteByPK(2);

//        Ingredient ingredient = ingredientDaoImpl.getByPK(3);
//        ingredient.setName("Картошечка");
//        ingredientDaoImpl.update(ingredient);

        System.out.println("list all");
        List<Ingredient> ingredients = ingredientDaoImpl.getAll();
        for (Ingredient ingredient1 : ingredients) {
            System.out.println(ingredient1);
        }

    }
}
