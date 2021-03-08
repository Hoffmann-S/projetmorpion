package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        /*Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Morpion");

        StackPane menu = new StackPane();
        Label jouer = new Label("Jouer");
        Rectangle rectangle = new Rectangle(300,100, Color.GREY);
        menu.getChildren().addAll(rectangle,jouer);

        Scene scene = new Scene(menu, 800, 800);
        primaryStage.setScene(scene);
        primaryStage.show();*/


            VBox root = new VBox();
            root.setAlignment(Pos.CENTER);

            root.setSpacing(10);
            root.setPadding(new Insets(15,20, 10,10));

            //texte titre
            Text text = new Text("Tic-Tac-Toe");
            text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 50));
            root.getChildren().add(text);

            //root.getChildren().add(text);

            // Button 1
            Button button1 = new Button("Jouer");
            button1.setPrefSize(250, 100);
            root.getChildren().add(button1);


            // Button 2
            Button button2 = new Button("Règles du Jeu");
            button2.setPrefSize(250, 100);
            root.getChildren().add(button2);

            // Button 3
            Button button3 = new Button("Réglages");
            button3.setPrefSize(250, 100);
            root.getChildren().add(button3);


            Scene scene = new Scene(root, 800, 800);

            primaryStage.setTitle("Tic-Tac-Toe");

            primaryStage.setScene(scene);
            primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
