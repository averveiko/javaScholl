package ru.sbt.averveyko.HibernateRecipeStore.model;

import javax.persistence.*;

@Entity
@Table(name = "Composition")
public class Composition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Ingredient ingredient;

    @Column(nullable = false)
    private Double amount;

    @OneToOne
    private Unit unit;

    public Composition() {
    }

    public Composition(Ingredient ingredient, Double amount, Unit unit) {
        this.ingredient = ingredient;
        this.amount = amount;
        this.unit = unit;
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
