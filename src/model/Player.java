package model;

import controller.MapController;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Player extends AbstractEssence
{
    private int experience = 0;  // Опыт игрока при достижении отметки повышает уровень
    private int experienceMax = 100; // Количество опыта который нобходимо получить для повышения уровня (на каждом уровне увеличивается в 2 раза)
    private int score = 0;  // Количество золота
    private Pane root;
    public Player(ImageView image, Pane root)
    {
        super(image);
        this.setTranslateX(250);
        this.setTranslateY(250);
        this.root = root;
        this.setHealth(100);
        this.image.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
        animation = new SpriteAnimation(image, Duration.millis(200),count,columns,offsetX,offsetY,width,height);
        getChildren().addAll(image); // Так как наследуется от Pane передаём картинку
    }

    public void isGoldEat(){   // Метод позволяющий собирать Золото
        Gold removeGold = null;
        for (Gold gold: MapController.gold) {
            if (this.getBoundsInParent().intersects(gold.getBoundsInParent())) {
                removeGold = gold;
                score++;
                System.out.println(score);  // Временный консольный вывод
            }
        }
        MapController.gold.remove(removeGold);
        root.getChildren().remove(removeGold);
    }

    @Override
    public void moveX(int x){                                            // Движение по оси X
        super.moveX(x);
        isGoldEat();
    }
    @Override
    public void moveY(int y) {                                           // Движение по оси Y (надо переделать в прыжки)
        super.moveY(y);
        isGoldEat();
    }

    public void attack(double x, double y)
    {
        Bullet bullet = new Bullet(this, this.root, x, y);
    }

    public void setExperience(int experience)
    {
        if (experience > 0) {
            this.experience += experience;
            if (this.experience >= experienceMax) {
                this.level++;
                this.experience = this.experience - experienceMax;
            }
        }
    }

}
