package model;

import javafx.application.Application;
import javafx.stage.Stage;
import view.View;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        View view = View.Init(primaryStage);
    }


    public static void main(String[] args) {
        launch(args);
    }

}
