package com.example.taskexample_section32concurrencyinjava;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;

import java.io.IOException;

public class HelloController {
    @FXML
    private ListView listView;

    private Task<ObservableList<String>> task;

    @FXML
    private ProgressBar progressBar;
    @FXML
    private Label progressLabel;



    public void initialize() {
        task = new Task<>() {
            @Override
            protected ObservableList<String> call() throws Exception {

                String[] names = {"Tim Buchalka",
                        "Bill Rogers",
                        "Jack Jill",
                        "Joan Andrews",
                        "Mary Johnson",
                        "Bob McDonald"};
                ObservableList<String> employees = FXCollections.observableArrayList();


                for (int i = 0; i < 6; i++) {
                    employees.add(names[i]);
                    updateMessage("Added " + names[i] + " to the list.");
                    updateProgress(i + 1, 6);
                    Thread.sleep(200);
                }
                return employees;
            }
        };

        progressBar.progressProperty().bind(task.progressProperty());
        progressLabel.textProperty().bind(task.messageProperty());
        listView.itemsProperty().bind(task.valueProperty());

    }

    @FXML
    public void buttonPressed() {
        new Thread(task).start();
    }

}