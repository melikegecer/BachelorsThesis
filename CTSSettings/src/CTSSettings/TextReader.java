package CTSSettings;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class TextReader {

    private Voice voice;
    private String voiceName = "mbrola_us1";
    private static TextReader instance = null;

    private TextReader() {
        System.setProperty("mbrola.base", "\\path\\to\\file\\mbrola");
        voice = VoiceManager.getInstance().getVoice(voiceName);

    }

    public static TextReader getInstance() {
        if (instance == null) {
            instance = new TextReader();
        }
        return instance;
    }

    public String getStyle() {
        return voice.getStyle();
    }

    public void setStyle(String style) {
        voice.setStyle(style);
    }

    public void setSpeakingRate(float rate) {
        voice.setRate(rate);
    }

    public float getSpeakingRate() {
        return voice.getRate();
    }

    public void open() {
        voice.allocate();
    }

    public void close() {
//        voice.deallocate();
    }

    public void read(String words) {
        voice.allocate();
        voice.speak(words);
//        voice.deallocate();
    }

    public void setVoiceName(String voiceName) {
        voice = VoiceManager.getInstance().getVoice(voiceName);
        this.voiceName = voiceName;
    }

    public String getVoiceName() {
        return voiceName;
    }

}
