import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class BarController{
    @FXML Button X;
    @FXML TextField News;
    private Stage mystage;
    private int iterator = -1;
    private ArrayList<String> news;
    private String bar;

    void setup(Stage stage, String string) {
        news = new ArrayList<>();
        mystage = stage;

        try {
            news = readXML.read(string);
            bar = new String();
            for (int i = 0; i < news.size(); i++) {
                bar.concat(news.get(i)+" * ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        TranslateTransition go = new TranslateTransition();
        go.setDuration(Duration.millis(350));
//        go.setToX(Screen.getPrimary().getVisualBounds().getMaxX());
        go.setToX(Screen.getPrimary().getBounds().getMaxX());
//        go.setAutoReverse(true);
        go.setNode(News);

        TranslateTransition come = new TranslateTransition();
        come.setDuration(Duration.millis(350));
        come.setToX(0);
//        come.setAutoReverse(true);
        come.setNode(News);

        AtomicBoolean dummy = new AtomicBoolean(true);
        Thread thread = new Thread(() -> {

            while(true) {
                if(dummy.get()) {
                    dummy.set(false);
                }else go.play();
                try {
                    Thread.sleep(175);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("layout: "+News.getLayoutX());
                System.out.println("translate: "+News.getTranslateX()+"\n=========");
                if (news != null) {
                    if (iterator == news.size() - 1)
                        iterator = -1;
                    News.setText(news.get(++iterator));
                }
                try {
                    Thread.sleep(300);
//                    News.setTranslateX(-500);
//                    News.setTranslateX(-Screen.getPrimary().getVisualBounds().getMaxX());
                    come.play();
                    System.out.println("layout: "+News.getLayoutX());
                    System.out.println("translate: "+News.getTranslateX()+"\n=========");
                    Thread.sleep(4525);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        thread.start();
//        news.add("12:00 pm Dr. Hassan Hamza - Lecture Topic 1");
//        news.add("01:00 pm Dr. Hassan Hamza - Lecture Topic 2");
//        news.add("02:00 pm Dr. Hassan Hamza - Lecture Topic 3");
//        news.add("03:00 pm Dr. Hassan Hamza - Lecture Topic 4");

//        Timer t = new Timer();
//        t.schedule(new TimerTask() {
//            @Override public void run() {
//                // textField_t.setText(YOUR TEXT);
//                transition.play();
//                if(news!=null) {
//                    if (iterator == news.size() - 1)
//                        iterator = -1;
//                    News.setText(news.get(++iterator));
//                }
//
//            }
//        }, 0L, 5000L);
    }

    public void close() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = fxmlLoader.load();
        MenuController menuController = fxmlLoader.getController();
        menuController.setup(mystage);
        mystage.setX(533.0);
        mystage.setY(151.0);
        mystage.setAlwaysOnTop(false);
        mystage.setScene(new Scene(root, 300, 275));
        mystage.show();
    }
}
