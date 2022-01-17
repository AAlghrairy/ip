/**
 * Represents a task without a time or date and has the same behavior as parent class Task.
 *
 * @author Abdulelah Faisal S Al Ghrairy
 */
public class Todo extends Task {

    /**
     * Constructor for Todo class
     * @param d a string representing a description of the task
     */
    public Todo(String d) {
        super(d);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
