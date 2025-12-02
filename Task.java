import java.time.LocalDate;
public class Task {
    private Subject subject;
    private double timeRequired; 
    private String description;
    private LocalDate dueDate;
    private boolean completed;

    public Task(Subject subject, LocalDate dueDate, double timeRequired, String description) {
        this.subject = subject;
        this.dueDate = dueDate;
        this.timeRequired = timeRequired;
        this.description = description;
        this.completed = false;
    }

    public Subject getSubject() {
        return new Subject(this.subject.getName());
    }

    public void setSubject(Subject subject) {
        this.subject = new Subject(subject.getName());
    }

    public double getTimeRequired() {
        return timeRequired;
    }

    public void setTimeRequired(double timeRequired) {
        this.timeRequired = timeRequired;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public boolean moreUrgentThan(Task other) {
        return this.dueDate.isBefore(other.dueDate);
    }
}
