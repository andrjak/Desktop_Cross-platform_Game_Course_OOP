package game;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import java.util.HashMap;

public class Controller {
    public HashMap<KeyCode, Boolean> keys = new HashMap<>(); // Хешь для хранения нажатой кнопки !!! Перенести в контролер
    private Player player;
    private Pane root;

    Controller(Player player, Pane root)
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
        else if (isPressed(KeyCode.SPACE )) {
            System.out.println("Скоро прыжки !");
            keys.clear();
        }
        else if (isPressed(KeyCode.TAB))
        {
            System.out.println("Скоро чит режим!");
            keys.clear();
        }
        else{                                               // Если кнопки не нажаты останавливаем анимацию
            player.animation.stop();
        }
    }
}
