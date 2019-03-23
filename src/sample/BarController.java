package sample;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.util.ArrayList;

public class BarController implements Runnable{
    @FXML Button X;
    @FXML TextArea News;
    private Stage mystage;
    private ArrayList<String> news;
    void setup(Stage stage, String string) {
        mystage = stage;
        try {
            news = readXML.read(string);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close(ActionEvent event) {
        mystage.close();
    }

    @Override
    public void run() {

    }
}
