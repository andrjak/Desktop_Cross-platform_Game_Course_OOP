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
import view.EndPage;
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
                new EndPage();
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

