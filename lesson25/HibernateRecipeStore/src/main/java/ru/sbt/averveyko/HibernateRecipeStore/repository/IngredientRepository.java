package ru.sbt.averveyko.HibernateRecipeStore.repository;

import org.springframework.data.repository.CrudRepository;
import ru.sbt.averveyko.HibernateRecipeStore.model.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
    Ingredient findByName(String name);
}
