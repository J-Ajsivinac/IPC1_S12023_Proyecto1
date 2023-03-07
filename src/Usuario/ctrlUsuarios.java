package Usuario;

import Interfaz.agregarUsuarios;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class ctrlUsuarios {

    public static ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
    public static int contadorUsuarios = 0;

    public static boolean verifiarUsuarios(String correo) {
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario u = usuarios.get(i);
            if (u.getCorreo().equals(correo)) {
                return true;
            }
        }
        return false;
    }

    public static boolean nuevoUsuario(String correo, String nombre, String apellido, String contrasena, String dpi,
            String fnacimiento, String genero, String nacionalidad, String alias, int telefono, String rol, String foto) {
        if (!ctrlUsuarios.verifiarUsuarios(correo)) {
            if (verificarPassword(contrasena)) {
                usuarios.add(new Usuario(contadorUsuarios, correo, nombre, apellido, contrasena, dpi, fnacimiento,
                        genero, nacionalidad, alias, telefono, rol, foto, 0));
                contadorUsuarios++;
                System.out.println("password: " + contrasena + "usuario_" + correo);
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "La contraseña no cumple con los requisitos", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "El correo ingresado ya esta regustrado");
        }
        return false;
    }

    public static boolean verificarPassword(String password) {
        Pattern patron = Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!#%&@*$_-])");
        Matcher m = patron.matcher(password);
        boolean c = m.find();
        return c;
    }

    public static void imprimir() {
        for (int i = 0; i < usuarios.size(); i++) {
            System.out.println(usuarios.get(i).getCorreo());
        }
    }

    public static Usuario getUsuarioID(int idUsuario) {
        Usuario regresar = null;
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario u = usuarios.get(i);
            if (u.getIdUsuario() == idUsuario) {
                regresar = new Usuario(idUsuario, u.getCorreo(), u.getNombre(), u.getApellido(), u.getContrasena(),
                        u.getDpi(), u.getFechaNacimiento(), u.getGenero(), u.getNacionalidad(), u.getAlias(),
                        u.getTelefono(), u.getRol(), u.getFotografia(), u.getContadorEnvios());
            }
        }
        return regresar;
    }

    public static Usuario getUsuarioID(String correo) {
        Usuario regresar = null;
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario u = usuarios.get(i);
            if (u.getCorreo().equals(correo)) {
                regresar = new Usuario(u.getIdUsuario(), u.getCorreo(), u.getNombre(), u.getApellido(), u.getContrasena(),
                        u.getDpi(), u.getFechaNacimiento(), u.getGenero(), u.getNacionalidad(), u.getAlias(),
                        u.getTelefono(), u.getRol(), u.getFotografia(), u.getContadorEnvios());
            }
        }
        return regresar;
    }

    public static Usuario getUsuarioDatosMinimos(int idUsuario) {
        Usuario regresar = null;
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario u = usuarios.get(i);
            if (u.getIdUsuario() == idUsuario) {
                regresar = new Usuario(idUsuario, u.getNombre(), u.getApellido());
            }
        }
        return regresar;
    }

    public static int iniciarSesion(String correo, String password) {
        int regresar = 0;
        if (!(correo.equals("") && password.equals(""))) {
            if (correo.equals("ipc1") && password.equals("2022")) {
                //Administrador
                //JOptionPane.showMessageDialog(null, "NICE"); 
                regresar = 1;
            } else {
                if (ctrlUsuarios.verifiarUsuarios(correo)) {
                    Usuario ingreso;
                    ingreso = getUsuarioID(correo);
                    if (password.equals(ingreso.getContrasena())) {
                        //Cliente
                        //JOptionPane.showMessageDialog(null, "NICE Usuario");
                        regresar = 2;
                    } else {
                        JOptionPane.showMessageDialog(null, "contraseña incorrecta");
                        regresar = 0;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario o Contaseña Incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ingrese Datos", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return regresar;
    }

    public static int getPosicionUsuario(String Codigo) {
        int regresar = -1;
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario d = usuarios.get(i);
            if (d.getCorreo().equals(Codigo)) {
                regresar = i;
                return regresar;
            }
        }
        return regresar;
    }

    public static boolean verificarTarjetas(String codigo, int index) {
            Usuario d = usuarios.get(index);
            if (d.getTarjetas().size() != 0) {
                for (int j = 0; j < d.getTarjetas().size(); j++) {
                    if (d.getTarjetas().get(j).getTarjetaNumero().equals(codigo)) {
                        return true;
                    }
                }
            }        
        return false;
    }
    public static boolean agregaTarjeta(String correo, String nombreTarjeta, String numeroT, String FechaV, int index) {
        int posicion = getPosicionUsuario(correo);

        if (verifiarUsuarios(correo) && !verificarTarjetas(numeroT, index)) {
            usuarios.get(posicion).getTarjetas().add(new Tarjeta(nombreTarjeta, numeroT, FechaV));
            return true;
        }else{
            JOptionPane.showMessageDialog(null, "El numero de Tarjeta ya ha sido Ingresado");
        }
        return false;
    }

    public static boolean agregarDatosFacturacion(String correo, String nombre, String direccion, String nit) {
        Usuario departa = getUsuarioID(correo);
        int posicion = getPosicionUsuario(correo);

        if (verifiarUsuarios(correo)) {
            usuarios.get(posicion).getDatosFacturacion().add(new DatosFacturacion(nombre, direccion, nit));
            return true;
        }
        return false;
    }

    public static ArrayList<Tarjeta> getAllTarjetas(String correo) {
        ArrayList<Tarjeta> mRegresar = new ArrayList<Tarjeta>();

        for (int j = 0; j < usuarios.size(); j++) {
            Usuario d = usuarios.get(j);
            if (d.getCorreo().equals(correo)) {
                for (int k = 0; k < d.getTarjetas().size(); k++) {
                    mRegresar.add(new Tarjeta(d.getTarjetas().get(k).getTarjetanombre(), d.getTarjetas().get(k).getNumeroTarjeta(), d.getTarjetas().get(k).getTarjetaVencimiento()));
                }
                break;
            }
        }

        return mRegresar;
    }

    public static ArrayList<DatosFacturacion> getAllDFacturacion(String correo) {
        ArrayList<DatosFacturacion> mRegresar = new ArrayList<DatosFacturacion>();

        for (int j = 0; j < usuarios.size(); j++) {
            Usuario d = usuarios.get(j);
            if (d.getCorreo().equals(correo)) {
                for (int k = 0; k < d.getDatosFacturacion().size(); k++) {
                    mRegresar.add(new DatosFacturacion(d.getDatosFacturacion().get(k).getNombreCompletoF(), d.getDatosFacturacion().get(k).getDireccionF(), d.getDatosFacturacion().get(k).getNit()));
                }
                break;
            }
        }

        return mRegresar;
    }

    public static void addContadorUser(String correo, int cantPa) {
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario reg = usuarios.get(i);
            if (reg.getCorreo().equals(correo)) {
                usuarios.get(i).setContadorEnvios(usuarios.get(i).getContadorEnvios() + cantPa);
            }
        }
    }

    public static Factura getFacturaByCode(String correo, String Guia) {
        Factura regresar = null;
        Envios en = ctrlEnvios.getEnvioByGuia(correo, Guia);
        if (en != null) {
            regresar = en.getFactura();
        }
        return regresar;
    }

    public static ArrayList<Usuario> getTodUsuarios() {
        return usuarios;
    }

    public static boolean cambiarTarjetas(int index, int opcion, int posicion, String valorNuevo) {
        System.out.println(index);
        if (index != -1) {
            switch (opcion) {
                case 1:
                    usuarios.get(index).getTarjetas().get(posicion).setTarjetanombre(valorNuevo);
                    return true;
                case 2:
                    usuarios.get(index).getTarjetas().get(posicion).setTarjetaNumero(valorNuevo);
                    return true;
                case 3:
                    usuarios.get(index).getTarjetas().get(posicion).setTarjetaVencimiento(valorNuevo);
                    return true;
                default:
                    return false;
            }
        }
        return false;
    }

    public static boolean eliminarTarjetas(String correo, int posiciónU, int posicionT) {
        if (verifiarUsuarios(correo)) {
            usuarios.get(posiciónU).getTarjetas().remove(posicionT);
            return true;
        }
        return false;
    }
}
