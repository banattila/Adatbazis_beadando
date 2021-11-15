module hu.banattila.beadando_prog {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens hu.banattila.beadando_prog to javafx.fxml;
    exports hu.banattila.beadando_prog;
}