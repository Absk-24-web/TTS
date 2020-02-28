package Tts;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.util.InvalidFormatException;
import opennlp.tools.util.Span;

import java.io.*;

public class SentenceDetect {
    public Tokenizer tokenizer = new Tokenizer();

//    public static void main(String[] args) {
//        SentenceDetect sentenceDetect = new SentenceDetect();
//
//    }

    public void SentenceDetect(String para) throws InvalidFormatException,
            IOException {

        // always start with a model, a model is learned from training data
        InputStream is = new FileInputStream("src/main/resources/en-sent.bin");
        SentenceModel model = new SentenceModel(is);
        SentenceDetectorME sDetector = new SentenceDetectorME(model);

        String paragraph = para;
        String sentences[] = sDetector.sentDetect(paragraph);
        //Detecting the position of the sentences in the raw text
        Span spans[] = sDetector.sentPosDetect(paragraph);
        double[] probs = sDetector.getSentenceProbabilities();
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


//    public void sentence(){
//        String sentence = " Hi. How are you? Welcome to Tutorialspoint. "
//                + "We provide free tutorials on various technologies";
//
//        String simple = "[.?!]";
//        String[] splitString = (sentence.split(simple));
//        for (String string : splitString)
//            System.out.println(string);
//    }

}

