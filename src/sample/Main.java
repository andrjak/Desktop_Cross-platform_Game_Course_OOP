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
        View view = new View(primaryStage, root, map, controller, player);
    }


    public static void main(String[] args) {
        launch(args);
    }

}
