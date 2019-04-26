package sample;

import javafx.scene.image.ImageView;

public class Bullet extends AbstractItem {

    private int damageSize = 10;
    private Player player;
    private double X;
    private double Y;

    public Bullet(ImageView image, Player player, double X, double Y)
    {
        super(image);
        this.player = player;
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
            Main.root.getChildren().remove(this);
            Map.bullets.remove(this);
        }
    }

    public void damage()  // Покачто уничтожает одним выстрелом
    {
        Enemy removeEnemy = null;
        boolean flag = true;
        for (Enemy enemy : Map.enemies) {
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
            Map.enemies.remove(removeEnemy);
            Main.root.getChildren().remove(removeEnemy);
        }
        if (removeEnemy != null)
        {
            Map.bullets.remove(this);
            Main.root.getChildren().remove(this);
        }
    }
}
