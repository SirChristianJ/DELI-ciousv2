package com.pluralsight.ingredients;

import com.pluralsight.iterfaces.IngredientChecker;

import java.util.ArrayList;
import java.util.List;

public class RegularTopping implements IngredientChecker {
    private List<String> toppingTypes;

    public RegularTopping(){
        toppingTypes = new ArrayList<>();
        toppingTypes.add("lettuce");
        toppingTypes.add("peppers");
        toppingTypes.add("onions");
        toppingTypes.add("tomatoes");
        toppingTypes.add("jalepenos");
        toppingTypes.add("cucumbers");
        toppingTypes.add("pickles");
        toppingTypes.add("guacamole");
        toppingTypes.add("mushrooms");
    }

    public List<String> getToppingTypes(){
        return toppingTypes;
    }

    @Override
    public boolean isAvailable(String item) {
        return getToppingTypes().stream().anyMatch(s -> s.equalsIgnoreCase(item));
    }
}
