package view;

import javafx.animation.AnimationTimer;
import javafx.scene.Camera;
import javafx.scene.ParallelCamera;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import controller.Controller;
import controller.MapController;
import javafx.scene.control.Label;
import model.Enemy;
import model.Player;

import java.awt.*;

public class View {

    private static View view;
    //Игровые переменные для настройки всего приложения
    public static Stage primaryStage;
    public static Pane root;
    private MapController map;
    private Controller controller;
    private Player player;
    public static AnimationTimer timer;
    public static Label coin;


    private View(Stage primaryStage)
    {
        coin = new Label("Золото: " + "0");
        coin.setLayoutX(5);
        coin.setLayoutY(5);
        coin.setStyle("-fx-font-weight: bold");
        //coin.setTextFill(Color.RED);
        coin.setFont(new Font("Arial", 16));
        root = new Pane();  // Основной экран
        Player player = Player.Init(new ImageView(new Image(View.class.getResourceAsStream("1.png"))),root);
        MapController map = new MapController(root);
        Controller controller = new Controller(player,root); // Контролер для управления играком MVC паттерн
        View.primaryStage = primaryStage;

        root.setPrefSize(500, 500);
        root.getChildren().addAll(new ImageView(new Image(getClass().getResourceAsStream("back.png")))); // временный фон
        root.getChildren().addAll(coin);
        root.getChildren().addAll(player); // Так как Player наследуется от Pane
        Scene scene = new Scene(root);

        scene.setOnKeyPressed(event->controller.keys.put(event.getCode(), true));
        scene.setOnKeyReleased(event->controller.keys.put(event.getCode(), false));

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {            // Вызывается в каждом кадре анимации
                controller.update();
                for (Enemy enemy: MapController.enemies) {
                    enemy.enemyGo();
                }
                map.GoldGenerator((int)primaryStage.getWidth(),(int)primaryStage.getHeight());
                map.EnemyGenerator((int)primaryStage.getWidth(),(int)primaryStage.getHeight());
                map.ElixirGenerator((int)primaryStage.getWidth(),(int)primaryStage.getHeight());
                //map.BulletFlight();
            }
        };
        timer.start();

        //primaryStage.setFullScreen(true);
        primaryStage.setTitle("MyGame");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void Restart()
    {
        view = null;
        Player.del();
        Init(View.primaryStage);
        MapController.Restart();
    }

    public static View Init(Stage primaryStage)
    {
        if (view == null)
        {
            view = new View(primaryStage);
            return view;
        }
        return view;
    }
}
