package sample;

import com.sun.javafx.tk.Toolkit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class MenuController {
    @FXML Button start;
    @FXML Button edit;
    @FXML Button exit;
    private Stage mystage;

    public void clicked(ActionEvent event) throws IOException {
        String id = ((Button) event.getSource()).getId();
        System.out.println(id);
        if(id.equals("start")){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("bar.fxml"));
            Parent root = fxmlLoader.load();
            mystage.setAlwaysOnTop(true);
            mystage.setX(0);mystage.setY(Screen.getPrimary().getVisualBounds().getMaxY()-30);
            BarController barController = fxmlLoader.getController();
            barController.setup(mystage,"hi");
            mystage.setScene(new Scene(root, Screen.getPrimary().getVisualBounds().getWidth(), 30));
            mystage.show();
        }else if(id.equals("edit")){

        }else{
            mystage.close();
        }
    }

    public void setup(Stage stage){
        mystage = stage;
    }

}
