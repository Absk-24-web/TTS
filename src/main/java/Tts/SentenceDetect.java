package Tts;

import opennlp.tools.parser.Parse;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.util.InvalidFormatException;
import opennlp.tools.util.Span;

import java.io.*;

public class SentenceDetect {
    public Tokenizer tokenizer = new Tokenizer();
    public Phonetic phonetic =new Phonetic();
    public Parser parser = new Parser();

//    public static void main(String[] args) {
//        SentenceDetect sentenceDetect = new SentenceDetect();
//
//    }

    public void SentenceDetect(String para) throws InvalidFormatException,
            IOException {

        // always start with a model, a model is learned from training data
        InputStream is = new FileInputStream("src/main/resources/LIB-Model/en-sent.bin");
        SentenceModel model = new SentenceModel(is);
        SentenceDetectorME sDetector = new SentenceDetectorME(model);

        String sentences[] = sDetector.sentDetect(para);
        //Detecting the position of the sentences in the raw text
        Span spans[] = sDetector.sentPosDetect(para);
        double[] prob = sDetector.getSentenceProbabilities();
        System.out.println("Sentences Detector:-");
        for (int i = 0; i < sentences.length; i++) {
            System.out.println(sentences[i]);
            System.out.println(spans[i]);
//                System.out.println(prob[i]);
        }
        System.out.println("");
        tokenizer.Tokenize(para);
        System.out.println("Phonetic:-");
        System.out.println(phonetic.generatePhonetic(para));
        parser.Parser(para);
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

