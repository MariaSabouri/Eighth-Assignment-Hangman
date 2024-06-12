package hangman;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;



public class HangmanApp extends Application {



    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HangmanApp.class.getResource("hangman-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Hangman");
        stage. setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
    public static org.json.JSONObject parsingFromAPI(String animal){
        String readline;
        try {
            URL url1 = new URL("https://api.api-ninjas.com/v1/animals?name="+animal);
            HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
            connection.setRequestProperty("X-API-KEY", "MUe0TUVLwecAyRMOykNLug==t8vIZHAmHzVqKBbU");
            connection.setRequestProperty("accept", "application/json");
            connection.setDoOutput(true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer stringBuffer = new StringBuffer();
            while((readline = reader.readLine()) != null) {
                stringBuffer.append(readline);
            }
            reader.close();
            System.out.println(stringBuffer);
            String JSON = stringBuffer.substring(0, stringBuffer.toString().length()-1).substring(1);
            JSONObject jsonObject=new JSONObject(JSON);
            return jsonObject;
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args){
//        JSONObject jsonObjectdog =parsingFromAPI("dog");
//        JSONObject jsonObjectfish=parsingFromAPI("fish");
//        JSONObject jsonObjectbird=parsingFromAPI("bird");
//        JSONObject jsonObjectlion=parsingFromAPI("lion");
//        System.out.println(jsonObjectdog);



        DatabaseManager.openConnection(true);

//        try {
//            DatabaseManager.userinfo("Maria","MSD",1234);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//        launch(args);

    }


}