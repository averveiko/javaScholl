package ru.sbt.averveyko.dao;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.sbt.averveyko.dao.utils.RecipeMapper;
import ru.sbt.averveyko.model.Recipe;

import javax.sql.DataSource;
import java.util.List;

public class RecipeDaoImpl implements RecipeDao {

    private static final String QUERY_INSERT =
            "INSERT INTO Recipe(name, description)\n" +
                    "VALUES (:name, :description);";

    private static final String QUERY_GET_BY_PK =
            "SELECT id, name, description\n" +
                    "FROM Recipe\n" +
                    "WHERE id=:id;";

    private static final String QUERY_GET_ALL =
            "SELECT id, name, description\n" +
                    "FROM Recipe;";

    private static final String QUERY_DELETE_BY_PK =
            "DELETE FROM Recipe\n" +
                    "WHERE id=:id;";

    private static final String QUERY_UPDATE =
            "UPDATE Recipe\n" +
                    "SET name = :name, description = :description\n" +
                    "WHERE id = :id;";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final RecipeMapper recipeMapper;

    public RecipeDaoImpl(DataSource dataSource, RecipeMapper recipeMapper) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.recipeMapper = recipeMapper;
    }

    @Override
    public void insert(Recipe recipe) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(recipe);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int result = namedParameterJdbcTemplate.update(QUERY_INSERT, param, keyHolder);
        recipe.setId(keyHolder.getKey().intValue());
        System.out.println("insert " + result + " recipe: " + recipe.getName());
    }

    @Override
    public Recipe getByPK(Integer primaryKey) {
        SqlParameterSource param = new MapSqlParameterSource("id", primaryKey);
        Recipe recipe = namedParameterJdbcTemplate.queryForObject(QUERY_GET_BY_PK, param, recipeMapper);
        return recipe;
    }

    @Override
    public List<Recipe> getAll() {
        List<Recipe> recipes = namedParameterJdbcTemplate.query(QUERY_GET_ALL, recipeMapper);
        return recipes;
    }

    @Override
    public void deleteByPK(Integer primaryKey) {
        SqlParameterSource param = new MapSqlParameterSource("id", primaryKey);
        int result = namedParameterJdbcTemplate.update(QUERY_DELETE_BY_PK, param);
        System.out.println("delete " + result + " recipe ");
    }

    @Override
    public void update(Recipe recipe) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(recipe);
        int result = namedParameterJdbcTemplate.update(QUERY_UPDATE, param);
        System.out.println("updated " + result + " recipe " + recipe.getName());
    }
}
