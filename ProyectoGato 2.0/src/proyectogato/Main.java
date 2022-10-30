package proyectogato;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Usuario
 */

public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Interface iE = new Interface();
        
        primaryStage.setScene(iE.welcomeScene());
        primaryStage.setTitle("Juego del Gato");
        primaryStage.getIcons().add(new Image("cat icon.png"));
        primaryStage.show();
    }

  
    public static void main(String[] args) {
        launch(args);
        
    }
    
}