package ru.sbt.averveyko;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.sbt.averveyko.dao.IngredientDao;
import ru.sbt.averveyko.model.Ingredient;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

        IngredientDao ingredientDaoImpl = context.getBean("ingredientDaoImpl", IngredientDao.class);


//        Ingredient ingredient = context.getBean("ingredient", Ingredient.class);
//        ingredient.setName("Картофель");
//
//        ingredientDao.insert(ingredient);

        Ingredient ingredient = ingredientDaoImpl.getByPK(2);
        System.out.println(ingredient);

    }
}
