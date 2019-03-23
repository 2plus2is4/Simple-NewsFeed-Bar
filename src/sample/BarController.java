package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class BarController {
    @FXML Button X;
    @FXML TextArea News;
    private Stage mystage;
    public void setup(Stage stage) {
        mystage = stage;
    }

    public void close(ActionEvent event) {
        mystage.close();
    }
}
