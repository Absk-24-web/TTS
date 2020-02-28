package Tts;

import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.InvalidFormatException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Tokenizer {
    public Normalization normalization = new Normalization();
    public NER findName = new NER();


    public void Tokenize(String para) throws InvalidFormatException, IOException {

        //Loading the tokenizer model
        InputStream inputStreamTokenizer = new
                FileInputStream("src/main/resources/en-token (1).bin");
        TokenizerModel tokenModel = new TokenizerModel(inputStreamTokenizer);

        //Instantiating the TokenizerME class
        TokenizerME tokenizer = new TokenizerME(tokenModel);

//        SimpleTokenizer simpleTokenizer =SimpleTokenizer.INSTANCE;

//        String tokens[] = tokenizer.tokenize("Hi. How are you? This is Mike.");
        String paragraph = para;
        String tokens[] = tokenizer.tokenize(paragraph);
//        String tokens[] = simpleTokenizer.tokenize(paragraph);
        System.out.println("Tokenizer:-");
        for (String a : tokens) {
            System.out.println(a);
        }
        System.out.println("");
        System.out.println("Normalization:-");
        for (String a : tokens)
            System.out.println(normalization.norm(a));
        System.out.println("");
        findName.findName(tokens);
        System.out.println("");
    }
}
