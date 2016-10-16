package sample;

import javafx.animation.PauseTransition;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.File;


public class AlertBox{
    Stage window = new Stage();
    public void display(){


        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Video");

        window.initStyle(StageStyle.UNDECORATED);
        String s = System.getProperty("user.dir");

        //File f = new File(s, "/src/sample/intro.mp4");

        File f = new File(s, "/intro.mp4");
        Media m = new Media(f.toURI().toString());
        MediaPlayer mp = new MediaPlayer(m);
        MediaView mv = new MediaView(mp);

        StackPane root = new StackPane();
        root.getChildren().add(mv);
        window.setScene(new Scene(root, 1366, 768));
        window.setTitle("Video");


        PauseTransition delay = new PauseTransition(Duration.seconds(7));
        delay.setOnFinished( event -> {
            window.close();


        });
        delay.play();

        mp.play();
        window.showAndWait();

    }


}
