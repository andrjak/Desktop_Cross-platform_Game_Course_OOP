package model;

import javafx.scene.image.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class SpeedElixir extends AbstractElixir{

    Timer timer;

    public SpeedElixir (ImageView image)
    {
        super(image);
    }

    @Override
    void getElixirEffect()
    {
        assert Player.Init() != null;
        Player.Init().speed++;

        timer = new Timer();
        timer.schedule(new Task(), 10000);
    }

    class Task extends TimerTask {

        @Override
        public void run() {
            assert Player.Init() != null;
            Player.Init().speed--;
        }
    }
}
