import java.util.Arrays;
import java.util.List;

public class displayMenu {
    private static final List<String> operations = Arrays.asList(
            "addition",
            "subtraction",
            "multiplication",
            "division",
            "sqrt",
            "cbrt",
            "exponentiation"
    );

    protected static void displayMainMenu() {
        System.out.println("Hello to the calculator");
        System.out.println("Choose the operation: ");

        StringBuilder ops = new StringBuilder();
        for (int i = 0; i < operations.size(); i++) {
            ops.append((i + 1)).append(" - ").append(operations.get(i));
            if (i < operations.size() - 1) {
                ops.append(" | ");
            }
        }
        System.out.println(ops);
    }
    protected static int getOperationCount(){
        return operations.size();
    }
}
