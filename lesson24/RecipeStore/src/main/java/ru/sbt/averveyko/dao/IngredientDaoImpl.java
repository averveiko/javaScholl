package ru.sbt.averveyko.dao;


import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.sbt.averveyko.dao.utils.IngredientMapper;
import ru.sbt.averveyko.model.Ingredient;

import javax.sql.DataSource;


public class IngredientDaoImpl implements IngredientDao {
    private static final String QUERY_INSERT =
            "INSERT INTO Ingredient(name)\n" +
                    "VALUES (:name);";

    private static final String QUERY_GET_BY_PK =
            "SELECT id, name\n" +
                    "FROM Ingredient\n" +
                    "WHERE id=:id;";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final IngredientMapper ingredientMapper;

    public IngredientDaoImpl(DataSource dataSource, IngredientMapper ingredientMapper) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.ingredientMapper = ingredientMapper;
    }

    @Override
    public void insert(Ingredient ingredient) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(ingredient);
        KeyHolder keyHolder = new GeneratedKeyHolder();

        int result = namedParameterJdbcTemplate.update(QUERY_INSERT, param, keyHolder);

        System.out.println("insert " + result + " ingredient: " + ingredient.getName());
    }

    @Override
    public Ingredient getByPK(Integer primaryKey) {
        SqlParameterSource param = new MapSqlParameterSource("id", primaryKey);

        Ingredient ingredient = namedParameterJdbcTemplate.queryForObject(QUERY_GET_BY_PK, param, ingredientMapper);
        return ingredient;
    }

    public void testJdbc() {
        System.out.println("test");
    }
}
