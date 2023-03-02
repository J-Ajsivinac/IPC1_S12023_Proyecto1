package Usuario;

import Administrador.ctrlRegiones;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;

public class ctrlEnvios {

    public static ArrayList<Envios> envios = new ArrayList<Envios>();
    public static int contadorFacuras = 1;

    public static boolean agregarEnvio(String correo, String codRegion, String tipoServicio, String destinatario,
            double totalEnvio, int tipoPago, String ori, String nit, int numerPaquetes, String tamaño) {
        if (!correo.equals("") && !codRegion.equals("") && !destinatario.equals("")) {
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
            envios.add(new Envios(correo, codRegion, codPaquete, tipoServicio, destinatario, totalEnvio, tipoPago, facturaingresar, guiaIngresar));
            System.out.println(codPaquete);
            ctrlRegiones.addContador(codRegion);
            ctrlUsuarios.addContadorUser(correo,numerPaquetes);
            return true;
        }
        return false;
    }

    public static Factura agregarFactura(String codPaquete, String Origen, String destino, String nit, String tipo,
            int numeroPaquetes, double total) {
        String numeroFactura = String.format("%08d", contadorFacuras);
        Factura newF = new Factura(numeroFactura, codPaquete, Origen, destino, nit, tipo, numeroPaquetes, total);
        return newF;
    }

    public static Guia agregarGuia(String codPaquete, String origen, String destino, String tipoPago, String tamano,
            int numeropaquetes, String fecha, double total) {
        Guia newG = new Guia(codPaquete, origen, destino, tipoPago, tamano, numeropaquetes, fecha, total);
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

    public static ArrayList<Envios> verEnvios(String correo) {

        ArrayList<Envios> regresar = new ArrayList<>();
        //Envios regresar = null;
        for (int i = 0; i < envios.size(); i++) {
            Envios e = envios.get(i);
            if (e.getIdUsuario().equals(correo)) {
                regresar.add(new Envios(correo, e.getCodRegion(), e.getGuia().getCodPaquete(), e.getTipoServicio(), e.getDestinatario(), e.getFactura().getTotal(), 0, e.getFactura(), e.getGuia()));
            }
        }

        return regresar;
    }

    public static ArrayList<Envios> getReportesByCod(String codRegion) {
        ArrayList<Envios> regresar = new ArrayList<>();
        //Envios regresar = null;
        for (int i = 0; i < envios.size(); i++) {
            Envios e = envios.get(i);
            if (e.getCodRegion().equals(codRegion)) {
                regresar.add(new Envios(e.getIdUsuario(), e.getCodRegion(), e.getGuia().getCodPaquete(), e.getTipoServicio(), e.getDestinatario(), e.getFactura().getTotal(), 0, e.getFactura(), e.getGuia()));
            }
        }

        return regresar;
    }
    
    public static Envios getEnvioByGuia(String correoUsuario, String codGuia){
        Envios regresar = null;
        if(ctrlUsuarios.verifiarUsuarios(correoUsuario)){
            for (Envios envio : envios) {
                if(envio.getGuia().getCodPaquete().equals(codGuia)){
                    regresar = envio;
                }
            }
        }
        return regresar;
    }

}
