package ru.sbt.averveyko.ui.console;

import ru.sbt.averveyko.dao.*;
import ru.sbt.averveyko.model.*;

import java.util.ArrayList;
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
        Recipe recipe = new Recipe();

        System.out.print("Введите название рецепта: ");
        String recipeName = scanner.nextLine();
        recipe.setName(recipeName);

        System.out.print("Введите описание рецепта: ");
        String recipeDescription = scanner.nextLine();
        recipe.setDescription(recipeDescription);

        Composition composition = new Composition();
        composition.setRecipeRef(recipe);

        List<CompositionEntry> compositionEntryList = new ArrayList<>();
        composition.setEntryList(compositionEntryList);

        while (true) {

            System.out.println("\nВвод состава рецепта");
            showIngredients();
            System.out.print("Введите id требуемого ингредиента, n для ввода нового, q для завершения редактирования: ");
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("q")) break;

            Ingredient ingredient;
            if (userInput.equalsIgnoreCase("n")){
                ingredient = createNewIngredient();
            } else {
                int ingredientId = Integer.parseInt(userInput);
                ingredient = ingredientDao.getByPK(ingredientId);
            }

            System.out.println("Введите еденицу измерения ингредиента " + ingredient.getName() + " :");
            showUnits();
            System.out.print("Введите id требуемой ед. изм., n для ввода новой, q для завершения редактирования: ");
            userInput = scanner.nextLine();

            Unit unit;
            if (userInput.equalsIgnoreCase("q")) break;
            if (userInput.equalsIgnoreCase("n")){
                unit = createNewUnit();
            } else {
                int unitId = Integer.parseInt(userInput);
                unit = unitDao.getByPK(unitId);
            }

            System.out.println("Введите требуемое количество ингредиента (в " + unit.getName() + "): " );
            Double amount = Double.valueOf(scanner.nextLine());

            compositionEntryList.add(new CompositionEntry(ingredient, amount, unit));

            System.out.println("Ингредиент добавлен (" + ingredient.getName() + " " + amount + " " + unit.getName() + ")");
        }

        compositionDao.insert(composition);
        System.out.println("Новый рецепт успешно сохранен.");
    }

    private Unit createNewUnit() {
        Unit unit = new Unit();
        System.out.print("\nВведите наименование новой ед. изм.: ");
        String name = scanner.nextLine();
        unit.setName(name);
        return unit;
    }

    private Ingredient createNewIngredient() {
        Ingredient ingredient = new Ingredient();
        System.out.print("\nВведите наименование нового ингредиента: ");
        String name = scanner.nextLine();
        ingredient.setName(name);
        return ingredient;
    }

    private void showUnits() {
        List<Unit> unitList = unitDao.getAll();
        for (Unit unit : unitList) {
            System.out.println(unit.getId() + ". " + unit.getName());
        }
    }

    private void showIngredients() {
        List<Ingredient> ingredientList = ingredientDao.getAll();
        for (Ingredient ingredient : ingredientList) {
            System.out.println(ingredient.getId() + ". " + ingredient.getName());
        }
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
