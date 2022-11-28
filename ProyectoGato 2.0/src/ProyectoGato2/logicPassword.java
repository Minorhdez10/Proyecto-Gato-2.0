package ProyectoGato2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 *
 * @author Brandon
 */
public class logicPassword {
 
   
    public boolean validatePassword(String a) {

        boolean validate = false;
        int numbers = 0, mayusc = 0, minusc = 0, chartsSpecials = 0;
        if (a.length() >= 8) {
            for (int i = 0; i < a.length(); i++) {
                char letter = a.charAt(i);
                if (letter >= 49 && letter <= 57) {
                    numbers++;
                }

                if (letter >= 65 && letter <= 90) {
                    mayusc++;
                }

                if (letter >= 97 && letter <= 122) {
                    minusc++;
                }

                if (letter >= 33 && letter <= 47) {
                    chartsSpecials++;
                }
            }// end for
            if (numbers > 0 && mayusc > 0 && minusc > 0 && chartsSpecials > 0) {
                validate = true;
            }

        }// end if extension
        return validate;
    }// end method

    public boolean valuateUserPassword(PasswordClass p) {
        String userProm = "";
         boolean inFile = false;
         BufferedReader bfR = getReadFileGeneric("login.txt");
        try {
             userProm = bfR.readLine();
            while (userProm != null) {
                    String[] parts = userProm.split(";");
                    String user= parts[0], password=parts[1];
                if (user.equals(p.getUser()) && password.equals(p.getPassword())){
                    inFile=true;
                    break;
                }// end if validate
                userProm = bfR.readLine();     
            }// END WHILE 
        } catch (IOException io) {
        }// end catch
        return inFile;
    }// end 

    // metodo general para hacer una lectira (mostrar) un archivo
    public BufferedReader getReadFileGeneric(String nameFile) {

        File file = new File(nameFile);
        BufferedReader brf = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
            brf = new BufferedReader(isr);
        } catch (FileNotFoundException gg) {
//            JOptionPane.showMessageDialog(null, "Error de tipo file en el archivo \n" + gg);
        }
        return brf;
    }
    public boolean valuateUserPasswordVALUE(String user, String password) {
        String userProm = "";
         boolean inFile = false;
         BufferedReader bfR = getReadFileGeneric("login.txt");
        try {
             userProm = bfR.readLine();
            while (userProm != null) {
                    String[] parts = userProm.split(";");
                if (user.equals(parts[0]+"") && password.equals(parts[1]+"")){
                    inFile=true;
                    break;
                }// end if validate
                userProm = bfR.readLine();     
            }// END WHILE 
        } catch (IOException io) {
        }// end catch
        return inFile;
    }// end 

}// end class
