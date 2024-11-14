package com.pluralsight.ingredients;

import com.pluralsight.iterfaces.IngredientChecker;

import java.util.ArrayList;
import java.util.List;

public class Meat implements IngredientChecker {
    private List<String> meatTypes;

    public Meat(){
        this.meatTypes = new ArrayList<>();
        this.meatTypes.add("Steak");
        this.meatTypes.add("Ham");
        this.meatTypes.add("Salami");
        this.meatTypes.add("Roast beef");
        this.meatTypes.add("Chicken");
        this.meatTypes.add("Bacon");
    }

    public List<String> getMeatTypes() {
        return this.meatTypes;
    }

    @Override
    public boolean isAvailable(String item) {
        return getMeatTypes().stream().anyMatch(s -> s.equalsIgnoreCase(item));
    }
}
