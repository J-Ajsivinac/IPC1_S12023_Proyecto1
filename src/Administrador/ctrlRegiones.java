package Administrador;

import java.util.ArrayList;

public class ctrlRegiones {

    public static ArrayList<Regiones> regiones = new ArrayList<Regiones>();

    public static boolean nuevaRegion(String codigo, String nombre, double precioEstandar, double precioEspecial) {
        if (!codigo.equals("") && !nombre.equals("") && precioEstandar > 0 && precioEspecial > 0) {
            regiones.add(new Regiones(codigo, nombre, precioEstandar, precioEspecial));
            return true;
        }
        return false;
    }

    public static boolean cambiarPrecios(String codigo, int opcion, double nuevoPrecio) {
        for (int i = 0; i < regiones.size(); i++) {
            Regiones reg = regiones.get(i);
            if (reg.getCodigo().equals(codigo)) {
                if (opcion == 1) {
                    reg.setPrecioEstandar(nuevoPrecio);
                    return true;
                } else if (opcion == 2) {
                    reg.setPrecioEspecial(nuevoPrecio);
                    return true;
                }
            }
        }
        return false;
    }

    public static ArrayList<Regiones> getTodasRegiones() {
        return regiones;
    }

    public static Regiones getRegionCodigo(String codigo) {
        Regiones regresar = null;
        for (int i = 0; i < regiones.size(); i++) {
            Regiones reg = regiones.get(i);
            if (reg.getCodigo().equals(codigo)) {
                regresar = new Regiones(codigo, reg.getNombre(), reg.getPrecioEstandar(), reg.getPrecioEspecial());
            }
        }
        return regresar;
    }
   
}
