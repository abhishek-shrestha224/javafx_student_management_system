module com.example {
    requires transitive javafx.controls;
    requires transitive javafx.fxml;
    requires com.google.gson;
    requires org.apache.commons.codec;

    opens com.example to javafx.fxml;
    opens com.example.controllers to javafx.fxml;
    opens com.example.models to com.google.gson, javafx.base, javafx.graphics;
    opens com.example.helpers to com.google.gson;

    exports com.example;
    exports com.example.helpers;
}
