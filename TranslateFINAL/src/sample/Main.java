//Vanessa Saunders 0570255 and Anecia Johnson 0598121

package sample;
//imports FXML, scene & stage packages

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
    public Main () { //constructor

    }
public void start(Stage primaryStage) throws Exception{  //start of application, loads source fxml & stage dimensions

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Indigenous Language Translator");
        primaryStage.setScene(new Scene(root, 800, 700));
        primaryStage.show();
    }

//main method for JavaFX

    public static void main(String[] args) {
        launch(args);
    }
}
