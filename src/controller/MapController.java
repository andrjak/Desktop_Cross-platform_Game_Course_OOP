package controller;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.*;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MapController {    // Отвечает за события которые происходят на карте (появление золота, врагов и т.д.)
    public static ArrayList<Gold> gold = new ArrayList<>();
    public static ArrayList<Enemy> enemies = new ArrayList<>();
    public static ArrayList<AbstractElixir> elixirs = new ArrayList<>();
    private Pane root;
    private Player player;
    private int zomcof = 2;
    private int skicof = 498;
    private Timer timer;

    public MapController(Pane root)
    {
        this.root = root;
        this.player = Player.Init();
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                double x = event.getSceneX();
                double y = event.getSceneY();
                player.attack(x,y);
            }
        });

        timer = new Timer();
        timer.schedule(new Task(), 15000, 20000);
    }

    public void EnemyGenerator(int lenX, int lenY)
    {
    double random = Math.floor(Math.random() * 500);
    int x = (int)Math.floor(Math.random()*lenX);
    int y = (int)Math.floor(Math.random()*lenY);
    if (Math.abs(x - player.getCentralX()) < 75 || Math.abs(y - player.getCentralY()) < 75)
    {
        EnemyGenerator(lenX, lenY);
        return;
    }
    if (enemies.size() <= 100) {
        if (random >= skicof) {
            Skillet skillet = new Skillet(new ImageView(new Image(getClass().getResourceAsStream("skeleton.png"))),root);
            skillet.setTranslateX(x);
            skillet.setTranslateY(y);
            enemies.add(skillet);
            root.getChildren().addAll(skillet);
        }
        if (random <= zomcof) {
            Zombie zombie = new Zombie(new ImageView(new Image(getClass().getResourceAsStream("zombie.png"))),root);
            zombie.setTranslateX(x);
            zombie.setTranslateY(y);
            enemies.add(zombie);
            root.getChildren().addAll(zombie);
        }
    }
    }

    public void GoldGenerator(int lenX, int lenY){
        int random = (int)Math.floor(Math.random() * 80);
        int x = (int)Math.floor(Math.random() * lenX);
        int y = (int)Math.floor(Math.random() * lenY);
        if (Math.abs(x - player.getCentralX()) < 75 || Math.abs(y - player.getCentralY()) < 75)
        {
            EnemyGenerator(lenX, lenY);
            return;
        }
        if(random == 5){
            Gold goldItem = new Gold(new ImageView(new Image(getClass().getResourceAsStream("gold.png"))));
            goldItem.setTranslateX(x);
            goldItem.setTranslateY(y);
            gold.add(goldItem);
            root.getChildren().addAll(goldItem);
        }
    }

    public void ElixirGenerator(int lenX, int lenY)
    {
        int random = (int)Math.floor(Math.random() * 1500);
        int x = (int)Math.floor(Math.random() * lenX);
        int y = (int)Math.floor(Math.random() * lenY);
        if (Math.abs(x - player.getCentralX()) < 75 || Math.abs(y - player.getCentralY()) < 75)
        {
            EnemyGenerator(lenX, lenY);
            return;
        }
        if (random == 5){
            BumBulletElixir elixirItem = new BumBulletElixir(new ImageView(new Image(getClass().getResourceAsStream("BumBulletElixirImage.png"))));
            elixirItem.setTranslateX(x);
            elixirItem.setTranslateY(y);
            elixirs.add(elixirItem);
            root.getChildren().addAll(elixirItem);
        }
        if (random == 10)
        {
            SpeedElixir elixirItem = new SpeedElixir(new ImageView(new Image(getClass().getResourceAsStream("SpeedBulletElixirImage.png"))));
            elixirItem.setTranslateX(x);
            elixirItem.setTranslateY(y);
            elixirs.add(elixirItem);
            root.getChildren().addAll(elixirItem);
        }
        if (random == 15)
        {
            BumBumElixir elixirItem = new BumBumElixir(new ImageView(new Image(getClass().getResourceAsStream("BumBumElixirImage.png"))));
            elixirItem.setTranslateX(x);
            elixirItem.setTranslateY(y);
            elixirs.add(elixirItem);
            root.getChildren().addAll(elixirItem);
        }
    }

    public static void Restart()
    {
        gold.clear();
        enemies.clear();
        elixirs.clear();
    }

    class Task extends TimerTask {

        @Override
        public void run() {
            zomcof++;
            skicof--;
        }
    }

}
