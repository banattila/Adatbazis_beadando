package hu.banattila.beadando_prog.utils;

import javafx.scene.control.Alert;

public class MyAlert {
    Alert alert;

    public MyAlert(String title, String message){
        alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.show();
    }
}
