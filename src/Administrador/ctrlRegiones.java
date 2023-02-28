package Administrador;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ctrlRegiones {

    public static ArrayList<Regiones> regiones = new ArrayList<Regiones>();

    public static boolean nuevaRegion(String codigo, String nombre, double precioEstandar, double precioEspecial, int contador) {
        if (!codigo.equals("") && !nombre.equals("") && precioEstandar > 0 && precioEspecial > 0) {
            if (!verificarCodigoRegion(codigo)) {
                regiones.add(new Regiones(codigo, nombre, precioEstandar, precioEspecial, contador));
                return true;
            }

        } else {
            JOptionPane.showMessageDialog(null, "Llene todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return false;
    }

    public static boolean cambiarPrecios(String codigo, int opcion, double nuevoPrecio, String nuevoNombre) {
        for (int i = 0; i < regiones.size(); i++) {
            Regiones reg = regiones.get(i);
            if (reg.getCodigo().equals(codigo)) {
                if (opcion == 1) {
                    reg.setPrecioEstandar(nuevoPrecio);
                    return true;
                } else if (opcion == 2) {
                    reg.setPrecioEspecial(nuevoPrecio);
                    return true;
                } else if (opcion == 3) {
                    if (!nuevoNombre.equals("")) {
                        reg.setNombre(nuevoNombre);
                        return true;
                    }
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
                regresar = new Regiones(codigo, reg.getNombre(), reg.getPrecioEstandar(), reg.getPrecioEspecial(), reg.getContadorEnvios());
            }
        }
        return regresar;
    }

    public static double getMultiplicador(String codRegion, int tipo) {
        double regresar = 0;

        for (int i = 0; i < regiones.size(); i++) {
            Regiones reg = regiones.get(i);
            if (reg.getCodigo().equals(codRegion)) {
                if (tipo == 0) {
                    regresar = reg.getPrecioEstandar();
                    return regresar;
                } else if (tipo == 1) {
                    regresar = reg.getPrecioEspecial();
                    return regresar;
                }
            }
        }

        return regresar;
    }

    public static void addContador(String codRegion) {
        for (int i = 0; i < regiones.size(); i++) {
            Regiones reg = regiones.get(i);
            if (reg.getCodigo().equals(codRegion)) {
                regiones.get(i).setContadorEnvios(regiones.get(i).getContadorEnvios() + 1);
            }
        }
    }

    public static boolean verificarCodigoRegion(String codigo) {
        for (int i = 0; i < regiones.size(); i++) {
            Regiones d = regiones.get(i);
            if (d.getCodigo().equals(codigo)) {
                return true;
            }
        }
        return false;
    }

    public static boolean eliminarRegion(String codR, int index) {
        boolean regresar = false;
        if (verificarCodigoRegion(codR)) {
            regiones.remove(index);
            regresar = true;
            return regresar;
        }
        return regresar;
    }

}
