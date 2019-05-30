package controller;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import model.BumBum;
import model.Enemy;
import model.Player;
import view.Paus;

import java.util.HashMap;

public class Controller {
    public HashMap<KeyCode, Boolean> keys = new HashMap<>(); // Хешь для хранения нажатой кнопки !!! Перенести в контролер
    private Player player;
    private Pane root;

    public Controller(Player player, Pane root)
    {
        this.player = player;
        this.root = root;
    }

    public boolean isPressed(KeyCode key) {                   // Проверяет нажата ли кнопка
        return keys.getOrDefault(key, false);
    }

    public void update() {                                    //Вызов анимации и движения player
        if (isPressed(KeyCode.UP)) {
            player.animation.play();
            player.animation.setOffsetY(72);
            player.moveY(-2);
        }
        else if (isPressed(KeyCode.DOWN)) {
            player.animation.play();
            player.animation.setOffsetY(0);
            player.moveY(2);
        }
        else if (isPressed(KeyCode.RIGHT)) {
            player.animation.play();
            player.animation.setOffsetY(36);
            player.moveX(2);
        }
        else if (isPressed(KeyCode.LEFT)) {
            player.animation.play();
            player.animation.setOffsetY(108);
            player.moveX(-2);
        }
        else {                                               // Если кнопки не нажаты останавливаем анимацию
            player.animation.stop();
        }

        if (isPressed(KeyCode.TAB))
        {
            assert Player.Init() != null;
            if (Player.Init().bumBum)
            {
                root.getChildren().remove(player.bum);
                Player.Init().bumBum = false;
                BumBum bum = new BumBum(root);
                bum.flight();
            }
            keys.clear();
        }
        if (isPressed(KeyCode.ESCAPE))
        {
            new Paus();
            keys.clear();
        }
    }
}
