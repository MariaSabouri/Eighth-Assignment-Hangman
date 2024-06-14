package hangman;

import java.math.BigInteger;
import java.sql.*;
import java.util.Scanner;

// Use JDBC to connect to your database and run queries

public class DatabaseManager {

    static Connection connection;
    public static Connection openConnection(Boolean b) {
        if (b == false) {
            try {
                connection.close();
                return connection;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
//                System.out.println("kjsndfkds");
                Class.forName("org.postgresql.Driver");
//                System.out.println("lalalala");
                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
                System.out.println("Opened database successfully");
                return connection;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        }
    }
    public static void newuserinfo(String name,String username,int password) throws Exception {
        if (checking(username)==true){
            System.out.println("There exist such username.\nPlease Enter another one:");
            String newuserName=new Scanner(System.in).next();
            HangmanHandelClass.Username=newuserName;
            newuserinfo(name,newuserName,password);
        }else {
            String sql = "INSERT INTO public.\"UserInfo\"(name,username,password) VALUES (?,?,?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, username);
            pstmt.setInt(3, password);
            pstmt.executeUpdate();
            System.out.println("Record newUser inserted successfully.");
        }
    }

    private static boolean checking(String username)throws Exception {
        String sql ="SELECT \"username\" FROM public.\"UserInfo\" WHERE \"username\" = ?";
        PreparedStatement st=connection.prepareStatement(sql);
        st.setString(1,username);
        ResultSet resultSet = st.executeQuery();
        if (resultSet.next()) {
            return true;
        }else return false;

    }


    public static void Gameinfo(String username,String word,int wrongGuess,int time,Boolean win)throws SQLException{
        String sql="INSERT INTO public.\"GameInfo\" (\"Username\",\"Word\",\"WrongGuess\",\"Time\",\"Win\") VALUES (?,?,?,?,?) ";
        PreparedStatement st=connection.prepareStatement(sql);

        st.setString(1,username);
        st.setString(2,word);
        st.setInt(3,wrongGuess);
        st.setInt(4,time);
        st.setBoolean(5,win);

        st.executeUpdate();


        System.out.println("Record GameInfo inserted successfully.");



    }


    public static Boolean login(String name, String username, int password) throws SQLException {
        String sql="SELECT * FROM public.\"UserInfo\" WHERE (name,username,password) = (?,?,?)";
        PreparedStatement st=connection.prepareStatement(sql);
        st.setString(1,name);
        st.setString(2,username);
        st.setInt(3,password);
        ResultSet rs=st.executeQuery();
        if (rs.next()) {
            return true;
        }else{
            System.out.println("didn't find such account!\nPlease run again! ");
            return false;}
    }
    public static String userStatistic(String username) {
        String sql="SELECT count(*) FROM public.\"GameInfo\" where \"Username\"=? and \"Win\"='true'";
        String query = "SELECT \"Username\", COUNT(*) AS \"WinCount\" FROM public.\"GameInfo\" WHERE \"Win\" = 'TRUE' GROUP BY \"Username\" ORDER BY \"WinCount\" DESC";
        try {
            PreparedStatement st=connection.prepareStatement(sql);
            st.setString(1,username);
            ResultSet rs=st.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                String statistic="Username: "+username+"\n"+"Number of winning: "+count;


                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
                String resultString = "";
                while (resultSet.next()) {
                    String USERrname = resultSet.getString("Username");
                    int winCount = resultSet.getInt("WinCount");
                    resultString += "Username: " + USERrname + ", WinCount: " + winCount + "\n";
                }
                statistic+="\n"+resultString;


                return statistic;
            }else return "";

        }catch (SQLException e){
            e.printStackTrace();
            return "";
        }

    }
    public static void tops(){

    }

}