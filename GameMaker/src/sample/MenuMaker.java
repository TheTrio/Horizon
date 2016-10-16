package sample;


import javafx.application.Application;
import javafx.scene.control.*;
import javafx.scene.*;
import javafx.stage.Stage;

public class MenuMaker extends Application{

    private Stage w;

    public MenuMaker(Stage window){
        w =window;
        launch();
    }

    public void start(Stage we){
        w = we;
        Menu menu = new Menu("File");
        menu.getItems().add(new MenuItem("Item1"));
        menu.getItems().add(new MenuItem("Item2"))
        ;menu.getItems().add(new MenuItem("Item3"));

        MenuBar mb = new MenuBar();
        mb.getMenus().addAll(menu);




    }
}
