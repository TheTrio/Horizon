package sample;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.File;

public class MyMediaPlayer extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        String s = System.getProperty("C:/Class");
        File f = new File(s, "/media/video.mp4");
        Media m = new Media(f.toURI().toString());
        MediaPlayer mp = new MediaPlayer(m);
        MediaView mv = new MediaView(mp);

        StackPane root = new StackPane();
        root.getChildren().add(mv);
        stage.setScene(new Scene(root, 400, 400));
        stage.setTitle("Video");
        stage.show();
        mp.play();

    }


    public static void Go(){
        launch();
    }

}