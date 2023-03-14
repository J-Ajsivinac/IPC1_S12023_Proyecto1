package proyecto1;

import Administrador.ctrlRegiones;
import Interfaz.login;
import Usuario.ctrlUsuarios;
import com.formdev.flatlaf.intellijthemes.FlatOneDarkIJTheme;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Proyecto1 {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatOneDarkIJTheme());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
        UIManager.put("defaultFont", new Font("Montserrat", 0, 13));
        UIManager.put("Component.arrowType", "chevron");
        UIManager.put("Component.focusedBorderColor", new Color(85, 89, 146));
        UIManager.put("TextComponent.arc", 10);
        UIManager.put("Component.arc", 10);
        UIManager.put("List.selectionArc", 10);
        UIManager.put("TextField.margin", new Insets(0, 10, 0, 10));
        UIManager.put("PasswordField.margin", new Insets(0, 10, 0, 10));
        UIManager.put("ComboBox.selectionArc", 10);
        UIManager.put("PasswordField.showRevealButton", true);
        UIManager.put("PasswordField.showCapsLock", false);
        UIManager.put("TextField.background", new Color(40, 41, 52));
        UIManager.put("ComboBox.background", new Color(40, 41, 52));
        UIManager.put("PasswordField.background", new Color(40, 41, 52));
        UIManager.put("CheckBox.icon.selectedBackground", new Color(0, 0, 0, 0));
        UIManager.put("CheckBox.icon.checkmarkColor", new Color(200, 187, 250));
        UIManager.put("Table.background", new Color(50, 51, 64));
        
        ctrlRegiones.nuevaRegion("M", "Metropolitana", 35.00, 25.00);
        ctrlRegiones.nuevaRegion("NT", "Norte", 68.50, 45.55);
        ctrlRegiones.nuevaRegion("NO", "Nororiente", 58.68, 35.48);
        ctrlRegiones.nuevaRegion("SO", "Suroriente", 38.68, 32.48);
        ctrlRegiones.nuevaRegion("SOC", "Suroccidente", 34.00, 29.00);
        ctrlRegiones.nuevaRegion("NOC", "Noroccidente", 44.50, 40.00);
        ctrlUsuarios.nuevoUsuario("a", "???", "____", "Ji#0", "20222254512", "12/02/2002", "Femenino", "Guatemala", "Hola", 1234567890, "Usuario Empresarial", "C:\\Users\\mesoi\\Downloads\\HTML5_Badge_256.png");
        login l = new login();
        l.setVisible(true);
    }

}
