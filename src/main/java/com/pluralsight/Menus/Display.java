package com.pluralsight.Menus;

import com.pluralsight.Utility.ColorCodes;

public class Display {
    /**
     *  Display menu choices and prices to user
     * */
    public static void displayBreadOptions(){
        String breadMenu =
        """
                _____________________________________________________
                |                 |   4in     |   8in    |  12in    |
                |     Bread       |   $5.50   |   $7.00  |  $8.50   |
                |     - white     |           |          |          |
                |     - wheat     |           |          |          |
                |     - rye       |           |          |          |
                |     - wrap      |           |          |          |
                |     - italian   |           |          |          |
                |     - raisin    |           |          |          |
                |_________________|___________|__________|__________|
        """;
        System.out.println(ColorCodes.WHITE_BACKGROUND);
        System.out.println(ColorCodes.BLACK);
        System.out.println(breadMenu);

    }

    public static void displaySizeOptions(){
        String sizeMenu =
         """
                _______________________________
                |    Bread Sizes |   Price    |
                |     4  in      |    $5.50   |
                |     8  in      |    $7.00   |
                |     12 in      |    $8.50   |
                |________________|____________|
         """;
        System.out.println(ColorCodes.WHITE_BACKGROUND);
        System.out.println(ColorCodes.BLACK);
        System.out.println(sizeMenu);
    }
    public static void displayMeatOptions(){
        String meatMenu =
        """
                _________________________________________________________
                |                      |   4in     |   8in    |  12in   |
                |     Meats            |   $1.00   |   $2.00  |  $3.00  |
                |     - steak          |           |          |         |
                |     - ham            |           |          |         |
                |     - salami         |           |          |         |
                |     - roast beef     |           |          |         |
                |     - chicken        |           |          |         |
                |     - bacon          |           |          |         |
                |______________________|___________|__________|_________|
                |     Extra Meat       |   $0.50   |   $1.00  |  $1.50  |
                |______________________|___________|__________|_________|
        """;
        System.out.println(ColorCodes.WHITE_BACKGROUND);
        System.out.println(ColorCodes.BLACK);
        System.out.println(meatMenu);
    }
    public static void displayCheeseOptions(){
        String cheeseMenu =
        """
                ____________________________________________________________
                |                     |    4in     |   8in     |   12in    |
                |     Cheese          |    $0.75   |   $1.50   |   $2.25   |
                |     - american      |            |           |           |
                |     - provolone     |            |           |           |
                |     - cheddar       |            |           |           |
                |     - swiss         |            |           |           |
                |_____________________|____________|___________|___________|
                |     Extra Cheese    |    $0.30   |   $0.60   |   $0.90   |
                |_____________________|____________|___________|___________|
        """;
        System.out.println(ColorCodes.WHITE_BACKGROUND);
        System.out.println(ColorCodes.BLACK);
        System.out.println(cheeseMenu);
    }
    public static void displayToppingsOptions(){
        String toppingsMenu =
        """
                         Regular Toppings
                _________________________________
                |  - jalepenos  |  - cucumbers  |
                |  - pickles    |  - guacamole  |
                |  - mushrooms  |  - lettuce    |
                |  - peppers    |  - onions     |
                |  - tomatoes   |               |
                |_______________|_______________|
       """;
        System.out.println(ColorCodes.WHITE_BACKGROUND);
        System.out.println(ColorCodes.BLACK);
        System.out.println(toppingsMenu);
    }
    public static void displayDrinkOptions(){
        String drinksMenu =
       """
                ____________________________________________________
                | Drinks        |   Small  |   Medium  |   Large   |
                |               |   $2.00  |   $2.50   |   $3.00   |
                | -Pepsi        |          |           |           |
                | -Coca-Cola    |          |           |           |
                | -Fanta        |          |           |           |
                | -Water        |          |           |           |
                | -Canada Dry   |          |           |           |
                |_______________|__________|___________|___________|
       """;
        System.out.println(ColorCodes.WHITE_BACKGROUND);
        System.out.println(ColorCodes.BLACK);
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
        System.out.println(ColorCodes.WHITE_BACKGROUND);
        System.out.println(ColorCodes.BLACK);
        System.out.println(chipMenu);
    }
}
