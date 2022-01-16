import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static List<Task> tasks;
    public static final String DIVIDER = "\n____________________________________________________________\n";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        tasks = new ArrayList<>();
        String userInput = "";

        System.out.println(DIVIDER + "Why hello there! My name is Wensleydale\nWhat do you need?" + DIVIDER);

        while (!userInput.equals("bye")) {
            userInput = sc.nextLine();
            String message = processMessage(userInput);
            if (message == null) {
                break;
            } else {
                System.out.println(message);
            }
        }

        System.out.println(DIVIDER + "Farewell then!" + DIVIDER);
    }

    private static String processMessage(String message) {
        String currMessage;
        Task currTask;
        int index;

        if (message.toLowerCase().equals("bye")) {
            return null;
        }

        if (message.indexOf(" ") == -1) { //take the first word of the input
            currMessage = message.toLowerCase();
        } else {
            currMessage = message.substring(0, message.indexOf(" ")).toLowerCase();
        }

        switch (currMessage) {
        case "list":
            StringBuilder listOfTasks = new StringBuilder();
            for (int i = 0; i < tasks.size(); i++) {
                listOfTasks.append(i + 1).append(".").append(tasks.get(i)).append("\n");
            }

            message = listOfTasks.toString();
            break;
        case "mark":
            index = Integer.parseInt(message.substring(message.indexOf(" ") + 1)) - 1; //get the index
            currTask = tasks.get(index);
            currTask.markDone();

            message = "Alright then! I've marked that task as done: " + "\n\t" + currTask.toString();
            break;
        case "unmark":
            index = Integer.parseInt(message.substring(message.indexOf(" ") + 1)) - 1; //get the index
            currTask = tasks.get(index);
            currTask.markUndone();

            message = "Alright then! I've marked that task as not done: " + "\n\t" + currTask.toString();
            break;
        default:
            Task newTask = new Task(message);
            tasks.add(newTask);
            message = "added: " + message;
            break;
        }

        return DIVIDER + message + DIVIDER;
    }
}
