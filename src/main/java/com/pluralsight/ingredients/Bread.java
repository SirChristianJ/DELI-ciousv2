package com.pluralsight.ingredients;

import com.pluralsight.iterfaces.IngredientChecker;

import java.util.ArrayList;
import java.util.List;

public class Bread implements IngredientChecker {
    private List<String> breadType;

    public Bread(){
        breadType = new ArrayList<>();
        breadType.add("White");
        breadType.add("Wheat");
        breadType.add("Rye");
        breadType.add("Wrap");
        breadType.add("Italian");
        breadType.add("Raisin");
    }

    public List<String> getBreadType() {
        return breadType;
    }

    @Override
    public boolean isAvailable(String item) {
        do {
            return getBreadType().stream().anyMatch(s -> s.equalsIgnoreCase(item));
        }while (false);
    }
}
