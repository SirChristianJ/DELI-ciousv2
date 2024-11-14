package com.pluralsight;

import com.pluralsight.Accounts.Account;
import com.pluralsight.Accounts.AccountFileManager;
import com.pluralsight.Customers.Customer;
import com.pluralsight.Customers.CustomerFileManager;

import com.pluralsight.Menus.Display;
import com.pluralsight.Utility.ColorCodes;
import com.pluralsight.Utility.Console;
import com.pluralsight.deli.Chips;
import com.pluralsight.deli.Drinks;
import com.pluralsight.deli.Order;
import com.pluralsight.deli.Sandwich;
import com.pluralsight.ingredients.Bread;
import com.pluralsight.ingredients.Cheese;
import com.pluralsight.ingredients.Meat;
import com.pluralsight.ingredients.RegularTopping;

import java.sql.SQLData;
import java.util.*;

public class UserInterface {

    /**
     * Methods responsible for prompting if the user has an account, if not then they'll create one
     * else if they do, the user will enter the required account information for verification
     * **/
    /**
     * Prompt if the user has an account*/
    public void promptUserForAccount(){
        do{
            String welcomePrompt =
                    """
                    ______________________________________
                   ||                                    ||
                   ||  Welcome to DELI-cious Sandwiches! ||
                   ||____________________________________||
                            
                    """;
            System.out.println(welcomePrompt);
            boolean isCreatingAccount = Console.PromptForYesNo("Do you have an account?");
            if (!isCreatingAccount){
                userSignupScreen();
            }
            else{
                verifyUserScreen();
            }

            return;

        }while(true);
    }
    /**
     * Create an account */
    public void userSignupScreen(){
        try{
            String signupMenu =
                    """
                     ______________________________________
                    ||                                    ||
                    ||        Lets create an account!     ||
                    ||____________________________________||
                    
                    """;
            System.out.println(signupMenu + "\n");
            String userFirstName = Console.PromptForString("Enter first name: ");
            String userLastName = Console.PromptForString("Enter last name: ");
            String userEmail = Console.PromptForString("Enter your email: ");
            String userPhoneNumber = Console.PromptForString("Enter phone number: ");

            Customer customer = new Customer(userFirstName, userLastName, userEmail, userPhoneNumber);
            Account account = new Account(customer);
            AccountFileManager.addAccount(account);
            AccountFileManager.writeToCSV();
            homeScreen(account);
        }
        catch (InputMismatchException e){
            System.out.println(e.getMessage() + "<--- Error creating account");
        }

    }
    /**
     * Verify account information */
    public void verifyUserScreen(){
        String userFirstName = Console.PromptForString("Enter first name: ");
        String userLastName = Console.PromptForString("Enter last name: ");
        String userEmail = Console.PromptForString("Enter your email: ");
        String userPhoneNumber = Console.PromptForString("Enter phone number: ");

        Customer verifyCustomer = new Customer(userFirstName,userLastName,userEmail,userPhoneNumber);
        Account verifyAccount = new Account(verifyCustomer);

        String verifyMenu =
                """
                ______________________________________
               ||                                    ||
               ||  Account Successfully Verified!    ||
               ||____________________________________||
                        
                """;

        for(Account a: AccountFileManager.readFromCSV()){
            if(verifyAccount.getCustomer().encodedString().equalsIgnoreCase(a.getCustomer().encodedString())){
                System.out.println(verifyMenu);
                homeScreen(a);
            }
        }
    }

