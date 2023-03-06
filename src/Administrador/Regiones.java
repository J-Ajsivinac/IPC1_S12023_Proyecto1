package Administrador;

public class Regiones {

    private String codigo;
    private String nombre;
    private double precioEstandar;
    private double precioEspecial;
    
    public Regiones(String codigo, String nombre, double precioEstandar, double precioEspecial) {
        super();
        this.setCodigo(codigo);
        this.setNombre(nombre);
        this.setPrecioEstandar(precioEstandar);
        this.setPrecioEspecial(precioEspecial);
    }
    
    public Regiones(String codigo, String nombre) {
        this.setCodigo(codigo);
        this.setNombre(nombre);
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecioEstandar() {
        return precioEstandar;
    }

    public void setPrecioEstandar(double precioEstandar) {
        this.precioEstandar = precioEstandar;
    }

    public double getPrecioEspecial() {
        return precioEspecial;
    }

    public void setPrecioEspecial(double precioEspecial) {
        this.precioEspecial = precioEspecial;
    }
     
    @Override
    public String toString() {
        return this.getNombre();
    }
}
