package project;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UIMain extends Application {
    private static Processor pro = new Processor();
    private static Integer hours = pro.getHours();
    private static Integer minutes = pro.getMinutes();
    private static Text h;
    private Text columns;
    private static Text m;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FlowPane rootNode = new FlowPane(20, 20);

        h = new Text(hours.toString());
        columns = new Text(":");
        m = new Text(minutes.toString());

        rootNode.getChildren().addAll(h, columns, m);

        Scene scene = new Scene(rootNode, 200, 50);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

// todo redesign the class - should be one more thread to change the numbers


    public static void main(String[] args) {
        Thread t = new Thread(pro);
        t.start();

        launch();

    }
}
