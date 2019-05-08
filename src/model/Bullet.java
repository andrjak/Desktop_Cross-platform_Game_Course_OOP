package model;

import controller.MapControler;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Bullet extends Circle {

    private int damageSize = 100;
    private Player player;
    private double X; // Кординаты места назначения
    private double Y;
    private Pane root;
    private Timeline timeline;

    public Bullet(Player player, Pane root, double X, double Y)
    {
        super(5, Paint.valueOf("black"));
        this.player = player;
        this.root = root;
        this.setCenterX(player.getTranslateX());
        this.setCenterY(player.getTranslateY());
        this.X = X;
        this.Y = Y;
        flight();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {            // Вызывается в каждом кадре анимации
                damage();
            }
        };
        timer.start();
    }

    private void flight()
    {
        int duration = (int)(Math.sqrt(Math.pow(Math.abs(this.getTranslateX() - this.X),2) +
                Math.pow(Math.abs(this.getTranslateY() - this.Y),2))); // Время рассчитывается в зависимости от расстояния
        KeyValue xValue = new KeyValue(this.centerXProperty(), this.X);
        KeyValue yValue = new KeyValue(this.centerYProperty(), this.Y);

        KeyFrame keyFrame = new KeyFrame(Duration.millis(duration), xValue, yValue);
        timeline = new Timeline();
        timeline.getKeyFrames().addAll(keyFrame);
        timeline.setOnFinished(event -> this.endFlight());
        timeline.play();
    }

    private void endFlight()
    {
        MapControler.bullets.remove(this);
        root.getChildren().remove(this);
    }


    private void damage()  // Покачто уничтожает одним выстрелом
    {
        Enemy removeEnemy = null;
        boolean flag = false;
        for (Enemy enemy : MapControler.enemies) {
            if (this.getBoundsInParent().intersects(enemy.getBoundsInParent())) {
                removeEnemy = enemy;
                timeline.stop();
                if (removeEnemy.health - damageSize <= 0)
                {
                    removeEnemy.health -= damageSize;
                    player.setExperience(enemy.experience);
                    flag = true;
                    System.out.println("Вы получили опыт :" + enemy.experience);
                }
                root.getChildren().remove(this);
                MapControler.bullets.remove(this);
            }
        }
        if (flag)
        {
            MapControler.enemies.remove(removeEnemy);
            root.getChildren().remove(removeEnemy);
        }
    }
}
