package com.pluralsight.ingredients;

import com.pluralsight.iterfaces.IngredientChecker;

import java.util.ArrayList;
import java.util.List;

public class Cheese implements IngredientChecker {
    private List<String> cheeseTypes;

    public Cheese(){
        cheeseTypes = new ArrayList<>();
        cheeseTypes.add("American");
        cheeseTypes.add("Provolone");
        cheeseTypes.add("Cheddar");
        cheeseTypes.add("Swiss");
        cheeseTypes.add("Mozzarella");
    }

    public List<String> getCheeseTypes(){
        return cheeseTypes;
    }

    @Override
    public boolean isAvailable(String item) {
        return getCheeseTypes().stream().anyMatch(s -> s.equalsIgnoreCase(item));
    }
}
