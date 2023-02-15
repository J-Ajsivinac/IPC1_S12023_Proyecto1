package Administrador;

import java.util.ArrayList;
import java.util.Random;

public class ctrlDepartamentos {

    public static ArrayList<Departamentos> departamentos = new ArrayList<Departamentos>();

    public static boolean nuevoDepartamento(String codigo, String nombreDepartamento) {
        if (!codigo.equals("") && !nombreDepartamento.equals("")) {
            String codDepartamento = "";
            while (true) {
                codDepartamento = generarCodigos("dep");
                if (!verificarCodigoDepa(codDepartamento)) {
                    break;
                }
            }
            Regiones r = ctrlRegiones.getRegionCodigo(codigo);
            departamentos.add(new Departamentos(codigo, r.getNombre(), r.getPrecioEstandar(), r.getPrecioEspecial(),
                    codDepartamento, nombreDepartamento));
            return true;
        }
        return false;
    }

    public static Departamentos getDepartamentoCodigo(String Codigo) {
        Departamentos regresar = null;
        for (int i = 0; i < departamentos.size(); i++) {
            Departamentos d = departamentos.get(i);
            if (d.getCodigo().equals(Codigo)) {
                regresar = new Departamentos(Codigo, d.getNombre(), d.getPrecioEstandar(), d.getPrecioEspecial(), d.getCodDepartamento(), d.getNombreDepartamento());
            }
        }
        return regresar;
    }

    public static String generarCodigos(String codigo) {
        String cod = codigo;
        for (int i = 0; i <= 5; i++) {
            Random rand = new Random();
            int digito = rand.nextInt();
            cod += digito;
        }
        return cod;
    }

    public static boolean verificarCodigoDepa(String codigo) {
        for (int i = 0; i < departamentos.size(); i++) {
            Departamentos d = departamentos.get(i);
            if (d.getCodigo().equals(codigo)) {
                return true;
            }
        }
        return false;
    }

    public static void agregarMunicipios(String codigoDepar, Municipios muni) {
        Departamentos departa = getDepartamentoCodigo(codigoDepar);
        if (departa != null) {
            departa.getMunicipios().add(muni);
        }

    }
}
