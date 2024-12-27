package org.example;

public class Task {

    //declare the attributes taskName, priority, description, isCompleted, category
    private String taskName;
    private int priority;
    private String description;
    private Category category;
    private boolean isCompleted;

    public Task(String taskName, int priority, String description, boolean isCompleted, Category category) {
        this.taskName = taskName;
        this.priority = priority;
        this.description = description;
        this.isCompleted = isCompleted;
        this.category = category;
    }


    //declare getter setter and toString method


    public String getTaskName() {
        return taskName;
    }

    public int getPriority() {
        return priority;
    }

    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskName='" + taskName + '\'' +
                ", description='" + description + '\'' +
                ", priority=" + priority +
                ", isCompleted=" + isCompleted +
                ", category=" + category +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;
        return priority == task.priority && isCompleted == task.isCompleted && taskName.equals(task.taskName) && description.equals(task.description) && category.equals(task.category);
    }
    @Override
    public int hashCode() {
        int result = taskName.hashCode();
        result = 31 * result + priority;
        result = 31 * result + description.hashCode();
        result = 31 * result + (isCompleted ? 1 : 0);
        result = 31 * result + category.hashCode();
        return result;
    }
}

