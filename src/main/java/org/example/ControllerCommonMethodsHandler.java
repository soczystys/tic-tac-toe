package org.example;

import javafx.event.ActionEvent;
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

public class ControllerCommonMethodsHandler {


    public void switchToMenuButtonClicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/main-menu-view.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }


    public void clickedGridCell(MouseEvent mouseEvent, boolean circleBoolean, MoveResolver moveResolver, Board board, Label myLabel, GridPane gridPane, int numberRequiredForWin) throws IOException {
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

//        Integer x = Integer.parseInt(String.valueOf(id.charAt(0)));
//        Integer y = Integer.parseInt(String.valueOf(id.charAt(1)));

        Integer x = extractX(id);
        Integer y = extractY(id);

//        TODO

        try {
            moveResolver.resolveMove(board, new Coordinates(x, y), playerChar);
        } catch (CoordinateNotEmptyException e) {

        }

        if (moveResolver.hasStreak(board, 'o', numberRequiredForWin)) {
            System.out.println("player with \'o\' won");
            myLabel.setText("player with \'o\' won");

            gridPane.setDisable(true);

        } else if (moveResolver.hasStreak(board, 'x', numberRequiredForWin)) {
            System.out.println("player with \'x\' won");
            myLabel.setText("player with \'x\' won");
            gridPane.setDisable(true);
        } else if (moveResolver.allCoordinatesTaken(board)) {
            myLabel.setText("its a draw");
            gridPane.setDisable(true);
        }
    }

    private static Integer extractX(String id) {
        return Integer.parseInt(id.split("-")[0]);
    }

    private static Integer extractY(String id) {
        return Integer.parseInt(id.split("-")[1]);
    }

//    public static void main(String[] args) {
////        System.out.println(Arrays.toString("9-10".split("-")));
//        System.out.println(extractX("9-10"));
//        System.out.println(extractY("9-10"));
//    }

    public static void drawCross(Pane pane) {
        Line line1 = new Line(0,0, pane.getHeight(), pane.getHeight());
        line1.setStrokeWidth(2.0);
        Line line2 = new Line(pane.getHeight(),0, 0, pane.getHeight());
        line2.setStrokeWidth(2.0);
        pane.getChildren().add(line1);
        pane.getChildren().add(line2);
        pane.setDisable(true);
    }

    public static void drawCircle(Pane pane) {
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
