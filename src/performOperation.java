import java.util.Scanner;
import java.util.function.DoubleUnaryOperator;

public class performOperation extends displayMenu {

    static class NumberExpression {
        private final double value;
        private final String expression;

        NumberExpression(double value, String expression) {
            this.value = value;
            this.expression = expression;
        }

        public double getValue() {
            return value;
        }

        public String getExpression() {
            return expression;
        }
    }

    static class OperationResult {
        private final Double value;
        private final String expression;
        private final String formattedResult; // Add this field

        OperationResult(Double value, String expression) {
            this.value = value;
            this.expression = expression;
            this.formattedResult = (value != null) ? formatResult(value) : "Error"; // Format here
        }

        // Add a getter for the formatted result
        public String getFormattedResult() {
            return formattedResult;
        }

        public Double getValue() {
            return value;
        }

        public String getExpression() {
            return expression;
        }
    }

    protected static OperationResult operate(Scanner sc, int operation) {
        return switch (operation) {
            case 5 -> handleUnaryOp(sc, "√", Math::sqrt);
            case 6 -> handleUnaryOp(sc, "∛", Math::cbrt);
            case 7 -> handleExponentiation(sc);
            default -> handleBinaryOp(sc, operation);
        };
    }

    private static OperationResult handleUnaryOp(Scanner sc, String symbol, DoubleUnaryOperator op) {
        System.out.println("Enter the number: ");
        NumberExpression input = getValidNumber(sc);
        double result = op.applyAsDouble(input.getValue());
        String expr = symbol + "(" + input.getExpression() + ")";
        return new OperationResult(result, expr);
    }

    private static OperationResult handleExponentiation(Scanner sc) {
        System.out.println("Enter the base (e.g., 2 or sqrt(5)): ");
        NumberExpression base = getValidNumber(sc);
        System.out.println("Enter the exponent: ");
        NumberExpression exponent = getValidNumber(sc);
        double result = Math.pow(base.getValue(), exponent.getValue());
        String expr = base.getExpression() + "^" + exponent.getExpression();
        return new OperationResult(result, expr);
    }

    private static OperationResult handleBinaryOp(Scanner sc, int operation) {
        System.out.println("Enter the first number (e.g., 5 or sqrt(5)): ");
        NumberExpression num1 = getValidNumber(sc);
        System.out.println("Enter the second number: ");
        NumberExpression num2 = getValidNumber(sc);

        String opSymbol;
        double resultVal;

        switch (operation) {
            case 1:
                resultVal = num1.getValue() + num2.getValue();
                opSymbol = " + ";
                break;
            case 2:
                resultVal = num1.getValue() - num2.getValue();
                opSymbol = " - ";
                break;
            case 3:
                resultVal = num1.getValue() * num2.getValue();
                opSymbol = " * ";
                break;
            case 4:
                if (num2.getValue() == 0) {
                    System.out.println("Cannot divide by zero.");
                    return new OperationResult(null, null);
                }
                resultVal = num1.getValue() / num2.getValue();
                opSymbol = " / ";
                break;
            default:
                System.out.println("Invalid operation.");
                return new OperationResult(null, null);
        }

        String expr = num1.getExpression() + opSymbol + num2.getExpression();
        return new OperationResult(resultVal, expr);
    }

    private static NumberExpression getValidNumber(Scanner sc) {
        while (true) {
            String input = sc.next().trim();
            try {
                if (input.startsWith("sqrt(") && input.endsWith(")")) {
                    String numStr = input.substring(5, input.length() - 1);
                    double num = Double.parseDouble(numStr);
                    if (num < 0) {
                        System.out.println("Cannot calculate sqrt of a negative number. Try again:");
                        continue;
                    }
                    double sqrtResult = Math.sqrt(num); // Compute sqrt here
                    return new NumberExpression(sqrtResult, "sqrt(" + numStr + ")");
                } else if (input.startsWith("cbrt(") && input.endsWith(")")) {
                    String numStr = input.substring(5, input.length() - 1);
                    double num = Double.parseDouble(numStr);
                    double cbrtResult = Math.cbrt(num); // Compute cbrt here
                    return new NumberExpression(cbrtResult, "cbrt(" + numStr + ")");
                } else {
                    double num = Double.parseDouble(input);
                    return new NumberExpression(num, input);
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input. Enter a number or expression (e.g., sqrt(5)):");
            }
        }
    }
    private static String formatResult(double value) {
        // Check if the value is very close to an integer
        if (Math.abs(value - Math.round(value)) < 1e-10) {
            return String.format("%.0f", value); // Display as integer (e.g., 5)
        } else {
            // Trim trailing zeros and avoid scientific notation
            return String.format("%s", value).replaceAll("(\\.\\d*?[1-9])0+$", "$1").replaceAll("\\.$", "");
        }
    }
}