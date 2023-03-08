package Usuario;

import static Administrador.ctrlDepartamentos.generarCodigos;
import static Administrador.ctrlDepartamentos.verificarCodigoDepa;
import Interfaz.agregarUsuarios;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class ctrlUsuarios {

    private static ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

    public static boolean verifiarUsuarios(String correo) {
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario u = usuarios.get(i);
            if (u.getCorreo().equals(correo)) {
                return true;
            }
        }
        return false;
    }

    public static boolean verificarID(String codigo) {
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario d = usuarios.get(i);
            if (d.getIdUsuario().equals(codigo)) {
                return true;
            }
        }
        return false;
    }

    public static boolean nuevoUsuario(String correo, String nombre, String apellido, String contrasena, String dpi,
            String fnacimiento, String genero, String nacionalidad, String alias, int telefono, String rol, String foto) {
        if (!ctrlUsuarios.verifiarUsuarios(correo)) {
            if (verificarPassword(contrasena)) {
                String codUsuario = "";
                while (true) {
                    codUsuario = generarCodigos("U");
                    if (!verificarID(codUsuario)) {
                        break;
                    }
                }
                usuarios.add(new Usuario(codUsuario, correo, nombre, apellido, contrasena, dpi, fnacimiento,
                        genero, nacionalidad, alias, telefono, rol, foto, 0));
                System.out.println("password: " + contrasena + "usuario_" + correo + "id_ " + codUsuario);
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
        } else {
            JOptionPane.showMessageDialog(null, "El numero de Tarjeta ya ha sido Ingresado");
        }
        return false;
    }

    public static boolean verificarDatosF(String nit, int index) {
        Usuario d = usuarios.get(index);
        if (d.getDatosFacturacion().size() != 0) {
            for (int j = 0; j < d.getDatosFacturacion().size(); j++) {
                if (d.getDatosFacturacion().get(j).getNit().equals(nit)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean agregarDatosFacturacion(String correo, String nombre, String direccion, String nit, int index) {
        Usuario departa = getUsuarioID(correo);
        int posicion = getPosicionUsuario(correo);

        if (verifiarUsuarios(correo) && !verificarDatosF(nit, index)) {
            usuarios.get(posicion).getDatosFacturacion().add(new DatosFacturacion(nombre, direccion, nit));
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "El nit ya existe");
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

    public static Usuario getUsuarioIndice(int index) {
        return usuarios.get(index);
    }

    public static boolean cambiarTarjetas(int index, int opcion, int posicion, String valorNuevo) {
        if (index != -1) {
            switch (opcion) {
                case 1:
                    usuarios.get(index).getTarjetas().get(posicion).setTarjetanombre(valorNuevo);
                    return true;
                case 2:
                    if (!verificarTarjetas(valorNuevo, index)) {
                        usuarios.get(index).getTarjetas().get(posicion).setTarjetaNumero(valorNuevo);
                        return true;
                    } else {
                        JOptionPane.showMessageDialog(null, "El No de tarjeta ya existe");
                        return false;
                    }

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

    public static boolean cambiarDatosF(int index, int opcion, int posicion, String valorNuevo) {
        if (index != -1) {
            switch (opcion) {
                case 1:
                    usuarios.get(index).getDatosFacturacion().get(posicion).setNombreCompletoF(valorNuevo);
                    return true;
                case 2:
                    usuarios.get(index).getDatosFacturacion().get(posicion).setDireccionF(valorNuevo);
                    return true;
                case 3:
                    if (!verificarDatosF(valorNuevo, index)) {
                        usuarios.get(index).getDatosFacturacion().get(posicion).setNit(valorNuevo);
                        return true;
                    } else {
                        JOptionPane.showMessageDialog(null, "El Nit ya existe");
                        return false;
                    }

                default:
                    return false;
            }
        }
        return false;
    }

    public static boolean eliminarDatosF(String correo, int posiciónU, int posicionT) {
        if (verifiarUsuarios(correo)) {
            usuarios.get(posiciónU).getDatosFacturacion().remove(posicionT);
            return true;
        }
        return false;
    }

    public static boolean cambiarIMG(int index, String nRuta) {
        if (!nRuta.equals(usuarios.get(index).getFotografia())) {
            usuarios.get(index).setFotografia(nRuta);
            return true;
        }
        return false;
    }

    public static boolean cambiarDGeneral(int index, String nuevoCorreo, String nuevaContra, int opcion) {
        Usuario u = usuarios.get(index);
        if (!ctrlUsuarios.verifiarUsuarios(nuevoCorreo)) {
            JOptionPane.showMessageDialog(null, "Este correo ya esta registrado");
            return false;
        }

        if (u.getCorreo().equals(nuevoCorreo) && u.getContrasena().equals(nuevaContra) || nuevaContra.equals("")) {
            return false;
        }

        if (opcion == 1) {
            usuarios.get(index).setCorreo(nuevoCorreo);
            return true;
        } else if (opcion == 2) {
            if (!usuarios.get(index).getCorreo().equals(nuevoCorreo)) {
                if (verificarPassword(nuevaContra)) {
                    usuarios.get(index).setCorreo(nuevoCorreo);
                    usuarios.get(index).setContrasena(nuevaContra);
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "La contraseña no cumple con los requistos");
                }
            } else {
                if (verificarPassword(nuevaContra)) {
                    usuarios.get(index).setContrasena(nuevaContra);
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "La contraseña no cumple con los requistos");
                }
            }
        }
        return false;
    }

    public static boolean cambiarPerfil(String[] nuevosvalores, int index) {
        Usuario u = usuarios.get(index);
        boolean regresar = false;
        boolean cambio = false;
        String[] valores = {u.getNombre(), u.getApellido(), u.getFechaNacimiento(), u.getTelefono() + "", u.getNacionalidad(), u.getGenero(), u.getDpi()};
        for (int i = 0; i < valores.length; i++) {
            if (!valores[i].equals(nuevosvalores[i])) {
                switch (i) {
                    case 0:
                        usuarios.get(index).setNombre(nuevosvalores[i]);
                        cambio = true;
                        regresar = true;
                        break;
                    case 1:
                        usuarios.get(index).setApellido(nuevosvalores[i]);
                        cambio = true;
                        regresar = true;
                        break;
                    case 2:
                        usuarios.get(index).setFechaNacimiento(nuevosvalores[i]);
                        regresar = true;
                        break;
                    case 3:
                        int numero = Integer.parseInt(nuevosvalores[i]);
                        usuarios.get(index).setTelefono(numero);
                        regresar = true;
                        break;
                    case 4:
                        usuarios.get(index).setNacionalidad(nuevosvalores[i]);
                        regresar = true;
                        break;
                    case 5:
                        usuarios.get(index).setGenero(nuevosvalores[i]);
                        regresar = true;
                        break;
                    case 6:
                        usuarios.get(index).setDpi(nuevosvalores[i]);
                        regresar = true;
                        break;
                    default:
                        return false;
                }
            }
        }
        if (cambio) {
            ctrlEnvios.cambiarDatosU(u.getIdUsuario(), nuevosvalores[0] + " " + nuevosvalores[1]);
        }
        return regresar;
    }

    public static boolean cambiarRol(String RolCompleto, int index) {
        if (!usuarios.get(index).getRol().equals(RolCompleto)) {
            usuarios.get(index).setRol(RolCompleto);
            return true;
        }
        return false;
    }

    public static boolean eliminarUsuario(int index, String password) {
        if (usuarios.get(index).getContrasena().equals(password)) {
            usuarios.remove(index);
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "La contraseña no es la correcta");
        }
        return false;
    }
}
