package game;

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

    public void attack()
    {

    }
}

class Skillet extends Enemy
{
    public Skillet(ImageView image)
    {
        super(image);
        damage = 25;
        health = 100;

        count = 4;       // Количество строк в картинке
        columns = 3;     // Количество столбцов в картинке
        offsetX = 0;     // Смещение
        offsetY = 0;     // -//-
        width = 30;      // Размеры вырезаемого прямо угольника
        height = 48;     // -//-

        this.image.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
        animation = new SpriteAnimation(image,Duration.millis(200),count,columns,offsetX,offsetY,width,height);
        getChildren().addAll(image); // Так как наследуется от Pane передаём картинку
    }
}

class Zombie extends Enemy
{
    public Zombie(ImageView image)
    {
        super(image);
        damage = 10;
        health = 200;

        count = 4;       // Количество строк в картинке
        columns = 3;     // Количество столбцов в картинке
        offsetX = 0;     // Смещение
        offsetY = 0;     // -//-
        width = 32;      // Размеры вырезаемого прямо угольника
        height = 41;     // -//-

        this.image.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
        animation = new SpriteAnimation(image, Duration.millis(200),count,columns,offsetX,offsetY,width,height);
        getChildren().addAll(image); // Так как наследуется от Pane передаём картинку
    }
}
