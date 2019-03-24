import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class EditController {
    @FXML
    TextField txt;
    @FXML
    Button add;
    @FXML
    Button remov;
    @FXML
    ListView list;
    @FXML
    Label lbl;
    @FXML
    Button save;
    @FXML
    Button load;
    private Stage mystage;

    void setup(Stage stage) {
        mystage = stage;
        add.setDisable(true);
        save.setDisable(true);
    }

    public void click(ActionEvent event) throws IOException, TransformerException, ParserConfigurationException {
        String button = ((Button) event.getSource()).getText();
        switch (button) {
            case "Add":
                list.getItems().add(txt.getText());
                txt.clear();
                add.setDisable(true);
                break;
            case "Remove":
                ObservableList x = list.getSelectionModel().getSelectedItems();
                if (x.size() > 0) {
                    for (Object o : x) {
                        list.getItems().remove(o);
                    }
                    add.setDisable(false);
                    save.setDisable(false);
                }
                break;
            case "Save": {
                FileChooser dc = new FileChooser();
                dc.setInitialDirectory(new File(System.getProperty("user.dir")));
                dc.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML File", "*.XML"));
                File f = dc.showSaveDialog(mystage);
                ArrayList<String> strings = new ArrayList<>();
                for (int i = 0; i < list.getItems().size(); i++) {
                    strings.add((String) list.getItems().get(i));
                }
                saveXML.save(strings, f.getPath());
                break;
            }
            case "Load": {
                FileChooser dc = new FileChooser();
                dc.setInitialDirectory(new File(System.getProperty("user.dir")));
                dc.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML File", "*.XML"));
                File f = dc.showOpenDialog(mystage);
                System.out.println(System.getProperty("user.dir"));
                if (f != null) {
                    ArrayList<String> strings = readXML.read(f.getPath());
                    while (list.getItems().size() > 0) {
                        list.getItems().remove(0);
                    }
                    assert strings != null;
                    for (String string : strings) {
                        list.getItems().add(string);
                    }
                }
                break;
            }
            default:
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sample.fxml"));
                Parent root = fxmlLoader.load();
                MenuController menuController = fxmlLoader.getController();
                menuController.setup(mystage);
                mystage.setX(533.0);
                mystage.setY(151.0);
                mystage.setAlwaysOnTop(false);
                mystage.setScene(new Scene(root, 300, 275));
                mystage.show();
                System.out.println(mystage);
                break;
        }
    }

    public void typing() {
        if (txt.getText().length() < 1) {
            add.setDisable(true);
            save.setDisable(true);
        } else {
            add.setDisable(false);
            save.setDisable(false);
        }
    }

}
