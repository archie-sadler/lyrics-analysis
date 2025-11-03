package org.example;
//import java.util.HashMap;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.IOException;


/*
PLAN:

Allow the url to be user input to get the users desired lyrics.

 */

public class Main {

    //API TOKEN(S)
    //static String geniusApiToken = "placeholder";

    public static void getLyrics(){

        //song URL
        String url = "https://genius.com/Radiohead-creep-lyrics";

        try {

            // complicated user agent to avoid bot detection
            Document document = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/118.0.5993.90 Safari/537.36")
                    .get();

            //string builder so it can be .append()ed
            StringBuilder lyrics = new StringBuilder();

            //genius holds the lyrics in a div structure like this:
            for (Element element : document.select("div[data-lyrics-container='true']")) {

                lyrics.append(element.text()).append("\n");
            }

            //Remove all text before the first verse
            String[] justLyricsArr = lyrics.toString().split("Verse 1");

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

            System.out.println("Lyrics: " + "\n");

            iterateThroughString(justLyrics);




        } catch (IOException error){

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

        getLyrics();
    }
}