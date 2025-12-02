import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
public class taskList {
    private List<Task> tasks = new ArrayList<>(); 

    public taskList() {}

    public void addTask(Task task) {
        if(tasks.size() == 0) {
        tasks.add(task);
        } else {
            for(int i = 0; i < tasks.size(); i++) {
                if(task.moreUrgentThan(tasks.get(i))) {
                    tasks.add(i, task);
                    return;
                }
            }
            tasks.add(task); 
        }
    }
    public void removeTask(int index) {
        if(index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        }
    }

    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    public void printTasks() {
        for(Task task : tasks) {
            System.out.println("Task Name: " + task.getDescription() + " Subject: " + task.getSubject().getName() + ", Due Date: " + task.getDueDate() + ", Time Required: " + task.getTimeRequired() + ", Completed: " + task.isCompleted());
        }
    }

    public void printTillThisDay(LocalDate date) {
        for(Task task : tasks) {
            if(!task.getDueDate().isAfter(date)) {
                System.out.println("Task Name: " + task.getDescription() + " Subject: " + task.getSubject().getName() + ", Due Date: " + task.getDueDate() + ", Time Required: " + task.getTimeRequired() + ", Completed: " + task.isCompleted());
            }
        }
    }

    public void printSubjectTasks(String subjectName) {
        for(Task task : tasks) {
            if(task.getSubject().getName().equals(subjectName)) {
                System.out.println("Task Name: " + task.getDescription() + " Subject: " + task.getSubject().getName() + ", Due Date: " + task.getDueDate() + ", Time Required: " + task.getTimeRequired() + ", Completed: " + task.isCompleted());
            }
        }
    }

    public void printIncompleteTasks() {
        for(Task task : tasks) {
            if(!task.isCompleted()) {
                System.out.println("Description: " + task.getDescription() + " Subject: " + task.getSubject().getName() + ", Due Date: " + task.getDueDate() + ", Time Required: " + task.getTimeRequired() + ", Completed: " + task.isCompleted());
            }
        }
    }

    public void printSubjectSummary(String subjectName) {
        double totalTime = 0;
        double numTasks = 0; 
        for(Task task : tasks) {
            if(task.getSubject().getName().equals(subjectName)) {
                totalTime += task.getTimeRequired();
                numTasks++;
            }
        }
        System.out.println("Total time required for subject " + subjectName + ": " + totalTime + " hours for " + numTasks + " tasks.");
    }


    public void saveToFile(String filename) {
        try (PrintWriter out = new PrintWriter(new FileWriter(filename))) {
            for (Task task : tasks) {
                String subjectName = task.getSubject().getName();
                String taskName = task.getDescription(); // or getName() if you have that
                String dueDateStr = task.getDueDate().toString(); // ISO: YYYY-MM-DD
                double timeRequired = task.getTimeRequired();

                String line = subjectName + "|" + taskName + "|" + dueDateStr + "|" + timeRequired;
                out.println(line);
            }
            System.out.println("Tasks saved to file: " + filename);
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

        public void loadFromFile(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            // No existing file, nothing to load the first time
            System.out.println("No saved file found (" + filename + "). Starting with an empty list.");
            return;
        }

        ArrayList<Task> loadedTasks = new ArrayList<>();

        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split("\\|");
                if (parts.length != 4) {
                    System.out.println("Skipping invalid line: " + line);
                    continue;
                }

                String subjectName = parts[0];
                String taskName = parts[1];
                String dueDateStr = parts[2];
                String timeRequiredStr = parts[3];

                try {
                    LocalDate dueDate = LocalDate.parse(dueDateStr);
                    double timeRequired = Double.parseDouble(timeRequiredStr);

                    Subject subject = new Subject(subjectName);
                    Task task = new Task(subject, dueDate, timeRequired, taskName);
                    loadedTasks.add(task);
                } catch (Exception parseEx) {
                    System.out.println("Skipping line (parse error): " + line);
                }
            }

            // Only replace if everything went okay
            this.tasks = loadedTasks;
            System.out.println("Tasks loaded from file: " + filename);

        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
}
}
