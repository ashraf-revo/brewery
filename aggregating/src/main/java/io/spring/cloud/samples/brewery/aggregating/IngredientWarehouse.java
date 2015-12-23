package io.spring.cloud.samples.brewery.aggregating;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import io.spring.cloud.samples.brewery.aggregating.model.Ingredient;
import io.spring.cloud.samples.brewery.aggregating.model.IngredientType;
import io.spring.cloud.samples.brewery.aggregating.model.Ingredients;

@Service
class IngredientWarehouse {

    private static final Map<IngredientType, Integer> DATABASE = new ConcurrentHashMap<>();

    public void addIngredient(Ingredient ingredient) {
        int currentQuantity = DATABASE.getOrDefault(ingredient.getType(), 0);
        DATABASE.put(ingredient.getType(), currentQuantity + ingredient.getQuantity());
    }

    public void useIngredients(Integer amount) {
        DATABASE.forEach((ingredientType, integer) -> DATABASE.put(ingredientType, integer - amount));
    }

    public Ingredients getCurrentState() {
        return new Ingredients(DATABASE
                .entrySet()
                .stream()
                .map((entry) -> new Ingredient(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList()));
    }
}
