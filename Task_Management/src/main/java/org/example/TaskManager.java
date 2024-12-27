package org.example;


import java.util.List;
import java.util.Scanner;

public class TaskManager {
    // Attributes for category and task operations
    private CategoryOperations categoryOperations;
    private TaskOperations taskOperations;

    /**
     * Constructs a new TaskManager object.
     * Initializes category and task operation objects.
     */
    public TaskManager() {
        // Initialize category and task operation objects
        categoryOperations = new CategoryOperations();
        taskOperations = new TaskOperations();
    }

    /**
     * Allows the authenticated user to interact with the task manager by providing menu choices.
     *
     * @param authenticatedUser The authenticated user accessing the task manager.
     */
    public void takeChoices(User authenticatedUser) {
        Scanner sc = new Scanner(System.in);
        String categoryName;
        Category selectedCategory;
        int choice;

        // Display the menu options and handle user choices until the user chooses to exit
        do {
            System.out.println("\nTask Manager Menu");
            System.out.println("1. Add a Task");
            System.out.println("2. List all Tasks");
            System.out.println("3. Mark a Task as Completed");
            System.out.println("4. Remove a Task");
            System.out.println("5. Add a Category");
            System.out.println("6. List all Categories");
            System.out.println("7. Search for a Task by Name");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    // Add a new task
                    // Enter category name and fetch the category
                    // Check if category is present
                    // If present, display appropriate message to add task
                    // If category is not present, display appropriate message

                    System.out.print("Enter Category name: ");
                    categoryName = sc.next();
                    selectedCategory = categoryOperations.findCategory(categoryName);
                    if (selectedCategory != null) {
                        System.out.println("Category Exists. Enter Task details:");
                        System.out.print("Enter Task name: ");
                        String name = sc.next();
                        System.out.print("Enter Task Description: ");
                        String desc = sc.next();
                        System.out.print("Enter Task Priority: ");
                        int priority = sc.nextInt();
                        System.out.print("Enter Task Completion: ");
                        boolean completed = sc.nextBoolean();
                        Task task = new Task(name, priority, desc, completed, selectedCategory);
                        taskOperations.addTask(selectedCategory, task, authenticatedUser);
                    }
                    System.out.println("Category Not found, Please add it first.");
                    break;
                case 2:
                    // List all tasks
                    System.out.print("Enter Category name: ");
                    List<String> tasklist = taskOperations.listAllTasks(sc.next());
                    for (String task : tasklist) {
                        System.out.println(task);
                    }
                    break;
                case 3:
                    // Mark a task as completed
                    // Get the task name and task status
                    System.out.print("Enter Task name: ");
                    String name = sc.next();
                    System.out.print("Enter Status: ");
                    String status = sc.next();
                    taskOperations.markTaskAsCompleted(name, status);
                    break;
                case 4:
                    // Remove a task
                    // Enter task name and handle exceptions
                    System.out.print("Enter Task to be removed: ");
                    name = sc.next();
                    try {
                        taskOperations.removeTask(name);
                    } catch (TaskNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 5:
                    // Add a new category
                    // Enter category name
                    System.out.print("Enter category name: ");
                    categoryName = sc.next();
                    if (categoryOperations.addCategory(categoryName)) {
                        System.out.println("Category Successfully added.");
                    } else {
                        System.out.println("Category already exists");
                    }
                    break;
                case 6:
                    // List all categories
                    List<Category> catList = categoryOperations.listAllCategories();
                    for (Category category : catList) {
                        System.out.println(category.toString());
                    }
                    break;
                case 7:
                    // Search for a task by name
                    // Enter task name to search
                    System.out.print("Enter Task name: ");
                    name = sc.next();
                    Task task = taskOperations.searchTasksByName(name);
                    System.out.println(task.toString());
                    break;
                case 8:
                    // Exit the program
                    System.out.println("Exiting Task Manager...");
                    break;
                default:
                    // Invalid choice
                    System.out.println("Invalid choice. Please enter a number between 1 and 8.");
                    break;
            }
        } while (choice != 8);

        // Close the scanner to avoid resource leak
        sc.close();
    }
}

