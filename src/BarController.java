import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class BarController{
    @FXML Button X;
    @FXML TextField News;
    private Stage mystage;
    private int iterator = -1;
    private ArrayList<String> news;

    void setup(Stage stage, String string) {
        news = new ArrayList<>();
        news.add("12:00 pm Dr. Hassan Hamza - Lecture Topic 1");
        news.add("01:00 pm Dr. Hassan Hamza - Lecture Topic 2");
        news.add("02:00 pm Dr. Hassan Hamza - Lecture Topic 3");
        news.add("03:00 pm Dr. Hassan Hamza - Lecture Topic 4");
        mystage = stage;
        try {
            news = readXML.read(string);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override public void run() {
                // textField_t.setText(YOUR TEXT);
                if(news!=null) {
                    if (iterator == news.size() - 1)
                        iterator = -1;
                    News.setText(news.get(++iterator));
                }
            }
        }, 0L, 5000L);
    }

    public void close(ActionEvent event) throws IOException {
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
