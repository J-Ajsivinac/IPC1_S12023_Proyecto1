package Administrador;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;

public class ctrlDepartamentos {

    private static ArrayList<Departamentos> departamentos = new ArrayList<Departamentos>();

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
                departamentos.add(new Departamentos(r.getIdRegion(), r.getCodigo(), r.getNombre(), r.getPrecioEstandar(), r.getPrecioEspecial(),
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
                regresar = new Departamentos(d.getIdRegion(), d.getCodigo(), d.getNombre(), d.getPrecioEstandar(), d.getPrecioEspecial(), d.getCodDepartamento(), d.getNombreDepartamento());
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

    public static boolean modificarNombreDep(String codDepartamento, String nuevoNombre) {
        for (int i = 0; i < departamentos.size(); i++) {
            Departamentos dep = departamentos.get(i);
            if (dep.getCodDepartamento().equals(codDepartamento)) {
                if (!verificarNombreDepa(nuevoNombre)) {
                    dep.setNombreDepartamento(nuevoNombre);
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "El nombre del Departamento ya existe");
                }

            }
        }
        return false;
    }

    public static boolean modificarNombreMun(String codDepartamento, String codMunicipio, String nuevoNombre) {
        ArrayList<Municipios> muni1 = getMuniDepartamento(codDepartamento);

        for (int i = 0; i < departamentos.size(); i++) {
            Departamentos dep = departamentos.get(i);
            for (int j = 0; j < muni1.size(); j++) {
                Municipios mun = dep.getMunicipios().get(j);
                if (mun.getCodigoMunicipio().equals(codMunicipio)) {
                    if (!verificarNombreMuni(nuevoNombre)) {
                        mun.setNombreMunicipio(nuevoNombre);
                        return true;
                    }else{
                        JOptionPane.showMessageDialog(null, "El nombre del Municipio ya existe");
                    }

                }
            }
        }

        return false;
    }

    public static boolean eliminarDepartamento(String codDep) {
        if (verificarCodigoDepa(codDep)) {
            int posicion = getPosicionDepartamentos(codDep);
            departamentos.remove(posicion);
            return true;
        }
        return false;
    }

    public static void eliminarTDepartamentos(String codRegion) {
        for (int j = 0; j < departamentos.size(); j++) {
            Departamentos d = departamentos.get(j);
            if (d.getCodigo().equals(codRegion)) {
                departamentos.remove(j);
            }
        }
    }

    public static boolean eliminarMunicipio(String codigDepar, String codigoMun) {
        if (verificarCodigoDepa(codigDepar)) {
            int posicion = getPosicionDepartamentos(codigDepar);
            ArrayList<Municipios> mun = getMuniDepartamento(codigDepar);
            for (int i = 0; i < mun.size(); i++) {
                if (mun.get(i).getCodigoMunicipio().equals(codigoMun)) {
                    departamentos.get(posicion).getMunicipios().remove(i);
                    return true;
                }
            }
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

    public static ArrayList<Municipios> getAllMunicipios() {
        ArrayList<Municipios> mRegresar = new ArrayList<Municipios>();

        for (int j = 0; j < departamentos.size(); j++) {
            Departamentos d = departamentos.get(j);
            for (int k = 0; k < d.getMunicipios().size(); k++) {
                mRegresar.add(new Municipios(d.getCodDepartamento(), d.getMunicipios().get(k).getNombreMunicipio(), d.getMunicipios().get(k).getCodigoMunicipio()));
            }
        }
        return mRegresar;
    }

    public static String getRegionbyCodigo(String codDepartamento) {
        String regresar = "";
        for (int i = 0; i < departamentos.size(); i++) {
            Departamentos d = departamentos.get(i);
            if (d.getCodDepartamento().equals(codDepartamento)) {
                regresar = d.getCodigo();
                return regresar;
            }
        }
        return regresar;
    }

    public static ArrayList<Departamentos> getAllDepartamentosByCod(String codigRegion) {
        ArrayList<Departamentos> mRegresar = new ArrayList<Departamentos>();
        for (int j = 0; j < departamentos.size(); j++) {
            Departamentos d = departamentos.get(j);
            if (d.getIdRegion().equals(codigRegion)) {
                mRegresar.add(new Departamentos(d.getIdRegion(), codigRegion, d.getNombre(), d.getPrecioEstandar(), d.getPrecioEspecial(), d.getCodDepartamento(), d.getNombreDepartamento()));
            }
        }
        return mRegresar;
    }

}
