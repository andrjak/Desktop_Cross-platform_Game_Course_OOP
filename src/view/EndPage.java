package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Player;

public class EndPage {

    public EndPage()
    {
        Stage endStage = new Stage();
        endStage.initModality(Modality.APPLICATION_MODAL);

        Label label = new Label("Игра окончена!");
        label.setFont(new Font("Arial" , 20));
        label.setLayoutX(22.0);
        label.setLayoutY(22.0);
        label.setPrefHeight(17.0);
        label.setPrefWidth(207.0);
        label.setTextAlignment(TextAlignment.CENTER);

        assert Player.Init() != null;
        Label status = new Label("Ваш счёт = " + Player.Init().score);
        status.setStyle("-fx-font-weight: bold");
        status.setFont(new Font("Arial", 16));
        status.setLayoutX(40.0);
        status.setLayoutY(74.0);
        status.setPrefHeight(17.0);
        status.setPrefWidth(207.0);
        status.setTextAlignment(TextAlignment.CENTER);

        Button endButton = new Button("Выйти");
        endButton.setLayoutX(22.0);
        endButton.setLayoutY(170.0);
        endButton.setPrefHeight(25.0);
        endButton.setPrefWidth(207.0);
        endButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                endStage.close();
                View.primaryStage.close();
            }
        });

        Button restartButton = new Button("Заново");
        restartButton.setLayoutX(22.0);
        restartButton.setLayoutY(120.0);
        restartButton.setPrefHeight(25.0);
        restartButton.setPrefWidth(207.0);
        restartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                View.Restart();
                endStage.close();
            }
        });

        Pane newPane = new Pane(label, endButton, restartButton, status);
        newPane.setPrefHeight(250);
        newPane.setPrefWidth(250);
        //FlowPane newPane = new FlowPane(Orientation.VERTICAL, 10, 10, label, endButton, status);
        //newPane.setAlignment(Pos.TOP_CENTER);
        //StackPane newPane = new StackPane(label, status, endButton);

        Scene scene = new Scene(newPane, 250, 250);
        endStage.setHeight(250);
        endStage.setWidth(250);
        endStage.setScene(scene);
        View.timer.stop();
        endStage.setAlwaysOnTop(true);
        endStage.setResizable(false);
        endStage.show();
    }
}
