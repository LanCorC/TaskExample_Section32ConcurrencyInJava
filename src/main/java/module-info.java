module com.example.taskexample_section32concurrencyinjava {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.taskexample_section32concurrencyinjava to javafx.fxml;
    exports com.example.taskexample_section32concurrencyinjava;
}