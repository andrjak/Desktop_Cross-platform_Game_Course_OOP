package model;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Enemy extends AbstractEssence {

    public int experience = 10;      // Опыт который получает игрок за уничтожение врага
    private int speed = 20;

    public Enemy(ImageView image)
    {
        super(image);
    }

    public void getDamage(int damage) // Полученный урон
    {
        this.setHealth(this.getHealth() - damage);
    }

    public void attack()
    {

    }
}

