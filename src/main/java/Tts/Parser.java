package Tts;

import opennlp.tools.cmdline.parser.ParserTool;
import opennlp.tools.parser.Parse;
import opennlp.tools.parser.ParserFactory;
import opennlp.tools.parser.ParserModel;
import opennlp.tools.util.InvalidFormatException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Parser {

    public static void Parser(String para) throws InvalidFormatException, IOException {
        // http://sourceforge.net/apps/mediawiki/opennlp/index.php?title=Parser#Training_Tool
        InputStream is = new FileInputStream("src/main/resources/en-parser-chunking.bin");

        ParserModel model = new ParserModel(is);

        opennlp.tools.parser.Parser parser = ParserFactory.create(model);

//        String sentence = "Programcreek is a very huge and useful website.";
        String sentence = para;
        Parse topParses[] = ParserTool.parseLine(sentence, parser, 1);
        System.out.println("Parser:-");
        for (Parse p : topParses)
            p.show();

        is.close();

        /*
         * (TOP (S (NP (NN Programcreek) ) (VP (VBZ is) (NP (DT a) (ADJP (RB
         * very) (JJ huge) (CC and) (JJ useful) ) ) ) (. website.) ) )
         */
    }


}
