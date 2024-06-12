module hangman {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;
    requires com.fasterxml.jackson.databind;
    requires json.simple;
    requires org.json;
    requires postgresql;

    opens hangman to javafx.fxml;
    exports hangman;
}