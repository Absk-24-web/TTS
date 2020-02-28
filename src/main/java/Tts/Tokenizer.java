package Tts;

import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.InvalidFormatException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Tokenizer {
    public NER ner = new NER();

    public void Tokenize(String para) throws InvalidFormatException, IOException {

        //Loading the tokenizer model
        InputStream inputStreamTokenizer = new
                FileInputStream("src/main/resources/LIB-Model/en-token (1).bin");
        TokenizerModel tokenModel = new TokenizerModel(inputStreamTokenizer);

        //Instantiating the TokenizerME class
        TokenizerME tokenizer = new TokenizerME(tokenModel);
//        SimpleTokenizer simpleTokenizer =SimpleTokenizer.INSTANCE;
        String tokens[] = tokenizer.tokenize(para);
//        String tokens[] = simpleTokenizer.tokenize(para);
        System.out.println("Tokenizer:-");
        for (String a : tokens) {
            System.out.println(a);
        }
        System.out.println("");
        ner.findName(tokens);
        System.out.println("");
    }
}
