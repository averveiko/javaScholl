package ru.sbt.averveyko.HibernateRecipeStore.repository;

import org.springframework.data.repository.CrudRepository;
import ru.sbt.averveyko.HibernateRecipeStore.model.Unit;

public interface UnitRepository extends CrudRepository<Unit, Long> {
    Unit getByName(String name);
}
