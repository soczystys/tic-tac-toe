module org.example {
    requires javafx.controls;
    requires javafx.fxml;

//    requires com.almasb.fxgl.all;

    opens org.example to javafx.fxml;
    exports org.example;
}