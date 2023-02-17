package Administrador;

public class Municipios {
    private String codigoMunicipio;
    private String codigoDepartamento;
    private String nombreDepartamento;

    public Municipios(String codigoDepartamento, String nombreDepartamento, String codigoMun) {
        this.setCodigoDepartamento(codigoDepartamento);
        this.setNombreDepartamento(nombreDepartamento);
        this.setCodigoMunicipio(codigoMun);
    }

    public String getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(String codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
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
