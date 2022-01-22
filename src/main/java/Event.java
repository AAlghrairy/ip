/**
 * Represents a task that starts and ends at a specific date/time.
 * Includes a String representation of the date/time.
 *
 * @author Abdulelah Faisal S Al Ghrairy
 */
public class Event extends Task {

    protected String at;

    /**
     * Constructor for Event class
     * @param d a string representing a description of the task
     * @param a a string representing the start and end date/time
     */
    public Event(String d, String a) {
        super(d);
        this.at = a;
    }

    @Override
    /**
     * Returns the task properties in the format of the task to be saved onto hard disk
     * @return String representing the task toString in hard-disk format
     */
    public String toStringInFileFormat() {
        return "E|" + this.getStatusIcon() + "|" + this.description + "|" + this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
