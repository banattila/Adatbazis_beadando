package hu.banattila.beadando_prog.utils;

import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import java.util.List;

public class MyAlert {
    Alert alert;

    public MyAlert(String title, String message) {
        alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.show();
    }

    public static <T> void alertWithAction(
            Alert alert, String message,
            String action, List<T> getAction, TableView<T> tw) {
        String res = action;
        tw.setItems(FXCollections.observableArrayList(getAction));
        alert.setAlertType(Alert.AlertType.INFORMATION);
        if (res == "Ok") {
            alert.setTitle("Siker");
            alert.setContentText(message);
        } else {
            alert.setTitle("Hiba");
            alert.setContentText(res);
        }
        alert.show();
    }
}
