package TrainingModel;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.NameSample;
import opennlp.tools.namefind.NameSampleDataStream;
import opennlp.tools.namefind.TokenNameFinderFactory;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.TrainingParameters;

public class NERModel {

    //Where to save the model once trained
    static String onlpModelPath = "src/main/resources/en-ner-names.bin";
    // location of the training data set
//    static String trainingDataFilePath = "src/main/resources/File/name.txt";
    static String trainingDataFilePath = "src/main/resources/File/AnnotatedSentences.txt";

    public static void main(String[] args) throws IOException {

        Charset charset = Charset.forName("UTF-8");

        ObjectStream lineStream =
                new PlainTextByLineStream(() -> new FileInputStream(trainingDataFilePath), charset);

        ObjectStream sampleStream = new NameSampleDataStream(lineStream);

        TokenNameFinderModel model;
        TokenNameFinderFactory nameFinderFactory = new TokenNameFinderFactory();

        try {
            model = NameFinderME.train("en",
                    "asian.person",
                    sampleStream,
                    TrainingParameters.defaultParams(),
                    nameFinderFactory);
        }
        finally {
            sampleStream.close();
        }
//Saving the model
        BufferedOutputStream modelOut = null;
        try {
            modelOut = new BufferedOutputStream(new FileOutputStream(onlpModelPath));
            model.serialize(modelOut);
        } finally {
            if (modelOut != null)
                modelOut.close();
        }
    }
}
























//package TrainingModel;
//
//import opennlp.tools.namefind.NameFinderME;
//import opennlp.tools.namefind.NameSample;
//import opennlp.tools.namefind.NameSampleDataStream;
//import opennlp.tools.namefind.TokenNameFinderModel;
//import opennlp.tools.util.ObjectStream;
//import opennlp.tools.util.PlainTextByLineStream;
//import java.io.*;
//import java.nio.charset.Charset;
//public class NERModel {
//
//    public String train(String lang, String entity, InputStream taggedCorpusStream, OutputStream modelStream)
//    {
//        Charset charset = Charset.forName(CHAR_ENCODING);
//        try {
//            ObjectStream<String> lineStream = new PlainTextByLineStream( taggedCorpusStream, charset);
//            ObjectStream<NameSample> sampleStream = new NameSampleDataStream( lineStream);
//            TokenNameFinderModel model; OutputStream modelOut = null;
//            try {
//                model = NameFinderME.train(lang, entity, sampleStream, null);
//                modelOut = new BufferedOutputStream(modelStream);
//                if (model != null) {
//                    model.serialize(modelOut);
//                }
//                return entity + " model trained successfully";
//            }
//            catch (Exception ex) {
//                ex.printStackTrace();
//            }
//            finally {
//                sampleStream.close();
//                if (modelOut != null) {
//                    modelOut.close();
//                }
//            }
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "Something goes wrong with training module.";
//    }
//
//    public String train(String lang, String entity, String taggedCoprusFile, String modelFile)
//    {
//        try {
//            return train(lang, entity, new FileInputStream(taggedCoprusFile), new FileOutputStream(modelFile));
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "Something goes wrong with training module.";
//    }
//}
