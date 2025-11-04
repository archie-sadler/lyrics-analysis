package org.example;
//import java.util.HashMap;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
import java.io.IOException;
import com.opencsv.CSVReader;
import java.io.FileReader;
import com.opencsv.exceptions.CsvValidationException;



/*
PLAN:

Allow the url to be user input to get the users desired lyrics.

 */

public class Main {

    //API TOKEN(S)
    //static String geniusApiToken = "placeholder";

    public static void getLyrics(){

        //csv file directory path

        String CSVfile = "song_lyrics.csv";

        try {


            CSVReader reader = new CSVReader(new FileReader(CSVfile));

            String[] nextLine;

            int counter = 0;
            String lyrics = "";

            reader.readNext();

            while((nextLine = reader.readNext()) != null && counter < 25){
                
                try{
                String song = nextLine[0];
                //String genre = nextLine[1];
                //String artist = nextLine[2];
                lyrics = nextLine[6];
                
                System.out.println();
                System.out.println("Song: " + song);
                System.out.println();

                //System.out.println("Genre: " + genre);
                //System.out.println("Artist: " + artist);

                System.out.println("Lyrics: " + lyrics);
                counter++;

                } catch(Exception error){

                }
            }

            //Remove all text before the first verse
            String[] justLyricsArr = lyrics.split("Verse 1");

            //create a new string and remove words that aren't lyrics
            String justLyrics = justLyricsArr[1].replace("Chorus", "");

            //the rest of the characters / words we don't want
            justLyrics = justLyrics.replace("Bridge", "");

            justLyrics = justLyrics.replace("[", "");

            justLyrics = justLyrics.replace("]", "");

            if(justLyrics.contains("Verse 2")){

                justLyrics = justLyrics.replace("Verse 2", "");
            }

            if(justLyrics.contains("Verse 3")){

                justLyrics = justLyrics.replace("Verse 3", "");
            }

            System.out.println("Lyrics: ");
            System.out.println();

            iterateThroughString(justLyrics);




        } catch (IOException | CsvValidationException error){

            System.err.println("Failed to fetch the page: " + error.getMessage());
            //error.printStackTrace();
        }
    }

    public static void iterateThroughString(String string){

        String[] subStrings = string.split(" ");

        for(String subString : subStrings){


            if(!subString.isEmpty()) {
                char firstChar = subString.charAt(0);

                if (Character.isUpperCase(firstChar) && firstChar != 'I') {

                    System.out.println();
                    System.out.print(subString + " ");


                }

                else{

                    System.out.print(subString + " ");
                }


            }
        }
    }


    public static void main(String[] args) {

        //System.out.println("Current working directory: " + System.getProperty("user.dir"));

        getLyrics();
    }
}