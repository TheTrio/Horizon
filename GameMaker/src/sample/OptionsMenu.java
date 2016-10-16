package sample;

import com.sun.corba.se.impl.presentation.rmi.ExceptionHandlerImpl;
import com.sun.media.sound.JARSoundbankReader;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Formatter;

public class OptionsMenu implements EventHandler<ActionEvent> {
    private Stage window;
    private Scene scene;
    private Scene OptionsMenu;
    private Button PlayGame;
    private Button Options_Game;
    private Button Exit_Game;
    private ToggleGroup tg, tg2;
    private RadioButton sound1;
    private RadioButton sound2;
    private RadioButton FullScreen1;
    private RadioButton FullScreen2;
    private boolean bool = false;
    private File f;
    private boolean bool_sound1;
    private boolean bool_sound2;
    private boolean bool_Screen1;
    private boolean bool_Screen2;
    private PrintWriter pr;
    public void MakeWindow(String title){

        window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);

        VBox vb = new VBox();
        VBox vb2 = new VBox();
        BorderPane bb = new BorderPane();
        Label mess = new Label("\tHorizon Version 3 is in beta. Some \n\tfunctions may not work as expected");
        mess.setFont(new Font("Ariel", 30));

        PlayGame = new Button("Play Game");

        PlayGame.setOnAction(this);

        Options_Game = new Button("Options Game");
        Options_Game.setOnAction(this);

        Exit_Game = new Button("Exit Game");
        Exit_Game.setOnAction(this);

        vb.setPadding(new Insets(10,10,10,10));
        vb.setSpacing(20);

        vb.getChildren().addAll(PlayGame, Options_Game, Exit_Game);
        vb.setAlignment(Pos.CENTER);

        vb2.getChildren().add(mess);
        vb2.setAlignment(Pos.CENTER);
        bb.setTop(vb2);
        bb.setCenter(vb);

        scene = new Scene(bb, 644, 342);

        window.setScene(scene);

        window.showAndWait();




    }

    public boolean GiveScreen(){
        return bool_Screen1;
    }

    public boolean GiveSound(){
        return bool_sound1;
    }

    @Override
    public void handle(ActionEvent event) {
        if(event.getSource()==PlayGame){
            PlayGame();
        }else if(event.getSource()==Options_Game){
            DisplayOptions();
        }else if(event.getSource()==Exit_Game){
            ExitGame();
        }
    }

    private void PlayGame() {
        try {
            Runtime.getRuntime().exec("cmd /c start Make.bat");
            bool = true;
            window.close();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error " + e);
        }
    }

    private void DisplayOptions() {

        bool = false;

        Button Save =  new Button("Save Settings");
        Label sound = new Label("Sound Settings");
        Label screen = new Label("Window Setting");
        tg = new ToggleGroup();
        VBox vb = new VBox();
        vb.setSpacing(50);
        vb.setPadding(new Insets(50,50,50,50));
        vb.setAlignment(Pos.CENTER);
        HBox hb = new HBox();
        hb.setSpacing(25);
        hb.setPadding(new Insets(25,25,25,25));

        HBox hb2 = new HBox();
        hb2.setSpacing(20);
        hb2.setPadding(new Insets(20,20,20,20));
        vb.getChildren().addAll(sound);
        sound1 = new RadioButton("Enable Sound");
        sound2 = new RadioButton("Disable Sound");

        sound1.setToggleGroup(tg);
        sound2.setToggleGroup(tg);

        hb.getChildren().addAll(sound1, sound2);

        tg2 = new ToggleGroup();
        FullScreen1 = new RadioButton("Enable FullScreen");
        FullScreen2 = new RadioButton("Disable FullScreen");

        GiveSetting gr = new GiveSetting();
        gr.OpenFile();
        gr.ReadFile();
        gr.CloseFile();
        System.out.println("The Setting 1 is  " + gr.GiveSound());
        System.out.println("The Setting 2 is  " + gr.GiveScreen());




        hb2.getChildren().addAll(FullScreen1, FullScreen2);

        FullScreen2.setToggleGroup(tg2);
        FullScreen1.setToggleGroup(tg2);

        vb.getChildren().addAll(hb);
        vb.getChildren().addAll(screen);
        vb.getChildren().addAll(hb2);
        vb.getChildren().addAll(Save);
        getSettings();


        if(gr.GiveSound().endsWith("On")){
            sound1.setSelected(true);
        }else {
            sound2.setSelected(true);
        }

        if(gr.GiveScreen().endsWith("On")){
            FullScreen1.setSelected(true);
        } else {
            FullScreen2.setSelected(true);
        }
        try {
            pr = new PrintWriter(new FileWriter("Setting.data", false));

        }catch (Exception e){
            System.out.println(e);
        }

        Save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {

                    System.out.println("Sound1 " + bool_sound1);
                    System.out.println("Sound2 " + bool_sound2);

                    System.out.println("Screen1 " + bool_Screen1);
                    System.out.println("Screen2 " + bool_Screen2);

                    if(bool_sound1){

                        pr.println("SoundOn");
                    }else{
                        pr.println("SoundOff");
                    }

                    if(bool_Screen1){
                        JOptionPane.showMessageDialog(null, "This May take some time. Don't close the window that opens up");
                        Runtime.getRuntime().exec("cmd /c  start Image.bat");
                        Thread.sleep(15000);
                        pr.println("ScreenOn");
                    }else {
                    JOptionPane.showMessageDialog(null, "This May take some time. Don't close the window that opens up");
                    Runtime.getRuntime().exec("cmd /c  start Image2.bat");
                    Thread.sleep(10000);
                    pr.println("ScreenOff");
                }





                }catch (Exception e){
                    JOptionPane.showMessageDialog(null, "Error " + e, "error", JOptionPane.ERROR_MESSAGE);
                }finally {
                    pr.close();
                    try{Runtime.getRuntime().exec("cmd /c Copy.bat");
                    }catch (Exception e){

                    }
                }


        window.setScene(scene);
    }
});

        OptionsMenu = new Scene(vb, 400,400);
        window.setScene(OptionsMenu);

    }

    private void getSettings() {
        tg.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
                public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {

                    RadioButton sel = (RadioButton) newValue;

                    if(sel.getText().equalsIgnoreCase(sound1.getText())){
                        bool_sound1 = true;
                        bool_Screen2  = false;
                    }else {
                        System.out.println(sound2.getText());
                        bool_sound2 = true;
                        bool_sound1 = false;
                }


            }
        });

        tg2.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {

                RadioButton sel = (RadioButton) newValue;

                if(sel.getText().equalsIgnoreCase(FullScreen1.getText())){
                    System.out.println(FullScreen1.getText());
                    bool_Screen1 = true;
                    bool_Screen2 = false;
                }else {
                    System.out.println(FullScreen2.getText());
                    bool_Screen2 = true;
                    bool_Screen1 = false;
                }


            }
        });


    }

    private void ExitGame(){
        bool = false;
        window.close();
    }

    public boolean isBool() {
        return bool;
    }
}
