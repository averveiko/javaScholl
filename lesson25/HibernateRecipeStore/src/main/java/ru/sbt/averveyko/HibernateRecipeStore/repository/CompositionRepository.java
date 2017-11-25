package ru.sbt.averveyko.HibernateRecipeStore.repository;

import org.springframework.data.repository.CrudRepository;
import ru.sbt.averveyko.HibernateRecipeStore.model.Composition;

import java.util.List;

public interface CompositionRepository  extends CrudRepository<Composition, Long> {
    List<Composition> findByRecipeId(Long id);
}
