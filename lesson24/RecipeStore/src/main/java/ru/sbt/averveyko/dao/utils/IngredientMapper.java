package ru.sbt.averveyko.dao.utils;

import org.springframework.jdbc.core.RowMapper;
import ru.sbt.averveyko.model.Ingredient;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IngredientMapper implements RowMapper<Ingredient> {
    @Override
    public Ingredient mapRow(ResultSet resultSet, int i) throws SQLException {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(resultSet.getInt("id"));
        ingredient.setName(resultSet.getString("name"));
        return ingredient;
    }
}
