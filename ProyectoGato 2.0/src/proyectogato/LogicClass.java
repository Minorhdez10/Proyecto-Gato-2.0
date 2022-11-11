package proyectogato;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Brandon Vargas, Kendall Sánchez, Minor Hernández
 */
public class LogicClass {

    boolean player = false;
    Button matrixGame[][] = new Button[3][3];
    Button matrixGraphics[][] = new Button[3][3];
    int decideTurn, clicksCounter = 1, value = 0;
    String playerOneName = "", playerTwoName = "", firstTurn = "", identifier = "";
    Label playerTurn = new Label("");

    //Va cambiando el turno de los jugadores
    // da un identificado al jugador  sea X ó O en caso de que sea falso 
    // indica el turno de los jugadores , está inveso para que saque quien sigue en la variable
    public boolean changePlayerTurn() {
        if (player) {
            identifier = "O";
            playerTurn.setText("Turno de: " + playerOneName);
            playerTurn.setStyle("-fx-text-fill: white; -fx-font-size: 20px;");
            player = false;
        } else {
            identifier = "X";
            playerTurn.setText("Turno de: " + playerTwoName);
            playerTurn.setStyle("-fx-text-fill: white; -fx-font-size: 20px;");
            player = true;
        }
        return player;
    }

    // inidica solo si hay un ganador , este recibe el true o false de la jugadada y el true se le asigno el jugador 1 y al false el jugador 2
    // este metodo se puede reciclar, ya que nos dice quien está jugando al momento , name es el booleano 
    public String winner(boolean name) {
        String wonPlayer;
        if (name) {
            wonPlayer = playerOneName;
        } else {
            wonPlayer = playerTwoName;
        }
        return wonPlayer;
    }

    // resetea la matriz y la pone editable
    public void restartMatrixGame() {

        for (int i = 0; i < matrixGame.length; i++) {
            for (int c = 0; c < matrixGame[0].length; c++) {
                matrixGame[i][c].setText("");
                matrixGame[i][c].setDisable(false);
                matrixGraphics[i][c].setDisable(false);
                matrixGraphics[i][c].setGraphic(new ImageView());
            }
        }
        player = true;
        value = 0;
        playerTurn.setText("");
        clicksCounter = 1;//indica si la matriz se llenó, se resetea al entrar aqui
        whoStart(); // un nuevo juego un nuevo jugador aleatorio
    }

// llena la matriz de botones
    public void fillButtonsMatriz() {
        for (int i = 0; i < matrixGame.length; i++) {
            for (int c = 0; c < matrixGame[0].length; c++) {
                matrixGame[i][c] = new Button();
                matrixGraphics[i][c] = new Button();
            }
        }
    }

// determina quien empieza aleatoriamente 
    // coloca a la variable como  deberia empezar
    public String whoStart() {
        //Lanza un número aleatorio entre 0 y 2 para ver qué jugador empieza primero
        decideTurn = (int) (1 + Math.random() * 3);

        if (decideTurn == 1) {
            firstTurn = playerOneName;
            playerTurn.setText("Empiezas : " + playerOneName);
            playerTurn.setStyle("-fx-text-fill: white; -fx-font-size: 20px;");
            player = false;
        } else {
            firstTurn = playerTwoName;
            playerTurn.setText("Empiezas : " + playerTwoName);
            playerTurn.setStyle("-fx-text-fill: white; -fx-font-size: 20px;");
            player = true;// la pone en true porque si empieza el segundo para que la variable quede seteada en verdadero (jugador 1) 
        }
        return firstTurn;
    }

    // muestra quien gana segun qué variable ingresó
    public void showWinner() {

        // llama al metodo y lo consulta cada vez
        //el valor está definido por una variable que desprende el método que evalua como gana, haciendo consultas a la matriz "   searchHowWin(X ó O)"
        // valor por defecto entra en 0 , cae a default y no hay un 0 y sigue jugando hasta que alguien gane o no y la variable "completa"
        // llega a 9
        switch (searchHowWin(identifier)) {

            case 1:
                playerTurn.setText("Ganas por filas  " + winner(player));
                playerTurn.setStyle("-fx-font-size: 18px;");
                break;
            case 2:
                playerTurn.setText("Ganaste por columnas " + winner(player));
                playerTurn.setStyle("-fx-font-size: 18px;");
                break;
            case 3:
                playerTurn.setText("Ganas por diagonal  " + winner(player));
                playerTurn.setStyle("-fx-font-size: 18px;");
                break;
            case 4:
                playerTurn.setText("Ganaste por diagonal inversa  " + winner(player));
                playerTurn.setStyle("-fx-font-size: 18px;");
                break;
            default:
                if ((clicksCounter == 9)) {
                    playerTurn.setText(" Hubo un empate ");
                    playerTurn.setStyle("-fx-font-size: 18px;");

                }
        }
    }

