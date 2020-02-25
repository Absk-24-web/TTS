import com.sun.speech.freetts.FreeTTS;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import com.sun.speech.freetts.audio.JavaClipAudioPlayer;
public class FreeTTSHelloWorld {

    static Voice helloVoice;

        public static void main(String[] args) {

            // listAllVoices();
            FreeTTS freetts;

            String voiceName = "Arabic";
            System.out.println("Using voice: " + voiceName);

            /* The VoiceManager manages all the voices for FreeTTS.
             */

            try{
                VoiceManager voiceManager = VoiceManager.getInstance();
                helloVoice = voiceManager.getVoice(voiceName);
                System.out.println(helloVoice);
            }catch (Exception e){
                System.out.println(e);
            }
            helloVoice.allocate();
            /* Synthesize speech. */
            helloVoice.speak("Thank you for giving me a voice. "
                    + "I'm so glad to say hello to this world.");
            /* Clean up and leave.*/
            helloVoice.deallocate();
//            if (helloVoice == null) {
//                System.err.println(
//                        "Cannot find a voice named "
//                                + voiceName + ".  Please specify a different voice.");
//                System.exit(1);
//            }
            System.exit(0);
        }
}
