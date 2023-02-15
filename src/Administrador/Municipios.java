package Administrador;

public class Municipios {
    private String codigoDepartamento;
    private String nombreDepartamento;

    public Municipios(String codigoDepartamento, String nombreDepartamento) {
        this.setCodigoDepartamento(codigoDepartamento);
        this.setNombreDepartamento(nombreDepartamento);
    }
    
    
    
    public String getCodigoDepartamento() {
        return codigoDepartamento;
    }

    public void setCodigoDepartamento(String codigoDepartamento) {
        this.codigoDepartamento = codigoDepartamento;
    }

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }
    
}
