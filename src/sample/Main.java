package sample;

import IA.MultiLayerPerceptron;
import javafx.application.Application;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.css.Size;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.scene.effect.DropShadow;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Main extends Application {

    private Stage progressStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
            DropShadow shadow = new DropShadow();
            //parametres des boutons
            Background backgroundBoutons = new Background(new BackgroundFill(Color.DARKCYAN, CornerRadii.EMPTY, Insets.EMPTY));
            Font fontBoutons = Font.font("Arial", FontWeight.BOLD, 30);

            TaskService service = new TaskService();
            service.setOnScheduled(e -> progressStage.show());
            service.setOnSucceeded(e -> progressStage.hide());
            ProgressBar progressBar = new ProgressBar();
            progressBar.progressProperty().bind(service.progressProperty());

            progressStage = new Stage();
            progressStage.setScene(new Scene(new StackPane(progressBar), 300, 100));
            progressStage.setAlwaysOnTop(true);
            progressStage.setTitle("Apprentissage en cours..");


            VBox root = new VBox();
            root.setAlignment(Pos.TOP_CENTER); // lightgrey
            root.setBackground(new Background(new BackgroundFill(Color.BLANCHEDALMOND, CornerRadii.EMPTY, Insets.EMPTY)));
            root.setSpacing(10);
            root.setPadding(new Insets(80,20, 10,10));

            //texte titre
            Text text = new Text(400,50,"Tic-Tac-Toe");

            root.setMargin(text, new Insets(0,0,30,0));
            text.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 85));
            text.setFill(Color.BLACK); //BLANCHEDALMOND
            text.setEffect(shadow);
            root.getChildren().add(text);
            //root.getChildren().add(text);

            // Button 1
            Button buttonJouer = new Button("Jouer");

            Font font = Font.font("Verdana", FontWeight.BOLD, 30);
            buttonJouer.setFont(font);
            buttonJouer.setBackground(new Background(new BackgroundFill(Color.DARKCYAN, CornerRadii.EMPTY, Insets.EMPTY)));
            buttonJouer.setStyle("-fx-border-color: black; -fx-border-width: 3px;");
            buttonJouer.setPrefSize(400, 20);
            root.getChildren().add(buttonJouer);

            // HBOX modes: vs ORDI ET vs JOUEUR
            HBox modes = new HBox();
            modes.setAlignment(Pos.TOP_CENTER);
            Font fontModes = Font.font("Arial", FontWeight.BOLD, 20);

            // Button VS IA
            Button vsIa = new Button("Contre IA");
            vsIa.setFont(fontModes);
            vsIa.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
            vsIa.setStyle("-fx-border-color: black; -fx-border-width: 3px;");
            vsIa.setPrefSize(200, 100);
            modes.getChildren().add(vsIa);
            vsIa.addEventHandler(MouseEvent.MOUSE_ENTERED,
                    new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent e) {
                                    vsIa.setEffect(shadow);
                                    vsIa.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
                            }
                    });

            vsIa.addEventHandler(MouseEvent.MOUSE_EXITED,
                    new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent e) {
                                    vsIa.setEffect(null);
                                    vsIa.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
                            }
                    });

        vsIa.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        vsIa.setEffect(null);
                        vsIa.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
                        /*Service<String> service = new Service<String>(){

                            @Override
                            protected Task<String> createTask() {
                                return new Task<String>() {
                                    @Override
                                    protected String call() throws Exception {
                                        System.out.println("Apprentisage en cours...");
                                        Thread.sleep(1000);
                                        System.out.println("Fini!");
                                        return null;
                                    }

                                };
                            }
                        };

                         */
                        service.restart();
                    }
                });


            // Button VS PLAYER
            Button vsPlayer = new Button("Contre Joueur");
            vsPlayer.setFont(fontModes);
            vsPlayer.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
            vsPlayer.setStyle("-fx-border-color: black; -fx-border-width: 3px;");
            vsPlayer.setPrefSize(200, 100);
            modes.getChildren().add(vsPlayer);

            vsPlayer.addEventHandler(MouseEvent.MOUSE_ENTERED,
                    new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent e) {
                                    vsPlayer.setEffect(shadow);
                                    vsPlayer.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
                            }
                    });

            vsPlayer.addEventHandler(MouseEvent.MOUSE_EXITED,
                    new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent e) {
                                    vsPlayer.setEffect(null);
                                    vsPlayer.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
                            }
                    });


            root.setMargin(modes, new Insets((-10),0,0,0));
            root.getChildren().add(modes);
            ToggleGroup group = new ToggleGroup();
            RadioButton c1 = new RadioButton("Facile");
            RadioButton c2 = new RadioButton("Difficile");
            c1.setToggleGroup(group);
            c2.setToggleGroup(group);
            c1.setSelected(true);

            HBox vbButtons = new HBox();
            vbButtons.setAlignment(Pos.TOP_CENTER);
            vbButtons.setSpacing(50);
            vbButtons.setPadding(new Insets(0, 20, 10, 20));
            vbButtons.getChildren().addAll(c1,c2);
            root.getChildren().add(vbButtons);

            // Button 2
            Button buttonRules = new Button("Règles du Jeu");

            buttonRules.setFont(fontBoutons);
            buttonRules.setBackground(backgroundBoutons);
            buttonRules.setStyle("-fx-border-color: black; -fx-border-width: 3px;");
            buttonRules.setPrefSize(400, 100);
            root.getChildren().add(buttonRules);

            buttonRules.addEventHandler(MouseEvent.MOUSE_ENTERED,
                    new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent e) {
                                    buttonRules.setEffect(shadow);
                                    buttonRules.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
                            }
                    });

            buttonRules.addEventHandler(MouseEvent.MOUSE_EXITED,
                    new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent e) {
                                    buttonRules.setEffect(null);
                                    buttonRules.setBackground(backgroundBoutons);
                            }
                    });

            // Button 3
            Button buttonSettings = new Button("Réglages");

            buttonSettings.setFont(fontBoutons);
            buttonSettings.setBackground(backgroundBoutons);
            buttonSettings.hoverProperty();
            buttonSettings.setStyle("-fx-border-color: black; -fx-border-width: 3px;");
            buttonSettings.setPrefSize(400, 100);
            root.getChildren().add(buttonSettings);

            buttonSettings.addEventHandler(MouseEvent.MOUSE_ENTERED,
                    new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent e) {
                                    buttonSettings.setEffect(shadow);
                                    buttonSettings.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
                            }
                    });

            buttonSettings.addEventHandler(MouseEvent.MOUSE_EXITED,
                    new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent e) {
                                    buttonSettings.setEffect(null);
                                    buttonSettings.setBackground(backgroundBoutons);
                            }
                    });

            HBox parametres = new HBox();
            parametres.setSpacing(10);
            parametres.setPadding(new Insets(15,20, 10,10));
            Text t = new Text("Facile:");
            Text t1 = new Text("Difficile:");
            Label label1 = new Label("h:");
            Label label2 = new Label("Learning Rate:");
            Label label3 = new Label("l:");

            TextField h = new TextField();
            TextField learningRate = new TextField();
            TextField l = new TextField();
            h.setMaxWidth(80);
            learningRate.setMaxWidth(80);
            l.setMaxWidth(80);
            parametres.getChildren().addAll(label1, h, label2, learningRate, label3, l);


            buttonSettings.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {

                        StackPane reglageLayout = new StackPane();

                        reglageLayout.getChildren().add(parametres);

                        Scene reglageScene = new Scene(reglageLayout, 500, 200);
                        reglageLayout.getChildren().add(t);
                        reglageLayout.setAlignment(t, Pos.TOP_CENTER);
                        // New window (Stage)
                        Stage newWindow = new Stage();
                        newWindow.setTitle("Reglage");
                        newWindow.setScene(reglageScene);

                        // Set position of second window, related to primary window.
                        newWindow.setX(primaryStage.getX() + 200);
                        newWindow.setY(primaryStage.getY() + 100);

                        newWindow.show();
                    }
                });

            // Button 4
            Button buttonExit = new Button("Quitter");

            buttonExit.setFont(fontBoutons);
            buttonExit.setBackground(backgroundBoutons);
            buttonExit.setStyle("-fx-border-color: black; -fx-border-width: 3px;");
            buttonExit.setPrefSize(400, 100);
            root.getChildren().add(buttonExit);

            buttonExit.addEventHandler(MouseEvent.MOUSE_ENTERED,
                    new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent e) {
                                    buttonExit.setEffect(shadow);
                                    buttonExit.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
                            }
                    });

            buttonExit.addEventHandler(MouseEvent.MOUSE_EXITED,
                    new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent e) {
                                    buttonExit.setEffect(null);
                                    buttonExit.setBackground(backgroundBoutons);
                                    //System.exit(0);
                            }
                    });

            buttonExit.addEventHandler(MouseEvent.MOUSE_CLICKED,
                    new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent e) {
                                    System.exit(0);
                            }
                    });

            Scene scene = new Scene(root, 800, 800);

            primaryStage.setTitle("Tic-Tac-Toe");
            primaryStage.setScene(scene);
            primaryStage.show();


    }

    private class TaskService extends Service<Void> {

        @Override
        protected Task<Void> createTask() {
            Task<Void> task = new Task<Void>() {

                @Override
                protected Void call() throws Exception {

                    for (int p = 0; p < 100; p++) {
                        Thread.sleep(40);
                        updateProgress(p, 100);
                    }
                    return null;
                }
            };
            return task;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

       /*Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Morpion");

        StackPane menu = new StackPane();
        Label jouer = new Label("Jouer");
        Rectangle rectangle = new Rectangle(300,100, Color.GREY);
        menu.getChildren().addAll(rectangle,jouer);

        Scene scene = new Scene(menu, 800, 800);
        primaryStage.setScene(scene);
        primaryStage.show();*/