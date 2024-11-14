package com.pluralsight.Menus;

public class Display {
    /**
     *  Display menu choices and prices to user
     * */
    public static void displayBreadOptions(){
        String breadMenu = """
                                            4in        8in      12in
                Bread                       $5.50      $7.00    $8.50
                - white
                - wheat
                - rye
                - wrap
                - italian
                - raisin
                
                """;

        System.out.println(breadMenu);

    }
    public static void displayMeatOptions(){
        String meatMenu = """
                                            4in        8in      12in
                Meats                       $1.00      $2.00    $3.00
                - steak                 
                - ham
                - salami
                - roast beef
                - chicken
                - bacon
                
                Extra Meat                  $0.50      $1.00    $1.50
                """;
        System.out.println(meatMenu);
    }
    public static void displayCheeseOptions(){
        String cheeseMenu = """
                                            4in        8in      12in
                Cheese                      $.75       $1.50    $2.25                             
                - american
                - provolone
                - cheddar
                - swiss  
                
                Extra Cheese                $0.30      $0.60    $0.90
                """;
        System.out.println(cheeseMenu);
    }
    public static void displayToppingsOptions(){
        String toppingsMenu =
                """
                Regular Toppings
                - lettuce
                - peppers
                - onions
                - tomatoes
                - jalepenos
                - cucumbers
                - pickles
                - guacamole
                - mushrooms
                """;
        System.out.println(toppingsMenu);
    }
    public static void displayDrinkOptions(){
        String drinksMenu =
                """
               Drinks            Small     Medium     Large
                                 $2.00     $2.50      $3.00
                                 
               
               -Pepsi
               -Coca-Cola
               -Fanta
               -Water
               -Canada Dry
                   
               """;
        System.out.println(drinksMenu);
    }
    public static void displayChipOptions(){
        String chipMenu =
                """
               Chips            Any
                                $1.50
              -Doritos
              -Fritos
              -Takis
              -Lays
               """;
        System.out.println(chipMenu);
    }
}
