import java.util.Scanner;

public class Duke {
    public static final String DESIGN_BAR = "\n____________________________________________________________\n";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String userInput = "";

        System.out.println(DESIGN_BAR + "Why hello there! My name is Wensleydale\nWhat do you need?" + DESIGN_BAR);

        userInput = sc.nextLine();
        while (!userInput.equals("bye")) {
            System.out.println(processMessage(userInput));
            userInput = sc.nextLine();
        }

        System.out.println(DESIGN_BAR + "Farewell then!" + DESIGN_BAR);
    }

    private static String processMessage (String message) {
        return DESIGN_BAR + message + DESIGN_BAR;
    }
}
