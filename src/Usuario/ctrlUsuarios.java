package Usuario;

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
        boolean v = verifiarUsuarios(correo);
        if (!v) {
            if (verificarPassword(contrasena)) {
                usuarios.add(new Usuario(contadorUsuarios, correo, nombre, apellido, contrasena, dpi, fnacimiento,
                        genero, nacionalidad, alias, telefono, rol, foto, null, null));
                contadorUsuarios++;
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "La contraseña no cumple con los requisitos", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "El correo ya existe", "Error", JOptionPane.ERROR_MESSAGE);
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
                        u.getTelefono(), u.getRol(), u.getFotografia(), u.getTarjetas(), u.getDatosFacturacion());
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
                        u.getTelefono(), u.getRol(), u.getFotografia(), u.getTarjetas(), u.getDatosFacturacion());
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
}
