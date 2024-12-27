package org.example;

import java.util.*;

import static org.example.CategoryOperations.getCategoryByName;

public class TaskOperations {
    //declare the attribute taskName,userTaskMap,categoryTaskMap
    private Map<Category, List<Task>> categoryTaskMap;
    private Map<User, List<Task>> userTaskMap;

    public TaskOperations() {
        //initialize the attributes
        this.categoryTaskMap = new HashMap<Category, List<Task>>();
        this.userTaskMap = new HashMap<User, List<Task>>();
    }

    public boolean addTask(Category selectedCategory,Task task, User authenticatedUser) {
        //check if task is already present in taskNames
        //if present display appropriate message and return false;
        //if not present  get the userTask from the authenticatedUser.
        //check if UserTask not equal to null
        //initialize the list  and the task to the list.
        //if present just add the task to the list.
        //check if categoryTasks not equal to null
        //initialize the list  and the task to the list.
        //if present just add the task to the list.
        //add the task to the taskNames
        //return true
        List<Task> user = this.userTaskMap.get(authenticatedUser);
        List<Task> categoryTasks = this.categoryTaskMap.get(selectedCategory);

        if (categoryTasks == null && user == null) {
            categoryTasks = new ArrayList<Task>();
            categoryTasks.add(task);
            categoryTaskMap.put(selectedCategory, categoryTasks);
            user = new ArrayList<Task>();
            user.add(task);
            userTaskMap.put(authenticatedUser, user);
            return true;
        }
        if (categoryTasks != null && !categoryTasks.contains(task)) {
            categoryTasks.add(task);
            categoryTaskMap.put(selectedCategory, categoryTasks);
        }
        if (!user.contains(task)) {
            user.add(task);
            userTaskMap.put(authenticatedUser, user);
        }
        if (categoryTasks != null && categoryTasks.contains(task) && user.contains(task)) {
            System.out.println("Task already Present for user.");
            return false;
        }
        return true;
    }
    public List<String> listAllTasks(String categoryName) {
        //fetch categories by categoryName  using getCategoryByName method of the category class
        //create a list of sortedTasks
        //if the selectedCategory is null display appropriate message
        //if selectedCategory is not null
        //generate a string "Category:  " + categoryName + " - " +"Name:  " +  taskName + " Priority:  " + taskPriority + " Description:  " + taskDescription + " Status:  " + isCompleted
        //add the string to the list and return the list
        Category category = getCategoryByName(this.categoryTaskMap, categoryName);
        List<String> taskDetails = new ArrayList<String>();
        StringBuilder displayString = new StringBuilder("Category:").append(categoryName).append("-");
        if (category == null) {
            System.out.println("Category: " + categoryName + " Not Found.");
        }
        for (Task task : categoryTaskMap.get(category)) {
            displayString.append("Name:").append(task.getTaskName());
            displayString.append("Priority:").append(task.getPriority());
            displayString.append("Description:").append(task.getDescription());
            displayString.append("Status:").append(task.isCompleted());
            taskDetails.add(displayString.toString());
            displayString.setLength(0);
        }
        return taskDetails;
    }
    public boolean markTaskAsCompleted(String taskToComplete,String statusInput ) {
        //iterate the list of task from categoryTaskMap
        //check if the task with the given name is present
        //if the given statusInput is completed setCompleted to true and return true
        //if the given statusInput is pending setCompleted to false and return true
        //if the given statusInput is neither completed nor pending return false
        Task modifyTask = searchTasksByName(taskToComplete);
        if (statusInput.equals("completed")) {
            modifyTask.setCompleted(true);
            return true;
        } else if (statusInput.equals("pending")) {
            modifyTask.setCompleted(false);
            return true;
        }
        return false;
    }

    public static <Key, Value extends List<Task>> boolean deleteTaskFromMap(Map<Key, Value> map, Task task) {
        for (Map.Entry<Key, Value> entry : map.entrySet()) {
            if (entry.getValue().remove(task)) {
                if (entry.getValue().isEmpty()) {
                    map.remove(entry.getKey());
                }
                return true;
            }
        }
        return false;
    }
    public boolean removeTask(String taskToRemove) throws TaskNotFoundException {
        //iterate the list of task from categoryTaskMap
        //check if the task with the given name is present
        // remove the task
        //remove the task from taskNames
        //return true if the task is removed
        //if task is not removed throw TaskNotFoundException
        Task DeleteTask = searchTasksByName(taskToRemove);
        if (DeleteTask != null) {
            boolean userDeleteFlag = deleteTaskFromMap(userTaskMap, DeleteTask);
            boolean categoryDeleteFlag = deleteTaskFromMap(categoryTaskMap, DeleteTask);
            if (userDeleteFlag && categoryDeleteFlag) {
                return true;
            }
        }
        throw new TaskNotFoundException("Task: " + taskToRemove + " Not found");
    }
    public Task searchTasksByName(String taskName) {
        //iterate the list of task from categoryTaskMap
        //check if the task with the given name is present
        //add it to the matchingTaskList
        for (Category category : categoryTaskMap.keySet()) {
            for (Task task : categoryTaskMap.get(category)) {
                if (task.getTaskName() == null ? taskName == null : task.getTaskName().equals(taskName)) {
                    return task;
                }
            }
        }
        return null;
    }
}


