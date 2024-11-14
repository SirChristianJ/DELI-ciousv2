package com.pluralsight.deli;

import com.pluralsight.iterfaces.ICalculable;
import com.pluralsight.iterfaces.IEncoded;

import java.util.*;

public class Order implements ICalculable, IEncoded {
    private List<Sandwich> sandwiches;
    //private Map<String,String> drinkMap = new HashMap<>();
    private List<List<String>> drinksList = new ArrayList<>();
    private List<String> chipsList = new ArrayList<>();

    public Order(List<Sandwich> sandwiches, /*Map<String,String> drinkMap,*/ List<List<String>> drinksList , List<String> chipsList) {
        this.sandwiches = sandwiches;
        //this.drinkMap = drinkMap;
        this.drinksList = drinksList;
        this.chipsList = chipsList;
    }

    public List<Sandwich> getSandwiches() {
        return sandwiches;
    }

    public void setSandwiches(List<Sandwich> sandwiches) {
        this.sandwiches = sandwiches;
    }

    /*public Map<String, String> getDrinkMap() {
        return drinkMap;
    }*/

    public List<List<String>> getDrinksList(){
        return drinksList;
    }

/*
    public void setDrinkMap(Map<String, String> drinkMap) {
        this.drinkMap = drinkMap;
    }
*/

    public List<String> getChipsList() {
        return chipsList;
    }

    public void setChipsList(List<String> chipsList) {
        this.chipsList = chipsList;
    }

    @Override
    public double calculatePrice() {
        double sandwichTotal = getSandwiches().stream().map(Sandwich::calculatePrice).reduce(0.00, Double::sum);
        double drinksTotal = 0;
        double chipTotal = 0;

        if(!getChipsList().isEmpty()){
            chipTotal+= Prices.chipsCost * getChipsList().size();
        }

        try {
            /*for (String size : getDrinkMap().values()) {
                switch (size) {
                    case "small", "Small" -> drinksTotal += Prices.drinksSmallCost;
                    case "medium", "Medium", "med" -> drinksTotal += Prices.drinksMediumCost;
                    case "large", "Large" -> drinksTotal += Prices.drinksLargeCost;
                }
            }*/

            for (List<String> row : getDrinksList()) {
                for (String size : row) {
                    switch (size) {
                        case "small", "Small" -> drinksTotal += Prices.drinksSmallCost;
                        case "medium", "Medium", "med" -> drinksTotal += Prices.drinksMediumCost;
                        case "large", "Large" -> drinksTotal += Prices.drinksLargeCost;
                    }
                }
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }


        return sandwichTotal+drinksTotal+chipTotal;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(getSandwiches());
        /*getDrinkMap().keySet().forEach(s -> sb.append("\nDrink: ").append(s));
        getDrinkMap().values().forEach(s -> sb.append("\nDrink Size: ").append(s));*/

        /*for (List<String> row : getDrinksList()) {
            for (String drinkSize : row) {
                sb.append("\nDrinkSize").append(drinkSize);
            }
        }*/

        getDrinksList().forEach(strings -> sb.append("\nDrink Size: ").append(strings));

        getChipsList().forEach(s -> sb.append("\nChip: ").append(s));


        sb.append(String.format("\nTotal Price: $%.2f", this.calculatePrice()));
        return sb.toString();
    }

    @Override
    public String encodedString() {
        StringBuilder sb = new StringBuilder();
        getSandwiches().forEach(s -> sb.append(s).append("\n"));
        /*getDrinkMap().keySet().forEach(s -> sb.append("\nDrink: ").append(s));
        getDrinkMap().values().forEach(s -> sb.append("\nDrink Size: ").append(s));*/
        getDrinksList().forEach(strings -> sb.append("\nDrink Size: ").append(strings));
        getChipsList().forEach(s -> sb.append("\nChips: ").append(s));


        sb.append(String.format("\nTotal Price: $%.2f", this.calculatePrice()));
        return sb.toString();
    }
}
