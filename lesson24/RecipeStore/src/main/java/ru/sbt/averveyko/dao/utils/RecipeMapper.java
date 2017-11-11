package ru.sbt.averveyko.dao.utils;

import org.springframework.jdbc.core.RowMapper;
import ru.sbt.averveyko.model.Recipe;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RecipeMapper implements RowMapper<Recipe> {
    @Override
    public Recipe mapRow(ResultSet resultSet, int i) throws SQLException {
        Recipe recipe = new Recipe();
        recipe.setId(resultSet.getInt("id"));
        recipe.setName(resultSet.getString("name"));
        recipe.setDescription(resultSet.getString("description"));
        return recipe;
    }
}
