package model;

import controller.MapController;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import view.View;

public class Enemy extends AbstractEssence {

    int experience = 10;      // Опыт который получает игрок за уничтожение врага
    private int speed = 20;
    private Pane root;
    private Player player;
    private AnimationTimer timer;
    // Переменные которые определяют поведение при перемещении (пока не уверен как это сделать)
    private int goLeft = 0;
    private int goRight = 0;
    private int goStraight = 0;
    private int goBack = 0;
    //********************************************************

    Enemy(ImageView image, Pane root)
    {
        super(image);
        this.root = root;
        player = Player.Init();

    }

    void getDamage(int damage) // Полученный урон
    {
        this.setHealth(this.getHealth() - damage);
    }

    private boolean isEnemyStop() // Останавливатся когда врезался в врага
    {
        if (this.getBoundsInParent().intersects(player.getBoundsInParent()))
        {
                return false;
        }
        return true;
    }

    @Override
    public void moveX(int x){                                            // Движение по оси X
        if (isEnemyStop())
        {
            super.moveX(x);
            attack();
        }
    }
    @Override
    public void moveY(int y) {                                           // Движение по оси Y (надо переделать в прыжки)
        if (isEnemyStop())
        {
            super.moveY(y);
            attack();
        }
    }

    void attack()
    {
        if (this.getBoundsInParent().intersects(player.getBoundsInParent())) {
                System.out.println("Вы проиграли!");
                Stage endStage = new Stage();
                endStage.initModality(Modality.APPLICATION_MODAL);

                Label label = new Label("Игра окончена!");
                label.setFont(new Font("Arial" , 20));
                label.setLayoutX(22.0);
                label.setLayoutY(22.0);
                label.setPrefHeight(17.0);
                label.setPrefWidth(207.0);
                label.setTextAlignment(TextAlignment.CENTER);

                Label status = new Label("Ваш счёт = " + player.score);
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

    public void enemyGo()
    {
        if (!this.getBoundsInParent().intersects(player.getBoundsInParent())) {
            int flag = (int)Math.floor((Math.random() * 4));
            if (((player.getTranslateY() + player.getWidth() / 2) < (this.getTranslateY() + this.getWidth() / 2) && flag == 0)) {
                //this.animation.play();
                this.animation.setOffsetY(2 * height);
                this.moveY(-2);
            } else if (((player.getTranslateY() + player.getWidth() / 2) > (this.getTranslateY() + this.getWidth() / 2) && flag == 1)) {
                //this.animation.play();
                this.animation.setOffsetY(0);
                this.moveY(2);
            } else if (((player.getTranslateX() + player.getHeight() / 2) > (this.getTranslateX() + this.getHeight() / 2) && flag == 2)) {
                //this.animation.play();
                this.animation.setOffsetY(height);
                this.moveX(2);
            } else if (((player.getTranslateX() + player.getHeight() / 2) < (this.getTranslateX() + this.getHeight() / 2) && flag == 3)) {
                //this.animation.play();
                this.animation.setOffsetY(3 * height);
                this.moveX(-2);
            }
        }
    }

    void death()
    {
        MapController.enemies.remove(this);
        root.getChildren().remove(this);
    }

}

