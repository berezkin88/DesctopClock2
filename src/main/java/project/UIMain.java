package project;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.text.DecimalFormat;

/**
 * here is the UI is build and the clock is processed
 */

public class UIMain extends Application {

    // variables
    private double xOffset = 0;
    private double yOffset = 0;
    private static Time time = new Time();
    private static Integer hours = time.getHours();
    private static Integer minutes = time.getMinutes();
    private static int seconds = time.getSeconds();
    private static Text h;
    private static Text columns;
    private static Text m;

    @Override
    public void start(Stage primaryStage) throws Exception {
        DecimalFormat fmt = new DecimalFormat("00");
        FlowPane rootNode = new FlowPane(20, 20);

//        drag a stage over the desktop
        rootNode.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        rootNode.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });

        // digits and columns
        h = new Text(fmt.format(hours));
        columns = new Text(":");
        m = new Text(fmt.format(minutes));

        // set font and size and columns position
        h.setFont(new Font("Courier", 42));
        columns.setFont(new Font("Courier", 42));
        columns.setStyle(" -fx-translate-y: -3 ");
        m.setFont(new Font("Courier", 42));

        //alignment
        rootNode.setAlignment(Pos.CENTER);

        rootNode.getChildren().addAll(h, columns, m);

        //draw the scene
        Scene scene = new Scene(rootNode, 160, 50);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setOpacity(0.3);
        primaryStage.show();
    }

    public static void main(String[] args) {

//        spawn the counting thread
        Thread t = new Thread(UIMain::doWork);
        t.setDaemon(true);
        t.start();

//        launch the UI
        launch();
    }

//    counting time
    private static void doWork() {
        int firstTimeout = 1000;
        int timeout = firstTimeout;
        long finishTime = 0L;
        boolean isVisible = true;

        while (true) {
            long startTime = System.currentTimeMillis();
            try {
                Thread.sleep(timeout);
                elapse();
                isVisible = twinkle(isVisible);
                finishTime = System.currentTimeMillis();
            } catch (InterruptedException x) {
                x.printStackTrace();
            }

            int adjust = (int) (finishTime - startTime);
//            System.out.println(adjust);

//            checking with System time and adjusting if needed
            timeout = adjust > 1000 ? firstTimeout - (adjust - 1000) : firstTimeout + (1000 - adjust);
        }
    }

//    making colons twinkles
    private static boolean twinkle(boolean isVisible) {

        if (isVisible) {
            columns.setVisible(isVisible);
            return false;
        } else {
            columns.setVisible(isVisible);
            return true;
        }
    }

    //    computing seconds, minutes and hours
    private static void elapse() {
        DecimalFormat fmt = new DecimalFormat("00");
        if (seconds == 59) {
            seconds = 0;
            minutes++;
            m.setText(fmt.format(minutes));
            if (minutes == 60) {
                minutes = 0;
                m.setText(fmt.format(minutes));
                hours++;
                h.setText(fmt.format(hours));
                if (hours == 24) {
                    hours = 0;
                    h.setText(fmt.format(hours));
                }
            }
        } else {
            seconds ++;
        }
    }
}
