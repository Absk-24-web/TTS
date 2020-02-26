package Tts;

import opennlp.tools.lemmatizer.LemmatizerModel;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Lemmatizer {


    public void lemm() throws IOException {
        InputStream is = new FileInputStream("");
        LemmatizerModel model  = new LemmatizerModel(is);




    }
}
