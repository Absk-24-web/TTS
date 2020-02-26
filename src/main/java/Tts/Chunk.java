package Tts;

import opennlp.tools.chunker.ChunkerME;
import opennlp.tools.chunker.ChunkerModel;
import opennlp.tools.cmdline.PerformanceMonitor;
import opennlp.tools.cmdline.postag.POSModelLoader;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.Span;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Chunk {

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
}
