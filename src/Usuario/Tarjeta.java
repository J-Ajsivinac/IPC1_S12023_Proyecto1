package Usuario;

public class Tarjeta {

    private String tarjetanombre;
    private String tarjetaNumero;
    private String tarjetaVencimiento;

    public Tarjeta(String tarjetanombre, String tarjetaNumero, String tarjetaVencimiento) {
        super();
        this.setTarjetanombre(tarjetanombre);
        this.setTarjetaNumero(tarjetaNumero);
        this.setTarjetaVencimiento(tarjetaVencimiento);
    }

    public String getTarjetanombre() {
        return tarjetanombre;
    }

    public void setTarjetanombre(String tarjetanombre) {
        this.tarjetanombre = tarjetanombre;
    }

    public String getTarjetaNumero() {
        return tarjetaNumero;
    }

    public void setTarjetaNumero(String tarjetaNumero) {
        this.tarjetaNumero = tarjetaNumero;
    }

    public String getTarjetaVencimiento() {
        return tarjetaVencimiento;
    }

    public void setTarjetaVencimiento(String tarjetaVencimiento) {
        this.tarjetaVencimiento = tarjetaVencimiento;
    }

    public String getNumeroTarjeta() {
        return "XXXXXXXX" + tarjetaNumero.substring(tarjetaNumero.length() - 4);
    }
}
