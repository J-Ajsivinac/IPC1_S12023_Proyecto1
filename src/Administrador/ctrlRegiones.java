package Administrador;

import static Administrador.ctrlDepartamentos.generarCodigos;
import Usuario.ctrlEnvios;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ctrlRegiones {

    private static ArrayList<Regiones> regiones = new ArrayList<Regiones>();

    public static boolean nuevaRegion(String codigo, String nombre, double precioEstandar, double precioEspecial) {
        if (!codigo.equals("") && !nombre.equals("") && precioEstandar > 0 && precioEspecial > 0) {

            if (verficarNombreRegion(nombre)) {
                JOptionPane.showMessageDialog(null, "Este Nombre de Región ya existe");
                return false;
            }

            String codRegion = "";
            while (true) {
                codRegion = generarCodigos("R");
                if (!verficarIDRegion(codRegion)) {
                    break;
                }
            }
            BigDecimal precioE = new BigDecimal(precioEstandar).setScale(2, RoundingMode.HALF_UP);
            BigDecimal precioEspe = new BigDecimal(precioEspecial).setScale(2, RoundingMode.HALF_UP);
            if (!verificarCodigoRegion(codigo)) {
                regiones.add(new Regiones(codRegion, codigo, nombre, precioE.doubleValue(), precioEspe.doubleValue()));
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "El codigo de Region ya existe");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Llene todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return false;
    }

    public static boolean verficarIDRegion(String idR) {
        for (int i = 0; i < regiones.size(); i++) {
            Regiones d = regiones.get(i);
            if (d.getIdRegion().equals(idR)) {
                return true;
            }
        }
        return false;
    }

    public static boolean verficarNombreRegion(String NombreR) {
        for (int i = 0; i < regiones.size(); i++) {
            Regiones d = regiones.get(i);
            if (d.getNombre().equals(NombreR)) {
                return true;
            }
        }
        return false;
    }

    public static boolean cambiarPrecios(String codigo, int opcion, double nuevoPrecio, String nuevoNombre) {
        BigDecimal bd = new BigDecimal(nuevoPrecio).setScale(2, RoundingMode.HALF_UP);
        for (int i = 0; i < regiones.size(); i++) {
            Regiones reg = regiones.get(i);
            if (reg.getIdRegion().equals(codigo)) {
                if (opcion == 1) {
                    reg.setPrecioEstandar(bd.doubleValue());
                    return true;
                } else if (opcion == 2) {
                    reg.setPrecioEspecial(bd.doubleValue());
                    return true;
                } else if (opcion == 3) {
                    if (!nuevoNombre.equals("")) {
                        if (verficarNombreRegion(nuevoNombre)) {
                            JOptionPane.showMessageDialog(null, "El nombre de Región ya existe");
                            return false;
                        } else {
                            ctrlEnvios.cambiarDatosR(codigo, nuevoNombre);
                            reg.setNombre(nuevoNombre);
                            return true;
                        }
                    }
                } else if (opcion == 4) {
                    if (!nuevoNombre.equals("")) {
                        if (!verificarCodigoRegion(nuevoNombre)) {
                            reg.setCodigo(nuevoNombre);
                            return true;
                        } else {
                            JOptionPane.showMessageDialog(null, "El codigo ya ha sido ingresado");
                        }

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
            if (reg.getIdRegion().equals(codigo)) {
                regresar = new Regiones(reg.getIdRegion(), reg.getCodigo(), reg.getNombre(), reg.getPrecioEstandar(), reg.getPrecioEspecial());
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
            ctrlDepartamentos.eliminarTDepartamentos(codR);
            ctrlKioscos.eliminarTKisoco(codR);
            regresar = true;
            return regresar;
        }
        return regresar;
    }

}
