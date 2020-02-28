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

    public  void chunk(String para) throws IOException {
        POSModel model = new POSModelLoader()
                .load(new File("src/main/resources/LIB-Model/en-pos-maxent (1).bin"));
        PerformanceMonitor perfMon = new PerformanceMonitor(System.err, "sent");
        POSTaggerME tagger = new POSTaggerME(model);

        perfMon.start();
        String whitespaceTokenizerLine[] ;
        String[] tags ;

        whitespaceTokenizerLine = WhitespaceTokenizer.INSTANCE
                .tokenize(para);
        tags = tagger.tag(whitespaceTokenizerLine);

        POSSample sample = new POSSample(whitespaceTokenizerLine, tags);
        System.out.println(sample.toString());
        perfMon.incrementCounter();
        perfMon.stopAndPrintFinalResult();

        // chunker
        InputStream is = new FileInputStream("src/main/resources/LIB-Model/en-chunker.bin");
        ChunkerModel cModel = new ChunkerModel(is);

        ChunkerME chunkerME = new ChunkerME(cModel);
        String result[] = chunkerME.chunk(whitespaceTokenizerLine, tags);

        for (String s : result){
            System.out.println(s);
        }
        Span[] span = chunkerME.chunkAsSpans(whitespaceTokenizerLine, tags);
        for (Span s : span)
            System.out.println(s.toString());
    }
}
