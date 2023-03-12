package Usuario;

public class Factura {

    private String numFactura;
    private String codPaquete;
    private String direccionFacturacion;
    private String origen;
    private String destino;
    private String nit;
    private String tipoPago;
    private int numeropaquetes;
    private double total;

    public Factura(String numFactura, String codPaquete,String direccionF, String origen, String destino, String nit, String tipoPago,
            int numeropaquetes, double total) {
        super();
        this.setDireccionFacturacion(direccionF);
        this.setNumFactura(numFactura);
        this.setCodPaquete(codPaquete);
        this.setOrigen(origen);
        this.setDestino(destino);
        this.setNit(nit);
        this.setTipoPago(tipoPago);
        this.setNumeropaquetes(numeropaquetes);
        this.setTotal(total);
    }

    public String getNumFactura() {
        return numFactura;
    }

    public void setNumFactura(String numFactura) {
        this.numFactura = numFactura;
    }

    public String getCodPaquete() {
        return codPaquete;
    }

    public void setCodPaquete(String codPaquete) {
        this.codPaquete = codPaquete;
    }

    public String getDireccionFacturacion() {
        return direccionFacturacion;
    }

    public void setDireccionFacturacion(String direccionFacturacion) {
        this.direccionFacturacion = direccionFacturacion;
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

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public int getNumeropaquetes() {
        return numeropaquetes;
    }

    public void setNumeropaquetes(int numeropaquetes) {
        this.numeropaquetes = numeropaquetes;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
