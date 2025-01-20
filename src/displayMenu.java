import java.util.Arrays;
import java.util.List;

public class displayMenu {
    private static final List<String> operations = Arrays.asList(
            "addition",
            "subtraction",
            "multiplication",
            "division",
            "sqrt",
            "cbrt"
    );

    protected static void displayMenu() {
        System.out.println("Hello to the calculator");
        System.out.println("Choose the operation: ");
        for (int i = 0; i < operations.size(); i++){
            System.out.println((i + 1) + " - " + operations.get(i));
        }
    }
    protected static int getOperationCount(){
        return operations.size();
    }
}
