package game;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Bullet extends AbstractItem {

    private int damageSize = 10;
    private Player player;
    private double X;
    private double Y;
    private Pane root;

    public Bullet(ImageView image, Player player, Pane root, double X, double Y)
    {
        super(image);
        this.player = player;
        this.root = root;
        this.setTranslateX(player.getTranslateX());
        this.setTranslateY(player.getTranslateY());
        this.X = X;
        this.Y = Y;
    }

    public void flight()
    {
        int len = (int)((this.getTranslateX() - X) * (this.getTranslateX() - X) +
                (this.getTranslateY() - Y) * (this.getTranslateY() - Y));
        int counter = len * 10;
        double lenXadd = (this.getTranslateX() - X) / counter * -1;
        double lenYadd = (this.getTranslateY() - Y) / counter * -1;
        while (counter != 0)
        {
            this.setTranslateX(getTranslateX() + lenXadd);
            this.setTranslateY(getTranslateY() + lenYadd);
            damage();
            counter--;
        }
        if (this.getTranslateX() >= X && this.getTranslateY() >= Y) {
            root.getChildren().remove(this);
            MapControler.bullets.remove(this);
        }
    }

    private void damage()  // Покачто уничтожает одним выстрелом
    {
        Enemy removeEnemy = null;
        boolean flag = true;
        for (Enemy enemy : MapControler.enemies) {
            if (this.getBoundsInParent().intersects(enemy.getBoundsInParent())) {
                removeEnemy = enemy;
                if (removeEnemy.health - damageSize <= 0)
                {
                    removeEnemy.health -= damageSize;
                    player.setExperience(enemy.experience);
                    flag = true;
                    System.out.println("Вы получили опыт :" + enemy.experience);
                }
            }
        }
        if (flag)
        {
            MapControler.enemies.remove(removeEnemy);
            root.getChildren().remove(removeEnemy);
            root.getChildren().remove(this);
            MapControler.bullets.remove(this);
        }
    }
}
