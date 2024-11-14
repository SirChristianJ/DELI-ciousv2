package com.pluralsight.deli;

import com.pluralsight.iterfaces.IngredientChecker;

import java.util.ArrayList;
import java.util.List;

public class Chips implements IngredientChecker {
    private List<String> chips;

    public Chips(){
        this.chips = new ArrayList<>();
        this.chips.add("Doritos");
        this.chips.add("Fritos");
        this.chips.add("Takis");
        this.chips.add("Lays");
    }

    public List<String> getChips() {
        return chips;
    }

    @Override
    public boolean isAvailable(String item) {
        return getChips().stream().anyMatch(s -> s.equalsIgnoreCase(item));
    }
}
