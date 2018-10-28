package project;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UIMain extends Application {
    private static Time time = new Time();
    private static Integer hours = time.getHours();
    private static Integer minutes = time.getMinutes();
    private static int seconds = time.getSeconds();
    private static Text h;
    private static Text columns;
    private static Text m;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FlowPane rootNode = new FlowPane(20, 20);

        h = new Text(hours.toString());
        columns = new Text(":");
        m = new Text(minutes.toString());
        h.setFont(new Font("Courier", 36));
        columns.setFont(new Font("Courier", 36));
        m.setFont(new Font("Courier", 36));

        rootNode.setAlignment(Pos.CENTER);

        rootNode.getChildren().addAll(h, columns, m);

        Scene scene = new Scene(rootNode, 200, 50);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

// todo redesign the class - should be one more thread to change the numbers
    public static void main(String[] args) {
        new Thread(UIMain::doWork).start();
        launch();
    }

    private static void doWork() {
        int firstTimeout = 1000;
        int timeout = firstTimeout;
        long finishTime = 0L;

        while (true) {
            long startTime = System.currentTimeMillis();
            try {
                Thread.sleep(timeout);
                elapse();
                finishTime = System.currentTimeMillis();
            } catch (InterruptedException x) {
                x.printStackTrace();
            }

            int adjust = (int) (finishTime - startTime);
            System.out.println(adjust);

            timeout = adjust > 1000 ? firstTimeout - (adjust - 1000) : firstTimeout + (1000 - adjust);
        }
    }

    private static void elapse() {
        if (seconds == 59) {
            seconds = 0;
            minutes++;
            m.setText(minutes.toString());
            if (minutes == 60) {
                minutes = 0;
                m.setText(minutes.toString());
                hours++;
                h.setText(hours.toString());
                if (hours == 24) {
                    hours = 0;
                    h.setText(hours.toString());
                }
            }
        } else {
            seconds ++;
        }
    }
}
