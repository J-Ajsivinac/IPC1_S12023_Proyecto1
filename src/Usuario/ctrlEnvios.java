package Usuario;

import Administrador.ctrlRegiones;
import static Usuario.ctrlEnvios.envios;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.HashMap;

public class ctrlEnvios {

    public static ArrayList<Envios> envios = new ArrayList<Envios>();
    public static int contadorFacuras = 1;

    public static boolean agregarEnvio(String correo, String id, String Nombre, String codRegion, String tipoServicio, String destinatario,
            double totalEnvio, String tipoPago, String ori, String nit, int numerPaquetes, String tamaño) {
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
            Factura facturaingresar = agregarFactura(codPaquete, ori, destinatario, nit, tipoPago, numerPaquetes,
                    totalEnvio);
            Guia guiaIngresar = agregarGuia(codPaquete, ori, destinatario, tipoServicio, tamaño, numerPaquetes, dtf.format(now), totalEnvio);
            envios.add(new Envios(correo, id, Nombre, codRegion, codPaquete, tipoServicio, destinatario, totalEnvio, facturaingresar, guiaIngresar));
            System.out.println(codPaquete);
            ctrlUsuarios.addContadorUser(correo, numerPaquetes);
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
            if (e.getCorreo().equals(correo)) {
                regresar.add(new Envios(correo, e.getIdUsuario(), e.getNombreUsuario(), e.getCodRegion(), e.getGuia().getCodPaquete(), e.getTipoServicio(), e.getDestinatario(), e.getFactura().getTotal(), e.getFactura(), e.getGuia()));
            }
        }

        return regresar;
    }

    public static Envios verUltimoEnvios() {
        return envios.get(envios.size() - 1);
    }

    public static ArrayList<Envios> getReportesByCod(String codRegion) {
        ArrayList<Envios> regresar = new ArrayList<>();
        //Envios regresar = null;
        for (int i = 0; i < envios.size(); i++) {
            Envios e = envios.get(i);
            if (e.getCodRegion().equals(codRegion)) {
                regresar.add(new Envios(e.getCorreo(), e.getIdUsuario(), e.getNombreUsuario(), e.getCodRegion(), e.getGuia().getCodPaquete(), e.getTipoServicio(), e.getDestinatario(), e.getFactura().getTotal(), e.getFactura(), e.getGuia()));
            }
        }

        return regresar;
    }

    public static Envios getEnvioByGuia(String correoUsuario, String codGuia) {
        Envios regresar = null;
        if (ctrlUsuarios.verifiarUsuarios(correoUsuario)) {
            for (Envios envio : envios) {
                if (envio.getGuia().getCodPaquete().equals(codGuia)) {
                    regresar = envio;
                }
            }
        }
        return regresar;
    }

    public static ArrayList<Envios> getAllEnvios() {
        return envios;
    }

    public static ArrayList<Envios> ordenarPorRegion() {
        ArrayList<Envios> listaOrdenada = new ArrayList<>(envios);
        Collections.sort(listaOrdenada, new comparadorRegiones(listaOrdenada));
        return listaOrdenada;
    }

}

class comparadorRegiones implements Comparator<Envios> {

    private ArrayList<Envios> listaOriginal;

    public comparadorRegiones(ArrayList<Envios> listaOriginal) {
        this.listaOriginal = listaOriginal;
    }

    @Override
    public int compare(Envios envio1, Envios envio2) {
        int repeticiones1 = 0;
        int repeticiones2 = 0;

        for (Envios obj : envios) {
            if (obj.getCodRegion().equals(envio1.getCodRegion())) {
                repeticiones1++;
            }
            if (obj.getCodRegion().equals(envio2.getCodRegion())) {
                repeticiones2++;
            }
        }
        return Integer.compare(repeticiones2, repeticiones1);
    }
}

class comparadorUsuarios implements Comparator<Envios> {

    private ArrayList<Envios> listaOriginal;

    public comparadorUsuarios(ArrayList<Envios> listaOriginal) {
        this.listaOriginal = listaOriginal;
    }

    @Override
    public int compare(Envios envio1, Envios envio2) {
        int repeticiones1 = 0;
        int repeticiones2 = 0;

        for (Envios obj : envios) {
            if (obj.getCodRegion().equals(envio1.getIdUsuario())) {
                repeticiones1++;
            }
            if (obj.getCodRegion().equals(envio2.getIdUsuario())) {
                repeticiones2++;
            }
        }
        return Integer.compare(repeticiones2, repeticiones1);
    }
}
