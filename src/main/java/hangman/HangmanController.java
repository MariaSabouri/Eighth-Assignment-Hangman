package hangman;

import com.fasterxml.jackson.databind.JsonNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.ResourceBundle;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HangmanController implements Initializable  {
    @FXML
    private Button BE;

    @FXML
    private Button bA;

    @FXML
    private Button bB;

    @FXML
    private Button bC;

    @FXML
    private Button bD;


    @FXML
    private Button bF;

    @FXML
    private Button bG;

    @FXML
    private Button bH;

    @FXML
    private Button bI;

    @FXML
    private Button bJ;

    @FXML
    private Button bK;

    @FXML
    private Button bL;

    @FXML
    private Button bM;
    @FXML
    private Button bN;

    @FXML
    private Button bO;

    @FXML
    private Button bP;

    @FXML
    private Button bQ;

    @FXML
    private Button bR;

    @FXML
    private Button bS;

    @FXML
    private Button bT;

    @FXML
    private Button bU;

    @FXML
    private Button bV;

    @FXML
    private Button bW;

    @FXML
    private Button bX;

    @FXML
    private Button bY;
    @FXML
    private Button bZ;

    @FXML
    private Button bSpace;
    @FXML
    private Button btNext;

    @FXML
    private Text hangmanView;
    @FXML
    private Label lableWord;
    @FXML
    private VBox KeyboardVbox;
    public static char charchoosen;
    static long finishtime=0;
    static long starttime=0;
    public static String secreteWord=HangmanHandelClass.playingNewWord();
    private static int secreteWordLength=HangmanHandelClass.UniqueCharsSet(HangmanHandelClass.secretword).size();
    static ArrayList<String> hangmanLives=new ArrayList<>(Arrays.asList(
            """
                    ===============
                    |             |
                    |
                    |
                    |
                    |
                    |
                    --------------------
                    """
            ,
            """
                    ===============
                    |             |
                    |             O
                    |
                    |
                    |
                    |
                    --------------------
                    """
            ,
            """
                    ===============
                    |             |
                    |             O
                    |             |
                    |
                    |
                    |
                    --------------------
                    """
            ,
            """
                    ===============
                    |             |
                    |             O
                    |            /|
                    |
                    |
                    |
                    --------------------
                    """
            ,
            """
                    ===============
                    |             |
                    |             O
                    |            /|\\
                    |
                    |
                    |
                    --------------------
                    """
            ,
            """
                    ===============
                    |             |
                    |             O
                    |            /|\\
                    |            /
                    |
                    |
                    --------------------
                    """
            ,
            """
                    ===============
                    |             |
                    |             O
                    |            /|\\
                    |            / \\
                    |
                    |
                    --------------------
                    """

    ));
    public static Boolean wordHandled=false;
    public static void WordHandled(boolean b) {
        wordHandled=b;
    }


    @FXML
    void CreatingAlphabetKeyBoard(ActionEvent event) {


        Button button=(Button) event.getSource();

        button.setDisable(true);
        charchoosen=button.getText().charAt(0);
        System.out.println(charchoosen);
        lableWord.setText(HangmanHandelClass.SearchingForChar(charchoosen));
        lableWord.setTextFill(Color.BLACK);
        if (wordHandled==true){
            secreteWordLength--;
        }else {
            try {
                changeHangmanView(counterForHangmanView);
            }catch (Exception e){

                counterForHangmanView=0;
                finishtime=System.currentTimeMillis();
                timer();
                try {
                    DatabaseManager.Gameinfo(HangmanHandelClass.Username,HangmanHandelClass.secretword,HangmanHandelClass.wrongGuess,HangmanHandelClass.time,false);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                KeyboardVbox.setDisable(true);
                lableWord.setText("Game Over!");

                lableWord.setTextFill(Color.RED);
                btNext.setDisable(true);

            }
        }
        if (secreteWordLength<1){
            finishtime=System.currentTimeMillis();
            timer();
            try {
                DatabaseManager.Gameinfo(HangmanHandelClass.Username,HangmanHandelClass.secretword,HangmanHandelClass.wrongGuess,HangmanHandelClass.time,true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            lableWord.setTextFill(Color.GREEN);
            btNext.setDisable(false);
            btNext.setVisible(true);
            KeyboardVbox.setDisable(true);
        }
    }
    @FXML
    void KeyNextBotton(ActionEvent event) {
        try {
            finishtime = System.currentTimeMillis();
            timer();
            btNext.setDisable(true);
            btNext.setVisible(false);
            KeyboardVbox.setDisable(false);
            changeActionOfBottons();
            secreteWord=HangmanHandelClass.playingNewWord();
            secreteWordLength=HangmanHandelClass.UniqueCharsSet(HangmanHandelClass.secretword).size();
//                System.out.println(HangmanHandelClass.UniqueCharsSet(HangmanHandelClass.secretword));
            lableWord.setText(secreteWord);
            lableWord.setTextFill(Color.BLACK);

        }catch (Exception e){
            finishtime = System.currentTimeMillis();
            timer();
            lableWord.setText("Game finished");

            KeyboardVbox.setDisable(true);
            btNext.setDisable(true);
        }
    }

    private static int counterForHangmanView=0;
    public static int changeManSituationView(){
        counterForHangmanView++;

        if (counterForHangmanView==hangmanLives.size()-1){
            return 7;
        }else {
            return counterForHangmanView;
        }
    }

    public void changeHangmanView(int i){
        hangmanView.setText(hangmanLives.get(i));
    }



    private void changeActionOfBottons(){
        for (javafx.scene.Node node : KeyboardVbox.getChildren()) {
            if (node instanceof HBox) {
                for (javafx.scene.Node node2 : ((HBox) node).getChildren()){
                    if (node2 instanceof Button){
                        ((Button) node2).setDisable(false);
                    }
                }
            }
        }


    }

    private void timer() {
        HangmanHandelClass.time= ((int) (finishtime-starttime));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        starttime=System.currentTimeMillis();
        lableWord.setText(secreteWord);
        hangmanView.setText(hangmanLives.get(0));

    }

}