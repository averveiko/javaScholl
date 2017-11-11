package ru.sbt.averveyko.model;

import java.util.ArrayList;
import java.util.List;

public class Composition {
    private Integer id;
    private Recipe recipeRef;
    private List<CompositionEntry> entryList;

    public Composition() {
    }

    public Composition(Integer id, Recipe recipeRef, List<CompositionEntry> entryList) {
        this.id = id;
        this.recipeRef = recipeRef;
        this.entryList = entryList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Recipe getRecipeRef() {
        return recipeRef;
    }

    public void setRecipeRef(Recipe recipeRef) {
        this.recipeRef = recipeRef;
    }

    public List<CompositionEntry> getEntryList() {
        return entryList;
    }

    public void setEntryList(List<CompositionEntry> entryList) {
        this.entryList = entryList;
    }

    @Override
    public String toString() {
        return "Composition{" +
                "id=" + id +
                ", recipeRef=" + recipeRef +
                ", entryList=" + entryList +
                '}';
    }
}
