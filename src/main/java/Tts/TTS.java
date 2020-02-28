package Tts;

import java.io.IOException;
import java.util.Scanner;

public class TTS {
    public static Scanner scan = new Scanner(System.in);
    public static SentenceDetect sentenceDetect = new SentenceDetect();
    public static GraphemeToPhoneme graphemeToPhoneme = new GraphemeToPhoneme();

    public static void main(String args[]) throws IOException {
        System.out.println("Enter the Paragraph or Sentences:");
        sentenceDetect.SentenceDetect(scan.nextLine());
//        tokenizer.Tokenize(scan.nextLine());
        //graphemeToPhoneme.graphemeToPhonemeMap();

//            System.out.println(phonetic.generatePhonetic(scan.nextLine()));
//        System.out.println(normalization.norm(scan.nextLine()));
//        findName();
//        POSTag();
//        String paragraph = scan.nextLine();
//        chunk(paragraph);
//        Parser();
    }
}
//    Hi. How are you? This is  Mike. And This Space is Occurred.

//    Hi. How are you? Welcome to Tutorialspoint. We provide free tutorials on various technologies

//    Mike or Rama is senior programming manager both are working at Delhi or Noida.
//    Mine is from Delhi and Mike is from Noida.
//    Mrs. Wilson went to Mary's house for dinner.


//System.out.println("Normalization:-");
//        for (String a : tokens)
//            System.out.println(normalization.norm(a));
//        System.out.println("");