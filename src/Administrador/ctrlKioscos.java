package Administrador;

import static Administrador.ctrlDepartamentos.generarCodigos;
import java.util.ArrayList;

public class ctrlKioscos {

    public static ArrayList<Kioscos> kioscos = new ArrayList<Kioscos>();

    public static boolean nuevoKiosco(String codigo, String codigoKiosco, String nombreKiosco) {
        if (!codigo.equals("") && !nombreKiosco.equals("")) {

            if (!verificarKioscos(codigoKiosco)) {
                Regiones r = ctrlRegiones.getRegionCodigo(codigo);
                kioscos.add(new Kioscos(codigo, r.getNombre(), r.getPrecioEstandar(), r.getPrecioEspecial(),
                        codigoKiosco, nombreKiosco));
                return true;
            }
        }
        return false;
    }

    public static boolean verificarKioscos(String codigo) {
        for (int i = 0; i < kioscos.size(); i++) {
            Kioscos d = kioscos.get(i);
            if (d.getCodigoKioco().equals(codigo)) {
                return true;
            }
        }
        return false;
    }
    
    public static ArrayList<Kioscos> getAllKioscos() {
        return kioscos;
    }
}
