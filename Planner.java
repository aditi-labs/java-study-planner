import java.util.*; 
import java.time.LocalDate;
public class Planner {
    public static void main(String[] args) {
        taskList myList = new taskList();

        // Starting Console....
        System.out.println("Welcome to the Task Planner!");
        myList.loadFromFile("tasks.txt");
        Scanner input = new Scanner(System.in); 
        boolean running = true; 

        try {

        while( running) {
            System.out.println("How can I help you today?"); 
            System.out.println("1. Add Task\n2. View Tasks\n3. Remove Task\n4. Print Tasks Till Date\n5. Print All Tasks Within a Subject\n6. Print Incomplete Tasks\n7. Edit Task\n8. Mark Task as Completed(Does not remove task)\n9. Exit and Save!");
            int choice = input.nextInt(); 
            switch(choice) {
                case 1:
                    System.out.println("Enter Subject Name:");
                    String subjectName = input.next();
                    Subject subject = new Subject(subjectName);

                    System.out.println("Enter Task Name:");
                    String name = input.next();

                    System.out.println("Enter Due Date (YYYY-MM-DD):");
                    String dueDateStr = input.next();

                    LocalDate dueDate = LocalDate.parse(dueDateStr);

                    System.out.println("Enter Time Required (in hours):");
                    double timeRequired = input.nextDouble();

                    
                    Task newTask = new Task(subject, dueDate, timeRequired, name);
                    myList.addTask(newTask);
                    System.out.println("Task Added Successfully!");
                    break;
                case 2:
                    myList.printTasks();
                    break;
                case 3:
                    System.out.println("Enter Task Name to Remove:");
                    String taskName = input.next();
                    for(Task task : myList.getTasks()) {
                        if(task.getDescription().equals(taskName)) {
                            myList.removeTask(myList.getTasks().indexOf(task));
                            System.out.println("Task Removed Successfully!");
                            break;
                        }

                    }
                    System.out.println("Task Not Found. Task not removed.");
                    break;
                case 4:
                    System.out.println("Enter Date (YYYY-MM-DD):");
                    String dateStr = input.next();
                    LocalDate date = LocalDate.parse(dateStr);
                    myList.printTillThisDay(date);
                    break;
                case 5:
                    System.out.println("Enter Subject Name:");
                    String subName = input.next();
                    myList.printSubjectTasks(subName);
                    break;
                case 6:
                    myList.printIncompleteTasks();
                    break;
                case 7:
                    System.out.println("Enter Task Name to Edit:");
                    String editTaskName = input.next();
                    for(Task task : myList.getTasks()) {
                        if(task.getDescription().equals(editTaskName)) {
                            System.out.println("Enter New Task Name:");
                            String newName = input.next();
                            task.setDescription(newName);

                            System.out.println("Enter New Due Date (YYYY-MM-DD):");
                            String newDueDateStr = input.next();
                            LocalDate newDueDate = LocalDate.parse(newDueDateStr);
                            task.setDueDate(newDueDate);

                            System.out.println("Enter New Time Required (in hours):");
                            double newTimeRequired = input.nextDouble();
                            task.setTimeRequired(newTimeRequired);

                            System.out.println("Task Edited Successfully!");
                            break;
                        }
                    }
                    System.out.println("Task Not Found. Task not edited.");
                    break;
                case 8:
                    System.out.println("Enter Task Name to Mark as Completed:");
                    String completedTaskName = input.next();
                    for(Task task : myList.getTasks()) {
                        if(task.getDescription().equals(completedTaskName)) {
                            task.setCompleted(true);
                            System.out.println("Task Marked as Completed Successfully!");
                            break;
                        }
                    }
                    System.out.println("Task Not Found. Task not marked as completed.");
                    break;
                case 9:
                    System.out.println("Exiting Planner. Good Luck Studying!");
                    myList.saveToFile("tasks.txt");
                    running = false;
                    input.close();
                    return;

                default:
                    System.out.println("Invalid Choice. Please try again.");
            }
        }
        } catch (Exception e) {
            System.out.println("Sorry an error occured :(. Please restart the planner.");
            System.out.println("Error Details: " + e.getMessage());
            input.close();
        }
        
    }
}