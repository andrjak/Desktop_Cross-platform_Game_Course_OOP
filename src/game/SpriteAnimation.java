package game;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class SpriteAnimation extends Transition {
    private ImageView image;  // Картинка
    private int count;    // Количество строк
    private int columns;  // Количество столбцов
    private int offsetX;  // Смещение по картинке
    private int offsetY;  // -//-
    private int width;    // Размеры картинки
    private int height;   // -//-

    // Конструктор устанавливает переменные и продолжительность анимации
    public SpriteAnimation(ImageView image, Duration duration, int count, int columns, int offsetX,
                           int offsetY, int width, int height) {
        this.image = image;
        this.count = count;
        this.columns = columns;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.width = width;
        this.height = height;
        setCycleDuration(duration);
        setCycleCount(Animation.INDEFINITE); // -1 Бескончная анимация
        setInterpolator(Interpolator.LINEAR); // Линейная анимация
        this.image.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
    }
    // Смещение (используется для перемещения по спрайтам)
    public void setOffsetX(int x){
        this.offsetX = x;
    }
    public void setOffsetY(int y){
        this.offsetY = y;
    }
    @Override
    protected void interpolate(double num) {
        final int index = Math.min((int) Math.floor(num * count), count - 1);
        final int x = (index % columns) * width + offsetX;
        final int y = (index / columns) * height + offsetY;
        image.setViewport(new Rectangle2D(x, y, width, height));
    }
}
