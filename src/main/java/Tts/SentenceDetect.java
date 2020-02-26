package Tts;

import opennlp.tools.chunker.ChunkerME;
import opennlp.tools.chunker.ChunkerModel;
import opennlp.tools.cmdline.PerformanceMonitor;
import opennlp.tools.cmdline.parser.ParserTool;
import opennlp.tools.cmdline.postag.POSModelLoader;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.parser.Parse;
import opennlp.tools.parser.Parser;
import opennlp.tools.parser.ParserFactory;
import opennlp.tools.parser.ParserModel;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.InvalidFormatException;
import opennlp.tools.util.Span;
import java.io.*;
import java.util.Scanner;

public class SentenceDetect {
        public static  Scanner scan = new Scanner(System.in);
        public static Phonetic phonetic = new Phonetic();


    public static void main(String args[]) throws IOException {
        System.out.println("Enter the Paragraph or Sentences:");
        SentenceDetect();
//        Tokenize();
//        findName();
//        POSTag();
//        String paragraph = scan.nextLine();
//        chunk(paragraph);
//        Parser();
    }

    public static void SentenceDetect() throws InvalidFormatException,
            IOException {

//        String paragraph = "Hi. How are you? This is Mike.";
        String paragraph = scan.nextLine();

        // always start with a model, a model is learned from training data
        InputStream is = new FileInputStream("src/main/resources/en-sent.bin");
        SentenceModel model = new SentenceModel(is);
        SentenceDetectorME sdetector = new SentenceDetectorME(model);

        String sentences[] = sdetector.sentDetect(paragraph);
        //Detecting the position of the sentences in the raw text
        Span spans[] = sdetector.sentPosDetect(paragraph);
        double[] probs = sdetector.getSentenceProbabilities();
        System.out.println("Sentences Detector:-");
            for(int i=0; i<sentences.length;i++){
                System.out.println(sentences[i]);
                System.out.println(spans[i]);
//                System.out.println(probs[i]);
            }

            System.out.println("");
            System.out.println(phonetic.generatePhonetic(paragraph));
       // Tokenize(paragraph);
//            POSTag(paragraph);
            chunk(paragraph);
//            Parser(paragraph);
        is.close();
    }

    public static void Tokenize(String para) throws InvalidFormatException, IOException {
        InputStream is = new FileInputStream("src/main/resources/en-token (1).bin");

        TokenizerModel model = new TokenizerModel(is);

        Tokenizer tokenizer = new TokenizerME(model);

//        String tokens[] = tokenizer.tokenize("Hi. How are you? This is Mike.");
//        String paragraph = scan.nextLine();
        String paragraph = para;
        String tokens[] = tokenizer.tokenize(paragraph);
        System.out.println("Tokenizer:-");
        for (String a : tokens)
            System.out.println(a);
        System.out.println("");
        is.close();
    }

    public static void findName() throws IOException {
        InputStream is = new    FileInputStream("src/main/resources/en-ner-person.bin");

        TokenNameFinderModel model = new TokenNameFinderModel(is);
        is.close();

        NameFinderME nameFinder = new NameFinderME(model);

        String []sentence =
                new String[]{
                "Mike",
                "Smith",
                "is",
                "a",
                "good",
                "person"
        };

        Span nameSpans[] = nameFinder.find(sentence);

        for(Span s: nameSpans)
            System.out.println(s.toString());
    }

    public static void POSTag(String para) throws IOException {
        POSModel model = new POSModelLoader()
                .load(new File("src/main/resources/en-pos-maxent.bin"));
        PerformanceMonitor perfMon = new PerformanceMonitor(System.err, "sent");
        POSTaggerME tagger = new POSTaggerME(model);

//        Charset charset = Charset.forName("UTF-8");
//        String input = "Hi. How are you? This is Mike.";
        String input = para;

        //ObjectStream<String> lineStream = new PlainTextByLineStream(new StringReader(input),charset);
        perfMon.start();
        String line;
//        while ((line = lineStream.read()) != null) {

            String whitespaceTokenizerLine[] = WhitespaceTokenizer.INSTANCE
                    .tokenize(input);
            String[] tags = tagger.tag(whitespaceTokenizerLine);

            POSSample sample = new POSSample(whitespaceTokenizerLine, tags);
            System.out.println(sample.toString());
        System.out.println("");

            perfMon.incrementCounter();
//        }
        perfMon.stopAndPrintFinalResult();
    }

    public static void chunk(String para) throws IOException {
        POSModel model = new POSModelLoader()
                .load(new File("src/main/resources/en-pos-maxent (1).bin"));
        PerformanceMonitor perfMon = new PerformanceMonitor(System.err, "sent");
        POSTaggerME tagger = new POSTaggerME(model);

//        String input = "Hi. How are you? This is Mike.";
        String input = para;
//        ObjectStream<String> lineStream = new PlainTextByLineStream(new StringReader(input));

        perfMon.start();
        String line;
        String whitespaceTokenizerLine[] = null;

        String[] tags = null;
//        while ((line = lineStream.read()) != null) {
            whitespaceTokenizerLine = WhitespaceTokenizer.INSTANCE
                    .tokenize(input);
            tags = tagger.tag(whitespaceTokenizerLine);

            POSSample sample = new POSSample(whitespaceTokenizerLine, tags);
            System.out.println(sample.toString());
            perfMon.incrementCounter();
//        }
        perfMon.stopAndPrintFinalResult();

        // chunker
        InputStream is = new FileInputStream("src/main/resources/en-chunker.bin");
        ChunkerModel cModel = new ChunkerModel(is);

        ChunkerME chunkerME = new ChunkerME(cModel);
        String result[] = chunkerME.chunk(whitespaceTokenizerLine, tags);

        for (String s : result)
            System.out.println(s);

        Span[] span = chunkerME.chunkAsSpans(whitespaceTokenizerLine, tags);
        for (Span s : span)
            System.out.println(s.toString());
    }

    public static void Parser(String para) throws InvalidFormatException, IOException {
        // http://sourceforge.net/apps/mediawiki/opennlp/index.php?title=Parser#Training_Tool
        InputStream is = new FileInputStream("src/main/resources/en-parser-chunking.bin");

        ParserModel model = new ParserModel(is);

        Parser parser = ParserFactory.create(model);

//        String sentence = "Programcreek is a very huge and useful website.";
        String sentence = para;
        Parse topParses[] = ParserTool.parseLine(sentence, parser, 1);

        for (Parse p : topParses)
            p.show();

        is.close();

        /*
         * (TOP (S (NP (NN Programcreek) ) (VP (VBZ is) (NP (DT a) (ADJP (RB
         * very) (JJ huge) (CC and) (JJ useful) ) ) ) (. website.) ) )
         */
    }

}

