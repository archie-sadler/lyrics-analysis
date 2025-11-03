package org.example;
import java.util.HashMap;

public class semanticAnalysis {

    //keyword analysis
    public static void keyWordAnalysis(String Lyrics){

        String[] loveKeywords = {"heart", "kiss", "forever", "baby", "sweet", "darling", "devotion", "angel", "passion", "sweetheart", "love"};
        String[] joyKeywords = {"happy", "joy", "smile", "sunshine", "bright", "love", "dancing", "euphoria", "bliss", "pleasure", "elated", "excited"};
        String[] sadnessKeywords = {"cry", "tears", "lonely", "broken", "goodbye", "lost", "pain", "despair", "misery", "loss", "miserable", "gloomy", "heartbroken"};
        String[] angerKeywords = {"hate", "rage", "fire", "scream", "fight", "burn", "mad", "hatred", "Annoyed", "irritate", "fuming", "wrath", "temper"};

        Lyrics = Lyrics.toLowerCase();
    }
}
