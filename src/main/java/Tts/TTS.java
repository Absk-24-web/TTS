package Tts;
import java.io.IOException;
import java.util.Scanner;

public class TTS {
    public static Scanner scan = new Scanner(System.in);
    public static Phonetic phonetic = new Phonetic();

    public static void main(String args[]) throws IOException {
        System.out.println("Enter the Paragraph or Sentences:");
        new SentenceDetect();
//        Tokenize();
//        findName();
//        POSTag();
//        String paragraph = scan.nextLine();
//        chunk(paragraph);
//        Parser();
    }
}
