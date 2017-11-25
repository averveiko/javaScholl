package ru.sbt.averveyko.HibernateRecipeStore.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Recipe")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    //@OneToMany
    //private List<Composition> compositions;

    public Recipe() {
    }

    public Recipe(String name, String description) {
        this.name = name;
        this.description = description;
        //this.compositions = compositions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public List<Composition> getCompositions() {
//        return compositions;
//    }
//
//    public void setCompositions(List<Composition> compositions) {
//        this.compositions = compositions;
//    }
}
