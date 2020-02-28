package Tts;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class NER {
    public  POSTag posTag = new POSTag();

//    public static void main(String[] args) throws IOException {
//        FindName findName =new FindName();
//        findName.findName();
//    }

    public  void findName(String [] token) throws IOException {

        //Loading the NER-person model
        InputStream inputStreamNameFinder = new
                FileInputStream("src/main/resources/en-ner-person.bin");
        TokenNameFinderModel model = new TokenNameFinderModel(inputStreamNameFinder);

        //Instantiating the NameFinderME class
        NameFinderME nameFinder = new NameFinderME(model);

        //Finding the names in the sentence
        Span nameSpans[] = nameFinder.find(token);

        //Printing the names and their spans in a sentence
        System.out.println("NER Name:-");
        for(Span s: nameSpans)
            System.out.println(s.toString()+"  "+token[s.getStart()]);


        //Loading the NER-location model
        InputStream inputStreamLocationFinder = new
                FileInputStream("src/main/resources/en-ner-location.bin");
        TokenNameFinderModel locationModel = new TokenNameFinderModel(inputStreamLocationFinder);

        //Instantiating the LocationFinderME class
        NameFinderME locationFinder = new NameFinderME(locationModel);

        //Finding the names in the sentence
        Span locationSpans[] = locationFinder.find(token);

        //Printing the names and their spans in a sentence
        System.out.println("NER Location:-");
        for(Span s: locationSpans)
            System.out.println(s.toString()+"  "+token[s.getStart()]);
    }
}
