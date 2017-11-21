package ru.sbt.averveyko.HibernateRecipeStore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class HibernateRecipeStoreApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(HibernateRecipeStoreApplication.class, args);

		App app = context.getBean(App.class);
		//app.test();
	}
}
