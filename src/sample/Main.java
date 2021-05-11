package sample;

import javafx.application.Application;
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
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.effect.DropShadow;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
            DropShadow shadow = new DropShadow();
            //parametres des boutons
            Background backgroundBoutons = new Background(new BackgroundFill(Color.DARKCYAN, CornerRadii.EMPTY, Insets.EMPTY));
            Font fontBoutons = Font.font("Arial", FontWeight.BOLD, 30);


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