package Administrador;

import java.util.ArrayList;
import java.util.Random;

public class ctrlDepartamentos {

    public static ArrayList<Departamentos> departamentos = new ArrayList<Departamentos>();

    public static boolean nuevoDepartamento(String codigo, String nombreDepartamento) {
        if (!codigo.equals("") && !nombreDepartamento.equals("")) {
            String codDepartamento = "";
            while (true) {
                codDepartamento = generarCodigos("DEP");
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
            if (d.getCodDepartamento().equals(Codigo)) {
                regresar = new Departamentos(Codigo, d.getNombre(), d.getPrecioEstandar(), d.getPrecioEspecial(), d.getCodDepartamento(), d.getNombreDepartamento());
            }
        }
        return regresar;
    }

    public static int getPosicionDepartamentos(String Codigo) {
        int regresar = -1;
        for (int i = 0; i < departamentos.size(); i++) {
            Departamentos d = departamentos.get(i);
            if (d.getCodDepartamento().equals(Codigo)) {
                regresar = i;
                break;
            }
        }
        return regresar;
    }

    public static String generarCodigos(String codigo) {
        String cod = codigo;
        for (int i = 0; i <= 5; i++) {
            Random rand = new Random();
            int digito = rand.nextInt(10);
            cod += digito;
        }
        return cod;
    }

    public static boolean verificarCodigoDepa(String codigo) {
        for (int i = 0; i < departamentos.size(); i++) {
            Departamentos d = departamentos.get(i);
            if (d.getCodDepartamento().equals(codigo)) {
                return true;
            }
        }
        return false;
    }

    public static boolean verificarCodigoMun(String codigo) {
        for (int i = 0; i < departamentos.size(); i++) {
            Departamentos d = departamentos.get(i);
            if (d.getMunicipios().equals(codigo)) {
                return true;
            }
        }
        return false;
    }

    public static boolean agregarMunicipios(String codigoDepar, String nombreMunicipio) {
        Departamentos departa = getDepartamentoCodigo(codigoDepar);
        int posicion = getPosicionDepartamentos(codigoDepar);
        String codigoMunicipio = "";
        while (true) {
            codigoMunicipio = generarCodigos("MUN");
            if (!verificarCodigoMun(codigoMunicipio)) {
                break;
            }
        }
        if (!verificarCodigoDepa(codigoDepar)) {
            departamentos.get(posicion).getMunicipios().add(new Municipios(codigoDepar, nombreMunicipio, codigoMunicipio));
            return true;
        }
        return false;
    }
    

    public static ArrayList<Departamentos> getAllD() {
        return departamentos;
    }
}
