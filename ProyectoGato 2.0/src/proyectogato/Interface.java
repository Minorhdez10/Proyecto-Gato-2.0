package proyectogato;
//
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javax.swing.JOptionPane;
/**
 *
 * @author Brandon
 */
public class Interface extends LogicClass {

    //Escena principal de bienvenida
    public Scene welcomeScene() {
        Stage gameStage = new Stage();
        VBox vbContainer = new VBox();
        vbContainer.setSpacing(20);
        vbContainer.setStyle("-fx-background-color: orange");
        vbContainer.setAlignment(Pos.CENTER);
        BorderPane bpnWelcome = new BorderPane();
        HBox hboxBottom = new HBox();
        hboxBottom.setPadding(new Insets(10, 10, 10, 10));
        hboxBottom.setStyle("-fx-background-color: orange");
        Scene welcomeScene = new Scene(bpnWelcome, 550, 550);
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

        Label start = new Label("Juego del Gato");
        start.setStyle("-fx-font-size: 25px;");

        Button btnGoGame = new Button("Aceptar");
        Button btnDescription = new Button("Descripción");
        Button btn_goToLog = new Button("Volver a registrar");
        btnGoGame.setMinHeight(40);
        btnGoGame.setCursor(Cursor.HAND);
        //Da acción al botón aceptar, recoge los nombres, cambia de escena y limpia los textfields
        btnGoGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                playerOneName = tfPlayerOne.getText();// aqui de una capturo los nombres, esto cuando le de al boton de aceptar, los carga en esa variable global
                // la de los nombres
                playerTwoName = tfPlayerTwo.getText();

                // esto evalua si hay nombres, sino , no empieza
                if (tfPlayerOne.getText().isEmpty() || tfPlayerTwo.getText().isEmpty()) {
                    warning.setText("Llena los espacios con algún nombre ");
                    warning.setStyle("-fx-text-fill: black; -fx-font-size: 25px;");
                    tfPlayerOne.setStyle("-fx-background-color: red; -fx-text-fill: white");
                    tfPlayerTwo.setStyle("-fx-background-color: red;-fx-text-fill: white");
                } else {

                    showFirstPlayer();// esto manda aquel panelito que dice quien empieza por aleatorio,
                    // y cuando estoy ahi llamo a catgame, pd no hace falta pasarle los valores tipo textfield pd
                    tfPlayerOne.clear();//Limpiar los textfields
                    tfPlayerTwo.clear();
                    warning.setText("");
                    tfPlayerOne.setStyle("-fx-background-color: gray");
                    tfPlayerTwo.setStyle("-fx-background-color: gray");
                    gameStage.close();

                }
            }
        });
        btnGoGame.setOnMouseEntered((Event event) -> {
            btnGoGame.setTooltip(new Tooltip("Carga el juego"));
        });
        //Muestra la descripción del juego
        btnDescription.setOnAction((ActionEvent event) -> {
            showGameDescription();
        });
        btn_goToLog.setOnAction((ActionEvent event) -> {
            logeo();
            gameStage.close();
        });
        vbContainer.getChildren().addAll(start, lbPlayerOne, tfPlayerOne, lbPlayerTwo, tfPlayerTwo, warning, btnGoGame, btnDescription, btn_goToLog);
        hboxBottom.getChildren().addAll(btnDescription, btn_goToLog);
        bpnWelcome.setCenter(vbContainer);
        bpnWelcome.setBottom(hboxBottom);
        btnDescription.setCursor(Cursor.HAND);
        bpnWelcome.setCenter(vbContainer);
        gameStage.setScene(welcomeScene);
        gameStage.setTitle("   Bienvenidos  ");
        gameStage.getIcons().add(new Image("cat icon.png"));
        gameStage.show();
        return welcomeScene;
    }

    //Escena del juego
    public Scene catGame() {
        Stage gameStage = new Stage();
        GridPane gpnMatriz = new GridPane();
        BorderPane bpn = new BorderPane();

        gpnMatriz.setStyle("-fx-background-color: green");
        bpn.setStyle("-fx-background-color: orange");

        gpnMatriz.setMaxSize(20, 20);
        gpnMatriz.setAlignment(Pos.CENTER);
        VBox Vb_elementsTop = new VBox();
        GridPane gpBottom = new GridPane();
        gpBottom.setPadding(new Insets(10, 10, 10, 10));
        gpBottom.setVgap(20);
        gpBottom.setHgap(70);
        Button btnRestart = new Button("Reiniciar juego");
        Button btnCredits = new Button("créditos");
        Button btn_NewGame = new Button("Nuevo juego");
        playerTurn.setStyle("-fx-text-fill: white; -fx-font-size: 18px;");
        gpBottom.add(btn_NewGame, 0, 0);
        gpBottom.add(btnRestart, 2, 0);
        gpBottom.add(btnCredits, 4, 0);

        fillButtonsMatriz();// llama a este metodo que llena a la matriz de botones vacios

        MenuBar mB_Menu = new MenuBar();

//      Menú del sistema
        Menu m_PlaySuports = new Menu("Juego");
//       sub menus
        Menu m_Configuration = new Menu("Configuración");
        Menu m_Reports = new Menu("Reportes");
        Menu m_Help = new Menu("Ayuda");
        MenuItem mI_About = new MenuItem("Acerca de");
        mI_About.setOnAction((event) -> {
           showGameDescription();
        });
        MenuItem mI_Credits = new MenuItem("Créditos");
        mI_Credits.setOnAction((event) -> {
            creditsScene();
        });
        
        MenuItem mI_logPanel = new MenuItem(" Ir al loguin");
         mI_logPanel.setOnAction((event) -> {
            logeo();
            gameStage.close();
        });
          MenuItem mI_ChangePassword = new MenuItem(" Cambiar la contraseña ");
               mI_ChangePassword.setOnAction((event) -> {
           JOptionPane.showMessageDialog(null, "Prueba ");
        });
        MenuItem mI_Exit = new MenuItem(" Salir del juego ");
        mI_Exit.setOnAction((event) -> Platform.exit());
        m_PlaySuports.getItems().addAll(mI_Credits,mI_About,mI_logPanel,mI_ChangePassword,mI_Exit);
        
          MenuItem mI2_changeTypeOfToken = new MenuItem("Cambio de Fichas");
       mI2_changeTypeOfToken.setOnAction((event) -> {
            
        });
            m_Configuration.getItems().addAll(mI2_changeTypeOfToken);
            
          MenuItem mI3_listOfGames= new MenuItem("lista de partidas");
        mI3_listOfGames.setOnAction((event) -> {
            
        });
         MenuItem mI3_listOfGamePlayer= new MenuItem("lista de juegos por jugador");
        mI3_listOfGamePlayer.setOnAction((event) -> {
        });
             m_Reports.getItems().addAll(mI3_listOfGames,mI3_listOfGamePlayer);
        mB_Menu.getMenus().addAll(m_PlaySuports,m_Configuration,m_Reports,m_Help);

        for (int f = 0; f < matrixGame.length; f++) {//Añade los botones al gridpane y les pone el tamaño
            for (int c = 0; c < matrixGame[0].length; c++) {
                gpnMatriz.add(matrixGraphics[f][c], c, f);
                matrixGraphics[f][c].setMinHeight(90);
                matrixGraphics[f][c].setMinWidth(90);
            }
        }

        Vb_elementsTop.setAlignment(Pos.CENTER);
        Vb_elementsTop.getChildren().addAll(mB_Menu, playerTurn);

        bpn.setTop(Vb_elementsTop);
        bpn.setCenter(gpnMatriz);
        bpn.setBottom(gpBottom);
        bpn.setStyle("-fx-background-color: orange");

        for (int r = 0; r < matrixGame.length; r++) {
            int rows = r;
            for (int c = 0; c < matrixGame[0].length; c++) {
                int columns = c;
                matrixGraphics[rows][columns].setCursor(Cursor.HAND);
                matrixGraphics[rows][columns].setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        if (changePlayerTurn()) {// si tuno de del jugador (true o false) , changeplayer alterna  TODO el rato en si es true la pone en falso y si es falso en true 
                            Image img_u = new Image("icono_prueba.png");
                            matrixGraphics[rows][columns].setGraphic(new ImageView("icono_prueba.png"));
                            matrixGame[rows][columns].setText("X");// si es true que ponga x 
                        } else {
                            Image img_a = new Image("otraPrueba.png");
                            matrixGame[rows][columns].setText("O");// sino una 0
                            matrixGraphics[rows][columns].setGraphic(new ImageView("otraPrueba.png"));
                        }
                        matrixGame[rows][columns].setDisable(true);// si alguien toca ese boton entonces lo desabilita,
                        matrixGraphics[rows][columns].setDisable(true);
                        showWinner();// llama al metodo si gana filas o todos, y dice si hay empate
                        clicksCounter++;
                        // clicksCounter me dice que si llegó a 9 y no pasó por filas o columnas o diagonales entonces llego a 9 y estaba vacia por tanto empate
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
                btnRestart.setTooltip(new Tooltip("Quita los iconos y del juego para volver a jugar"));
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

        btn_NewGame.setCursor(Cursor.HAND);
        //Vuelve a la escena del inicio para ingresar los nombres de nuevo
        btn_NewGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                welcomeScene();
                restartMatrixGame();
                gameStage.close();

            }
        });

        //MouseEvent que muestra la funcionalidad del botón Nuevo juego
        btn_NewGame.setOnMouseEntered(new EventHandler() {
            @Override
            public void handle(Event event) {
                btn_NewGame.setTooltip(new Tooltip("Vuelve a colocar jugadores"));
            }
        });
  
        Scene gameScene = new Scene(bpn, 550, 550);
        gameStage.setScene(gameScene);
        gameStage.setTitle("Juego del Gato");
        gameStage.getIcons().add(new Image("cat icon.png"));
        gameStage.show();
        return gameScene;
    }

    //Escena de la descripción del juego
    public Scene showGameDescription() {
        Stage descStage = new Stage();
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
        vbDesc.getChildren().addAll(lbText, btnHideDescription);
        Scene descScene = new Scene(vbDesc, 700, 120);
        descStage.setScene(descScene);
        descStage.setTitle("Descripción del juego");
        descStage.getIcons().add(new Image("cat icon.png"));
        descStage.show();
        //Cuando se le da al botón cierra la ventana
        btnHideDescription.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                descStage.hide();
            }
        });

        return descScene;
    }

    //Escena donde da la bienvenida y muestra el jugador que inicia
    public void showFirstPlayer() {
        Button btnGoGame = new Button("Cerrar ventana");
        btnGoGame.setCursor(Cursor.HAND);
        //Llama al método catGame que contiene la escena del juego, así cuando el botón es presionado, se muestra la otra escena
        //bpnWelcome.getChildren().setAll(catGame(playerOneName,playerTwoName));
        //catGame(playerOneName,playerTwoName);
        VBox vbFirstPlayer = new VBox();
        vbFirstPlayer.setStyle("-fx-background-color: orange");
        vbFirstPlayer.setSpacing(30);
        vbFirstPlayer.setAlignment(Pos.CENTER);
        Label lbWelcome = new Label("¡Bienvenid@s " + playerOneName + " y " + playerTwoName + "\n"
                + "empieza jugando primero " + whoStart());//Muestra mensaje de bienvenida y que jugador empieza
        lbWelcome.setStyle("-fx-font-size: 16px;");
        vbFirstPlayer.getChildren().addAll(lbWelcome, btnGoGame);
        Scene sceneWelcome = new Scene(vbFirstPlayer, 250, 250);
        Stage stageWelcome = new Stage();
        stageWelcome.setScene(sceneWelcome);
        stageWelcome.setTitle("¡Bienvenid@s!");
        stageWelcome.getIcons().add(new Image("cat icon.png"));
        stageWelcome.show();

        //Si se le da al botón cierra la ventana
        btnGoGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stageWelcome.close();
                catGame();
            }
        });
    }

    //Escena de los créditos
    public void creditsScene() {
        VBox vbContainer = new VBox();
        vbContainer.setStyle("-fx-background-color: orange");
        vbContainer.setAlignment(Pos.CENTER);
        vbContainer.setSpacing(20);
        Label lbText = new Label("         Proyecto del curso IF2000\n \n        "
                + "Brandon Vargas Solano C28223 \n \n    "
                + "Minor Hernández Navarro C23766\n \n"
                + "Kendall Sánchez Chinchilla C27227");
        lbText.setStyle("-fx-font-size: 20px; -fx-text-fill: black;");
        Button btnCredits = new Button("Cerrar créditos");
        btnCredits.setCursor(Cursor.HAND);
        vbContainer.getChildren().addAll(lbText, btnCredits);
        Scene creditsScene = new Scene(vbContainer, 430, 300);
        Stage creditsStage = new Stage();
        creditsStage.setScene(creditsScene);
        creditsStage.setTitle("Créditos");
        creditsStage.getIcons().add(new Image("cat icon.png"));
        creditsStage.show();
        //Si se le da al botón se cierra la ventana
        btnCredits.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                creditsStage.close();
            }
        });
    }

    public void logeo() { // loguearse                                                        L O G U E O 
        Stage stageLogin = new Stage();
        VBox vbContainer = new VBox();
        vbContainer.setStyle("-fx-background-color: orange");
        vbContainer.setAlignment(Pos.CENTER);
        vbContainer.setSpacing(20);
        Label lB_User = new Label("  Bienvenido, ingrese su usuario  ");
        lB_User.setStyle("-fx-text-fill: black; -fx-font-size: 20px;");
        TextField tF_user = new TextField();
        tF_user.setAlignment(Pos.CENTER);
        tF_user.setMaxWidth(200);
        Label lB_password = new Label("  Contraseña  ");
        lB_password.setStyle("-fx-text-fill: black; -fx-font-size: 20px;");
        TextField tF_password = new TextField();
        tF_password.setMaxWidth(200);
        tF_password.setAlignment(Pos.CENTER);
        tF_user.setStyle("-fx-background-color: gray");
        tF_password.setStyle("-fx-background-color: gray");
        Button bTn_registerUser = new Button("Registrar");
        Label lB_WarningMessage = new Label("");
        lB_WarningMessage.setStyle("-fx-text-fill: black; -fx-font-size: 30px;");
        Label lB_WarningAccepted = new Label("");
        lB_WarningAccepted.setStyle("-fx-text-fill: green; -fx-font-size: 30px;");
        Scene loginScene = new Scene(vbContainer, 700, 500);
        bTn_registerUser.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (tF_user.getText().isEmpty()) {
                    lB_WarningMessage.setText(" Ingrese algún Usuario ");
                    lB_WarningMessage.setStyle("-fx-text-fill: black; -fx-font-size: 25px;");
                    tF_user.setStyle("-fx-background-color: red; -fx-text-fill: white");
                } else {
                    if (tF_password.getText().isEmpty()) {
                        lB_WarningMessage.setText(" Ingrese una Contraseña ");
                        lB_WarningMessage.setStyle("-fx-text-fill: black; -fx-font-size: 25px;");

                        tF_password.setStyle("-fx-background-color: red;-fx-text-fill: white");
                    } else {
                        welcomeScene();
                        tF_user.clear();//Limpiar los textfields
                        tF_password.clear();
                        lB_WarningMessage.setText("");
                        tF_user.setStyle("-fx-background-color: gray");
                        tF_password.setStyle("-fx-background-color: gray");
                        stageLogin.close();
                    }
                }
            }

        });
        vbContainer.getChildren().addAll(lB_User, tF_user, lB_password, tF_password, bTn_registerUser, lB_WarningMessage, lB_WarningAccepted);
        stageLogin.setScene(loginScene);
        stageLogin.setTitle("Registro");
        stageLogin.getIcons().add(new Image("cat icon.png"));
        stageLogin.show();
    }
}
