import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Program that represents a Personal Assistant Chatbot based on Project Duke.
 *
 * @author Abdulelah Faisal S Al Ghrairy
 */
public class Duke {
    /** contains a list of tasks stored for the user */
    private static List<Task> tasks;
    /** A divider for design purposes*/
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
            listOfTasks.append("Provided are the tasks currently in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                listOfTasks.append(i + 1).append(".").append(tasks.get(i)).append("\n");
            }

            message = listOfTasks.toString();
            break;
        case "mark":
            index = getIndexFromMessage(message); //get the index
            currTask = tasks.get(index);
            currTask.markDone();

            message = "Alright then! I've marked that task as done: " + "\n\t" + currTask.toString();
            break;
        case "unmark":
            index = getIndexFromMessage(message); //get the index
            currTask = tasks.get(index);
            currTask.markUndone();

            message = "Alright then! I've marked that task as not done: " + "\n\t" + currTask.toString();
            break;
        case "todo":
            currTask = new Todo(message.substring(message.indexOf(" ") + 1));
            tasks.add(currTask);
            message = confirmAddition(currTask);
            break;
        case "deadline":
            currTask = new Deadline(retrieveMessage(message), retrieveDateAndTime(message));
            tasks.add(currTask);
            message = confirmAddition(currTask);
            break;
        case "event":
            currTask = new Event(retrieveMessage(message), retrieveDateAndTime(message));
            tasks.add(currTask);
            message = confirmAddition(currTask);
            break;
        default:
            //echoes the message
        }

        return DIVIDER + message + DIVIDER;
    }

    private static int getIndexFromMessage(String message) {
        return Integer.parseInt(message.substring(message.indexOf(" ") + 1)) - 1;
    }
    private static String retrieveMessage(String message) {
        return message.substring(message.indexOf(" ") + 1, message.indexOf("/") - 1);
    }

    private static String retrieveDateAndTime(String time) {
        return time.substring(time.indexOf("/") + 4);
    }

    private static String confirmAddition(Task task) {
        return "Alright then! I've added the task to your list: " + "\n\t" + task.toString() + viewTasksCount();

    }

    private static String viewTasksCount() {
        return "\nYou currently have " + tasks.size() + " task(s) remaining in your list.";
    }
}
