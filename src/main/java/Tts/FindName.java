package Tts;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.util.Span;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FindName {
    public  POSTag posTag = new POSTag();

    public  void findName(String[] token) throws IOException {
        InputStream is = new FileInputStream("src/main/resources/en-ner-person.bin");


        TokenNameFinderModel model = new TokenNameFinderModel(is);
        is.close();

        NameFinderME nameFinder = new NameFinderME(model);

        String []sentence = token;
//                new String[]{
//                        "Mike",
//                        "and",
//                        "Smith",
//                        "is",
//                        "a",
//                        "good",
//                        "person"
//                };
        Span nameSpans[] = nameFinder.find(sentence);
            System.out.println("FindName:-");
        for(Span s: nameSpans)
            System.out.println(s.toString());
        posTag.POSTag(token);
    }
}
