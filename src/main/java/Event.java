public class Event extends Task {

    protected String at;

    public Event(String d, String a) {
        super(d);
        this.at = a;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
