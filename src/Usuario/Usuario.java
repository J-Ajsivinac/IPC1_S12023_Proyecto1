package Usuario;

import java.util.ArrayList;

public class Usuario {

    private int idUsuario;
    private String correo;
    private String nombre;
    private String apellido;
    private String contrasena;
    private String dpi;
    private String fechaNacimiento;
    private String genero;
    private String nacionalidad;
    private String alias;
    private int telefono;
    private int rol;
    private String fotografia;
    private int contadorEnvios;
    private ArrayList<Tarjeta> tarjetas;
    private ArrayList<DatosFacturacion> DatosFacturacion;

    public Usuario(int idUsuario, String correo, String nombre, String apellido, String contrasena, String dpi, String fechaNacimiento, String genero, String nacionalidad, String alias, int telefono, int rol, String fotografia, int contador) {
        this.setIdUsuario(idUsuario);
        this.setCorreo(correo);
        this.setNombre(nombre);
        this.setApellido(apellido);
        this.setContrasena(contrasena);
        this.setDpi(dpi);
        this.setFechaNacimiento(fechaNacimiento);
        this.setGenero(genero);
        this.setNacionalidad(nacionalidad);
        this.setAlias(alias);
        this.setTelefono(telefono);
        this.setRol(rol);
        this.setFotografia(fotografia);
        this.tarjetas = new ArrayList<>();
        this.DatosFacturacion = new ArrayList<>();
        this.setContadorEnvios(contador);
    }

    public Usuario(int idUsuario, String nombre, String Apellido){
        this.setIdUsuario(idUsuario);
        this.setNombre(nombre);
        this.setApellido(Apellido);
    }
    
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public String getFotografia() {
        return fotografia;
    }

    public void setFotografia(String fotografia) {
        this.fotografia = fotografia;
    }

    public ArrayList<Tarjeta> getTarjetas() {
        return tarjetas;
    }

    public void setTarjetas(ArrayList<Tarjeta> tarjetas) {
        this.tarjetas = tarjetas;
    }

    public ArrayList<DatosFacturacion> getDatosFacturacion() {
        return DatosFacturacion;
    }

    public void setDatosFacturacion(ArrayList<DatosFacturacion> DatosFacturacion) {
        this.DatosFacturacion = DatosFacturacion;
    }

    public int getContadorEnvios() {
        return contadorEnvios;
    }

    public void setContadorEnvios(int contadorEnvios) {
        this.contadorEnvios = contadorEnvios;
    }
    
    

}
