package ru.sbt.averveyko.HibernateRecipeStore.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.sbt.averveyko.HibernateRecipeStore.model.Ingredient;
import ru.sbt.averveyko.HibernateRecipeStore.repository.IngredientRepository;

import java.util.List;

@RestController
@RequestMapping("/ingredient")
public class IngredientRestController {
    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientRestController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @RequestMapping("/get")
    public List<Ingredient> get() {
        return (List<Ingredient>)ingredientRepository.findAll();
    }

    @RequestMapping("/get/{id}")
    public Ingredient get(@PathVariable Long id) {
        return ingredientRepository.findOne(id);
    }
}
