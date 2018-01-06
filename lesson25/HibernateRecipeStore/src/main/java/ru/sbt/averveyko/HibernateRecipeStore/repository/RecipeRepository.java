package ru.sbt.averveyko.HibernateRecipeStore.repository;

import org.springframework.data.repository.CrudRepository;
import ru.sbt.averveyko.HibernateRecipeStore.model.Recipe;

import java.util.List;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
    List<Recipe> getByNameContains(String name);
}
