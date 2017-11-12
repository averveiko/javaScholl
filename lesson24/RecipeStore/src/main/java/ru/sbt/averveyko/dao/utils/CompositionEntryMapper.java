package ru.sbt.averveyko.dao.utils;

import org.springframework.jdbc.core.RowMapper;
import ru.sbt.averveyko.dao.IngredientDao;
import ru.sbt.averveyko.dao.UnitDao;
import ru.sbt.averveyko.model.CompositionEntry;
import ru.sbt.averveyko.model.Ingredient;
import ru.sbt.averveyko.model.Unit;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CompositionEntryMapper implements RowMapper<CompositionEntry> {

    private final IngredientDao ingredientDao;
    private final UnitDao unitDao;

    public CompositionEntryMapper(IngredientDao ingredientDao, UnitDao unitDao) {
        this.ingredientDao = ingredientDao;
        this.unitDao = unitDao;
    }

    @Override
    public CompositionEntry mapRow(ResultSet resultSet, int i) throws SQLException {

        Integer ingredientId = resultSet.getInt("ingredient_id");
        Integer unitId = resultSet.getInt("unit_id");

        Ingredient ingredient = ingredientDao.getByPK(ingredientId);
        Unit unit = unitDao.getByPK(unitId);

        CompositionEntry compositionEntry = new CompositionEntry();
        compositionEntry.setIngredientRef(ingredient);
        compositionEntry.setAmount(resultSet.getDouble("amount"));
        compositionEntry.setUnitRef(unit);

        return compositionEntry;
    }
}
