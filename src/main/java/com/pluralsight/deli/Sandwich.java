package com.pluralsight.deli;

import com.pluralsight.ingredients.Cheese;
import com.pluralsight.ingredients.Meat;
import com.pluralsight.iterfaces.ICalculable;

import java.util.ArrayList;
import java.util.List;

public class Sandwich implements ICalculable {
    private int length;
    private String bread;
    private List <String> meat;
    private List<String> cheese;
    private List<String> toppings;

    public Sandwich(){
        this.length = 0;
        this.bread = "";
        this.meat = new ArrayList<>();
        this.cheese = new ArrayList<>();
        this.toppings = new ArrayList<>();
    }

    public Sandwich(int length,String bread, List<String> meat, List<String> cheese, List<String> toppings) {
        this.length = length;
        this.bread = bread;
        this.meat = meat;
        this.cheese = cheese;
        this.toppings = toppings;
    }

    public int getLength() {
        return length;
    }

    public String getBread() {
        return bread;
    }

    public List<String> getMeat() {
        return meat;
    }

    public List<String> getCheese() {
        return cheese;
    }

    public List<String> getToppings() {
        return toppings;
    }

    public void setLength(int length) {
        if(length==4 || length==8 || length==12) {
            this.length = length;
        }
        else throw new RuntimeException("Size not available.");
    }

    public void setBread(String bread) {
        this.bread = bread;
    }

    public void setMeat(List<String> meat) {
        this.meat = meat;
    }

    public void setCheese(List<String> cheese) {
        this.cheese = cheese;
    }

    public void setToppings(List<String> toppings) {
        this.toppings = toppings;
    }

    @Override
    public double calculatePrice() {
        double priceBasedOnBread = 0.00;
        double priceBasedOnMeat = 0.00;
        double  priceBasedOnCheese = 0.00;

        switch (getLength()){
            case 4 -> {
                priceBasedOnBread = Prices.breadCostAt4inches;
                priceBasedOnMeat = Prices.meatCostAt4inches;
                priceBasedOnCheese = Prices.cheeseCostAt4inches;
                if (getMeat().size() > 1){
                    priceBasedOnMeat += ( Prices.extraMeatCostAt4inches * (getMeat().size()-1) );
                }
                if (getCheese().size() > 1){
                    priceBasedOnCheese += ( Prices.extraCheeseCostAt4inches * (getCheese().size()-1) );
                }
            }
            case 8 -> {
                priceBasedOnBread = Prices.breadCostAt8inches;
                priceBasedOnMeat = Prices.meatCostAt8inches;
                priceBasedOnCheese = Prices.cheeseCostAt8inches;
                if (getMeat().size() > 1){
                    priceBasedOnMeat += ( Prices.extraMeatCostAt8inches * (getMeat().size()-1) ) ;
                }
                if (getCheese().size() > 1){
                    priceBasedOnCheese += ( Prices.extraCheeseCostAt8inches * (getCheese().size()-1) );
                }
            }
            case 12 -> {
                priceBasedOnBread = Prices.breadCostAt12inches;
                priceBasedOnMeat = Prices.meatCostAt12inches;
                priceBasedOnCheese = Prices.cheeseCostAt12inches;
                if (getMeat().size() > 1){
                    priceBasedOnMeat += ( Prices.extraMeatCostAt12inches * (getMeat().size()-1) );
                }
                if (getCheese().size() > 1){
                    priceBasedOnCheese += ( Prices.extraCheeseCostAt12inches * (getCheese().size()-1) );
                }
            }
            default -> throw new RuntimeException("This size is not an option.");
        }

        return priceBasedOnBread + priceBasedOnMeat + priceBasedOnCheese;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Size: %d inches", this.getLength())).append("\nBread: " + this.getBread()).append("\nMeat: " + this.getMeat()).append("\nCheese: " + this.getCheese()).append("\nToppings: " + this.getToppings()).append(String.format("\nPrice: $%.2f", this.calculatePrice()));
        return sb.toString();
    }
}
