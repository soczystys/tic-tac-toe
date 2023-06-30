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

public class StandardGameController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    GridPane gridPane;
    @FXML
    Label myLabel;

    private boolean circleBoolean = true;

    Board board = new Board();
    MoveResolver moveResolver = new MoveResolver();

    public void switchToMenuButtonClicked(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/hello-view.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }

    public void clickedGridCell(MouseEvent mouseEvent) throws IOException {
        var border = ((Pane) mouseEvent.getSource()).getBorder();
        var pane = ((Pane) mouseEvent.getSource());
        var id = pane.getId();
        char playerChar;

        if (circleBoolean) {
            drawCircle(pane);
            playerChar = 'o';
        } else {
            drawCross(pane);
            playerChar = 'x';
        }
        circleBoolean = !circleBoolean;

        Integer x = Integer.parseInt(String.valueOf(id.charAt(0)));
        Integer y = Integer.parseInt(String.valueOf(id.charAt(1)));

        try {
            moveResolver.resolveMove(board, new Coordinates(x, y), playerChar);
        } catch (CoordinateNotEmptyException e) {

        }

        if (moveResolver.hasStreak(board, 'o', 3)) {
            System.out.println("player with \'o\' won");
            myLabel.setText("player with \'o\' won");

//            switchToMenu(mouseEvent);

        } else if (moveResolver.hasStreak(board, 'x', 3)) {
            System.out.println("player with \'x\' won");
            myLabel.setText("player with \'x\' won");
//            switchToMenu(mouseEvent);
        } else if (moveResolver.allCoordinatesTaken(board)) {
            System.out.println("board is full - you cannot make more moves");
            System.out.println("its a draw");
            myLabel.setText("its a draw");
//            switchToMenu(mouseEvent);
        }

    }

    private void switchToMenu(MouseEvent mouseEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/hello-view.fxml"));
        stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }

    private static void drawCross(Pane pane) {
        Line line1 = new Line(0,0, pane.getHeight(), pane.getHeight());
        line1.setStrokeWidth(2.0);
        Line line2 = new Line(pane.getHeight(),0, 0, pane.getHeight());
        line2.setStrokeWidth(2.0);
        pane.getChildren().add(line1);
        pane.getChildren().add(line2);
        pane.setDisable(true);
    }

    private static void drawCircle(Pane pane) {
        Circle circle = new Circle(pane.getHeight()/2, pane.getHeight()/2, 5);
        pane.getChildren().add(circle);
        pane.setDisable(true);
    }

    public void mouseExited(MouseEvent mouseEvent) {
        var pane = ((Pane) mouseEvent.getSource());

        pane.setBackground(new Background(new BackgroundFill(Color.GRAY, new CornerRadii(0), Insets.EMPTY)));
    }

    public void mouseEntered(MouseEvent mouseEvent) {
        var pane = ((Pane) mouseEvent.getSource());

        pane.setBackground(new Background(new BackgroundFill(Color.BLUE, new CornerRadii(0), Insets.EMPTY)));
    }

}
