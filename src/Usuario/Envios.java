package Usuario;

import java.util.Comparator;

public class Envios {

    private String idUsuario;
    private String nombreUsuario;
    private String codRegion;
    private String tipoServicio;
    private String destinatario;
    private Factura factura;
    private Guia Guia;

    public Envios(String idUsuario, String nombreUsuario, String codRegion, String codPaquete, String tipoServicio, String destinatario,
            double totalEnvio, Factura factura, Guia guia) {
        super();

        this.setIdUsuario(idUsuario);
        this.setNombreUsuario(nombreUsuario);
        this.setCodRegion(codRegion);
        this.setTipoServicio(tipoServicio);
        this.setDestinatario(destinatario);
        this.setFactura(factura);
        this.setGuia(guia);
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    
    

    public String getCodRegion() {
        return codRegion;
    }

    public void setCodRegion(String codRegion) {
        this.codRegion = codRegion;
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