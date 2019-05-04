package model;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class AbstractItem extends Pane {    // Класс для различных предметов расположенных на карте(Картинка)
    protected ImageView image;       // Просто картинка которая будет являтся пердставлением объекта

    public AbstractItem(ImageView image)
    {
        this.image = image;
        getChildren().addAll(image);
    }
}
