package Tts;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class NER {
    public Normalization normalization = new Normalization();
    public StringBuffer pos = new StringBuffer();
    public Chunk chunk =new Chunk();


    public void findName(String[] token) throws IOException {

        //Loading the NER-person model
        InputStream inputStreamNameFinder = new
                FileInputStream("src/main/resources/LIB-Model/en-ner-person.bin");
        TokenNameFinderModel model = new TokenNameFinderModel(inputStreamNameFinder);

        //Instantiating the NameFinderME class
        NameFinderME nameFinder = new NameFinderME(model);

        //Finding the names in the sentence
        Span nameSpans[] = nameFinder.find(token);

        //Printing the names and their spans in a sentence
        System.out.println("NER Name:-");
        for (Span s : nameSpans)
            System.out.println(s.toString() + "  " + token[s.getStart()]);


        //Loading the NER-location model
        InputStream inputStreamLocationFinder = new
                FileInputStream("src/main/resources/LIB-Model/en-ner-location.bin");
        TokenNameFinderModel locationModel = new TokenNameFinderModel(inputStreamLocationFinder);

        //Instantiating the LocationFinderME class
        NameFinderME locationFinder = new NameFinderME(locationModel);

        //Finding the names in the sentence
        Span locationSpans[] = locationFinder.find(token);

        //Printing the names and their spans in a sentence
        System.out.println("NER Location:-");
        for (Span s : locationSpans)
            System.out.println(s.toString() + "  " + token[s.getStart()]);
        System.out.println("");
        System.out.println("Normalization:-");
        for (String s : token) {
            System.out.println(normalization.norm(s));
            pos.append(normalization.norm(s) + " ");
        }
        String str = pos.toString();
//        token = str.split(" ");
        chunk.chunk(str);


    }
}


//    String stringArray[] = token;
//        StringBuffer sb = new StringBuffer();
//        for(int i = 0; i < stringArray.length; i++) {
//            sb.append(stringArray[i]);
//        }
