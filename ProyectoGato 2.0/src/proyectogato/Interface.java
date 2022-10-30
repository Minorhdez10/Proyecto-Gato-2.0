package proyectogato;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Usuario
 */

public class Interface extends LogicClass {
    
    //Escena principal de bienvenida
    public Scene welcomeScene(){
        VBox vbContainer = new VBox();
        vbContainer.setSpacing(20);
        vbContainer.setStyle("-fx-background-color: orange");
        vbContainer.setAlignment(Pos.CENTER);
        
        HBox hboxBottom = new HBox();
        hboxBottom.setPadding(new Insets(10, 10, 10, 10));
        hboxBottom.setStyle("-fx-background-color: orange");
        
        Label lbPlayerOne = new Label("Ingresar nombre Jugador 1");
        lbPlayerOne.setStyle("-fx-text-fill: white; -fx-font-size: 17px;");
        TextField tfPlayerOne = new TextField();
        tfPlayerOne.setAlignment(Pos.CENTER);
        tfPlayerOne.setMaxWidth(200);
        
        Label lbPlayerTwo = new Label("Ingresar nombre Jugador 2");
        lbPlayerTwo.setStyle("-fx-text-fill: white; -fx-font-size: 17px;");
        Label warning = new Label("");
        TextField tfPlayerTwo = new TextField();
        tfPlayerTwo.setAlignment(Pos.CENTER);
        tfPlayerTwo.setMaxWidth(200); 
        
        Label title = new Label("Juego del Gato");
        title.setStyle("-fx-font-size: 25px;");
        
         Button btnGoGame = new Button("Aceptar");
         btnGoGame.setMinHeight(40);
         btnGoGame.setCursor(Cursor.HAND);
         //Da acción al botón aceptar, recoge los nombres, cambia de escena y limpia los textfields
         btnGoGame.setOnAction(new EventHandler<ActionEvent>(){
              @Override
            public void handle(ActionEvent event) {
                playerOneName = tfPlayerOne.getText();
                playerTwoName = tfPlayerTwo.getText();
                if (tfPlayerOne.getText().isEmpty() || tfPlayerTwo.getText().isEmpty()) {
                    warning.setText("Llena los espacios con algún nombre ");
                    warning.setStyle("-fx-font-size: 25px;");
                    tfPlayerOne.setStyle("-fx-background-color: red; -fx-text-fill: white");
                    tfPlayerTwo.setStyle("-fx-background-color: red;-fx-text-fill: white");
                } 
                else{
                catGame(playerOneName,playerTwoName);
                showFirstPlayer();
                tfPlayerOne.clear();//Limpiar los textfields
                tfPlayerTwo.clear();
                }
            }
         });
         //MouseEvent del botón aceptar, muestra su funcionalidad al usuario
         btnGoGame.setOnMouseEntered(new EventHandler() {
                    @Override
                    public void handle(Event event) {
                        btnGoGame.setTooltip(new Tooltip("Carga la escena del juego"));
                    }
        });
         
        vbContainer.getChildren().addAll(title,lbPlayerOne,tfPlayerOne,lbPlayerTwo,tfPlayerTwo,warning,btnGoGame);
        hboxBottom.getChildren().addAll(btnDescription);
        bpnWelcome.setCenter(vbContainer);
        bpnWelcome.setBottom(hboxBottom);

        btnDescription.setCursor(Cursor.HAND);
        //Muestra la descripción del juego
        btnDescription.setOnAction(new EventHandler<ActionEvent>(){
            @Override
                public void handle(ActionEvent event){
                    showGameDescription();
                }
            });

        Scene welcomeScene = new Scene(bpnWelcome,550,550);
        return welcomeScene;
    }
    
