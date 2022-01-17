/**
 * Represents a task to be completed. Includes a description of the task and a boolean representing whether it has been
 * completed
 *
 * @author Abdulelah Faisal S Al Ghrairy
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for task class
     * @param d a string representing a description of the task
     */
    public Task(String d) {
        this.description = d;
        this.isDone = false;
    }

    /**
     * Marks the task as "Done"
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as "Undone"
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Retrieves the status of the task
     * @return If marked as "Done", returns "X", otherwise returns " "
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // X means task is done
    }

    /**
     * ToString implementation of the Task class
     * @return String representation of the status of the task along with its description
     */
    @Override
    public String toString() {
        return "["+ getStatusIcon() + "] " + description;
    }
}
