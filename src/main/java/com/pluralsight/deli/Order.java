package com.pluralsight.deli;

import com.pluralsight.iterfaces.ICalculable;
import com.pluralsight.iterfaces.IEncoded;

import java.util.*;

public class Order implements ICalculable, IEncoded {
    private List<Sandwich> sandwiches;
    /*private String drinksType;
    private String drinkSize;
    private String chips;*/
    private Map<String,String> drinkMap = new HashMap<>();
    private List<String> chipsList = new ArrayList<>();

/*
    public Order() {
        this.sandwiches = null;
        this.drinksType = "";
        this.drinkSize = "";
        this.chips = "";
        this.drinkMap =  null;
        this.chipsList = null;
    }
*/

    public Order(List<Sandwich> sandwiches, Map<String,String> drinkMap, List<String> chipsList) {
        this.sandwiches = sandwiches;
        /*this.drinksType = drinksType;
        this.drinkSize = drinkSize;
        this.chips = chips;*/
        this.drinkMap = drinkMap;
        this.chipsList = chipsList;
    }

    public List<Sandwich> getSandwiches() {
        return sandwiches;
    }

    public void setSandwiches(List<Sandwich> sandwiches) {
        this.sandwiches = sandwiches;
    }



    /*public String getDrinksType() {
        return drinksType;
    }

    public void setDrinksType(String drinksType) {
        this.drinksType = drinksType;
    }

    public String getDrinkSize() {
        return drinkSize;
    }

    public void setDrinkSize(String drinkSize) {
        this.drinkSize = drinkSize;
    }

    public String getChips() {
        return chips;
    }

    public void setChips(String chips) {
        this.chips = chips;
    }*/

    public Map<String, String> getDrinkMap() {
        return drinkMap;
    }

    public void setDrinkMap(Map<String, String> drinkMap) {
        this.drinkMap = drinkMap;
    }

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
            for(String size: getDrinkMap().values()){
                switch (size) {
                    case "small", "Small" -> drinksTotal += Prices.drinksSmallCost;
                    case "medium", "Medium", "med" -> drinksTotal += Prices.drinksMediumCost;
                    case "large", "Large" -> drinksTotal += Prices.drinksLargeCost;
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
        getDrinkMap().keySet().forEach(s -> sb.append("\nDrink: ").append(s));
        getDrinkMap().values().forEach(s -> sb.append("\nDrink Size: ").append(s));

        getChipsList().forEach(s -> sb.append("\nChip: ").append(s));


        sb.append(String.format("\nTotal Price: $%.2f", this.calculatePrice()));
        return sb.toString();
    }

    @Override
    public String encodedString() {
        StringBuilder sb = new StringBuilder();
        getSandwiches().forEach(s -> sb.append(s).append("\n"));
        getDrinkMap().keySet().forEach(s -> sb.append("\nDrink: ").append(s));
        getDrinkMap().values().forEach(s -> sb.append("\nDrink Size: ").append(s));

        getChipsList().forEach(s -> sb.append("\nChips: ").append(s));


        sb.append(String.format("\nTotal Price: $%.2f", this.calculatePrice()));
        return sb.toString();
    }
}
