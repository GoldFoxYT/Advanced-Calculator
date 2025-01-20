import java.util.InputMismatchException;
import java.util.Scanner;

public class Calculator extends performOperation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean continueCalculation = true;

        while (continueCalculation) {
            displayMainMenu();
            int operation;

            while (true){
                try{
                    System.out.println("Enter operation number: ");
                    operation = sc.nextInt();
                    if (operation < 1 || operation > getOperationCount()) {
                        System.out.println("Invalid operation. Please enter a valid number.");
                        continue;
                    }
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    sc.next();
                }
            }

            Double result = operate(sc, operation);

            if (result != null) {
                System.out.println("Result: " + result);
            }
            System.out.println("Do you want to perform another calculation? (y/n)");
            String userResponse = sc.next();
            if (!userResponse.equalsIgnoreCase("y")) {
                continueCalculation = false;
            }
        }
        sc.close();
        System.out.println("Thank you for using the calculator. Goodbye!");
    }
}