package Administrador;

import java.util.ArrayList;

public class Departamentos extends Regiones {

    private String codDepartamento;
    private String nombreDepartamento;
    private ArrayList<Municipios> municipios;
    
    public Departamentos(String codigo, String nombre, double precioEstandar, double precioEspecial, String codDepartamento,
            String nombreDepa) {
        super(codigo, nombre, precioEstandar, precioEspecial);
        this.setCodDepartamento(codDepartamento);
        this.setNombreDepartamento(nombreDepa);
        this.municipios = new ArrayList<>();
    }
    
    public String getCodDepartamento() {
        return codDepartamento;
    }

    public void setCodDepartamento(String codDepartamento) {
        this.codDepartamento = codDepartamento;
    }

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }

    public ArrayList<Municipios> getMunicipios() {
        return municipios;
    }

    public void setMunicipios(ArrayList<Municipios> municipios) {
        this.municipios = municipios;
    }
    
    @Override
    public String toString() {
        return this.getNombreDepartamento();
    }
}
