package ProyectoGato2;

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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javax.swing.JOptionPane;

/**
 *
 * @author Brandon Vargas, Kendall Sánchez, Minor Hernández
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
        hboxBottom.setSpacing(340);
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
        tfPlayerOne.setPromptText("Nombre del primer jugador ");
        tfPlayerTwo.setPromptText("Nombre del segundo jugador ");

        Label start = new Label("Juego del Gato");
        start.setStyle("-fx-font-size: 25px;");
        Label tokensWarning = new Label("Seleccione un item de juego");
        Button btnGoGame = new Button("Aceptar");
        Button btnDescription = new Button("Descripción");
        Button btn_goToLog = new Button("Volver a registrar");
        btnGoGame.setMinHeight(40);
        btnGoGame.setCursor(Cursor.HAND);
        
        welcomeScene.setOnKeyPressed(event->{
                switch(event.getCode()){
                    //Al presionar ENTER hace la misma función que el botón aceptar
                    case ENTER:
                        playerOneName = tfPlayerOne.getText();// aqui de una capturo los nombres, esto cuando le de al boton de aceptar, los carga en esa variable global - la de los nombres
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
                            break;
                    }
            
        });
        
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
            login();
            gameStage.close();
        });

        vbContainer.getChildren().addAll(start, lbPlayerOne, tfPlayerOne, lbPlayerTwo, tfPlayerTwo, warning, btnGoGame, tokensWarning, CBItemsGame(), btnDescription, btn_goToLog);
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
        HBox hboxBottom = new HBox();
        hboxBottom.setPadding(new Insets(10, 10, 10, 10));
        hboxBottom.setSpacing(350);
        Button btnRestart = new Button("Reiniciar juego (r)");
        Button btn_NewGame = new Button("Nuevo juego (n)");
        playerTurn.setStyle("-fx-text-fill: white; -fx-font-size: 18px;");
        hboxBottom.getChildren().addAll(btnRestart,btn_NewGame);
        fillButtonsMatriz();// llama a este metodo que llena a la matriz de botones vacios
        ComboBox CBoxSelectiont = new ComboBox();
        CBoxSelectiont.getItems().addAll("Facebook vs Twitter", "Gato vs Perro", "UCR vs TEC", "Spotify vs YouTube",
                "SoundCloud vs Spotify", "YouTube vs Twitch", "Discord vs Whatssapp", "Apple vs Android", "X vs O");
        CBoxSelectiont.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                restartMatrixGame();
                switch (CBoxSelectiont.getValue() + "") {

                    case "Facebook vs Twitter":
                        selectionToken(1);
                        break;
                    case "Gato vs Perro":
                        selectionToken(2);
                        break;
                    case "UCR vs TEC":
                        selectionToken(3);
                        break;
                    case "Spotify vs YouTube":
                        selectionToken(4);
                        break;
                    case "SoundCloud vs Spotify":
                        selectionToken(5);
                        break;
                    case "YouTube vs Twitch":
                        selectionToken(6);
                        break;
                    case "Discord vs Whatssapp":
                        selectionToken(7);
                        break;
                    case "Apple vs Android":
                        selectionToken(8);
                        break;
                    case "X vs O":
                        selectionToken(0);
                        break;
                    
                }

            }
        });
        CBoxSelectiont.setVisible(false);

        Label lB_selectOtherToken = new Label("Seleciona un nuevo Item de juego");
        lB_selectOtherToken.setVisible(false);
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

        MenuItem mI_logPanel = new MenuItem(" Ir al login");
        mI_logPanel.setOnAction((event) -> {
            login();
            gameStage.close();
        });
        MenuItem mI_ChangePassword = new MenuItem(" Cambiar la contraseña ");
        mI_ChangePassword.setOnAction((event) -> {
            JOptionPane.showMessageDialog(null, "Prueba ");
        });
        MenuItem mI_Exit = new MenuItem(" Salir del juego ");
        mI_Exit.setOnAction((event) -> Platform.exit());
        m_PlaySuports.getItems().addAll(mI_Credits, mI_About, mI_logPanel, mI_ChangePassword, mI_Exit);

        MenuItem mI2_changeTypeOfToken = new MenuItem("Cambio de Fichas");
        mI2_changeTypeOfToken.setOnAction((event) -> {
            CBoxSelectiont.setVisible(true);
            lB_selectOtherToken.setVisible(true);
        });
        m_Configuration.getItems().addAll(mI2_changeTypeOfToken);

        MenuItem mI3_listOfGames = new MenuItem("lista de partidas");
        mI3_listOfGames.setOnAction((event) -> {
        });
        MenuItem mI3_listOfGamePlayer = new MenuItem("lista de juegos por jugador");
        mI3_listOfGamePlayer.setOnAction((event) -> {
        });
        m_Reports.getItems().addAll(mI3_listOfGames, mI3_listOfGamePlayer);
        mB_Menu.getMenus().addAll(m_PlaySuports, m_Configuration, m_Reports, m_Help);

        for (int f = 0; f < matrixGame.length; f++) {//Añade los botones al gridpane y les pone el tamaño
            for (int c = 0; c < matrixGame[0].length; c++) {
                gpnMatriz.add(matrixGraphics[f][c], c, f);
                matrixGraphics[f][c].setMinHeight(90);
                matrixGraphics[f][c].setMinWidth(90);
            }
        }

        Vb_elementsTop.setAlignment(Pos.CENTER);
        Vb_elementsTop.getChildren().addAll(mB_Menu, playerTurn, lB_selectOtherToken, CBoxSelectiont);

        bpn.setTop(Vb_elementsTop);
        bpn.setCenter(gpnMatriz);
        bpn.setBottom(hboxBottom);
        bpn.setStyle("-fx-background-color: orange");
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

        selectionToken(value);
        Scene gameScene = new Scene(bpn, 590, 600);
        
        gameScene.setOnKeyPressed(event->{
                switch(event.getCode()){
                    //Al presionar la tecla R hace la misma función que al darle al botón reiniciar
                    case R:
                        restartMatrixGame();
                        break;
                    //Al presionar la tecla N hace la misma función que al darle al botón reiniciar
                    case N:
                        welcomeScene();
                        restartMatrixGame();
                        gameStage.close();
                        break;
                }
            
        });
        
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
        
        //Al presionar la tecla ENTER hace la misma función que al darle al botón cerrar ventana
        descScene.setOnKeyPressed(event->{
                switch(event.getCode()){
                    case ENTER:
                        descStage.hide();
                        break;
                }
            
        });
        
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
        Scene sceneWelcome = new Scene(vbFirstPlayer, 450, 450);
        Stage stageWelcome = new Stage();
        stageWelcome.setScene(sceneWelcome);
        stageWelcome.setTitle("¡Bienvenid@s!");
        stageWelcome.getIcons().add(new Image("cat icon.png"));
        stageWelcome.show();

        //Al presionar la tecla ENTER hace la misma función que al darle al botón cerrar ventana
        sceneWelcome.setOnKeyPressed(event->{
                switch(event.getCode()){
                    case ENTER:
                        stageWelcome.close();
                        catGame();
                        break;
                }
            
        });
        
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
        Label lbText = new Label("        Proyecto del curso IF2000\n \n        "
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
        
        //Al presionar la tecla ENTER hace la misma función que al darle al botón cerrar créditos
        creditsScene.setOnKeyPressed(event->{
                switch(event.getCode()){
                    case ENTER:
                        creditsStage.close();
                        break;
                }
            
        });
        
        //Si se le da al botón se cierra la ventana
        btnCredits.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                creditsStage.close();
            }
        });
    }

    public void login() { // loguearse                                                        L O G U E O 
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
        tF_user.setPromptText("Usuario ");
        Label lB_password = new Label("  Contraseña  ");
        lB_password.setStyle("-fx-text-fill: black; -fx-font-size: 20px;");
        TextField tF_password = new TextField();
        tF_password.setMaxWidth(200);
        tF_password.setAlignment(Pos.CENTER);
        tF_password.setPromptText("Contraseña");
        Button bTn_registerUser = new Button("Registrar");
        Label lB_WarningMessage = new Label("");
        lB_WarningMessage.setStyle("-fx-text-fill: black; -fx-font-size: 30px;");
        Label lB_WarningAccepted = new Label("");
        lB_WarningAccepted.setStyle("-fx-text-fill: green; -fx-font-size: 30px;");
        Scene loginScene = new Scene(vbContainer, 700, 500);
        
        //Al presionar la tecla ENTER hace la misma función que al darle al botón registrar
        loginScene.setOnKeyPressed(event->{
                switch(event.getCode()){
                    case ENTER:
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
                        break;
                }
            
        });
                
                
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
