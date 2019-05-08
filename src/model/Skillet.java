package model;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Skillet extends Enemy
{
    public Skillet(ImageView image)
    {
        super(image);
        damage = 25;
        setHealth(100);
        count = 4;       // Количество строк в картинке
        columns = 3;     // Количество столбцов в картинке
        offsetX = 0;     // Смещение
        offsetY = 0;     // -//-
        width = 30;      // Размеры вырезаемого прямо угольника
        height = 48;     // -//-

        this.image.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
        animation = new SpriteAnimation(image, Duration.millis(200),count,columns,offsetX,offsetY,width,height);
        getChildren().addAll(image); // Так как наследуется от Pane передаём картинку
    }
}
