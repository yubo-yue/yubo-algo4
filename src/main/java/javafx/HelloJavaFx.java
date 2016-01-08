package javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Created by yubo on 12/19/15.
 */
public class HelloJavaFx extends Application {

    public static void main(String[] args) {
        // how to bootstrap a JavaFX application
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Label message = new Label("Hello, JavaFX!");

        message.setFont(new Font(100));
        primaryStage.setScene(new Scene(message));
        primaryStage.setTitle("Hello");
        primaryStage.show();
    }


}
