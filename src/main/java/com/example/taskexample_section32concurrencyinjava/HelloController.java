package com.example.taskexample_section32concurrencyinjava;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.IOException;

public class HelloController {
    @FXML
    private ListView listView;

    private Task<ObservableList<String>> task;

    public void initialize() {
        task = new Task<>() {
            @Override
            protected ObservableList<String> call() throws Exception {
                Thread.sleep(1000);

                ObservableList<String> employees = FXCollections.observableArrayList(                        "Tim Buchalka",
                        "Bill Rogers",
                        "Jack Jill",
                        "Joan Andrews",
                        "Mary Johnson",
                        "Bob McDonald");

                return employees;
            }
        };

        listView.itemsProperty().bind(task.valueProperty());

    }

    @FXML
    public void buttonPressed() {
        new Thread(task).start();
    }

}