    // hace todas las consultas si gana un jugador o no y asigna el el aviso correspondiente al label aviso
    public int searchHowWin(String ID) {
        // if que evalua cada fila de la matriz "retorna" 1
        if ((matrixGame[0][0].getText().equals(ID) && matrixGame[0][1].getText().equals(ID) && matrixGame[0][2].getText().equals(ID)
                || (matrixGame[1][0].getText().equals(ID) && matrixGame[1][1].getText().equals(ID)) && matrixGame[1][2].getText().equals(ID)
                || (matrixGame[2][0].getText().equals(ID) && matrixGame[2][1].getText().equals(ID) && matrixGame[2][2].getText().equals(ID)))) {
            blockGame();
            return 1;
        }

        // if que evalua cada columna de la matriz "retorna" 2
        if ((matrixGame[0][0].getText().equals(ID) && matrixGame[1][0].getText().equals(ID) && matrixGame[2][0].getText().equals(ID)
                || (matrixGame[0][1].getText().equals(ID) && matrixGame[1][1].getText().equals(ID)) && matrixGame[2][1].getText().equals(ID)
                || (matrixGame[0][2].getText().equals(ID) && matrixGame[1][2].getText().equals(ID) && matrixGame[2][2].getText().equals(ID)))) {
            blockGame();
            return 2;
        }

        // if que evalua  diagonal "retorna" 3
        if (matrixGame[0][0].getText().equals(ID) && matrixGame[1][1].getText().equals(ID) && matrixGame[2][2].getText().equals(ID)) {
            blockGame();
            return 3;
        }

        // if que evalua  diagonal inversa "retorna" 4
        if (matrixGame[0][2].getText().equals(ID) && matrixGame[1][1].getText().equals(ID) && matrixGame[2][0].getText().equals(ID)) {

            blockGame();
            return 4;
        }
        return 0;
    }

    //Bloquea el juego cuando se termina
    public void blockGame() {
        for (int i = 0; i < matrixGame.length; i++) {
            for (int c = 0; c < matrixGame[0].length; c++) {
                matrixGame[i][c].setDisable(true);
                matrixGraphics[i][c].setDisable(true);
            }
        }
    }

    public void functionMatrixButtons(Image a, Image b) {

        for (int r = 0; r < matrixGame.length; r++) {
            int rows = r;
            for (int c = 0; c < matrixGame[0].length; c++) {
                int columns = c;
                matrixGraphics[rows][columns].setCursor(Cursor.HAND);
                matrixGraphics[rows][columns].setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        if (changePlayerTurn()) {// si tuno de del jugador (true o false) , changeplayer alterna  TODO el rato en si es true la pone en falso y si es falso en true 
                            //    Image img_u = new Image("icono_prueba.png");
                            matrixGraphics[rows][columns].setGraphic(new ImageView(a));
                            matrixGame[rows][columns].setText("X");// si es true que ponga x 
                        } else {
                            //      Image img_a = new Image("otraPrueba.png");
                            matrixGame[rows][columns].setText("O");// sino una 0
                            matrixGraphics[rows][columns].setGraphic(new ImageView(b));
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

    }

    public void selectionToken(int x) {

        switch (x) {
            // face vs tw
            case 1:
                Image aFace = new Image("face_icon.png");
                Image bTwi = new Image("twitt_icon.png");
                functionMatrixButtons(aFace, bTwi);

                break;
            case 2:
                Image cGato = new Image("cat icon.png");
                Image dPerro = new Image("dog_icon.png");
                functionMatrixButtons(cGato, dPerro);
                break;
            case 3:
                Image eUcr = new Image("logoUCR_icon.png");
                Image fTec = new Image("logotec_icon.png");
                functionMatrixButtons(eUcr, fTec);
                break;
            case 4:

                Image gSpotify = new Image("spotify_icon.png");
                Image hYoutube = new Image("youtube_icon.png");
                functionMatrixButtons(gSpotify, hYoutube);
                break;
            case 5:
                Image iSoundClo = new Image("soundcloud_icon.png");
                Image gSpotify2 = new Image("spotify_icon.png");
                functionMatrixButtons(iSoundClo, gSpotify2);
                break;
            case 6:
                Image hYoutube2 = new Image("youtube_icon.png");
                Image JTwitch = new Image("twitch_icon.png");
                functionMatrixButtons(hYoutube2, JTwitch);
                break;
            case 7:
                Image kDiscor = new Image("discord_icon.png");
                Image iWhatss = new Image("whatsapp_icon.png");
                functionMatrixButtons(iWhatss, kDiscor);
                break;
            case 8:
                Image mApple = new Image("apple_icon.png");
                Image nAndroid = new Image("Android_icon.png");
                functionMatrixButtons(mApple, nAndroid);
                break;
            default:
                Image oODefault = new Image("O_icon.png");
                Image pXDefault = new Image("X_icon.png");
                functionMatrixButtons(pXDefault, oODefault);
                break;
        }
    }

    public ComboBox CBItemsGame() {
        ComboBox CBoxCount = new ComboBox();
        CBoxCount.getItems().addAll("Facebook vs Twitter", "Gato vs Perro", "UCR vs TEC", "Spotify vs YouTube",
                "SoundCloud vs Spotify", "YouTube vs Twitch", "Discord vs Whatssapp", "Apple vs Android", "X vs O");
        CBoxCount.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                switch (CBoxCount.getValue() + "") {

                    case "Facebook vs Twitter":
                        value = 1;
                        break;
                    case "Gato vs Perro":
                        value = 2;
                        break;
                    case "UCR vs TEC":
                        value = 3;
                        break;
                    case "Spotify vs YouTube":
                        value = 4;
                        break;
                    case "SoundCloud vs Spotify":
                        value = 5;
                        break;
                    case "YouTube vs Twitch":
                        value = 6;
                        break;
                    case "Discord vs Whatssapp":
                        value = 7;
                        break;
                    case "Apple vs Android":
                        value = 8;
                        break;
                    case "X vs O":
                    default:
                        value = 0;
                        break;

                }
            }
        });
        return CBoxCount;
    }
}
