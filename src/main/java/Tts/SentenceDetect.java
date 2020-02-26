package Tts;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.util.InvalidFormatException;
import opennlp.tools.util.Span;

import java.io.*;

public class SentenceDetect {
    public Tokenizer tokenizer = new Tokenizer();

    public void SentenceDetect(String para) throws InvalidFormatException,
            IOException {

//        String paragraph = "Hi. How are you? This is Mike.";
        String paragraph = para;

        // always start with a model, a model is learned from training data
        InputStream is = new FileInputStream("src/main/resources/en-sent.bin");
        SentenceModel model = new SentenceModel(is);
        SentenceDetectorME sdetector = new SentenceDetectorME(model);

        String sentences[] = sdetector.sentDetect(paragraph);
        //Detecting the position of the sentences in the raw text
        Span spans[] = sdetector.sentPosDetect(paragraph);
        double[] probs = sdetector.getSentenceProbabilities();
        System.out.println("Sentences Detector:-");
        for (int i = 0; i < sentences.length; i++) {
            System.out.println(sentences[i]);
            System.out.println(spans[i]);
//                System.out.println(probs[i]);
        }
        System.out.println("");
        tokenizer.Tokenize(paragraph);
        is.close();
    }

}

