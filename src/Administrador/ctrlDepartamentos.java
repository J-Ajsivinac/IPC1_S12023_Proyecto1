package Administrador;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;

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
            if (!verificarNombreDepa(nombreDepartamento)) {
                Regiones r = ctrlRegiones.getRegionCodigo(codigo);
                departamentos.add(new Departamentos(codigo, r.getNombre(), r.getPrecioEstandar(), r.getPrecioEspecial(),
                        codDepartamento, nombreDepartamento));
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "El departamento ya existe");
            }

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

    public static boolean verificarNombreDepa(String nombre) {
        for (int i = 0; i < departamentos.size(); i++) {
            Departamentos d = departamentos.get(i);
            if (d.getNombreDepartamento().equals(nombre)) {
                return true;
            }
        }
        return false;
    }

    public static boolean verificarcodigoMuni(String codigo) {
        for (int i = 0; i < departamentos.size(); i++) {
            Departamentos d = departamentos.get(i);
             if (d.getMunicipios().size() != 0) {
                for (int j = 0; j < d.getMunicipios().size(); j++) {
                    if (d.getMunicipios().get(j).getCodigoMunicipio().equals(codigo)) {
                        return true;
                    }
                }
            }
           
        }
        return false;
    }

    public static boolean verificarNombreMuni(String nombre) {
        for (int i = 0; i < departamentos.size(); i++) {
            Departamentos d = departamentos.get(i);
           
             if (d.getMunicipios().size() != 0) {
                for (int j = 0; j < d.getMunicipios().size(); j++) {
                    if (d.getMunicipios().get(j).getNombreMunicipio().equals(nombre)) {
                        return true;
                    }
                }
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
            if (!verificarcodigoMuni(codigoMunicipio)) {
                break;
            }
        }
        if (!verificarNombreMuni(nombreMunicipio)) {
            departamentos.get(posicion).getMunicipios().add(new Municipios(codigoDepar, nombreMunicipio, codigoMunicipio));
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "El Municipio ya existe");
        }
        return false;
    }

    public static ArrayList<Departamentos> getAllD() {
        return departamentos;
    }

    public static ArrayList<Municipios> getMuniDepartamento(String codigoDepar) {
        ArrayList<Municipios> mRegresar = new ArrayList<Municipios>();

        for (int j = 0; j < departamentos.size(); j++) {
            Departamentos d = departamentos.get(j);
            if (d.getCodDepartamento().equals(codigoDepar)) {
                for (int k = 0; k < d.getMunicipios().size(); k++) {
                    mRegresar.add(new Municipios(codigoDepar, d.getMunicipios().get(k).getNombreMunicipio(), d.getMunicipios().get(k).getCodigoMunicipio()));
                }
                break;
            }
        }
        return mRegresar;
    }
    
    public static String getRegionbyCodigo(String codDepartamento){
        String regresar="";
        for (int i = 0; i < departamentos.size(); i++) {
            Departamentos d = departamentos.get(i);
            if (d.getCodDepartamento().equals(codDepartamento)) {
                regresar = d.getCodigo();
                return regresar;
            }
        }
        return regresar;
    }
}
