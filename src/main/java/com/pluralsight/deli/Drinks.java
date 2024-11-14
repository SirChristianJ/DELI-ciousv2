package com.pluralsight.deli;

import com.pluralsight.iterfaces.ICalculable;
import com.pluralsight.iterfaces.IngredientChecker;

import java.util.ArrayList;
import java.util.List;

public class Drinks implements IngredientChecker {
    private List<String> drinkList;

    public Drinks(){
        this.drinkList = new ArrayList<>();
        this.drinkList.add("Pepsi");
        this.drinkList.add("Coca-Cola");
        this.drinkList.add("Fanta");
        this.drinkList.add("Water");
        this.drinkList.add("Canada Dry");
        this.drinkList.add("null");
    }

    public List<String> getDrinkList() {
        return drinkList;
    }

    @Override
    public boolean isAvailable(String item) {
        return getDrinkList().stream().anyMatch(s -> s.equalsIgnoreCase(item));
    }

}
