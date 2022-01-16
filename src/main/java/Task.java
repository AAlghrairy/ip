public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String d) {
        this.description = d;
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // x means task is done
    }

    @Override
    public String toString() {
        return "["+ this.getStatusIcon() + "] " + this.description;
    }
}
