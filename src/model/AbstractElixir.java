package model;

import javafx.scene.image.ImageView;

public abstract class AbstractElixir extends AbstractItem {

    public AbstractElixir(ImageView image)
    {
        super(image);
    }

    public void getElixirEffect(){}
}
