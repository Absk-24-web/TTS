package Tts;

import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.InvalidFormatException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Tokenizer {
    public Normalization normalization = new Normalization();

    public void Tokenize(String para) throws InvalidFormatException, IOException {
        InputStream is = new FileInputStream("src/main/resources/en-token (1).bin");

        TokenizerModel model = new TokenizerModel(is);

        opennlp.tools.tokenize.Tokenizer tokenizer = new TokenizerME(model);

//        String tokens[] = tokenizer.tokenize("Hi. How are you? This is Mike.");
//        String paragraph = scan.nextLine();
        String paragraph = para;
        String tokens[] = tokenizer.tokenize(paragraph);
        System.out.println("Tokenizer:-");
        for (String a : tokens) {
            System.out.println(a);
        }
        System.out.println("");
        System.out.println("Normalization:-");
        for (String a : tokens)
            System.out.println(normalization.norm(a));
        System.out.println("");
        is.close();
    }
}
