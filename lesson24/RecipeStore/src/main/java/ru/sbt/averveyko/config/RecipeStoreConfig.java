package ru.sbt.averveyko.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.sbt.averveyko.dao.RecipeDao;

@Configuration
@PropertySource(
        value = {"classpath:db.properties"}
)
public class RecipeStoreConfig {

    @Autowired
    private Environment env;

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RecipeStoreConfig.class);

        RecipeDao recipeDao = context.getBean(RecipeDao.class);
        recipeDao.testJdbc();

    }

    @Bean
    public DriverManagerDataSource dataSource() {
        return new DriverManagerDataSource(
                env.getProperty("jdbc.url"),
                env.getProperty("jdbc.username"),
                env.getProperty("jdbc.password")
        );
    }

    @Bean
    public RecipeDao recipeDao() {
        return new RecipeDao(dataSource());
    }
}
