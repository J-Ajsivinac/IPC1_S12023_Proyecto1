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
    
    public static int getPosicionDepartamentos(String Codigo) {
        int regresar = -1;
        for (int i = 0; i < kioscos.size(); i++) {
            Kioscos k = kioscos.get(i);
            if (k.getCodigoKioco().equals(Codigo)) {
                regresar = i;
                break;
            }
        }
        return regresar;
    }
    
    public static ArrayList<Kioscos> getAllKioscos() {
        return kioscos;
    }
    
    public static boolean modificarNombreKiosco(String codKiosco, String nuevoNombre) {
        for (int i = 0; i < kioscos.size(); i++) {
            Kioscos kiosco = kioscos.get(i);
            if (kiosco.getCodigoKioco().equals(codKiosco)) {
                kiosco.setNombreKiosco(nuevoNombre);
                return true;
            }
        }
        return false;
    }
    
    public static boolean eliminarKiosco(String codKiosco) {
        if (verificarKioscos(codKiosco)) {
            int posicion = getPosicionDepartamentos(codKiosco);
            kioscos.remove(posicion);
            return true;
        }
        return false;
    }
    
     public static ArrayList<Kioscos> getAllDepartamentosKiosco(String codigRegion) {
        ArrayList<Kioscos> mRegresar = new ArrayList<Kioscos>();
        for (int j = 0; j < kioscos.size(); j++) {
            Kioscos d = kioscos.get(j);
            if (d.getCodigo().equals(codigRegion)) {
                mRegresar.add(new Kioscos(codigRegion, d.getNombre(), d.getPrecioEstandar(), d.getPrecioEspecial(), d.getCodigoKioco(), d.getNombreKiosco()));
            }
        }
        return mRegresar;
    }
}
