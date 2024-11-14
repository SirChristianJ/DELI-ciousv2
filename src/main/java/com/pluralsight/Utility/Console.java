package com.pluralsight.Utility;

import java.util.Scanner;

public class Console {
    static Scanner scanner = new Scanner(System.in);

    public static int PromptForInt(String prompt){
        System.out.println(ColorCodes.GREEN);
        System.out.println(prompt);

        return Integer.parseInt(scanner.nextLine());
    }
    public static String PromptForString(String prompt){
        System.out.println(ColorCodes.CYAN);
        System.out.println(prompt);
        return scanner.nextLine();
    }
    public static boolean PromptForYesNo(String prompt){
        System.out.println(ColorCodes.RED);
        System.out.print(prompt + " ( Y for Yes, N for No ) ?");
        String userinput = scanner.nextLine();

        return
                (
                        userinput.equalsIgnoreCase("Y")
                                ||
                                userinput.equalsIgnoreCase("YES")
                );

    }
}
