package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuController {
    @FXML Button start;
    @FXML Button edit;
    @FXML Button exit;
    private Stage mystage;

    public void clicked(ActionEvent event){
        String id = ((Button) event.getSource()).getId();
        System.out.println(id);
        if(id.equals("start")){

        }else if(id.equals("edit")){

        }else{
            mystage.close();
        }
    }

    public void setup(Stage stage){
        mystage = stage;
    }

}
