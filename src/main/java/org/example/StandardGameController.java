package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import org.example.layer.data.Board;
import org.example.layer.logic.CoordinateNotEmptyException;
import org.example.layer.logic.MoveResolver;
import org.example.layer.presentation.Coordinates;

import java.io.IOException;
import java.util.Objects;

public class StandardGameController {

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
        circleBoolean = !circleBoolean;
    }

    public void mouseExited(MouseEvent mouseEvent) {
        controllerCommonMethodsHandler.mouseExited(mouseEvent);
    }

    public void mouseEntered(MouseEvent mouseEvent) {
        controllerCommonMethodsHandler.mouseEntered(mouseEvent);
    }

}
