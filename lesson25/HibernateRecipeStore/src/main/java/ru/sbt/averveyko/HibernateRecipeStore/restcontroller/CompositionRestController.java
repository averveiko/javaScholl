package ru.sbt.averveyko.HibernateRecipeStore.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.sbt.averveyko.HibernateRecipeStore.model.Composition;
import ru.sbt.averveyko.HibernateRecipeStore.repository.CompositionRepository;

import java.util.List;

@RestController
@RequestMapping("/composition")
public class CompositionRestController {
    private final CompositionRepository compositionRepository;

    @Autowired
    public CompositionRestController(CompositionRepository compositionRepository) {
        this.compositionRepository = compositionRepository;
    }

    @RequestMapping("/get")
    List<Composition> get() {
        return (List<Composition>)compositionRepository.findAll();
    }

    @RequestMapping("/get/recipe/{id}")
    List<Composition> get(@PathVariable Long id) {
        return (List<Composition>)compositionRepository.findByRecipeId(id);
    }

}