    //Escena del juego
    public Scene catGame(String a, String b){
        howWon.setText("");
        GridPane gpn = new GridPane();
        BorderPane bpn = new BorderPane();
        HBox hboxTop = new HBox();
        hboxTop.setAlignment(Pos.CENTER);
        gpn.setMaxSize(20, 20);
        gpn.setAlignment(Pos.CENTER);
        
        GridPane gpBottom = new GridPane();
        gpBottom.setPadding(new Insets(10, 10, 10, 10));
        gpBottom.setVgap(20);
        gpBottom.setHgap(68);
        
        playerTurn.setStyle("-fx-text-fill: white; -fx-font-size: 18px;");
        
        Label lbFilling = new Label("   ");
        Button btnRestart = new Button("Reiniciar juego");
        Button btnCredits = new Button("Créditos");
        gpBottom.add(btnNewGame, 0, 0);
        gpBottom.add(btnRestart, 2, 0);
        gpBottom.add(btnCredits, 4, 0);
        gpBottom.add(lbFilling,3,0);
            
        fillButtonsMatriz();// llama a este metodo que llena a la matriz de botones vacios
        
        for (int i = 0; i < matrixGame.length; i++) {//Añade los botones al gridpane y les pone el tamaño
            for (int c = 0; c < matrixGame[0].length; c++) {
                gpn.add(matrixGame[i][c], c, i);
                matrixGame[i][c].setMinHeight(70);
                matrixGame[i][c].setMinWidth(70);
            }
        }
        
        hboxTop.getChildren().addAll(playerTurn,howWon);
        
        bpn.setTop(hboxTop);
        bpn.setCenter(gpn);
        bpn.setBottom(gpBottom);
        bpn.setStyle("-fx-background-color: orange");
        
        
        for(int r=0; r<matrixGame.length; r++){
            int rows = r;
            for(int c=0; c<matrixGame[0].length; c++){
                int columns = c;

                matrixGame[rows][columns].setCursor(Cursor.HAND);

                matrixGame[rows][columns].setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        if (changePlayerTurn()) {// si turno de del jugador (true o false) , changePlayerTurn alterna  TODO el rato en si es true la pone en falso y si es falso en true 
                            matrixGame[rows][columns].setText("X");// si es true que ponga x 
                        } else {
                            matrixGame[rows][columns].setText("O");// sino una 0
                        }
                        matrixGame[rows][columns].setDisable(true);// si alguuien toca ese boton entonces lo desabilita,
                        showWinner();// llama al metodo si gana filas o todos, y dice si hay empate
                        clicksCounter++;
                }
        });
            }
        }

        btnRestart.setCursor(Cursor.HAND);
        //Reinicia el juego
        btnRestart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               restartMatrixGame();
            }
        });
        
        //MouseEvent que muestra la funcionalidad del botón Reiniciar juego
        btnRestart.setOnMouseEntered(new EventHandler() {
                    @Override
                    public void handle(Event event) {
                        btnRestart.setTooltip(new Tooltip("Quita las X y los O de los botones del juego"));
                    }
        });

        btnCredits.setCursor(Cursor.HAND);
        //Muestra los créditos
        btnCredits.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               creditsScene();
            }
        });
        
        //MouseEvent que muestra la funcionalidad del botón Créditos
        btnCredits.setOnMouseEntered(new EventHandler() {
                    @Override
                    public void handle(Event event) {
                        btnCredits.setTooltip(new Tooltip("Muestra los créditos del juego"));
                    }
        });

        Stage gameStage = new Stage();

        btnNewGame.setCursor(Cursor.HAND);
        //Vuelve a la escena del inicio para ingresar los nombres de nuevo
        btnNewGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               //bpnWelcome.getChildren().setAll(welcomeScene());
                //welcomeScene();
                gameStage.close();
               restartMatrixGame();
            }
        });
        
        //MouseEvent que muestra la funcionalidad del botón Nuevo juego
        btnNewGame.setOnMouseEntered(new EventHandler() {
                    @Override
                    public void handle(Event event) {
                        btnNewGame.setTooltip(new Tooltip("Vuelve a la escena del inicio"));
                    }
        });

        Scene gameScene = new Scene(bpn,550,550);
        gameStage.setScene(gameScene);
        gameStage.setTitle("Juego del Gato");
        gameStage.show();
        return gameScene;
    }

    //Escena de la descripción del juego
    public Scene showGameDescription(){
        VBox vbDesc = new VBox();
        vbDesc.setStyle("-fx-background-color: orange");
        vbDesc.setSpacing(20);
        Button btnHideDescription = new Button("Cerrar ventana");
        btnHideDescription.setCursor(Cursor.HAND);
        vbDesc.setAlignment(Pos.CENTER);
        Label lbText = new Label("El juego del gato o tic tac toe es un juego entre dos jugadores "
                + "que se identifican con signos distintos: X y O,\nlos cuales marcan por turnos en un tablero de 3X3 alternadamente. "
                + "El jugador que consigue trazar primero \nuna línea con tres de sus símbolos, "
                + "ya sea vertical, horizontal o diagonal resulta ganador.");
        lbText.setStyle("-fx-font-size: 14px;");
        vbDesc.getChildren().addAll(lbText,btnHideDescription);
        Scene descScene = new Scene(vbDesc,700,120);
        //Stage descStage = new Stage();
        descStage.setScene(descScene);
        descStage.setTitle("Descripción del juego");
        descStage.getIcons().add(new Image("cat icon.png"));
        descStage.show();
        //Cuando se le da al botón cierra la ventana
        btnHideDescription.setOnAction(new EventHandler<ActionEvent>(){
            @Override
                public void handle(ActionEvent event){
                    descStage.hide();
                }
            });
        
        return descScene;
    }
    
    //Escena donde da la bienvenida y muestra el jugador que inicia
    public void showFirstPlayer(){
        Button btnGoGame = new Button("Cerrar ventana");
        btnGoGame.setCursor(Cursor.HAND);
        //Llama al método catGame que contiene la escena del juego, así cuando el botón es presionado, se muestra la otra escena
        //bpnWelcome.getChildren().setAll(catGame(playerOneName,playerTwoName));
        //catGame(playerOneName,playerTwoName);
        VBox vbFirstPlayer = new VBox();
        vbFirstPlayer.setStyle("-fx-background-color: orange");
        vbFirstPlayer.setSpacing(30);
        vbFirstPlayer.setAlignment(Pos.CENTER);
        Label lbWelcome = new Label("¡Bienvenid@s " + playerOneName + " y " + playerTwoName + "\n" +
                                    "empieza jugando primero " + whoStart());//Muestra mensaje de bienvenida y que jugador empieza
        
        //turnoJugador.setText("Turno de: " + firstTurn);
        lbWelcome.setStyle("-fx-font-size: 16px;");
        vbFirstPlayer.getChildren().addAll(lbWelcome,btnGoGame);
        Scene sceneWelcome = new Scene(vbFirstPlayer,250,250);
        Stage stageWelcome = new Stage();
        stageWelcome.setScene(sceneWelcome);
        stageWelcome.setTitle("¡Bienvenid@s!");
        stageWelcome.getIcons().add(new Image("cat icon.png"));
        stageWelcome.show();    
        
        //Si se le da al botón cierra la ventana
        btnGoGame.setOnAction(new EventHandler<ActionEvent>(){
            @Override
                public void handle(ActionEvent event){
                    stageWelcome.hide();
                }
            });
    }
    
    //Escena de los créditos
    public void creditsScene(){
        VBox vbContainer = new VBox();
        vbContainer.setStyle("-fx-background-color: orange");
        vbContainer.setAlignment(Pos.CENTER);
        vbContainer.setSpacing(20);
        Label lbText = new Label("         Proyecto del curso IF2000\n \n        "
                                + "Brandon Vargas Solano C28223 \n \n    "
                                + "Minor Hernández Navarro C23766\n \n"
                                + "Kendall Sánchez Chinchilla C27227");
        lbText.setStyle("-fx-font-size: 20px;");
        Button btnCredits = new Button("Cerrar créditos");
        btnCredits.setCursor(Cursor.HAND);

        vbContainer.getChildren().addAll(lbText,btnCredits);
        
        Scene creditsScene = new Scene(vbContainer,430,300);
        
        Stage creditsStage = new Stage();
        creditsStage.setScene(creditsScene);
        creditsStage.setTitle("Créditos");
        creditsStage.getIcons().add(new Image("cat icon.png"));
        creditsStage.show();
        
        //Si se le da al botón se cierra la ventana
        btnCredits.setOnAction(new EventHandler<ActionEvent>(){
              @Override
            public void handle(ActionEvent event) {
                creditsStage.hide();
            }
         });
    }
    
}