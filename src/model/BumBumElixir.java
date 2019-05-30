package model;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import view.View;

public class BumBumElixir extends AbstractElixir{

    public BumBumElixir(ImageView image)
    {
        super(image);
    }


    @Override
    void getElixirEffect()
    {
        assert Player.Init() != null;
        Player.Init().bumBum = true;
        Label label = new Label("\u1F4A");
        label.setLayoutX(100);
        label.setLayoutY(5);
        label.setStyle("-fx-font-weight: bold");
        label.setFont(new Font("Arial", 16));
        Player.Init().bum = label;
        View.root.getChildren().addAll(label);
    }
}
