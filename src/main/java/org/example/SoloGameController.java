package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import org.example.layer.data.Board;
import org.example.layer.logic.MoveResolver;

import java.io.IOException;

public class SoloGameController {

    @FXML
    GridPane gridPane;
    @FXML
    Label myLabel;

    private boolean circleBoolean = true;

    Board board = new Board();
    MoveResolver moveResolver = new MoveResolver();
    ControllerCommonMethodsHandler controllerCommonMethodsHandler = new ControllerCommonMethodsHandler();

    public void switchToMenuButtonClicked(ActionEvent actionEvent) throws IOException {
        controllerCommonMethodsHandler.switchToMenuButtonClicked(actionEvent);
    }

    public void clickedGridCell(MouseEvent mouseEvent) throws IOException {
        controllerCommonMethodsHandler.clickedGridCell(mouseEvent, circleBoolean, moveResolver, board, myLabel, gridPane, 3);
    }

    public void mouseEntered(MouseEvent mouseEvent) {
        controllerCommonMethodsHandler.mouseEntered(mouseEvent);
    }

    public void mouseExited(MouseEvent mouseEvent) {
        controllerCommonMethodsHandler.mouseExited(mouseEvent);
    }
}
