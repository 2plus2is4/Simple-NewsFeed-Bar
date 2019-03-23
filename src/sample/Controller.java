package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Controller {
    @FXML Button start;
    @FXML Button edit;
    @FXML Button exit;

    public void clicked(ActionEvent event){
        String id = ((Button) event.getSource()).getId();
        System.out.println(id);
        if(id.equals("start")){

        }else if(id.equals("start")){

        }else{

        }
    }

}
