package ru.sbt.averveyko.model;

public class CompositionEntry {
    private Ingredient ingredientRef;
    private Double amount;
    private Unit unitRef;

    public CompositionEntry() {
    }

    public CompositionEntry(Ingredient ingredientRef, Double amount, Unit unitRef) {
        this.ingredientRef = ingredientRef;
        this.amount = amount;
        this.unitRef = unitRef;
    }

    public Ingredient getIngredientRef() {
        return ingredientRef;
    }

    public void setIngredientRef(Ingredient ingredientRef) {
        this.ingredientRef = ingredientRef;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Unit getUnitRef() {
        return unitRef;
    }

    public void setUnitRef(Unit unitRef) {
        this.unitRef = unitRef;
    }

    @Override
    public String toString() {
        return "CompositionEntry{" +
                "ingredientRef=" + ingredientRef +
                ", amount=" + amount +
                ", unitRef=" + unitRef +
                '}';
    }
}
