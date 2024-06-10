package hangman;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class HangmanController implements Initializable {
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
    private Text hangmanView;
    @FXML
    private Label lableWord;
    @FXML
    private VBox KeyboardVbox;
    public static char charchoosen;
    public static String secreteWord=HangmanHandelClass.playingNewWord();
    private static int secreteWordLength=HangmanHandelClass.UniqueCharsSet(HangmanHandelClass.secretword).size();
    ArrayList<String> hangmanLives=new ArrayList<>(Arrays.asList(
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


    @FXML
    void CreatingAlphabetKeyBoard(ActionEvent event) {
        secreteWordLength--;
        Button button=(Button) event.getSource();
        button.setDisable(true);
        charchoosen=button.getText().charAt(0);
        System.out.println(charchoosen);
        lableWord.setText(HangmanHandelClass.SearchingForChar(charchoosen));

        if (secreteWordLength>0){
        }else {
            try {
                changeActionOfBottons();
                secreteWord=HangmanHandelClass.playingNewWord();
                secreteWordLength=HangmanHandelClass.UniqueCharsSet(HangmanHandelClass.secretword).size();
//                System.out.println(HangmanHandelClass.UniqueCharsSet(HangmanHandelClass.secretword));
                lableWord.setText(secreteWord);

            }catch (Exception e){
                lableWord.setText("Game finished");
                KeyboardVbox.setDisable(true);
            }
        }
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


    public static void Gamelable(String lable){
        //TODO
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lableWord.setText(secreteWord);
    }
}