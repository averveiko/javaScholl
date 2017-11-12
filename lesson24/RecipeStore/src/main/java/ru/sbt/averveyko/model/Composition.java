package ru.sbt.averveyko.model;

import java.util.ArrayList;
import java.util.List;

public class Composition {
    private List<Integer> idList;
    private Recipe recipeRef;
    private List<CompositionEntry> entryList = new ArrayList<>();

    public Composition() {
    }

    public void addEntry(CompositionEntry entry) {
        entryList.add(entry);
    }

    public List<Integer> getIdList() {
        return idList;
    }

    public void setIdList(List<Integer> idList) {
        this.idList = idList;
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
                "idList=" + idList +
                ", recipeRef=" + recipeRef +
                ", entryList=" + entryList +
                '}';
    }
}
