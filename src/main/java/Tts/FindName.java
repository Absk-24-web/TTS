package Tts;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.util.Span;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FindName {

    public static void findName() throws IOException {
        InputStream is = new FileInputStream("src/main/resources/en-ner-person.bin");

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
}
