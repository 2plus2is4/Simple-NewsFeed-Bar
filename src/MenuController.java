import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.*;

import java.io.File;
import java.io.IOException;

public class MenuController {
    @FXML
    Button start;
    @FXML
    Button edit;
    @FXML
    Button exit;
    private Stage mystage;

    public void clicked(ActionEvent event) throws IOException {
        String id = ((Button) event.getSource()).getId();
        switch (id) {
            case "start":
                FileChooser dc = new FileChooser();
                dc.setInitialDirectory(new File(System.getProperty("user.dir")));
                dc.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML File", "*.XML"));
                File f = dc.showOpenDialog(mystage);
                System.out.println(System.getProperty("user.dir"));
                if (f != null) {
                    readXML.read(f.getPath());
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("bar.fxml"));
                    Parent root = fxmlLoader.load();
                    /**/
                    mystage.setAlwaysOnTop(true);
                    mystage.setX(0);
                    mystage.setY(Screen.getPrimary().getVisualBounds().getMaxY() - Screen.getPrimary().getVisualBounds().getMaxY() / 20);
                    /**/
                    BarController barController = fxmlLoader.getController();
                    barController.setup(mystage, f.getPath());
                    mystage.setScene(new Scene(root, Screen.getPrimary().getVisualBounds().getWidth(), Screen.getPrimary().getVisualBounds().getMaxY() / 20));
                    mystage.show();
                }
                break;
            case "edit":
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("edit.fxml"));
                Parent root = fxmlLoader.load();
                EditController editController = fxmlLoader.getController();
                editController.setup(mystage);
                mystage.setScene(new Scene(root, 680, 540));
                mystage.show();
                break;
            default:
                mystage.close();
                break;
        }
    }

    void setup(Stage stage) {
        mystage = stage;
    }

}
