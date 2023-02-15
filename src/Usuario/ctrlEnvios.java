package Usuario;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;

public class ctrlEnvios {

    public static ArrayList<Envios> envios = new ArrayList<Envios>();
    public static int contadorFacuras = 1;

    public static boolean agregarEnvio(int idUsuario, String codRegion, String tipoServicio, String destinatario,
            double totalEnvio, int tipoPago, String ori, String nit, int numerPaquetes, String tamaño) {
        if (idUsuario != 0 && !codRegion.equals("") && !destinatario.equals("")) {
            String codPaquete = "";
            while (true) {
                codPaquete = generarGuia();
                if (!verificarGuia(codPaquete)) {
                    break;
                }
            }
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDateTime now = LocalDateTime.now();
            Factura facturaingresar = agregarFactura(codPaquete, ori, destinatario, nit, tipoServicio, numerPaquetes,
                    totalEnvio);
            Guia guiaIngresar = agregarGuia(codPaquete, ori, destinatario, tipoServicio, tamaño, numerPaquetes, dtf.format(now), totalEnvio);
            envios.add(new Envios(idUsuario, codRegion, codPaquete, tipoServicio, destinatario, totalEnvio, tipoPago, facturaingresar, guiaIngresar));

        }
        return false;
    }

    public static Factura agregarFactura(String codPaquete, String Origen, String destino, String nit, String tipo,
            int numeroPaquetes, double total) {
        Factura newF = null;
        String numeroFactura = String.format("%08d", contadorFacuras);
        newF = new Factura(numeroFactura, codPaquete, Origen, destino, nit, tipo, numeroPaquetes, total);
        return newF;
    }

    public static Guia agregarGuia(String codPaquete, String origen, String destino, String tipoPago, String tamaño,
            int numeropaquetes, String fecha, double total) {
        Guia newG = null;
        newG = new Guia(codPaquete, origen, destino, tipoPago, codPaquete, numeropaquetes, fecha, total);
        return newG;
    }

    public static String generarGuia() {
        String cod = "IPC1E";
        String posibilidades = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_";
        Random r = new Random();
        for (int i = 0; i < 5; i++) {
            char c = posibilidades.charAt(r.nextInt(posibilidades.length()));
            cod += c;
        }
        return cod;
    }

    public static boolean verificarGuia(String codigo) {
        for (int i = 0; i < envios.size(); i++) {
            Envios d = envios.get(i);
            if (d.getGuia().equals(codigo)) {
                return true;
            }
        }
        return false;
    }

    public static void verEnvios(int idUsuario) {
        //Envios regresar = null;
        for (int i = 0; i < envios.size(); i++) {
            Envios e = envios.get(i);
            if (e.getIdUsuario() == idUsuario) {
                System.out.println(e.getFactura().getNumFactura());
            }
        }
    }
}
