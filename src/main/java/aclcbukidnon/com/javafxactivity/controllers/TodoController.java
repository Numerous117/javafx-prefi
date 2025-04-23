package aclcbukidnon.com.javafxactivity.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class TodoController {

    @FXML
    private ListView<String> todoList;

    // Declare the ObservableList to hold todo items
    private ObservableList<String> todoItems;

    @FXML
    public void initialize() {
        // Initialize the ObservableList
        todoItems = FXCollections.observableArrayList();
        todoItems.add("Remove Me");

        // Set the list items to the ListView
        todoList.setItems(todoItems);
        todoList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        todoList.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) -> {
                    if (newVal != null) {
                        onTodoListItemClick(newVal);
                    }
                }
        );
    }

    // Method to handle clicking on a todo item
    private void onTodoListItemClick(String value) {
        var dialog = new TextInputDialog(value);
        dialog.setTitle("Update Todo");

        // Show the dialog and handle the result
        var result = dialog.showAndWait();
        result.ifPresent(text -> {
            // Find the selected item and update it with the new value
            int index = todoItems.indexOf(value);
            todoItems.set(index, text); // Update the list
        });
    }

    // Method to create a new todo item
    @FXML
    protected void onCreateClick() {
        var dialog = new TextInputDialog("");
        dialog.setTitle("Create New Todo");

        // Show the dialog and handle the result
        var result = dialog.showAndWait();
        result.ifPresent(text -> {
            // Add the new item to the list
            todoItems.add(text);
        });
    }

    // Method to delete a todo item
    @FXML
    protected void onDeleteClick() {
        String selectedItem = todoList.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            // Show confirmation dialog
            var confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setTitle("Confirmation Dialog");
            confirm.setHeaderText("Are you sure you want to delete this todo?");
            confirm.setContentText("This action cannot be undone.");

            // Handle the user's response
            var result = confirm.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                todoItems.remove(selectedItem); // Remove the item from the list
            }
        } else {
            // Show warning if no item is selected
            var alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Item Selected");
            alert.setHeaderText("Please select an item to delete.");
            alert.showAndWait();
        }
    }

    public void onListEdit(ListView.EditEvent<String> stringEditEvent) {
    }
}
