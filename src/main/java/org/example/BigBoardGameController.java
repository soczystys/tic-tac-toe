package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.example.layer.data.Board;
import org.example.layer.logic.MoveResolver;

import java.io.IOException;

public class BigBoardGameController {

    @FXML
    GridPane gridPane;
    @FXML
    Label myLabel;

    Board board = new Board(10);
    MoveResolver moveResolver = new MoveResolver();
    private boolean circleBoolean = true;
    ControllerCommonMethodsHandler controllerCommonMethodsHandler = new ControllerCommonMethodsHandler();
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void clickedGridCell(MouseEvent mouseEvent) throws IOException {
        controllerCommonMethodsHandler.clickedGridCell(mouseEvent, circleBoolean, moveResolver, board, myLabel, gridPane, 5);
        circleBoolean = !circleBoolean;
    }

    public void mouseEntered(MouseEvent mouseEvent) {
        controllerCommonMethodsHandler.mouseEntered(mouseEvent);
    }

    public void mouseExited(MouseEvent mouseEvent) {
        controllerCommonMethodsHandler.mouseExited(mouseEvent);
    }

    public void switchToMenuButtonClicked(ActionEvent actionEvent) throws IOException {
        controllerCommonMethodsHandler.switchToMenuButtonClicked(actionEvent, root, stage, scene);
    }
}
