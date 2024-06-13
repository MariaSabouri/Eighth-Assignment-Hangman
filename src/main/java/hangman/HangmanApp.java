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
import java.util.Scanner;

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

    public static void main(String[] args) throws Exception {
        DatabaseManager.openConnection(true);
        System.out.printf("%s%15s%15s\n","#####","Welcome","#####");
        System.out.printf("%d%10s\n",0,"logIn");
        System.out.printf("%d%10s\n",1,"SignUp");
        String answer=new Scanner(System.in).nextLine();
        if (Integer.parseInt(answer)==0){
        System.out.println("Enter Your name: ");
        String name=new Scanner(System.in).nextLine();
        System.out.println("Enter Your Username: ");
        String Username=new Scanner(System.in).nextLine();
        System.out.println("Enter Your Password:");
        String password=new Scanner(System.in).nextLine();
        Boolean b=DatabaseManager.login(name,Username,Integer.parseInt(password));
        if (b==true){
            HangmanHandelClass.Username=Username;
            launch();
        }
        } else if (Integer.parseInt(answer)==1) {
            System.out.println("Enter Your name: ");
            String name=new Scanner(System.in).nextLine();
            System.out.println("Enter Your Username: ");
            String Username=new Scanner(System.in).nextLine();
            System.out.println("Enter Your Password:");
            String password=new Scanner(System.in).nextLine();
            DatabaseManager.newuserinfo(name,Username, Integer.parseInt(password));
            HangmanHandelClass.Username=Username;
            launch();
        }else {
            System.out.println("Wrong input");
        }


        DatabaseManager.openConnection(false);


//        JSONObject jsonObjectdog =parsingFromAPI("dog");
//        JSONObject jsonObjectfish=parsingFromAPI("fish");
//        JSONObject jsonObjectbird=parsingFromAPI("bird");
//        JSONObject jsonObjectlion=parsingFromAPI("lion");
//        System.out.println(jsonObjectdog);

    }


}