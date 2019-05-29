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
    int score = 0;  // Количество золота
    private int live = 1;
    private Pane root;
    private static Player player;

    private Player(ImageView image, Pane root) // Сокрытый конструктор (вызывается через инициализатор)
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

    public static Player Init(ImageView image, Pane root) // Инициализатор начальный
    {
        if (player == null)
        {
            player = new Player(image, root);
            return player;
        }
        return player;
    }
    public static Player Init() // Для получения уже существующего объекта
    {
        if (player != null)
        {
            return player;
        }
        else return null;
    }

    public static void del()
    {
        player = null;
    }

    void getDamage(int damage) // Полученный урон
    {
        this.setHealth(this.getHealth() - damage);
    }

    private void isGoldEat(){   // Метод позволяющий собирать Золото
        Gold removeGold = null;
        for (Gold gold: MapController.gold) {
            if (this.getBoundsInParent().intersects(gold.getBoundsInParent())) {
                removeGold = gold;
                score++;
                System.out.println(score);  // Временный консольный вывод
                MapController.gold.remove(removeGold);
                root.getChildren().remove(removeGold);
                return;
            }
        }
    }

    private boolean isPlayerStop() // Останавливатся когда врезался в врага
    {
        for (Enemy enemy: MapController.enemies)
        {
            if (this.getBoundsInParent().intersects(enemy.getBoundsInParent()))
            {
                return false;
            }
        }
        return true;
    }


    @Override
    public void moveX(int x){                                            // Движение по оси X
        if (isPlayerStop())
        {
            super.moveX(x);
            isGoldEat();
        }
        else super.moveX(-x);
    }
    @Override
    public void moveY(int y) {                                           // Движение по оси Y
        if (isPlayerStop())
        {
            super.moveY(y);
            isGoldEat();
        }
        else super.moveY(-y);
    }

    public void attack(double x, double y)
    {
        Bullet bullet = new Bullet(this.root, x, y);
    }

    void setExperience(int experience)
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
