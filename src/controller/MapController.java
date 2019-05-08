package controller;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.*;

import java.util.ArrayList;

public class MapController {    // Отвечает за события которые происходят на карте (появление золота, врагов и т.д.)
    public static ArrayList<Gold> gold = new ArrayList<>();
    public static ArrayList<Enemy> enemies = new ArrayList<>();
    private Pane root;
    private Player player;

    public MapController(Pane root, Player player)
    {
        this.root = root;
        this.player = player;
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                double x = event.getSceneX();
                double y = event.getSceneY();
                player.attack(x,y);
            }
        });
    }

    public void EnemyGenerator(int lenX, int lenY)
    {
    int random = (int)Math.floor(Math.random()*100);
    int x = (int)Math.floor(Math.random()*lenX);
    int y = (int)Math.floor(Math.random()*lenY);
    if (enemies.size() <= 100) {
        if (random == 10) {
            Skillet skillet = new Skillet(new ImageView(new Image(getClass().getResourceAsStream("skeleton.png"))));
            skillet.setTranslateX(x);
            skillet.setTranslateY(y);
            enemies.add(skillet);
            root.getChildren().addAll(skillet);
        }
        if (random == 5) {
            Zombie zombie = new Zombie(new ImageView(new Image(getClass().getResourceAsStream("zombie.png"))));
            zombie.setTranslateX(x);
            zombie.setTranslateY(y);
            enemies.add(zombie);
            root.getChildren().addAll(zombie);
        }
    }
    }

    public void GoldGenerator(int lenX, int lenY){
        int random = (int)Math.floor(Math.random()*100);
        int x = (int)Math.floor(Math.random()*lenX);
        int y = (int)Math.floor(Math.random()*lenY);
        if(random == 5){
            Gold goldItem = new Gold(new ImageView(new Image(getClass().getResourceAsStream("gold.png"))));
            goldItem.setTranslateX(x);
            goldItem.setTranslateY(y);
            gold.add(goldItem);
            root.getChildren().addAll(goldItem);
        }
    }
}
