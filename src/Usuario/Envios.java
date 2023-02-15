package Usuario;

public class Envios {

    private int idUsuario;
    private String codRegion;
    private String codPaquete;
    private String tipoServicio;
    private String destinatario;
    private double totalEnvio;
    private int tipoPago;
    private Factura factura;
    private Guia Guia;

    public Envios(int idUsuario, String codRegion, String codPaquete, String tipoServicio, String destinatario,
            double totalEnvio, int tipoPago, Factura factura, Guia guia) {
        super();
        this.setIdUsuario(idUsuario);
        this.setCodRegion(codRegion);
        this.setCodPaquete(codPaquete);
        this.setTipoServicio(tipoServicio);
        this.setDestinatario(destinatario);
        this.setTotalEnvio(totalEnvio);
        this.setTipoPago(tipoPago);
        this.setFactura(factura);
        this.setGuia(guia);
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCodRegion() {
        return codRegion;
    }

    public void setCodRegion(String codRegion) {
        this.codRegion = codRegion;
    }

    public String getCodPaquete() {
        return codPaquete;
    }

    public void setCodPaquete(String codPaquete) {
        this.codPaquete = codPaquete;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public double getTotalEnvio() {
        return totalEnvio;
    }

    public void setTotalEnvio(double totalEnvio) {
        this.totalEnvio = totalEnvio;
    }

    public int getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(int tipoPago) {
        this.tipoPago = tipoPago;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Guia getGuia() {
        return Guia;
    }

    public void setGuia(Guia guia) {
        Guia = guia;
    }
}
