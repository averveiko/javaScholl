package ru.sbt.averveyko.dao;

import ru.sbt.averveyko.model.Composition;

import java.util.List;

public interface CompositionDao {

    void insert(Composition composition);

    Composition getByRecipeID(Integer recipeID);

    List<Composition> getAll();

    void deleteByRecipeID(Integer recipeID);

    List<Composition> searchByName(String name);
}
