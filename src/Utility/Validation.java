package utility;

import java.util.Scanner;

/**
 *
 * @author Yap Ying Sin
 */
public class Validation {
    
    static Scanner scan = new Scanner(System.in);
    
    public static int validateIntegerInput(String userInput) {
        int num = 0;
        boolean isValid = false;
        
        while (isValid == false) {
            try {
                num = Integer.parseInt(userInput);
                isValid = true;
            }
            catch (NumberFormatException e) {
                System.out.print("Input must be an integer. Please re-enter: ");
                userInput = scan.nextLine();
            }
        }
        
        return num;
    }
    
}
