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
            String fnacimiento, String genero, String nacionalidad, String alias, int telefono, int rol, String foto) {
        if (!ctrlUsuarios.verifiarUsuarios(correo)) {
            if (verificarPassword(contrasena)) {
                usuarios.add(new Usuario(contadorUsuarios, correo, nombre, apellido, contrasena, dpi, fnacimiento,
                        genero, nacionalidad, alias, telefono, rol, foto));
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
                        u.getTelefono(), u.getRol(), u.getFotografia());
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
                        u.getTelefono(), u.getRol(), u.getFotografia());
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

    public static void agregarTarjeta(int idUsuario, Tarjeta t) {
        Usuario u = getUsuarioID(idUsuario);
        if (t != null) {
            u.getTarjetas().add(t);
        }

    }

    public static void agregarDatosFacturación(int idUsuario, DatosFacturacion d) {
        Usuario u = getUsuarioID(idUsuario);
        if (d != null) {
            u.getDatosFacturacion().add(d);
        }

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
}
