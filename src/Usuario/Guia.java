package Usuario;

public class Guia {

    private String codPaquete;
    private String origen;
    private String destino;
    private String tipoPago;
    private String tamanoPaquete;
    private int numeropaquetes;
    private String fechaEnvio;
    private double multiplicadorServicio;
    private double total;

    public Guia(String codPaquete, String origen, String destino, String tipoPago, String tamanoPaquete,
            int numeropaquetes, String fechaEnvio, double total) {
        super();
        this.setCodPaquete(codPaquete);
        this.setOrigen(origen);
        this.setDestino(destino);
        this.setTipoPago(tipoPago);
        this.setTamanoPaquete(tamanoPaquete);
        this.setNumeropaquetes(numeropaquetes);
        this.setFechaEnvio(fechaEnvio);
        this.setTotal(total);
    }

    public Guia(String origen, String destino, String tamanoPaquete, int numeropaquetes) {
        this.setOrigen(origen);
        this.setDestino(destino);
        this.setTamanoPaquete(tamanoPaquete);
        this.setNumeropaquetes(numeropaquetes);
    }

    public String getCodPaquete() {
        return codPaquete;
    }

    public void setCodPaquete(String codPaquete) {
        this.codPaquete = codPaquete;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public String getTamanoPaquete() {
        return tamanoPaquete;
    }

    public void setTamanoPaquete(String tamanoPaquete) {
        this.tamanoPaquete = tamanoPaquete;
    }

    public int getNumeropaquetes() {
        return numeropaquetes;
    }

    public void setNumeropaquetes(int numeropaquetes) {
        this.numeropaquetes = numeropaquetes;
    }

    public String getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(String fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getMultiplicadorServicio() {
        return multiplicadorServicio;
    }

    public void setMultiplicadorServicio(double multiplicadorServicio) {
        this.multiplicadorServicio = multiplicadorServicio;
    }

}
