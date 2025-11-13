package com.example.cocina_service.common;

import java.util.List;
import java.util.Random;

public class Menu {

    public static List<Recipe> recipeList = List.of(
            new Recipe("Pollo con ensalada", List.of(
                new Portion(1,Ingredient.CHICKEN),
                new Portion(1,Ingredient.LEMON),
                new Portion(1,Ingredient.LETTUCE),
                new Portion(2,Ingredient.TOMATO)
            )),
            new Recipe("Carne con puré", List.of(
                    new Portion(1,Ingredient.MEAT),
                    new Portion(2,Ingredient.POTATO)
            )),
            new Recipe("Ensalada", List.of(
                    new Portion(1,Ingredient.ONION),
                    new Portion(2,Ingredient.CHEESE),
                    new Portion(1,Ingredient.LETTUCE),
                    new Portion(2,Ingredient.TOMATO)
            )),
            new Recipe("Arroz con pollo", List.of(
                    new Portion(1,Ingredient.RICE),
                    new Portion(2,Ingredient.CHICKEN),
                    new Portion(1,Ingredient.KETCHUP)
            )),
            new Recipe("Arroz con queso", List.of(
                    new Portion(1,Ingredient.RICE),
                    new Portion(2,Ingredient.CHEESE),
                    new Portion(2,Ingredient.LEMON)
            )),
            new Recipe("Tortilla de papas", List.of(
                    new Portion(3,Ingredient.POTATO),
                    new Portion(1,Ingredient.KETCHUP),
                    new Portion(2,Ingredient.ONION)
            ))
    );

    public static Recipe getRandomMenu() {
        int number = new Random().nextInt(recipeList.size());
        return recipeList.get(number);
    }
}
