package test;

import java.util.Locale;
import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
public class TextSpeech {

    public static void main(String[] args)
    {
        try {
            // Set property as Kevin Dictionary
            System.setProperty(
                    "freetts.voices",
                    "com.sun.speech.freetts.en.us"
                            + ".cmu_us_kal.KevinVoiceDirectory");

            // Register Engine
            Central.registerEngineCentral(
                    "com.sun.speech.freetts"
                            + ".jsapi.FreeTTSEngineCentral");

            // Create a Synthesizer
            Synthesizer synthesizer
                    = Central.createSynthesizer(
                    new SynthesizerModeDesc(Locale.US));

            // Allocate synthesizer
            synthesizer.allocate();

            // Resume Synthesizer
            synthesizer.resume();

            // Speaks the given text
            // until the queue is empty.
            synthesizer.speakPlainText(
                    "GeeksforGeeks", null);
            synthesizer.waitEngineState(
                    Synthesizer.QUEUE_EMPTY);

            // Deallocate the Synthesizer.
            synthesizer.deallocate();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}



//Loading the tokenizer model
//        InputStream inputStreamTokenizer = new
//                FileInputStream("src/main/resources/en-token (1).bin");
//        TokenizerModel tokenModel = new TokenizerModel(inputStreamTokenizer);
//
//        //Instantiating the TokenizerME class
//        TokenizerME tokenizer = new TokenizerME(tokenModel);
//
//        //Tokenizing the sentence in to a string array
//        String sentence = "Mike is senior programming manager and Rama is a clerk both are working at"+
//        "Tutorialspoint";
//        String tokens[] =
//                tokenizer.tokenize(sentence);