    /**
     *  First level menu introduces user to application*/
    public void homeScreen(Account a){
        do{
            try {
                String startMenu = """
                         ______________________________________
                        ||                                    ||
                        ||  Welcome to DELI-cious Sandwiches! ||
                        ||____________________________________||
                        
                            1) New Order
                            0) Exit Application
        
                        """;
                System.out.println(ColorCodes.YELLOW);
                System.out.println(startMenu);
                int menuChoice = Console.PromptForInt(String.format("Welcome, %s! \nPlease selection an option: ", a.getCustomer().getFirstName()));
                if (menuChoice == 1) {
                    orderScreen(a);
                } else {
                    System.out.println("Exiting application...");
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage() + "<-- Enter a numeric value.");
            }
        }while (true);
    }
    /**
     *  Second level menu provides user with options pertaining to creating an order*/
    public void orderScreen(Account a){
        List<Sandwich> sandwiches = new ArrayList<>();
        Map<String,String> drinkMap = new HashMap<>();
        List<String> chipList = new ArrayList<>();

        do{
            String orderMenu =
                    """  
                            _______________________________________
                            ||                                   ||
                            ||           Order Screen            ||
                            ||___________________________________||
                   
                            1)Add Sandwich
                            2)Add Drink
                            3)Add Chips
                            4)Checkout
                            0)Cancel Order 
                    """;
            System.out.println(orderMenu);
            int userSelection = Console.PromptForInt("Please selection an option.");

            switch (userSelection) {
                case 1 -> sandwiches = processAddSandwich().stream().toList();
                case 2 -> {
                    String[] drinkParameters = processAddDrink();
                    drinkMap.put(drinkParameters[0],drinkParameters[1]);
                }
                case 3 -> chipList.add(processAddChips());
                case 4 -> processCheckout(a,sandwiches, drinkMap, chipList);
                case 0 -> {
                    System.out.println("Canceling order... ");
                    return;
                }
            }
        }while (true);
    }
    /**
     *  This processAddSandwich method is used to build a sandwich and
     *   add those newly created sandwiches to a list of sandwiches*/
    public List<Sandwich> processAddSandwich(){
        List<Sandwich> sandwichList = new ArrayList<>();
        boolean continueAdding = true;
        do{
            try{ /*Create an empty sandwich*/
                Sandwich sandwich = new Sandwich();
                /*Calls static utility class console to prompt for user input*/
                int sandwichLength = Console.PromptForInt("Sandwich Size: ");
                /*Build the sandwich using helper methods*/
                sandwich.setLength(sandwichLength);
                sandwich.setBread(processBreadInformation());
                processMeatInformation(sandwich);
                processCheeseInformation(sandwich);
                processRegularToppingInformation(sandwich);

                System.out.println("\n" + sandwich);
                boolean isCorrectOrder = Console.PromptForYesNo("Is your sandwich correct ?");
                if (isCorrectOrder) {
                    sandwichList.add(sandwich);
                }

                boolean wantsAnotherSandwich = Console.PromptForYesNo("Would you like to add another sandwich? ");
                if (!wantsAnotherSandwich) {
                    continueAdding = false;
                }
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }

        }while(continueAdding);

        return sandwichList;
    }

