package model;

import javafx.scene.image.ImageView;

public abstract class AbstractElixir extends AbstractItem {

    AbstractElixir(ImageView image)
    {
        super(image);
    }

    void getElixirEffect(){}
}
