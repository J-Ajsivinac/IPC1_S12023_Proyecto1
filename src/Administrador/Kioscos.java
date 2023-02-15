package Administrador;

public class Kioscos extends Regiones {

    private int codigoKioco;
    private String nombreKiosco;

    public Kioscos(String codigo, String nombre, double precioEstandar, double precioEspecial, int codigoKioco,
            String nombreKiosco) {
        super(codigo, nombre, precioEstandar, precioEspecial);
        this.setCodigoKioco(codigoKioco);
        this.setNombreKiosco(nombreKiosco);
    }

    public int getCodigoKioco() {
        return codigoKioco;
    }

    public void setCodigoKioco(int codigoKioco) {
        this.codigoKioco = codigoKioco;
    }

    public String getNombreKiosco() {
        return nombreKiosco;
    }

    public void setNombreKiosco(String nombreKiosco) {
        this.nombreKiosco = nombreKiosco;
    }
}
