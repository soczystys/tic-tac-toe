package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloView {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToGame(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/hello2.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        System.out.println("inside switchToSecondView");
    }
}
