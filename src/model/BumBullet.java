package model;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class BumBullet extends Bullet
{
    BumBullet(Pane root, double X, double Y)
    {
        super(root, X, Y);
        this.setFill(Color.RED);
    }

    @Override
    void flight() // Нормальный проверенный код полёта пули не трогать
    {
        int duration = (int)(Math.sqrt(Math.pow((this.getCenterX() - this.X),2) +
                Math.pow((this.getCenterY() - this.Y),2))); // Время рассчитывается в зависимости от расстояния
        KeyValue xValue = new KeyValue(this.centerXProperty(), this.X);
        KeyValue yValue = new KeyValue(this.centerYProperty(), this.Y);
        KeyValue size = new KeyValue(this.radiusProperty(), 25);

        KeyFrame keyFrame = new KeyFrame(Duration.millis(duration), xValue, yValue);
        KeyFrame newKeyFrame = new KeyFrame(Duration.millis(duration), size);

        timeline = new Timeline();
        timeline.getKeyFrames().addAll(keyFrame, newKeyFrame);
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

}
