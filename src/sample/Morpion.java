package sample;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class Morpion extends Application {

    private boolean playable = true;
    private boolean turnX = true;
    private Tile[][] til = new Tile[3][3];

    private Pane root = new Pane();

    private Parent createContent() {
        root.setPrefSize(600, 600);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Tile tile = new Tile();
                tile.setTranslateX(j * 200); // position des tuiles
                tile.setTranslateY(i * 200);

                root.getChildren().add(tile);

                til[j][i] = tile;
            }
        }
        return root;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.setTitle("Morpion");
        primaryStage.show();
    }

    private boolean verifVainqueur() {
        if(checkRows() == true || checkColumns() == true || checkDiagonals() == true) {
                playable = false;
            return true;
        }
        else
            return false;
    }


    // checks rows for a win
    public boolean checkRows() {
        int j = 0;
        for(int i = 0;i<3;i++) {
            if(til[i][j].text.getText().equals(til[i][j+1].text.getText()) && til[i][j].text.getText().equals(til[i][j+2].text.getText())
                    && (til[i][j].text.getText() == "X" || til[i][j].text.getText() == "O")){
                return true;
            }
        }
        return false;
    }


    // checks columns for a win
    public boolean checkColumns() {
        int i = 0;
        for(int j = 0;j<3;j++) {
            if( til[i][j].text.getText().equals(til[i+1][j].text.getText()) && til[i][j].text.getText().equals(til[i+2][j].text.getText())
                    && (til[i][j].text.getText() == "X" || til[i][j].text.getText() == "O"))
            {
                return true;
            }
        }
        return false;
    }

    // checks diagonals for a win
    public boolean checkDiagonals() {
        if(til[0][0].text.getText().equals(til[1][1].text.getText()) && til[0][0].text.getText().equals(til[2][2].text.getText())
                && (til[0][0].text.getText() == "X" || til[0][0].text.getText() == "O"))
            return true;
        else if(til[0][2].text.getText().equals(til[1][1].text.getText()) && til[0][2].text.getText().equals(til[2][0].text.getText())
                && (til[0][2].text.getText() == "X" || til[0][2].text.getText() == "O")) return true;

        else return false;
    }

    private class Tile extends StackPane {
        private Text text = new Text();

        public Tile() {
            Rectangle border = new Rectangle(200, 200); // taille tuile
            border.setFill(Color.BLACK);
            border.setStroke(Color.WHITE); // border color

            text.setFont(Font.font(72)); //taille du text
            text.setFill(Color.RED); // couleur du text

            setAlignment(Pos.CENTER); // position
            getChildren().addAll(border, text);

            setOnMouseClicked(event -> {

                if (!playable)
                    return;
                displayVictor();
                if (event.getButton() == MouseButton.PRIMARY) {
                    if (!turnX)
                        return;


                    drawX();
                    turnX = false;

                }
                else if (event.getButton() == MouseButton.SECONDARY) {
                    if (turnX)
                        return;

                    drawO();
                    turnX = true;

                }

            });
        }

        public double getCenterX() {
            return getTranslateX() + 100;
        }

        public double getCenterY() {
            return getTranslateY() + 100;
        }

        public String getValue() {
            return text.getText();
        }

        private void drawX() {
            text.setText("X");
        }

        private void drawO() {
            text.setText("O");
        }
    }

    public void displayVictor() {

        if(verifVainqueur() == true) {
            String vainqueur = "b";
            // reverse the player marks
            // because after we put x and we win, the game changes it to o
            // but x is the winner
            if(turnX == true){
                turnX = false;
                vainqueur = "O";
            }
            else{
                turnX = true;
                vainqueur = "X";
            }

            JOptionPane pane = new JOptionPane();
            int dialogResult = JOptionPane.showConfirmDialog(pane, "Game Over. "+ vainqueur + " wins. Would you like to play again?","Game over.",
                    JOptionPane.YES_NO_OPTION);

            if(dialogResult == JOptionPane.YES_OPTION) resetTheButtons();
            else System.exit(0);
        }

        else if(verifFinis()) {
            JOptionPane pane = new JOptionPane();
            int dialogResult = JOptionPane.showConfirmDialog(pane, "Draw. Play again?","Game over.", JOptionPane.YES_NO_OPTION);
            if(dialogResult == JOptionPane.YES_OPTION)  resetTheButtons();
            else System.exit(0);
        }
    }



    // method used to reset the buttons
    // so you can play again
    private void resetTheButtons() {
        turnX = true;
        for(int i =0;i<3;i++) {
            for(int j =0;j<3;j++) {
                til[i][j].text.setText(null);
            }
        }
        playable = true;
    }

    public boolean verifFinis() {
        boolean full = true;
        for(int i = 0 ; i<3;i++) {
            for(int j = 0 ; j<3;j++) {
                if (til[i][j].text.getText() != "X" && til[i][j].text.getText() != "O") {
                    full = false;
                }
            }
        }
        if(full == true){
            playable = false;
        }
        return full;
    }

    public static void main(String[] args) {
        launch(args);
    }
}