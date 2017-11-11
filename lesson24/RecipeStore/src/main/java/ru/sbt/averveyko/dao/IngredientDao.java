package ru.sbt.averveyko.dao;

import ru.sbt.averveyko.model.Ingredient;

public interface IngredientDao {
    void insert(Ingredient ingredient);
    Ingredient getByPK(Integer primaryKey);

}
