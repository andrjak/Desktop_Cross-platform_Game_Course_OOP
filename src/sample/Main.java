package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.paint.Color;

import java.awt.*;

public class Main extends Application {
    //Игровые переменные для настройки всего приложения
    private Player player = new Player(new ImageView(new Image(getClass().getResourceAsStream("1.png"))),root);
    private static Pane root = new Pane();  // Основной экран
    private MapControler map = new MapControler(root, player);
    private Controller controller = new Controller(player,root); // Контролер для управления играком MVC паттерн

    @Override
    public void start(Stage primaryStage) throws Exception{
        root.setPrefSize(500, 500);
        //root.getChildren().addAll(new ImageView(new Image(getClass().getResourceAsStream("backGround.png")))); // временный фон
        root.getChildren().addAll(player); // Так как Player наследуется от Pane
        Scene scene = new Scene(root);

        scene.setOnKeyPressed(event->controller.keys.put(event.getCode(), true));
        scene.setOnKeyReleased(event->controller.keys.put(event.getCode(), false));

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {            // Вызывается в каждом кадре анимации
                controller.update();
                map.GoldGenerator((int)primaryStage.getWidth(),(int)primaryStage.getHeight());
                map.EnemyGenerator((int)primaryStage.getWidth(),(int)primaryStage.getHeight());
                //map.BulletFlight();
            }
        };
        timer.start();

        primaryStage.setFullScreen(true);
        primaryStage.setTitle("MyGame");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
