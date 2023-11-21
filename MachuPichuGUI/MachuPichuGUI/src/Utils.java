import java.util.Arrays;
import java.util.Scanner;

public class Utils {
    public static Integer checkValidInt(String numStr) {
        Integer num = null;
        try {
            num =  Integer.parseInt(numStr);
        } catch (NumberFormatException ex) {
            System.out.println(numStr +  " is not a valid integer");
        }
        return num;
    }
    public static String arrayStr(Integer[] array) {
        return Arrays.toString(array).replace("[", "").replace("]", "");
    }
    public static boolean getChoice(String message) {
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine().toUpperCase();
        if (choice.equals("Y")) {
            return true;
        } else {
            return false;
        }
    }

    public static void anyKey() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press enter to continue");
        scanner.nextLine();
    }
}
