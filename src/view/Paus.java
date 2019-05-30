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

public class Paus {

    public Paus()
    {
        Stage paus = new Stage();
        paus.initModality(Modality.APPLICATION_MODAL);

        Label label = new Label("Пауза!");
        label.setFont(new Font("Arial" , 20));
        label.setLayoutX(22.0);
        label.setLayoutY(22.0);
        label.setPrefHeight(17.0);
        label.setPrefWidth(207.0);
        label.setTextAlignment(TextAlignment.CENTER);

        Button nextButton = new Button("Продолжить");
        nextButton.setLayoutX(22.0);
        nextButton.setLayoutY(80.0);
        nextButton.setPrefHeight(25.0);
        nextButton.setPrefWidth(207.0);
        nextButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                View.timer.start();
                paus.close();
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
                paus.close();
            }
        });

        Button endButton = new Button("Выйти");
        endButton.setLayoutX(22.0);
        endButton.setLayoutY(160.0);
        endButton.setPrefHeight(25.0);
        endButton.setPrefWidth(207.0);
        endButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                paus.close();
                View.primaryStage.close();
            }
        });

        Pane newPane = new Pane(label, endButton, restartButton, nextButton);
        newPane.setPrefHeight(250);
        newPane.setPrefWidth(250);

        Scene scene = new Scene(newPane, 250, 250);
        paus.setHeight(250);
        paus.setWidth(250);
        paus.setScene(scene);
        View.timer.stop();
        paus.setAlwaysOnTop(true);
        paus.setResizable(false);
        paus.show();
    }
}
