package com.example.cocina_service.common;

import org.example.common.IngredientName;
import org.example.common.Portion;
import org.example.common.Recipe;

import java.util.List;
import java.util.Random;

public class Menu {

    public static List<Recipe> recipeList = List.of(
            new Recipe("Pollo con ensalada", List.of(
                new Portion(1, IngredientName.CHICKEN),
                new Portion(1,IngredientName.LEMON),
                new Portion(1,IngredientName.LETTUCE),
                new Portion(2,IngredientName.TOMATO)
            )),
            new Recipe("Carne con puré", List.of(
                    new Portion(1,IngredientName.MEAT),
                    new Portion(2,IngredientName.POTATO)
            )),
            new Recipe("Ensalada", List.of(
                    new Portion(1,IngredientName.ONION),
                    new Portion(2,IngredientName.CHEESE),
                    new Portion(1,IngredientName.LETTUCE),
                    new Portion(2,IngredientName.TOMATO)
            )),
            new Recipe("Arroz con pollo", List.of(
                    new Portion(1,IngredientName.RICE),
                    new Portion(2,IngredientName.CHICKEN),
                    new Portion(1,IngredientName.KETCHUP)
            )),
            new Recipe("Arroz con queso", List.of(
                    new Portion(1,IngredientName.RICE),
                    new Portion(2,IngredientName.CHEESE),
                    new Portion(2,IngredientName.LEMON)
            )),
            new Recipe("Tortilla de papas", List.of(
                    new Portion(3,IngredientName.POTATO),
                    new Portion(1,IngredientName.KETCHUP),
                    new Portion(2,IngredientName.ONION)
            ))
    );

    public static Recipe getRandomMenu() {
        int number = new Random().nextInt(recipeList.size());
        return recipeList.get(number);
    }
}