    /**
     *  Helper methods to process each aspect of building a sandwich as well as processing
     *  drinks and chips for the later creation of an order*/
    public String processBreadInformation(){
        do{
            try {
                /*Display Bread options*/
                Display.displayBreadOptions();
                /*Calls static utility class console to prompt for user input*/
                String breadType = Console.PromptForString("Enter type of bread: ");
                /*Check if the entered bread exists in the current menu by checking Bread class*/
                Bread bread = new Bread();
                if (bread.isAvailable(breadType)) {
                    return breadType;
                }
                else {
                    System.out.println("This bread is not available, please try again.");
                }
            }
            catch (Exception e) {
                System.out.println(e.getMessage() + "<--Error processing bread information");
            }
        }while (true);
    }
    public List<String> processMeatInformation(Sandwich s){
        Meat meat = new Meat();
        List<String> meatToppings = s.getMeat();
        boolean continueAdding = true;
        do{
            /*Display Meat options*/
            Display.displayMeatOptions();
            /*Calls static utility class console to prompt for user input*/
            String meatType = Console.PromptForString("Enter meat: ");
            try {
                /*Check if the entered meat exists in the current menu by checking Meat class*/
                if (meat.isAvailable(meatType)) {
                    meatToppings.add(meatType);
                    s.setMeat(meatToppings);
                    boolean wantsExtraMeat = Console.PromptForYesNo("Add extra meat? ");
                    if(wantsExtraMeat){
                        continueAdding = true;
                    }
                    else {
                        continueAdding = false;
                    }
                }
                else {
                    System.out.println("This meat is not available.");
                    continueAdding = true;
                }

            }
            catch (Exception e) {
                System.out.println(e.getMessage() + "<-- Error processing meat information");
            }

        }while (continueAdding);

        return meatToppings;
    }
    public List<String> processCheeseInformation(Sandwich s){
        Cheese cheese = new Cheese();
        List<String> cheeseToppings = s.getCheese();
        boolean continueAdding = true;
        do{
            try{
                /*Display Cheese options*/
                Display.displayCheeseOptions();
                /*Calls static utility class console to prompt for user input*/
                String cheeseType = Console.PromptForString("Enter cheese: ");
                /*Check if the entered cheese exists in the current menu by checking Cheese class*/
                if (cheese.isAvailable(cheeseType)) {
                    cheeseToppings.add(cheeseType);
                    s.setCheese(cheeseToppings);
                    boolean wantsExtraCheese = Console.PromptForYesNo("Add extra cheese? ");
                    if(wantsExtraCheese){
                        continueAdding = true;
                    }
                    else {
                        continueAdding = false;
                    }
                }
                else {
                    System.out.println("This cheese is not available.");
                    continueAdding = true;
                }
            }
            catch (Exception e) {
                System.out.println(e.getMessage() + "<-- Error processing cheese information");
            }
        }while (continueAdding);

        return cheeseToppings;
    }
    public List<String> processRegularToppingInformation(Sandwich s){
        RegularTopping topping  = new RegularTopping();
        List<String> regularToppingsList = s.getToppings();
        boolean continueAdding = true;
        do{
            try{
                Display.displayToppingsOptions();
                /*Calls static utility class console to prompt for user input*/
                String toppingType = Console.PromptForString("Enter topping: ");
                /*Check if the entered cheese exists in the current menu by checking Cheese class*/
                if (topping.isAvailable(toppingType)) {
                    regularToppingsList.add(toppingType);
                    s.setToppings(regularToppingsList);
                    boolean wantsExtraTopping = Console.PromptForYesNo("Add more regular toppings? ");
                    if(wantsExtraTopping){
                        continueAdding = true;
                    }
                    else {
                        continueAdding = false;
                    }
                    }
                else {
                    System.out.println("This topping is not available.");
                    continueAdding = true;
                }
            }
            catch (Exception e) {
                System.out.println(e.getMessage() + "<-- Error processing regular topping information");
            }
        }while (continueAdding);

        return regularToppingsList;
    }

    public String[] processAddDrink(){
        Display.displayDrinkOptions();
        String drinkSize = "";
        String drinkType = "";
        boolean isNotAvailable = true;
        do{
            try{
                    drinkSize = Console.PromptForString("Enter size for drink: ");
                    Drinks drinks = new Drinks();
                    drinkType = Console.PromptForString("Enter your drink: ");
                    if (drinks.isAvailable(drinkType)) {
                        isNotAvailable = false;
                    }
            }
            catch (Exception e){
                System.out.println(e.getMessage() + "<--Error processing drink");
            }

        }while (isNotAvailable);

        return new String[]{drinkType,drinkSize};
    }
    public String processAddChips(){
        Display.displayChipOptions();
        String chipType = "";
        boolean continueAdding = true;
        do{
            try{
                chipType = Console.PromptForString("Enter chip: ");
                Chips chips = new Chips();
                if (chips.isAvailable(chipType)){
                    continueAdding = false;
                }
            }
            catch (Exception e){
                System.out.println(e.getMessage() + "Error processing chip");
            }

        }while(continueAdding);

        return chipType;
    }
    /**
     *  Writes order receipt to file*/
    public void processCheckout(Account a,List<Sandwich> s, Map<String,String> d, List<String> c ){
        Order order = new Order(s,d,c);
        List<Order> ordersList = new ArrayList<>();
        /**
         *  Add order to the current logged in Customer
         * */
        ordersList.add(order);
        a.getCustomer().setOrders(ordersList);
        CustomerFileManager.writeToCSV(a);
        System.out.println(order + "\n");
    }

}
