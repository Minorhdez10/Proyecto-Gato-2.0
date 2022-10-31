package proyectogato;


import javafx.application.Application;

//import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Usuario
 */

public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Interface iE = new Interface();
      //  Scene gameScene = new Scene(iE.welcomeScene(),550,550);
        
        iE.logeo();
    }

  
    public static void main(String[] args) {
        launch(args);
        
    }
    
}
