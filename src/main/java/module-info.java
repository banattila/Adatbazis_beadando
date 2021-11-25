module hu.banattila.beadando_prog {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires mysql.connector.java;

    opens hu.banattila.beadando_prog to java.sql, javafx.fxml;
    exports hu.banattila.beadando_prog;
    exports hu.banattila.beadando_prog.models;
    exports hu.banattila.beadando_prog.utils;
    opens hu.banattila.beadando_prog.utils to java.sql, javafx.fxml;
}