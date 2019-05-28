package model;

import controller.MapController;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Bullet extends Circle {

    private int damageSize = 50;
    private Player player;
    private double X; // Кординаты места назначения
    private double Y;
    private Pane root;
    private Timeline timeline;
    AnimationTimer timer;

    Bullet(Pane root, double X, double Y)
    {
        super(5, Paint.valueOf("black"));       // Внешние параметры пули
        this.player = Player.Init();
        this.root = root;
        this.setCenterX(player.getTranslateX() + player.getHeight() / 2); // Создание снаряда в центре игрока
        this.setCenterY(player.getTranslateY() + player.getWidth() / 2); // Создание снаряда в центре игрока
        this.X = X;
        this.Y = Y;
        root.getChildren().addAll(this);
        flight();
    }

    private void flight() // Нормальный проверенный код полёта пули не трогать
    {
        int duration = (int)(Math.sqrt(Math.pow((this.getCenterX() - this.X),2) +
                Math.pow((this.getCenterY() - this.Y),2))); // Время рассчитывается в зависимости от расстояния
        KeyValue xValue = new KeyValue(this.centerXProperty(), this.X);
        KeyValue yValue = new KeyValue(this.centerYProperty(), this.Y);

        KeyFrame keyFrame = new KeyFrame(Duration.millis(duration), xValue, yValue);
        timeline = new Timeline();
        timeline.getKeyFrames().addAll(keyFrame);
        timeline.setOnFinished(event -> this.endFlight());
        timeline.play();

        timer = new AnimationTimer() { // Проверяет попала ли пуля в цель
            @Override
            public void handle(long now) {            // Вызывается в каждом кадре анимации
                damage();
            }
        };
        timer.start();
    }

    private void endFlight()  // Завершение анимации полёта пули
    {
        root.getChildren().remove(this);
    }


    public void damage()  // Проверка попадания в цель и соответствующее поведение объекта
    {
        Enemy removeEnemy = null;
        for (Enemy enemy : MapController.enemies) {
            if (this.getBoundsInParent().intersects(enemy.getBoundsInParent())) {
                timeline.stop();
                timer.stop();
                removeEnemy = enemy;
                removeEnemy.getDamage(damageSize);
                if (removeEnemy.getHealth() <= 0)
                {
                    player.setExperience(enemy.experience);
                    System.out.println("Вы получили опыт : " + enemy.experience);
                    removeEnemy.death();
                }
                root.getChildren().remove(this);
                return;
            }
        }
    }
}
