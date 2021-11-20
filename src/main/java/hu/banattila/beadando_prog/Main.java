package hu.banattila.beadando_prog;

import hu.banattila.beadando_prog.utils.PizzeriaConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static PizzeriaConnection pc;
    @Override
    public void start(Stage stage) throws IOException {
        pc = new PizzeriaConnection();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1024, 768);
        stage.setTitle("Pizzéria");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}