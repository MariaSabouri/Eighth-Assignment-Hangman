package hangman;

import java.util.*;

public class HangmanHandelClass {
    static String[] words=new String[]{"dog","bird","fish","lion","cheetah","monkey","cat","horse","girafe","Corvus"};
    static int WinnigChance;
    static int WinningCounter;
    static int wrongGuess;
    static int time;
    static String Username;
    public HangmanHandelClass() {
        WinnigChance=6;
        WinningCounter=0;
        wrongGuess=0;
        time=0;
    }



    private static int playingGameCounter =-1;
    public static String secretword;
    public static String out="";


    public static String playingNewWord(){
        if (playingGameCounter<words.length){
            wrongGuess=0;
            time=0;
            out="";
            playingGameCounter++;
            int n=words[playingGameCounter].length();
            HangmanController.starttime=System.currentTimeMillis();
            secretword=words[playingGameCounter];
            for (int i = 0; i < n; i++) {
                out+="*";
            }
            return out;
        }
        else {
            return null;
        }
    }


    private static String displayResultPane(Boolean b1,Boolean b2,char chara) {
        if (b1==true&&b2==false){
            char[] charArray = out.toCharArray();
            for (int i=0 ;i<out.length();i++) {
                if (secretword.toUpperCase().charAt(i)==chara){
                    charArray[i]=chara;
                    out=new String(charArray);
                }
            }
            HangmanController.WordHandled(true);

            return out;
        } else if (b1==true&&b2==true) {
            char[] charArray = out.toCharArray();
            for (int i=0 ;i<out.length();i++) {
                if (secretword.toUpperCase().charAt(i)==chara){
                    charArray[i]=chara;
                    out=new String(charArray);
                }
            }
            HangmanController.WordHandled(true);

            return out;
        } else if (b1 == false && b2==false) {
            HangmanController.WordHandled(false);
            HangmanController.changeManSituationView();
            return out;
        }else {
            HangmanController.WordHandled(false);
            HangmanController.changeManSituationView();
            return out;}
    }


    static int counter=0;

    public static String SearchingForChar(char chara) {
        List<Character> uniqueCharsSet=UniqueCharsSet(secretword.toUpperCase());
//        System.out.println(uniqueCharsSet);

        if (uniqueCharsSet.contains(chara)){
            counter++;
            if (counter==uniqueCharsSet.size()){
                counter=0;
                return displayResultPane(true,true,chara);
            }else {
                return displayResultPane(true,false,chara);}
        } else {
            WinnigChance--;
            wrongGuess++;
            if (WinnigChance==1){
                return displayResultPane(false,true,chara);
            }else {
                return displayResultPane(false,false,chara);}
        }
    }



    public static List UniqueCharsSet(String word) {
        List<Character> uniqueChars = new ArrayList<>();
        for (char c : word.toCharArray()) {
            if (!uniqueChars.contains(c)){
                uniqueChars.add(c);
            }
        }
        return uniqueChars;
    }
}
