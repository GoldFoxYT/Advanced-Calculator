import java.util.InputMismatchException;
import java.util.Scanner;

public class performOperation extends displayMenu {
    protected static Double performOperation(Scanner sc, int operation) {
        double num1, num2;

        switch (operation) {
            case 5:
                System.out.println("Enter the number for the square root: ");
                num1 = getValidNumber(sc);
                if (num1 < 0) {
                    System.out.println("Cannot calculate the square root of a negative number");
                    return null;
                }
                return Math.sqrt(num1);
            case 6:
                System.out.println("Enter the number for the cube root: ");
                num1 = getValidNumber(sc);
                return Math.cbrt(num1);
            default:
                System.out.println("Enter the first number: ");
                num1 = getValidNumber(sc);
                System.out.println("Enter the second number: ");
                num2 = getValidNumber(sc);

                return switch (operation) {
                    case 1 -> num1 + num2;
                    case 2 -> num1 - num2;
                    case 3 -> num1 * num2;
                    case 4 -> {
                        if (num2 == 0) {
                            System.out.println("Cannot divide by zero");
                            yield null;
                        }
                        yield num1 / num2;
                    }
                    default -> {
                        System.out.println("Invalid operation");
                        yield null;
                    }
                };
        }
    }
    private static double getValidNumber(Scanner sc){
        while (true) {
            try {
                return sc.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.next();
            }
        }
    }
}
