module com.example.dictionary_ver3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires freetts;
    requires sdk.core;
    requires language.translator;
    requires java.sql;
    requires java.desktop;
    requires text.to.speech;
    requires voicerss.tts;


    opens com.example.dictionary_ver3 to javafx.fxml;
    exports com.example.dictionary_ver3;
}