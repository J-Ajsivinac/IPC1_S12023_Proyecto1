package Usuario;

public class DatosFacturacion {

    private String nombreCompletoF;
    private String direccionF;
    private String nit;

    public DatosFacturacion(String nombreCompletoF, String direccionF, String nit) {
        super();
        this.nombreCompletoF = nombreCompletoF;
        this.direccionF = direccionF;
        this.nit = nit;
    }

    public String getNombreCompletoF() {
        return nombreCompletoF;
    }

    public void setNombreCompletoF(String nombreCompletoF) {
        this.nombreCompletoF = nombreCompletoF;
    }

    public String getDireccionF() {
        return direccionF;
    }

    public void setDireccionF(String direccionF) {
        this.direccionF = direccionF;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }
    
    @Override
    public String toString() {
        return this.nit;
    }
}
