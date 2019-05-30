package model;

import controller.MapController;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class BumBum extends Bullet {

    public BumBum(Pane root)
    {
        super(root, 0, 0);
        this.setFill(Color.YELLOW);
    }

    @Override
    public void flight() // Нормальный проверенный код полёта пули не трогать
    {
        KeyValue size = new KeyValue(this.radiusProperty(), 75);

        KeyFrame newKeyFrame = new KeyFrame(Duration.millis(500), size);

        timeline = new Timeline();
        timeline.getKeyFrames().addAll(newKeyFrame);
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

    @Override
    void damage()  // Проверка попадания в цель и соответствующее поведение объекта
    {
        Enemy removeEnemy = null;
        for (Enemy enemy : MapController.enemies) {
            if (this.getBoundsInParent().intersects(enemy.getBoundsInParent())) {
                timeline.stop();
                timer.stop();
                removeEnemy = enemy;
                removeEnemy.getDamage(500);
                if (removeEnemy.getHealth() <= 0)
                {
                    assert Player.Init() != null;
                    Player.Init().setExperience(enemy.experience);
                    System.out.println("Вы получили опыт : " + enemy.experience);
                    removeEnemy.death();
                }
                return;
            }
        }
    }
}
