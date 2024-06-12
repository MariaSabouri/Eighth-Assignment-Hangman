package hangman;

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
                System.out.println("kjsndfkds");
                Class.forName("org.postgresql.Driver");

                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "13821381Maria");
                System.out.println("Opened database successfully");
                return connection;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        }
    }
    public static void userinfo(String name,String username,int password) throws Exception {

        if (checkingUsername(username)==true){
            System.out.println("hello");
            System.out.println("There exist such username.\nPlease Enter another one:");
            String newName=new Scanner(System.in).next();
            userinfo(newName,username,password);
        }
        String sql="INSERT INTO public.\"UserInfo\"(name,username,password) VALUES (?,?,?)";


         PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, name);
            pstmt.setString(2,username);
            pstmt.setInt(3,password);
            pstmt.executeUpdate();


        System.out.println("Record inserted successfully.");




    }

    private static boolean checkingUsername(String username)throws Exception {
        String sql2="SELECT CASE\n" +
                "WHEN EXISTS (SELECT \"Username\" FROM public.\"GameInfo\" WHERE \"Username\" ="+ username+")\n" +
                "THEN TRUE\n" +
                "ELSE FALSE\n" +
                "END AS record_exists;";
        String sql = "SELECT CASE " +
                "WHEN EXISTS (SELECT \"Username\" FROM public.\"GameInfo\" WHERE \"Username\" = ?) " +
                "THEN TRUE " +
                "ELSE FALSE " +
                "END AS record_exists;";

        PreparedStatement st=connection.prepareStatement(sql);
        st.setString(1,username);



        ResultSet rs=st.executeQuery(sql);



        if (rs.next()==true){
            return true;
        }
        else return false;
    }

    public static void Gameinfo(String username,String word,int wronguess,Boolean win)throws SQLException{
        String sql="insert into public.\"GameInfo\" (Username,Word,WronGuess,Time,Win) VALUES (?,?,?,?,?)  ";
        PreparedStatement st=connection.prepareStatement(sql);
        st.setString(1,username);
        st.setString(2,word);
        st.setInt(3,wronguess);
        st.setBoolean(4,win);

        ResultSet rs=st.executeQuery(sql);



    }








}