
package ProyectoGato2;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author Brandon
 */
public class graphicPassword {

    //PasswordClass pC= new PasswordClass(user, password)
//    Interface iF= new Interface();
    logicPassword lP = new logicPassword();

    public Scene changePasswoord() {
        Stage changePasswordStage = new Stage();
        VBox vbContainer = new VBox();
        vbContainer.setSpacing(20);
        vbContainer.setStyle("-fx-background-color: black");
        vbContainer.setAlignment(Pos.CENTER);

        Label lB_userIn = new Label("Ingrese su usuario");
        lB_userIn.setStyle("-fx-text-fill: white; -fx-font-size: 17px;");
        TextField tF_userIN = new TextField();
        tF_userIN.setAlignment(Pos.CENTER);
        tF_userIN.setMaxWidth(200);
        tF_userIN.setPromptText("Usuario");
        Label lB_tiitle = new Label("Cambio de contraseña ");
        lB_tiitle.setStyle("-fx-text-fill: orange; -fx-font-size: 30px;");
        Label lB_passwordCurrent = new Label("Ingrese la contraseña ACTUAL ");
        lB_passwordCurrent.setStyle("-fx-text-fill: white; -fx-font-size: 17px;");
        TextField tF_passwordCurrent = new TextField();
        tF_passwordCurrent.setAlignment(Pos.CENTER);
        tF_passwordCurrent.setMaxWidth(200);
        tF_passwordCurrent.setPromptText("Contraseña actual ");
        Label lB_newPassword = new Label("Ingrese la contraseña NUEVA");
        lB_newPassword.setStyle("-fx-text-fill: white; -fx-font-size: 17px;");

        TextField tF_newPassword = new TextField();
        tF_newPassword.setAlignment(Pos.CENTER);
        tF_newPassword.setMaxWidth(200);
        tF_newPassword.setPromptText("Cotraseña nueva");

        Label lB_ConfirmnewPassword = new Label("Confirmar la NUEVA contraseña");
        lB_ConfirmnewPassword.setStyle("-fx-text-fill: white; -fx-font-size: 17px;");
        TextField tF_ConfirmnewPassword = new TextField();
        tF_ConfirmnewPassword.setAlignment(Pos.CENTER);
        tF_ConfirmnewPassword.setMaxWidth(200);
        tF_ConfirmnewPassword.setPromptText("Confrimar la nueva cotraseña");
        Label lB_warnings = new Label("");
        Button btnGoGame = new Button("Aceptar");
        btnGoGame.setMinHeight(40);
        btnGoGame.setCursor(Cursor.HAND);
        btnGoGame.setOnAction((event) -> {

            if (tF_userIN.getText().isEmpty()) {
                lB_warnings.setText(" Error, Ingrese un Usuario  ");
                lB_warnings.setStyle("-fx-text-fill: red; -fx-font-size: 25px;");
                tF_userIN.setStyle("-fx-background-color: red; -fx-text-fill: white");
            } else {
                if (tF_passwordCurrent.getText().isEmpty()) {
                    lB_warnings.setText(" Error, Ingrese una contraseña  ");
                    lB_warnings.setStyle("-fx-text-fill: white; -fx-font-size: 25px;");
                    tF_userIN.setStyle("-fx-background-color: white; -fx-text-fill: black");
                    tF_passwordCurrent.setStyle("-fx-background-color: red; -fx-text-fill: white");
                } else {
                    
                    if (tF_newPassword.getText().isEmpty()) {
                        lB_warnings.setText(" Error, Ingrese una contraseña NUEVA  ");
                        lB_warnings.setStyle("-fx-text-fill: white; -fx-font-size: 25px;");
                        tF_passwordCurrent.setStyle("-fx-background-color: white; -fx-text-fill: black");
                        tF_newPassword.setStyle("-fx-background-color: red; -fx-text-fill: white");
                    } else {
                        if (tF_ConfirmnewPassword.getText().isEmpty()) {
                            lB_warnings.setText(" Error, confirme la contraseña  ");
                            lB_warnings.setStyle("-fx-text-fill: white; -fx-font-size: 25px;");
                            tF_newPassword.setStyle("-fx-background-color: white; -fx-text-fill: black");
                            tF_ConfirmnewPassword.setStyle("-fx-background-color: red; -fx-text-fill: white");
                        } else {
                            tF_newPassword.setStyle("-fx-background-color: white; -fx-text-fill: black");
                            tF_ConfirmnewPassword.setStyle("-fx-background-color: white; -fx-text-fill: black");
                              tF_userIN.setStyle("-fx-background-color: white; -fx-text-fill: black");
                       tF_passwordCurrent.setStyle("-fx-background-color: white; -fx-text-fill: black");
                       String useR=tF_newPassword.getText(), passWord=tF_newPassword.getText();
                       //PasswordClass pC= new PasswordClass(useR,passWord);
                         JOptionPane.showMessageDialog(null, "entre con "+lP.valuateUserPasswordVALUE(useR, passWord));
                         // if(lP.valuateUserPasswordVALUE(useR, passWord)){
      
                            if (lP.validatePassword(passWord) == true) {
                                lB_warnings.setText("Contraseña nueva aceptada");
                                lB_warnings.setStyle("-fx-text-fill: white; -fx-font-size: 25px;");
                                tF_newPassword.setStyle("-fx-background-color: green; -fx-text-fill: white");
                            } else {
                                lB_warnings.setText("Contraseña mala");
                                lB_warnings.setStyle("-fx-text-fill: red; -fx-font-size: 25px;");
                                tF_newPassword.setStyle("-fx-background-color: red; -fx-text-fill: white");
                            }
//                           }else{
//                               lB_warnings.setText("El usuario y la contraseña no son iguales");
//                                lB_warnings.setStyle("-fx-text-fill: red; -fx-font-size: 25px;");
//                                tF_newPassword.setStyle("-fx-background-color: red; -fx-text-fill: white");
//                           }



                        }
                    }// end else  review confirm password Textfield
                }// end else review passwordCurrent Textfield
            }// end review first Textfield
        });

        //Da acción al botón aceptar, recoge los nombres, cambia de escena y limpia los textfields
        lB_tiitle.setAlignment(Pos.CENTER);
        vbContainer.getChildren().addAll(lB_tiitle, lB_userIn, tF_userIN, lB_passwordCurrent, tF_passwordCurrent, lB_newPassword, tF_newPassword, lB_ConfirmnewPassword, tF_ConfirmnewPassword, lB_warnings, btnGoGame);
        vbContainer.setSpacing(20);
        Scene chansePasswordScene = new Scene(vbContainer, 450, 550);
        changePasswordStage.setScene(chansePasswordScene);
        changePasswordStage.setTitle("   Cambio de Contraseña  ");
        changePasswordStage.getIcons().add(new Image("cat icon.png"));
        changePasswordStage.show();
        return chansePasswordScene;
    }

}
