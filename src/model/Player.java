package model;

import controller.MapController;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import view.View;

public class Player extends AbstractEssence
{
    private int experience = 0;  // Опыт игрока при достижении отметки повышает уровень
    private int experienceMax = 100; // Количество опыта который нобходимо получить для повышения уровня (на каждом уровне увеличивается в 2 раза)
    int score = 0;  // Количество золота
    private int live = 1;
    private Pane root;
    private static Player player;
    int bulletT = 0;
    int speed = 1;
    public boolean bumBum = false;
    public Label bum;

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
                View.coin.setText("Золото: " + score);
                System.out.println(score);  // Временный консольный вывод
                MapController.gold.remove(removeGold);
                root.getChildren().remove(removeGold);
                return;
            }
        }
    }

    private void isElixirEat()
    {
        for (AbstractElixir elixir: MapController.elixirs)
        {
            if (this.getBoundsInParent().intersects(elixir.getBoundsInParent()))
            {
                elixir.getElixirEffect();
                MapController.elixirs.remove(elixir);
                root.getChildren().remove(elixir);
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
        x *= speed;
        if (player.getTranslateX() <= 0 && x < 0)
            return;
        if (player.getTranslateX() + player.getWidth() >= View.primaryStage.getScene().getWidth() && x > 0)
            return;
        super.moveX(x);
        isGoldEat();
        isElixirEat();
    }

    @Override
    public void moveY(int y) {                                           // Движение по оси Y
        y *= speed;
        if (player.getTranslateY() <= 0 && y < 0)
            return;
        if (player.getTranslateY() + player.getHeight() >= View.primaryStage.getScene().getHeight() && y > 0)
            return;
        super.moveY(y);
        isGoldEat();
        isElixirEat();
    }

    public void attack(double x, double y)
    {
        if (bulletT == 0)
            new Bullet(this.root, x, y);
        else if (bulletT == 1)
            new BumBullet(this.root, x, y);
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
