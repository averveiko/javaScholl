package ru.sbt.averveyko.HibernateRecipeStore.model;

import javax.persistence.*;

@Entity
@Table(name = "Composition")
public class Composition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Recipe recipe;

    @OneToOne
    private Ingredient ingredient;

    @Column(nullable = false)
    private Double amount;

    @OneToOne
    private Unit unit;

    public Composition() {
    }

    public Composition(Recipe recipe, Ingredient ingredient, Double amount, Unit unit) {
        this.recipe = recipe;
        this.ingredient = ingredient;
        this.amount = amount;
        this.unit = unit;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
