package ru.sbt.averveyko.ui.console;

import ru.sbt.averveyko.dao.CompositionDao;
import ru.sbt.averveyko.dao.IngredientDao;
import ru.sbt.averveyko.dao.RecipeDao;
import ru.sbt.averveyko.dao.UnitDao;
import ru.sbt.averveyko.model.Composition;

import java.util.List;
import java.util.Scanner;

public class ConsoleUI {
    private final CompositionDao compositionDao;
    private final IngredientDao ingredientDao;
    private final RecipeDao recipeDao;
    private final UnitDao unitDao;

    private Scanner scanner = new Scanner(System.in);

    private static final String MAIN_MENU =
            "# База рецептов #\n" +
                    "1. Поиск рецепта по имени (или части имени);\n" +
                    "2. Добавление рецепта;\n" +
                    "3. Удаление рецепта;\n" +
                    "4. Выход из программы.\n" +
                    "\nВведите номер операции: ";

    public ConsoleUI(CompositionDao compositionDao, IngredientDao ingredientDao, RecipeDao recipeDao, UnitDao unitDao) {
        this.compositionDao = compositionDao;
        this.ingredientDao = ingredientDao;
        this.recipeDao = recipeDao;
        this.unitDao = unitDao;
    }

    public void start() {

        while (true) {
            System.out.print(MAIN_MENU);
            int userInput = Integer.valueOf(scanner.nextLine());

            switch (userInput) {
                case 1:
                    searchRecipe();
                    break;
                case 2:
                    addRecipe();
                    break;
                case 3:
                    deleteRecipe();
                    break;
                case 4:
                    System.out.println("Всего доброго!");
                    return;
                default:
                    System.out.println("Введите цифру 1-4");
            }
        }
    }

    private void deleteRecipe() {

    }

    private void addRecipe() {

    }

    private void searchRecipe() {
        System.out.print("Введите название рецепта (или его часть): ");
        String searchName = scanner.nextLine();
        System.out.println("Поиск рецептов, название которых содержит: " + searchName);

        List<Composition> compositionList = compositionDao.searchByName(searchName);
        System.out.println("Найдено " + compositionList.size() + " рецепт(ов)");
        printCompositionList(compositionList);
    }

    private void printCompositionList(List<Composition> compositionList) {
        for (Composition composition : compositionList) {
            System.out.println(composition);
        }

        System.out.printf("\n");
    }
}
