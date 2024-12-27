package org.example;

import java.util.*;

/**
 * The CategoryOperations class provides operations related to managing categories.
 */
public class CategoryOperations {

    // Declare the categories attribute
    // This attribute will hold all the categories
    private HashSet<Category> categories;

    public CategoryOperations() {
        this.categories = new HashSet<Category>();
    }

    public boolean addCategory(String categoryName) {
        // Check if the category with the same name already exists
        // If the category is present, return false
        // If the category is not present, add the category and return true
        for (Category category : categories) {
            if (category.getCategoryName().equals(categoryName)) {
                return false;
            }
        }
        return categories.add(new Category(categoryName));
    }

    public Category findCategory(String categoryName) {
        // Check if categories list is empty
        // If empty, return null
        // If not empty, iterate through the list to find if the given categoryName is present
        // If present, return the category, otherwise return null
        if (categories.isEmpty()) {
            return null;
        }
        for (Category category : categories) {
            if (category.getCategoryName().equals(categoryName)) {
                return category;
            }
        }
        return null;
    }

    public List<Category> listAllCategories() {
        // Display the categories and return the same
        System.out.println("List of Categories: ");
        for (Category category : categories) {
            System.out.println(category.getCategoryName());
        }
        return new ArrayList(this.categories);
    }

    public static Category getCategoryByName(Map<Category, List<Task>> categoryTaskMap, String categoryName) {
        // Get the keySet from the categoryTaskMap
        // Check if the given categoryName is present in the map
        // If the name is present, return the category, otherwise return null
        for (Category category : categoryTaskMap.keySet()) {
            if (category.getCategoryName().equals(categoryName)) {
                return category;
            }
        }
        return null;
    }
}

