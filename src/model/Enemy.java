package model;

import controller.MapController;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Enemy extends AbstractEssence {

    int experience = 10;      // Опыт который получает игрок за уничтожение врага
    private int speed = 20;
    private Pane root;
    private Player player;

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

    public void attack()
    {
        if (this.getBoundsInParent().intersects(player.getBoundsInParent())) {
           player.getDamage(damage);
           if (player.getHealth() <= 0)
           {
               System.out.println("Вы проиграли!");
           }
        }
    }

    void enemyGo()
    {
        if (!this.getBoundsInParent().intersects(player.getBoundsInParent())) {
            if (player.getTranslateY() + player.getWidth() / 2 < this.getTranslateY() * this.getWidth() / 2) {
                player.animation.play();
                player.animation.setOffsetY(2 * height);
                player.moveY(-2);
            } else if (player.getTranslateY() + player.getWidth() / 2 > this.getTranslateY() * this.getWidth() / 2) {
                player.animation.play();
                player.animation.setOffsetY(0);
                player.moveY(2);
            } else if (player.getTranslateX() + player.getHeight() / 2 > this.getTranslateX() * this.getHeight() / 2) {
                player.animation.play();
                player.animation.setOffsetY(height);
                player.moveX(2);
            } else if (player.getTranslateX() + player.getHeight() / 2 < this.getTranslateX() * this.getHeight() / 2) {
                player.animation.play();
                player.animation.setOffsetY(3 * height);
                player.moveX(-2);
            }
        }
    }

    void death()
    {
        MapController.enemies.remove(this);
        root.getChildren().remove(this);
    }

}

