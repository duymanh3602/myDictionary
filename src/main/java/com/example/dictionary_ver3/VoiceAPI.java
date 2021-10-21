package com.example.dictionary_ver3;

import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.text_to_speech.v1.model.SynthesizeOptions;
import com.ibm.watson.text_to_speech.v1.util.WaveUtils;

import java.io.*;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine.Info;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

import static javax.sound.sampled.AudioSystem.getAudioInputStream;
import static javax.sound.sampled.AudioFormat.Encoding.PCM_SIGNED;

import com.voicerss.tts.AudioCodec;
import com.voicerss.tts.Languages;
import com.voicerss.tts.VoiceParameters;
import com.voicerss.tts.VoiceProvider;



public class VoiceAPI {

    private static AudioFormat getOutFormat(AudioFormat inFormat) {
        final int ch = inFormat.getChannels();

        final float rate = inFormat.getSampleRate();
        return new AudioFormat(PCM_SIGNED, rate, 16, ch, ch * 2, rate, false);
    }

    private static void stream(AudioInputStream in, SourceDataLine line)
            throws IOException {
        final byte[] buffer = new byte[4096];
        for (int n = 0; n != -1; n = in.read(buffer, 0, buffer.length)) {
            line.write(buffer, 0, n);
        }
    }

    //https://stackoverflow.com/questions/2416935/how-to-play-wav-files-with-java
    public static void getVoiceEng(String text) {
        String myAPIUrl = "https://api.au-syd.text-to-speech.watson.cloud.ibm.com/instances/174dc2d3-1c8d-48e6-b74d-a9094c96cc51";
        IamAuthenticator authenticator = new IamAuthenticator("i_CxVjxprUzHkXrSeZget9f-l4jdPTPnYzEfn7rYIoze");
        TextToSpeech textToSpeech = new TextToSpeech(authenticator);
        textToSpeech.setServiceUrl(myAPIUrl);

        try {
            SynthesizeOptions synthesizeOptions =
                    new SynthesizeOptions.Builder()
                            .text(text)
                            .accept("audio/wav")
                            .voice("en-US_AllisonV3Voice")
                            .build();

            InputStream inputStream =
                    textToSpeech.synthesize(synthesizeOptions).execute().getResult();
            InputStream in = WaveUtils.reWriteWaveHeader(inputStream);

            OutputStream out = new FileOutputStream("eng_voice.wav");
            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }

            out.close();
            in.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void getVoiceVie(String text) throws Exception {
        VoiceProvider tts = new VoiceProvider("1dcdf6704b26484589e66302932600f1");

        VoiceParameters params = new VoiceParameters(text, Languages.Vietnamese);
        params.setCodec(AudioCodec.WAV);
        params.setFormat(com.voicerss.tts.AudioFormat.Format_16KHZ.AF_16khz_16bit_mono);
        params.setBase64(false);
        params.setSSML(false);
        params.setRate(0);

        byte[] voice = tts.speech(params);

        FileOutputStream fos = new FileOutputStream("vie_voice.wav");
        fos.write(voice, 0, voice.length);
        fos.flush();
        fos.close();
    }

    public static void play(String filePath) {
        final File file = new File(filePath);

        try (final AudioInputStream in = getAudioInputStream(file)) {

            final AudioFormat outFormat = getOutFormat(in.getFormat());
            final Info info = new Info(SourceDataLine.class, outFormat);

            try (final SourceDataLine line =
                         (SourceDataLine) AudioSystem.getLine(info)) {

                if (line != null) {
                    line.open(outFormat);
                    line.start();
                    stream(getAudioInputStream(outFormat, in), line);
                    line.drain();
                    line.stop();
                }
            }

        } catch (UnsupportedAudioFileException
                | LineUnavailableException
                | IOException e) {
            throw new IllegalStateException(e);
        }
    }


}
