package ru.sbt.averveyko.HibernateRecipeStore.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sbt.averveyko.HibernateRecipeStore.model.Recipe;
import ru.sbt.averveyko.HibernateRecipeStore.repository.RecipeRepository;

import java.util.List;

@RestController
@RequestMapping("/recipe")
public class RecipeRestController {
    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeRestController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @RequestMapping("/get")
    public List<Recipe> get() {
        return (List<Recipe>)recipeRepository.findAll();
    }

    @RequestMapping("/get/{id}")
    public Recipe get(@PathVariable Long id) {
        return recipeRepository.findOne(id);
    }
}
