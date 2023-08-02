package org.example;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import org.example.layer.data.Board;
import org.example.layer.logic.CoordinateNotEmptyException;
import org.example.layer.logic.MoveResolver;
import org.example.layer.presentation.CommandLineInterfaceHandler;
import org.example.layer.presentation.Coordinates;

import java.io.IOException;
import java.util.List;

import static org.example.ControllerCommonMethodsHandler.*;

public class SoloGameController {

    @FXML
    GridPane gridPane;
    @FXML
    Label mainLabel;

    private boolean circleBoolean = true;

    Board board = new Board();
    MoveResolver moveResolver = new MoveResolver();
    ControllerCommonMethodsHandler controllerCommonMethodsHandler = new ControllerCommonMethodsHandler();
    CommandLineInterfaceHandler commandLineInterfaceHandler = new CommandLineInterfaceHandler();

    public void switchToMenuButtonClicked(ActionEvent actionEvent) throws IOException {
        controllerCommonMethodsHandler.switchToMenuButtonClicked(actionEvent);
    }

    public void clickedGridCell(MouseEvent mouseEvent) throws IOException {

        var pane = ((Pane) mouseEvent.getSource());
        var id = pane.getId();
        var scene = pane.getScene();
        char playerChar;

        drawCircle(pane);
        playerChar = 'o';


        Integer x = Integer.parseInt(id.split("-")[0]);
        Integer y = Integer.parseInt(id.split("-")[1]);

        moveResolver.resolveAutoMove(board, new Coordinates(x, y), playerChar);

        determineIfWon();

        Coordinates coordinates = commandLineInterfaceHandler.pickRandomCoordinates(board);
        moveResolver.resolveAutoMove(board, coordinates, 'x');
        String paneIdString = coordinates.getX() + "-" + coordinates.getY();

        ObservableList<Node> panes = gridPane.getChildren();
        List<Node> paneInList = panes.stream().filter(node -> node.getId().equals(paneIdString))
                .toList();

        System.out.println("panes with id: " + paneInList.size());
        System.out.println("scene: " + scene);
        commandLineInterfaceHandler.showBoard(board);

        Pane autoPane = (Pane) paneInList.get(0);
        drawCross(autoPane);

        determineIfWon();

    }

    private void determineIfWon() {
        if (moveResolver.hasStreak(board, 'o', 3)) {
            System.out.println("player with \'o\' won");
            mainLabel.setText("player with \'o\' won");

            gridPane.setDisable(true);

        } else if (moveResolver.hasStreak(board, 'x', 3)) {
            System.out.println("player with \'x\' won");
            mainLabel.setText("player with \'x\' won");
            gridPane.setDisable(true);
        } else if (moveResolver.allCoordinatesTaken(board)) {
            mainLabel.setText("its a draw");
            gridPane.setDisable(true);
        }
    }

    public void mouseEntered(MouseEvent mouseEvent) {
        controllerCommonMethodsHandler.mouseEntered(mouseEvent);
    }

    public void mouseExited(MouseEvent mouseEvent) {
        controllerCommonMethodsHandler.mouseExited(mouseEvent);
    }
}
