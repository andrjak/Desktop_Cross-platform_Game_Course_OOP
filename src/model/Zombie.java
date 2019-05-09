package model;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Zombie extends Enemy
{
    public Zombie(ImageView image, Pane root)
    {
        super(image, root);
        damage = 10;
        setHealth(200);

        count = 4;       // Количество строк в картинке
        columns = 3;     // Количество столбцов в картинке
        offsetX = 0;     // Смещение
        offsetY = 0;     // -//-
        width = 32;      // Размеры вырезаемого прямо угольника
        height = 41;     // -//-
        experience = 15;

        this.image.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
        animation = new SpriteAnimation(image, Duration.millis(200),count,columns,offsetX,offsetY,width,height);
        getChildren().addAll(image); // Так как наследуется от Pane передаём картинку
    }
}
