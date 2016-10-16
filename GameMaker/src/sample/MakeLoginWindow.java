package sample;


import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.PickResult;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import javax.swing.text.html.ImageView;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MakeLoginWindow extends Application implements EventHandler, Runnable{
    private Button logout_button;
    private Thread mainThread;
    private GitHub git = new GitHub();
    private Label login_txt;
    boolean exit;
    private Label password_txt;
    private String x = "Data.txt";
    private Button Submit;
    private FeelChange feelChange = new FeelChange();
    private ImageView banner;
    private TextField login_field;
    private PasswordField password_field;
    private Button make_account;
    private Stage window;
    private Scene scene, scene_register;
    private Label name_scene2;
    private Label password_scene2;
    private TextField namefield_scene2;
    private PasswordField passwordField_scene2;
    private PasswordField repeatPassword;
    private Label repeatPassword_label;
    private Button Make;
    private Button Cancel;
    private MakeData md;
    private MakeData mb = new MakeData(x);
    private ReadFile rd = new ReadFile();
    private AppendData ap;
    private File fp = new File(x);
    private Scene abt;
    private Label abt_label;
    private Button abt_button;
    private TextArea message;
    private TextField message_txt;
    private Button Submit_txt;
    private Scene login_screen;
    private String many_names[] = new String[10];
    private Label Online;
    private String temp;
    private ChatData cht;
    private String username;
    private String many_pass[] = new String[10];
    private ReadFile rbb = new ReadFile();
    private ReadChatData rch = new ReadChatData();
    private ReadMessages rg = new ReadMessages();
    private String data[];
    private String normal[] = new String[1];
    private int pj = 0;
    private String fileName;
    private char lastChar = 'A';
    private ListView<String> listView;
    private String chas;
    private Button playgame;
    private int score = 0;
    private TextField highScore;
    private HighScoreCheck hck;
    private WriteHighScore hkk;
    private Stage stageplay;
    @Override

    public void start(Stage primaryStage) throws Exception{

        for(pj=0;pj<normal.length;pj++){
            normal[pj] = "";

        }

        mainThread = Thread.currentThread();
        pj = 0;

        window = primaryStage;

        hck = new HighScoreCheck();

        window.setTitle("Game Engine");

        VBox vb = new VBox();
        BorderPane bb = new BorderPane();
        VBox vb2 = new VBox();
        HBox hb = new HBox();


        make_account = new Button("Register");


        vb2.setPadding(new Insets(20,20,20,20));
        vb.setPadding(new Insets(28,28,28,28));


        window.setResizable(false);

        hb.setPadding(new Insets(20,20,60,140));


        bb.setLeft(vb);
        bb.setBottom(hb);

        scene = new Scene(bb, 400, 200);

        login_txt = new Label("User Name");
        password_txt = new Label("Password");
        login_txt.setFont(new Font(15));
        password_txt.setFont(new Font(15));

        login_field = new TextField();


        password_field = new PasswordField();



        login_field.setPromptText("Type UserName Here");

        password_field.setPromptText("Enter Password Here");

        vb2.getChildren().addAll(login_field,password_field);

        vb2.setSpacing(20);

        hb.setSpacing(20);

        bb.setRight(vb2);

        Submit = new Button("Submit");
        hb.getChildren().addAll(Submit, make_account);
        Submit.setOnAction(this);

        make_account.setOnAction(this);

        Menu menu = new Menu("File");
        Menu help = new Menu("Help");
        MenuItem about = new MenuItem("About");
        MenuItem exit = new MenuItem("Exit");
        MenuItem usermore = new MenuItem("Create A User");
        MenuItem delete = new MenuItem("Delete Previous Records");
        MenuItem page = new MenuItem("Open GitHub Page(Requires Internet Connection)");
        about.setOnAction(e -> {
            setAboutScene(scene);
        });
        MenuItem report = new MenuItem("Report Bugs and Issues");

        exit.setOnAction(e -> {
            System.exit(0);
        });

        usermore.setOnAction(e -> {
            SetScene();
        });

        delete.setOnAction(e -> {
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int result =  JOptionPane.showConfirmDialog (null, "This is not revertible, Are you Sure","Warning",dialogButton);

            if(result==JOptionPane.YES_OPTION){
                try{fp.delete();}catch (Exception efg) {
                    JOptionPane.showMessageDialog(null, "Error " + efg.getStackTrace()  + "\nFile Not Deleted");
                }
            }else {
                e.consume();
            }

        });

        report.setOnAction(e -> {
            git.OpenPage("https://github.com/TheTrio/Horizon/issues");
        });
        page.setOnAction(e -> {
            git.OpenPage("https://github.com/TheTrio/Horizon");
        });
        menu.getItems().addAll(usermore, delete, about,exit);
        help.getItems().addAll(page, report);

        MenuBar mb = new MenuBar();
        mb.getMenus().addAll(menu, help);

        bb.setTop(mb);


        vb.getChildren().addAll(login_txt,password_txt);
        vb.setSpacing(20);

        window.setScene(scene);

        window.show();

        setAction();


    }

    private void setAction() {
        Submit.setOnAction(this);
    }

    public void makeGo(){
        launch();

    }

    public void SetScene(){
        BorderPane bb = new BorderPane();
        VBox vb = new VBox();
        VBox vb2 = new VBox();
        HBox hb = new HBox();

        vb2.setPadding(new Insets(20,20,20,20));
        vb.setPadding(new Insets(28,28,28,28));

        hb.setPadding(new Insets(20,20,60,120));

        name_scene2 = new Label("Name");
        password_scene2 = new Label("Password");
        repeatPassword_label = new Label("Repeat Password");

        //Vbox 1

        vb.getChildren().addAll(name_scene2, password_scene2,repeatPassword_label);
        bb.setLeft(vb);

        //Fields

        namefield_scene2 = new TextField();
        passwordField_scene2 = new PasswordField();

        repeatPassword = new PasswordField();

        namefield_scene2.setPromptText("Name Here");
        passwordField_scene2.setPromptText("Password Here");
        repeatPassword.setPromptText("Enter password Again");

        vb2.getChildren().addAll(namefield_scene2,passwordField_scene2,repeatPassword);

        //Vbox2

        bb.setRight(vb2);

        //Menu

        Menu menu = new Menu("File");
        MenuItem about = new MenuItem("About");
        MenuItem exit = new MenuItem("Exit");
        MenuItem login = new MenuItem("Login");
        MenuItem delete = new MenuItem("Delete Previous Records");

        about.setOnAction(e -> {
            setAboutScene(scene);
        });

        exit.setOnAction(e -> {
            System.exit(0);
        });

        login.setOnAction(e -> {
            window.setScene(scene);
        });

        delete.setOnAction(e -> {
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int result =  JOptionPane.showConfirmDialog (null, "This is not revertible, Are you Sure","Warning",dialogButton);

            if(result==JOptionPane.YES_OPTION){
                try{fp.delete();}catch (Exception efg) {
                    JOptionPane.showMessageDialog(null, "Error " + efg.getStackTrace()  + "\nFile Not Deleted");
                }
            }else {
                e.consume();
            }

        });

        menu.getItems().addAll(login, delete, about,exit);

        MenuBar mb = new MenuBar();
        mb.getMenus().addAll(menu);

        bb.setTop(mb);

        //Buttons


        Make = new Button("Create Account");
        Cancel = new Button("Cancel");

        Cancel.setOnAction(e -> {
                window.setScene(scene);
        });

        Make.setOnAction(e ->{

            if(namefield_scene2.getText().equals("") || passwordField_scene2.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Enter Something");
            }else if(namefield_scene2.getText().length()<8 || passwordField_scene2.getText().length()<8){
                JOptionPane.showMessageDialog(null, "Enter Atleast 8 Characters");
            }else if(!passwordField_scene2.getText().equals(repeatPassword.getText())){
               JOptionPane.showMessageDialog(null, "Passwords Don't Match");
            }else {

                if(fp.exists()==true) {
                    ap = new AppendData();

                    ap.WriteFile(namefield_scene2.getText(), passwordField_scene2.getText());
                    ap.closeFile();
                    window.setScene(scene);
                }else {
                    md = new MakeData(x);
                String s[] = new String [2];

                s[0] = namefield_scene2.getText();
                s[1] = passwordField_scene2.getText();

                md.check_exists(s);


                window.setScene(scene);


                }

            }
        });
        hb.getChildren().addAll(Make, Cancel);

        bb.setBottom(hb);

        vb2.setSpacing(20);

        hb.setSpacing(20);

        vb.setSpacing(20);

        scene_register = new Scene(bb, 400,250);

        window.setScene(scene_register);

    }


    @Override
    public void handle(Event event) {

        if(event.getSource()==Submit){
            username = login_field.getText();
            if(login_field.getText().equals("") || password_field.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Username or password Field Empty");
            }else if(login_field.getText().length()<8 || password_field.getText().length()<8){
                JOptionPane.showMessageDialog(null, "UserName/Password Very Short");
            }else {
                if (mb.onlyCheck() == false) {
                    JOptionPane.showMessageDialog(null, "Register First");

                } else {

                    boolean a = false;

                    rbb.OpenFile(x);
                    rbb.readFiles();


                    String pass[] = rbb.givepasswordfiles();
                    String user[] = rbb.giveuserfiles();

                    String as = login_field.getText();
                    String bs = password_field.getText();
                    int i = 0;

                    for(int yu = 0;yu < user.length;yu++){
                        if(user[yu]==null){
                            user[yu] = "0";
                        }
                    }
                    for(int yu = 0;yu < pass.length;yu++){
                        if(pass[yu]==null){
                            pass[yu] = "0";
                        }
                    }

                    for(int zy=0;zy<10;zy++){
                        if(user[zy].equals(as) && pass[zy].equals(bs)){
                            a = true;
                            break;
                        }else {
                            a = false;
                        }
                    }

                    if(a==true){
                        LoginScreen();
                    }else {
                        JOptionPane.showMessageDialog(null, "UserName and password Don't Match");
                    }


                    
                }
            }

        }else if(event.getSource()==make_account){
            SetScene();
        }

    }

    private void setAboutScene(Scene pre){
        VBox vb = new VBox();
        vb.setPadding(new Insets(20,20,20,20));
        vb.setSpacing(20);

        abt_label = new Label("Game Engine is made with JDK 8 using \nJavaFx By Hartmann College");
        abt_button = new Button("OK");

        abt_button.setOnAction(e -> {
            window.setScene(pre);
        });

        vb.getChildren().addAll(abt_label, abt_button);

        vb.setAlignment(Pos.CENTER);

        abt = new Scene(vb, 300,200);

        window.setScene(abt);

    }

    private void LoginScreen() {
        BorderPane bb = new BorderPane();

        HBox vb = new HBox();
        VBox vb2 = new VBox();
        VBox vb3 = new VBox();

        vb.setSpacing(30);
        vb.setPadding(new Insets(20, 20, 20, 20));

        vb2.setSpacing(15);
        vb2.setPadding(new Insets(20, 20, 20, 20));

        vb3.setSpacing(30);
        vb3.setPadding(new Insets(20, 20, 20, 20));

        Menu menu = new Menu("File");
        MenuItem about = new MenuItem("About");
        MenuItem exit = new MenuItem("Exit");
        MenuItem usermore = new MenuItem("Logout");
        MenuItem delete = new MenuItem("Delete Previous Records");
        menu.getItems().addAll(about, exit,usermore,delete);
        MenuBar mb = new MenuBar();

        about.setOnAction(e -> {
            setAboutScene(login_screen);
        });

        exit.setOnAction(e -> {
            System.exit(0);
        });

        usermore.setOnAction(e -> {
            listView = null;


            window.setScene(scene);
        });

        delete.setOnAction(e -> {
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int result =  JOptionPane.showConfirmDialog (null, "This is not revertible, Are you Sure","Warning",dialogButton);

            if(result==JOptionPane.YES_OPTION){
                try{fp.delete();}catch (Exception efg) {
                    JOptionPane.showMessageDialog(null, "Error " + efg.getStackTrace()  + "\nFile Not Deleted");
                }
            }else {
                e.consume();
            }

        });

        mb.getMenus().addAll(menu);

        //bb.setTop(mb);

        rd.OpenFile(x);
        rd.readFiles();

        Online = new Label("Online");

        vb2.getChildren().addAll(Online);

        Submit_txt = new Button("Submit");

        listView = new ListView<>();
        many_names = rd.giveuserfiles();
        many_pass = rd.givepasswordfiles();

        message_txt = new TextField();
        vb.getChildren().addAll(message_txt);
        message_txt.setMaxWidth(320);
        message_txt.setMinWidth(320);

        bb.setBottom(vb);


        message = new TextArea();

        message.setMaxSize(320, 350);
        message.setEditable(false);


        int c = 1;
        for(String nae : many_names){

            if(nae!=null) {
                if(nae.equals(login_field.getText())){
                    listView.getItems().add("Inbox");
                }else
                    listView.getItems().add(nae);
            }
        }

        vb3.getChildren().addAll(message);

        bb.setLeft(vb3);
        FeelChange feelChange = new FeelChange();

        Thread t = new Thread(this);
        try{t.setDaemon(true);
        }catch (Exception e){

        }
        t.start();



        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if(newValue.equals("Inbox")){
                    newValue = login_field.getText();
                }
                fileName = newValue;
                message.setText("");

                File fix = new File(fileName + ".txt");
                rg = new ReadMessages();
                if(fix.exists()){
                    rg.OpenFile(fileName + ".txt");
                    rg.readFiles();
                    data = rg.giveuserfiles();

                    String a = message.getText();

                    for(String x: data){

                        try{

                        }catch (Exception e){

                        }

                        if(x!=null){

                            message.setText(message.getText()+  x + "\n");


                        }else {

                        }
                    }

                }else {
                    message.setText("");
                }
            }
        });

        listView.setMaxWidth(100);
        playgame = new Button("Play Game");
        highScore = new TextField("0");
        hck.OpenFile("HighScore.txt");
        hck.TakeFiles();
        hck.CloseFile();
        highScore.setText(hck.giveString());

        highScore.setMaxWidth(50);
        highScore.setMinWidth(50);


        hkk = new WriteHighScore();





        highScore.setDisable(true);
        playgame.setOnAction(e -> {


            //Media Player
            String s = System.getProperty("user.dir");
            AlertBox ab = new AlertBox();
            ab.display();
            OptionsMenu o = new OptionsMenu();
            o.MakeWindow("Options");

                if(o.isBool()==true){
                    try {
                        score = Integer.parseInt(JOptionPane.showInputDialog("Enter Your Score"));
                        //System.out.println(score);
                    }catch (Exception err){
                        JOptionPane.showMessageDialog(null, "Enter A Number(Error Code " + 414 + ") ", "Error", JOptionPane.ERROR_MESSAGE);
                    }finally {
                        if(score > 10000){
                            JOptionPane.showMessageDialog(null, "No Cheating" ,"Warning", JOptionPane.WARNING_MESSAGE);
                            score = 0;
                        }else {
                            message_txt.setText(" Scored " + score + " points in Horizon. Can you beat him");
                        }
                    }

                    hkk.SetScore(score, hck.giveFiles());




                    if(WriteHighScore.isTrue ==true){
                        highScore.setText(String.valueOf(score));
                    }


                }








        });


        vb2.getChildren().addAll(listView, playgame, highScore);

        vb.getChildren().addAll(Submit_txt);
        bb.setRight(vb2);

            login_screen = new Scene(bb, 500, 300);

            window.setScene(login_screen);

        Submit_txt.setOnAction(e -> {

            if(message_txt.getText().equals("")) {
                if(fileName.equals(login_field.getText()))
                    JOptionPane.showMessageDialog(null, "Can't Message Yourself");
            }else {
                    if (fileName.equals(login_field.getText()))
                        JOptionPane.showMessageDialog(null, "Can't Message Yourself");
                else {

                        temp = message.getText();

                        message.setText(temp + "\n" + login_field.getText() + ": " + message_txt.getText() + "\t");


                        normal[0] = message_txt.getText();
                        message_txt.setText("");
                        writeSomething();


                        normal[0] = "";
                    }






                }


        });



        }


    private String EnterScore() {

        return "Hello";
    }

    private void writeSomething(){
            cht = new ChatData();
            if(new File(fileName + ".txt").exists()) {
                rch.makeFile(fileName + ".txt");
                rch.WriteFile(normal, login_field.getText());
                rch.closeFile();


            } else {
                cht.makefile(fileName + ".txt");
                cht.addRecords(normal, login_field.getText());
                cht.closeFile();



            }
        }


    @Override
    public void run() {
            try{
                makeChange(login_field.getText());
            } catch (Exception e){
            }

    }

    public void makeChange(String s) throws Exception{
        final Path path = Paths.get("");
        try (final WatchService watchService = FileSystems.getDefault().newWatchService()) {
            final WatchKey watchKey = path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
            while (mainThread.isAlive()) {
                if(mainThread.isAlive()){

            }else {
                    System.out.println("Breaking from While-Loop");
                break;
            }
                final WatchKey wk = watchService.take();
                for (WatchEvent<?> event : wk.pollEvents()) {
                    if(mainThread.isAlive()){
                }else {
                    System.out.println("Breaking from For-Loop");
                    break;


                }

                    
                    //we only register "ENTRY_MODIFY" so the context is always a Path.
                    final Path changed = (Path) event.context();
                    if (changed.endsWith(s + ".txt")) {
                        JOptionPane.showMessageDialog(null, "Hey "+  login_field.getText()+", You Have a new Messsage");

                    }



                // reset the key
                boolean valid = wk.reset();
                if (!valid) {

                }
            }
        }
        }
    }

}

