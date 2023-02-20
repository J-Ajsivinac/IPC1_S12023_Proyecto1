package Administrador;

public class Municipios {
    private String codigoMunicipio;
    private String codigoDepartamento;
    private String nombreMunicipio;

    public Municipios(String codigoDepartamento, String nombreMunicipio, String codigoMun) {
        this.setCodigoDepartamento(codigoDepartamento);
        this.setNombreMunicipio(nombreMunicipio);
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

    public String getNombreMunicipio() {
        return nombreMunicipio;
    }

    public void setNombreMunicipio(String nombreDepartamento) {
        this.nombreMunicipio = nombreDepartamento;
    }
    
    @Override
    public String toString() {
        return this.getNombreMunicipio();
    }
    
}
