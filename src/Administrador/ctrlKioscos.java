package Administrador;

import static Administrador.ctrlDepartamentos.generarCodigos;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ctrlKioscos {

    public static ArrayList<Kioscos> kioscos = new ArrayList<Kioscos>();

    public static boolean nuevoKiosco(String idRegion, String codigoKiosco, String nombreKiosco) {
        if (!codigoKiosco.equals("") && !nombreKiosco.equals("")) {

            if (!verificarKioscos(codigoKiosco)) {
                if (!verificarKioscosNombre(nombreKiosco)) {
                    Regiones r = ctrlRegiones.getRegionCodigo(idRegion);
                    kioscos.add(new Kioscos(idRegion, r.getCodigo(), r.getNombre(), r.getPrecioEstandar(), r.getPrecioEspecial(),
                            codigoKiosco, nombreKiosco));
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "El Nombre de Kiosco ya existe");
                }

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

    public static boolean verificarKioscosNombre(String Nombre) {
        for (int i = 0; i < kioscos.size(); i++) {
            Kioscos d = kioscos.get(i);
            if (d.getNombreKiosco().equals(Nombre)) {
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
                if (!verificarKioscosNombre(nuevoNombre)) {
                    kiosco.setNombreKiosco(nuevoNombre);
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "El kiosco ya Existe");
                }

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

    public static void eliminarTKisoco(String codRegion) {
        for (int j = 0; j < kioscos.size(); j++) {
            Kioscos d = kioscos.get(j);
            if (d.getCodigo().equals(codRegion)) {
                kioscos.remove(j);
            }
        }
    }

    //REVISAR
    public static ArrayList<Kioscos> getAllDepartamentosKiosco(String codigRegion) {
        ArrayList<Kioscos> mRegresar = new ArrayList<Kioscos>();
        for (int j = 0; j < kioscos.size(); j++) {
            Kioscos d = kioscos.get(j);
            if (d.getIdRegion().equals(codigRegion)) {
                mRegresar.add(new Kioscos(d.getIdRegion(), d.getCodigo(), d.getNombre(), d.getPrecioEstandar(), d.getPrecioEspecial(), d.getCodigoKioco(), d.getNombreKiosco()));
            }
        }
        return mRegresar;
    }
}
