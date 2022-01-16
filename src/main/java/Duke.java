import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static List<String> tasks;
    public static final String DESIGN_BAR = "\n____________________________________________________________\n";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        tasks = new ArrayList<>();
        String userInput = "";

        System.out.println(DESIGN_BAR + "Why hello there! My name is Wensleydale\nWhat do you need?" + DESIGN_BAR);

        while (!userInput.equals("bye")) {
            userInput = sc.nextLine();
            String message = processMessage(userInput);
            if (message == null) {
                break;
            } else {
                System.out.println(message);
            }
        }

        System.out.println(DESIGN_BAR + "Farewell then!" + DESIGN_BAR);
    }

    private static String processMessage(String message) {
        String messageInLowerCase = message.toLowerCase();
        if (messageInLowerCase.equals("bye")) {
            return null;
        } else if (messageInLowerCase.equals("list")) {
            StringBuilder listOfTasks = new StringBuilder();
            for (int i = 0; i < tasks.size(); i++) {
                listOfTasks.append(i + 1).append(".").append(tasks.get(i)).append("\n");
            }

            message = listOfTasks.toString();
        } else {
            tasks.add(message);
            message = "added: " + message;
        }

        return DESIGN_BAR + message + DESIGN_BAR;
    }
}
