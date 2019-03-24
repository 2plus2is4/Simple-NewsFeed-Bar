import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Objects;

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

    public void setup(Stage stage) {
        mystage = stage;
        add.setDisable(true);
        save.setDisable(true);
    }

    public void click(ActionEvent event) throws IOException, TransformerException, ParserConfigurationException {
        String button = ((Button) event.getSource()).getText();
        if (button.equals("Add")) {
            list.getItems().add(txt.getText());
            txt.clear();
        } else if (button.equals("Remove")) {
            ObservableList x = list.getSelectionModel().getSelectedItems();
            if (x.size() > 0) {
                while (!x.isEmpty()) {
                    list.getItems().remove(x.remove(0));
                }
            }
        } else if (button.equals("Save")) {
            FileChooser dc = new FileChooser();
            dc.setInitialDirectory(new File(System.getProperty("user.dir")));
            dc.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML File", "*.XML"));
            File f = dc.showSaveDialog(mystage);
            String lol = f.getPath();
//            String done = lol.split("\.XML");
            ArrayList<String> strings = new ArrayList<>();
            for (int i = 0; i < list.getItems().size(); i++) {
                strings.add((String) list.getItems().get(i));
            }
            saveXML.save(strings,f.getPath());
        } else if (button.equals("Load")) {
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
                for (String string : strings) {
                    list.getItems().add(string);
                }
            }
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sample.fxml"));
            Parent root = fxmlLoader.load();
            MenuController menuController = fxmlLoader.getController();
            menuController.setup(mystage);
//        mystage.initStyle(StageStyle.DECORATED);
            mystage.setX(533.0);
            mystage.setY(151.0);
            mystage.setAlwaysOnTop(false);
            mystage.setScene(new Scene(root, 300, 275));
            mystage.show();
            System.out.println(mystage);
        }
    }

    public void typing(KeyEvent event) {
        if (txt.getText().length() < 1) {
            add.setDisable(true);
            save.setDisable(true);
        } else {
            add.setDisable(false);
            save.setDisable(false);
        }
    }

}
