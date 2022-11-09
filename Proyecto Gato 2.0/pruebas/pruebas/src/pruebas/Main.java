package pruebas;

import javafx.application.Application;
import javafx.stage.Stage;
/**
 *
 * @author Brandon Vargas
 */
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        Interface iE = new Interface();
      //  Scene gameScene = new Scene(iE.welcomeScene(),550,550);
        iE.login();
    }
    public static void main(String[] args) {
        launch(args);
    }
}