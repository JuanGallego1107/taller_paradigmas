import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;

// Class representing a task
class Task {
    private final String name;
    private boolean completed;

    public Task(String name) {
        this.name = name;
        this.completed = false;
    }

    public String getName() {
        return name;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void markAsCompleted() {
        this.completed = true;
    }
}

// Class representing the pending tasks management system
public class PendingTasksManagementSystem {
    private static final List<Task> tasks = new ArrayList<>();


    private static void addTask(String name) {
        tasks.add(new Task(name));
        System.out.println("Task added successfully.");
    }

    private static void markTaskAsCompleted(String name) {
        Predicate<Task> taskPredicate = task -> task.getName().equalsIgnoreCase(name);
        tasks.stream()
                .filter(taskPredicate)
                .findFirst()
                .ifPresent(task -> {
                    task.markAsCompleted();
                    System.out.println("Task marked as completed.");
                });
    }

    private static void showAllTasks() {
        System.out.println("All tasks:");
        tasks.forEach(task -> System.out.println(task.getName() + " - Completed: " + task.isCompleted()));
    }

    private static void showCompletedTasks() {
        System.out.println("Completed tasks:");
        List<Task> completedTasks = tasks.stream()
                .filter(Task::isCompleted)
                .toList();
        completedTasks.forEach(task -> System.out.println(task.getName()));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continueExecution = true;

        while (continueExecution) {
            System.out.println("1. Add new task");
            System.out.println("2. Mark task as completed");
            System.out.println("3. Show all tasks");
            System.out.println("4. Show completed tasks");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (option) {
                case 1 -> {
                    System.out.print("Enter the name of the new task: ");
                    String taskName = scanner.nextLine();
                    addTask(taskName);
                }
                case 2 -> {
                    System.out.print("Enter the name of the task to mark as completed: ");
                    String taskCompleted = scanner.nextLine();
                    markTaskAsCompleted(taskCompleted);
                }
                case 3 -> showAllTasks();
                case 4 -> showCompletedTasks();
                case 5 -> continueExecution = false;
                default -> System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }
}

