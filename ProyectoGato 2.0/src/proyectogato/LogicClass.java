package proyectogato;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 *
 * @author Brandon Vargas
 */

public class LogicClass {
   boolean player = false;
    Button matrixGame[][] = new Button[3][3];
    Button matrixGraphics[][] = new Button[3][3];
    int decideTurn, clicksCounter = 1;
    String playerOneName = "", playerTwoName = "",firstTurn = "", identifier = "";
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
        playerTurn.setText("");
        clicksCounter = 1;//indica si la matriz se llenó, se resetea al entrar aqui
        whoStart(); // un nuevo juego un nuevo jugador aleatorio
    }

// llena la matriz de botones
    public void fillButtonsMatriz() {
        for (int i = 0; i < matrixGame.length; i++) {
            for (int c = 0; c < matrixGame[0].length; c++) {
                matrixGame[i][c] = new Button();
               matrixGraphics[i][c]= new Button();
            }
        }
    }
 
// determina quien empieza aleatoriamente 
    // coloca a la variable como  deberia empezar
    public String whoStart() {
        //Lanza un número aleatorio entre 0 y 2 para ver qué jugador empieza primero
        decideTurn = (int) ( 1 + Math.random() * 3);
        
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
        switch ( searchHowWin(identifier)) {

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
}
