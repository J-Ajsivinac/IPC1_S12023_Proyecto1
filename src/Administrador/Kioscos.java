package Administrador;

public class Kioscos extends Regiones {

    private String codigoKioco;
    private String nombreKiosco;

    public Kioscos(String codigo, String nombre, double precioEstandar, double precioEspecial, String codigoKioco,
            String nombreKiosco) {
        super(codigo, nombre, precioEstandar, precioEspecial);
        this.setCodigoKioco(codigoKioco);
        this.setNombreKiosco(nombreKiosco);
    }
    
    public String getCodigoKioco() {
        return codigoKioco;
    }

    public void setCodigoKioco(String codigoKioco) {
        this.codigoKioco = codigoKioco;
    }

    public String getNombreKiosco() {
        return nombreKiosco;
    }

    public void setNombreKiosco(String nombreKiosco) {
        this.nombreKiosco = nombreKiosco;
    }
    
    @Override
    public String toString() {
        return this.getNombreKiosco();
    }
}
