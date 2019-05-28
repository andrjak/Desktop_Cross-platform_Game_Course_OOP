package view;

import javafx.animation.AnimationTimer;
import javafx.scene.Camera;
import javafx.scene.ParallelCamera;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import controller.Controller;
import controller.MapController;
import model.Enemy;
import model.Player;

public class View {

    private static View view;
    //Игровые переменные для настройки всего приложения
    public static Stage primaryStage;
    private Pane root;
    private MapController map;
    private Controller controller;
    private Player player;
    private Camera camera;
    public static AnimationTimer timer;


    private View(Stage primaryStage)
    {
        root = new Pane();  // Основной экран
        Player player = Player.Init(new ImageView(new Image(View.class.getResourceAsStream("1.png"))),root);
        MapController map = new MapController(root);
        Controller controller = new Controller(player,root); // Контролер для управления играком MVC паттерн
        View.primaryStage = primaryStage;

        root.setPrefSize(500, 500);
        //root.getChildren().addAll(new ImageView(new Image(getClass().getResourceAsStream("backGround.png")))); // временный фон
        root.getChildren().addAll(player); // Так как Player наследуется от Pane
        Scene scene = new Scene(root);
        //
        camera = new ParallelCamera();
        scene.setCamera(camera);
        //
        scene.setOnKeyPressed(event->controller.keys.put(event.getCode(), true));
        scene.setOnKeyReleased(event->controller.keys.put(event.getCode(), false));

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {            // Вызывается в каждом кадре анимации
                controller.update();
                //camera.setTranslateX(player.getCentralX() - primaryStage.getWidth() / 2);
                //camera.setTranslateY(player.getCentralY() - primaryStage.getHeight() / 2);
                for (Enemy enemy: MapController.enemies) {
                    enemy.enemyGo();
                }
                map.GoldGenerator((int)primaryStage.getWidth(),(int)primaryStage.getHeight());
                map.EnemyGenerator((int)primaryStage.getWidth(),(int)primaryStage.getHeight());
                //map.BulletFlight();
            }
        };
        timer.start();

        //primaryStage.setFullScreen(true);
        primaryStage.setTitle("MyGame");
        primaryStage.setScene(scene);
        primaryStage.show();
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
