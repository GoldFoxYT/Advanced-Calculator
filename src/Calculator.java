import java.util.InputMismatchException;
import java.util.Scanner;

public class Calculator extends performOperation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean continueCalculation = true;

        while (continueCalculation) {
            displayMainMenu();
            int operation = getValidOperation(sc);

            OperationResult result = operate(sc, operation);

            if (result != null && result.getValue() != null) {
                System.out.println("Expression: " + result.getExpression());
                System.out.println("Result: " + result.getFormattedResult()); // Use formatted result
            }

            System.out.println("Perform another calculation? (y/n)");
            if (!sc.next().equalsIgnoreCase("y")) {
                continueCalculation = false;
            }
        }
        sc.close();
        System.out.println("Thank you for using the calculator!");
    }

    private static int getValidOperation(Scanner sc) {
        while (true) {
            try {
                System.out.print("Enter operation number: ");
                int op = sc.nextInt();
                if (op >= 1 && op <= getOperationCount()) return op;
                System.out.println("Invalid operation. Try again.");
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Enter a number.");
                sc.next();
            }
        }
    }
}