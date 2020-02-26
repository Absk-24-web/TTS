package Tts;

import java.io.IOException;
import java.util.Scanner;

public class TTS {
    public static Scanner scan = new Scanner(System.in);
    public static Phonetic phonetic = new Phonetic();
    public static Tokenizer tokenizer = new Tokenizer();
    public static Normalization normalization = new Normalization();
    public static SentenceDetect sentenceDetect = new SentenceDetect();

    public static void main(String args[]) throws IOException {
        System.out.println("Enter the Paragraph or Sentences:");
        sentenceDetect.SentenceDetect(scan.nextLine());
//            System.out.println(phonetic.generatePhonetic(scan.nextLine()));
//        System.out.println(normalization.norm(scan.nextLine()));
//        findName();
//        POSTag();
//        String paragraph = scan.nextLine();
//        chunk(paragraph);
//        Parser();
    }
}
//    Hi. How are you? This is  Mike occurred.