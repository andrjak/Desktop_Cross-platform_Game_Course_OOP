package model;

import controller.MapController;
import javafx.scene.image.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class BumBulletElixir extends AbstractElixir {

    private Timer timer;

    public BumBulletElixir(ImageView image)
    {
        super(image);
    }

    @Override
    void getElixirEffect()
    {
        assert Player.Init() != null;
        Player.Init().bulletT = 1;

        timer = new Timer();
        timer.schedule(new Task(), 25000);
    }

    class Task extends TimerTask {

        @Override
        public void run() {
            assert Player.Init() != null;
            Player.Init().bulletT = 0;
        }
    }
}
