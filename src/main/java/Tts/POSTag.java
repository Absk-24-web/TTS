package Tts;

import opennlp.tools.cmdline.PerformanceMonitor;
import opennlp.tools.cmdline.postag.POSModelLoader;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.WhitespaceTokenizer;

import java.io.File;
import java.io.IOException;

public class POSTag {

    public Normalization normalization = new Normalization();

    public  void POSTag(String [] para) throws IOException {
        POSModel model = new POSModelLoader()
                .load(new File("src/main/resources/en-pos-maxent.bin"));
        PerformanceMonitor perfMon = new PerformanceMonitor(System.err, "sent");
        POSTaggerME tagger = new POSTaggerME(model);

//        Charset charset = Charset.forName("UTF-8");
//        String input = "Hi. How are you? This is Mike.";
        //String input = para;

        //ObjectStream<String> lineStream = new PlainTextByLineStream(new StringReader(input),charset);
        perfMon.start();
        String line;
//        while ((line = lineStream.read()) != null) {

        String whitespaceTokenizerLine[] = para;
//                WhitespaceTokenizer.INSTANCE
//                .tokenize(input);
        String[] tags = tagger.tag(whitespaceTokenizerLine);
        System.out.println("POSTag:-");
        POSSample sample = new POSSample(whitespaceTokenizerLine, tags);
        System.out.println(sample.toString());
        perfMon.incrementCounter();
//        }
        perfMon.stopAndPrintFinalResult();
        System.out.println("Normalization:-");
        for (String a : para)
            System.out.println(normalization.norm(a));
    }
}
