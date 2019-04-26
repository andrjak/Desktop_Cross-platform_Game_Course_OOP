package sample;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import sample.SpriteAnimation;
import javafx.geometry.Point2D;

public abstract class AbstractEssence extends Pane {
    protected ImageView image;     // Имеют значения по умолчанию кроме image (значения для player)
    protected int count = 3;       // Количество строк в картинке
    protected int columns = 3;     // Количество столбцов в картинке
    protected int offsetX = 0;     // Смещение
    protected int offsetY = 0;     // -//-
    protected int width = 36;      // Размеры вырезаемого прямо угольника
    protected int height = 36;     // -//-
    protected SpriteAnimation animation;  // Хранит анимацию для сущности
    protected int health = 100;    // Уровень здоровья
    protected int level  = 1;      // Уровень сущности
    protected int damage = 10;     // Урон сущности (player будет изменять эту переменную в зависимости от оружия)
    protected int resistance = 0;
    private boolean canJump;       // Возможен ли прыжок
    public Point2D essenceVelocity;

    public AbstractEssence(ImageView image)                                               //Стандартный конструктор
    {
        this.image = image;
        //image.setFitHeight(40);
        //image.setFitWidth(40);
    }

    public AbstractEssence(ImageView image, int count, int columns, int offsetX, int offsetY, int width, int height) //Расширенный конструктор
    {
        this.image = image;
        this.count = count;
        this.columns = columns;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.width = width;
        this.height = height;
        // Возможно длительность анимации тоже стоит сделать параметром !
    }

    public void moveX(int x){                                            // Движение по оси X
        for(int i = 0; i < Math.abs(x); i++) {
            if (x > 0) this.setTranslateX(this.getTranslateX() + 1);
            else this.setTranslateX(this.getTranslateX() - 1);
        }
    }

    public void moveY(int y) {                                           // Движение по оси Y (надо переделать в прыжки)
        for (int i = 0; i < Math.abs(y); i++) {
            if (y > 0) this.setTranslateY(this.getTranslateY() + 1);
            else this.setTranslateY(this.getTranslateY() - 1);
        }
    }

    public void jumpPlayer()
    {
        if (canJump)
        {

        }
    }
}
