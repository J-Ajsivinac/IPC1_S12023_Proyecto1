package proyecto1;

import Administrador.ctrlRegiones;
import Interfaz.login;

public class Proyecto1 {

    public static void main(String[] args) {
       
        // TODO code application logic here
        ctrlRegiones.nuevaRegion("M", "Metropolitana", 35.00, 25.00,0);
        ctrlRegiones.nuevaRegion("NT", "Norte", 68.50, 45.55,0);
        ctrlRegiones.nuevaRegion("NO", "Nororiente", 58.68, 35.48,0);
        ctrlRegiones.nuevaRegion("SO", "Suroriente", 38.68, 32.48,0);
        ctrlRegiones.nuevaRegion("SOC", "Suroccidente", 34.00, 29.00,0);
        ctrlRegiones.nuevaRegion("NOC", "Noroccidente", 44.50, 40.00,0);
        login l = new login();
        l.setVisible(true);
    }

}
