package Componentes.CRUD;

import Administrador.Departamentos;
import Administrador.Municipios;
import Administrador.Regiones;
import Administrador.ctrlDepartamentos;
import Administrador.ctrlRegiones;
import Elementos.CutomTable.TableActionCellEditorEliminar;
import Elementos.CutomTable.TableActionCellRenderEliminar;
import Elementos.CutomTable.TableActionEvent;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mesoi
 */
public class AdminModificarDepMun extends javax.swing.JPanel {

    public ArrayList<Regiones> regio;
    private ArrayList<Departamentos> totalDepartamentos;
    private DefaultTableModel modelo;
    private DefaultTableModel modelo2;

    /**
     * Creates new form AdminModificarDepMun
     */
    public AdminModificarDepMun() {
        initComponents();
        this.setBounds(0, 0, 700, 455);
        modelo = (DefaultTableModel) tabla1.getModel();
        modelo2 = (DefaultTableModel) tabla2.getModel();
        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
            }

            @Override
            public void onDelete(int row) {

                if (tabla1.isEditing()) {
                    tabla1.getCellEditor().stopCellEditing();
                }
                DefaultTableModel model = (DefaultTableModel) tabla1.getModel();
                String codigo = String.valueOf(model.getValueAt(row, 0));
                if (ctrlDepartamentos.eliminarDepartamento(codigo)) {
                    model.removeRow(row);
                    JOptionPane.showMessageDialog(null, "Region eliminada correctamente");
                    //cargarBoxRegiones();
                    //cargarRegiones();
                }

            }

            @Override
            public void onView(int row) {
            }
        };
        //Segunda Tabla
        TableActionEvent event2 = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
            }

            @Override
            public void onDelete(int row) {
                if (tabla2.isEditing()) {
                    tabla2.getCellEditor().stopCellEditing();
                }
                Departamentos depItem = (Departamentos) boxDeparE.getSelectedItem();
                if (depItem != null) {
                    DefaultTableModel model = (DefaultTableModel) tabla2.getModel();
                    String codigoDepartamento = depItem.getCodigo();
                    String codigo = String.valueOf(model.getValueAt(row, 0));
                    if (ctrlDepartamentos.eliminarMunicipio(codigoDepartamento,codigo)) {
                        model.removeRow(row);
                        JOptionPane.showMessageDialog(null, "Region eliminada correctamente");
                    }
                }

            }

            @Override
            public void onView(int row) {
            }
        };
        tabla1.getColumnModel().getColumn(2).setCellRenderer(new TableActionCellRenderEliminar());
        tabla1.getColumnModel().getColumn(2).setCellEditor(new TableActionCellEditorEliminar(event));

        tabla2.getColumnModel().getColumn(2).setCellRenderer(new TableActionCellRenderEliminar());
        tabla2.getColumnModel().getColumn(2).setCellEditor(new TableActionCellEditorEliminar(event2));
        //regio = ctrlRegiones.getTodasRegiones();
        cargarB();

        cargarTabla1();
        cargarTabla2();

    }

    public void cargarB() {
        cargarRegiones(boxRegionUpdate);
        cargarRegiones(boxRegionDepE);
        cargarRegiones(boxRegionEliminar);
        cargarRegiones(boxRegionMuni);
    }

    public void ActualizarDepartamento() {
        Departamentos dep = (Departamentos) boxDepartamentosUpdate.getSelectedItem();
        String codDepartamento = dep.getCodDepartamento();
        String nNombre = txtNuevoDepartamento.getText();
        if (ctrlDepartamentos.modificarNombreDep(codDepartamento, nNombre)) {
            JOptionPane.showMessageDialog(null, "Nombre Actualizado");
            ctrlDepartamentos.copiarHistorialDep();
            cargarB();
            //cargarDepartamentos(boxDepartamentosUpdate, codDepartamento);
        } else {
            JOptionPane.showMessageDialog(null, "Error");
        }

    }

    public void cargarRegiones(JComboBox caja) {
        caja.removeAllItems();
        regio = ctrlRegiones.getTodasRegiones();
        for (int i = 0; i < regio.size(); i++) {
            if (regio.get(i) != null) {
                String codeR = regio.get(i).getCodigo();
                String nombreRegion = regio.get(i).getNombre();
                caja.addItem(new Regiones(codeR, nombreRegion));
            }
        }
    }

    public void cargarDepartamentos(JComboBox actualizar, String codDepartamento) {
        //boxMunicipios.addItem("Municipios");
        // 
        limpiarBoxes(actualizar);
        ArrayList<Departamentos> departamento = (ArrayList<Departamentos>) ctrlDepartamentos.getAllDepartamentosByCod(codDepartamento).clone();
        for (int i = 0; i < departamento.size(); i++) {
            if (departamento.get(i) != null) {
                String codeD = departamento.get(i).getCodDepartamento();
                String nombreMuni = departamento.get(i).getNombreDepartamento();
                actualizar.addItem(new Departamentos(codeD, nombreMuni, departamento.get(i).getPrecioEstandar(), departamento.get(i).getPrecioEspecial(), departamento.get(i).getCodDepartamento(), departamento.get(i).getNombreDepartamento()));
            }
        }
    }

    public void limpiarBoxes(JComboBox eliminar) {
        eliminar.removeAllItems();
    }

    public void cargarTabla1() {
        modelo.setRowCount(0);
        //boxRegionEliminar
        Regiones depRegion = (Regiones) boxRegionEliminar.getSelectedItem();
        if (depRegion != null) {
            ArrayList<Departamentos> totalDepartamentos = (ArrayList<Departamentos>) ctrlDepartamentos.getAllDepartamentosByCod(depRegion.getCodigo()).clone();
            for (Departamentos dep : totalDepartamentos) {
                Object datos[] = new Object[2];
                datos[0] = dep.getCodDepartamento();
                datos[1] = dep.getNombreDepartamento();
                modelo.addRow(datos);
            }
        }
    }

    //
    public void cargarMunicipios(JComboBox actualizar) {
        //boxMunicipios.addItem("Municipios");
        limpiarBoxes(actualizar);
        Departamentos depItem = (Departamentos) boxDepartamentosM.getSelectedItem();
        if (depItem != null) {
            //limpiarBoxes(boxMuniUpdate);
            String codDepartamento = depItem.getCodDepartamento();
            limpiarBoxes(actualizar);
            ArrayList<Municipios> muni = ctrlDepartamentos.getMuniDepartamento(codDepartamento);
            for (int i = 0; i < muni.size(); i++) {
                if (muni.get(i) != null) {
                    String codeR = muni.get(i).getCodigoMunicipio();
                    String nombreMuni = muni.get(i).getNombreMunicipio();
                    actualizar.addItem(new Municipios(codDepartamento, nombreMuni, codeR));

                }
            }
        }

    }

    public void actualizarMunicipio() {
        //Departamentos dep = (Departamentos) boxRegionMuni.getSelectedItem();
        Municipios mun = (Municipios) boxMuniUpdate.getSelectedItem();
        //String codDepartamento = dep.getCodDepartamento();
        String nNombre = txtNuevoMunicipio.getText();
        if (ctrlDepartamentos.modificarNombreMun(mun.getCodigoDepartamento().toString(), mun.getCodigoMunicipio().toString(), nNombre)) {
            JOptionPane.showMessageDialog(null, "Nombre Actualizado");
            //cargarB();
            ctrlDepartamentos.copiarHistorialDep();
            cargarMunicipios(boxMuniUpdate);
            //cargarDepartamentos(boxDepartamentosUpdate, codDepartamento);
        } else {
            JOptionPane.showMessageDialog(null, "Error");
        }
    }

    public void cargarTabla2() {
        modelo2.setRowCount(0);
        //boxRegionEliminar
        Departamentos depSelected = (Departamentos) boxDeparE.getSelectedItem();
        if (depSelected != null) {
            // ArrayList<Departamentos> totalDepartamentos = (ArrayList<Departamentos>) ctrlDepartamentos.getAllDepartamentosByCod(depSelected.getCodigo()).clone();
            ArrayList<Municipios> totalMunicipios = ctrlDepartamentos.getMuniDepartamento(depSelected.getCodDepartamento());
            for (Municipios dep : totalMunicipios) {
                Object datos[] = new Object[2];
                datos[0] = dep.getCodigoMunicipio();
                datos[1] = dep.getNombreMunicipio();
                modelo2.addRow(datos);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelRound1 = new Elementos.PanelRound();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        panelRound2 = new Elementos.PanelRound();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        boxRegionUpdate = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        boxDepartamentosUpdate = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        lblDepartamentoActual = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtNuevoDepartamento = new javax.swing.JTextField();
        btnEditarDep = new Elementos.ButtonRound();
        jLabel7 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla1 = new Elementos.CutomTable.TableDark();
        jLabel18 = new javax.swing.JLabel();
        boxRegionEliminar = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        panelRound3 = new Elementos.PanelRound();
        jLabel10 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        boxRegionMuni = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        boxDepartamentosM = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        boxMuniUpdate = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        txtNuevoMunicipio = new javax.swing.JTextField();
        btnEditarMuni = new Elementos.ButtonRound();
        txtMunicipioActual = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabla2 = new Elementos.CutomTable.TableDark();
        jLabel19 = new javax.swing.JLabel();
        boxRegionDepE = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        boxDeparE = new javax.swing.JComboBox<>();

        jLabel1.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        jLabel1.setText("Modificar Departamentos");

        panelRound2.setBackground(new java.awt.Color(71, 73, 75));

        jPanel2.setOpaque(false);

        jLabel2.setText("Región");

        boxRegionUpdate.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                boxRegionUpdateItemStateChanged(evt);
            }
        });

        jLabel3.setText("Departamento");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boxRegionUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(boxDepartamentosUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(boxRegionUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(boxDepartamentosUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
        );

        jLabel4.setText("Nombre Actual");

        lblDepartamentoActual.setText("jLabel5");

        jLabel6.setText("Nuevo Nombre");

        btnEditarDep.setText("Modificar");
        btnEditarDep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarDepActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        jLabel7.setText("Actualizar Nombres");

        jLabel8.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        jLabel8.setText("Eliminar Departamentos");

        tabla1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Codigo", "Nombre", "Eliminar"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla1.setRowHeight(45);
        jScrollPane2.setViewportView(tabla1);

        jLabel18.setText("Region");

        boxRegionEliminar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                boxRegionEliminarItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout panelRound2Layout = new javax.swing.GroupLayout(panelRound2);
        panelRound2.setLayout(panelRound2Layout);
        panelRound2Layout.setHorizontalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound2Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(panelRound2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(lblDepartamentoActual, javax.swing.GroupLayout.PREFERRED_SIZE, 508, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtNuevoDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, 579, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelRound2Layout.createSequentialGroup()
                        .addGap(257, 257, 257)
                        .addComponent(btnEditarDep, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelRound2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7))
                    .addGroup(panelRound2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel8)
                            .addGroup(panelRound2Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addGap(18, 18, 18)
                                .addComponent(boxRegionEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 528, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2))))
                .addContainerGap(8, Short.MAX_VALUE))
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lblDepartamentoActual))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNuevoDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEditarDep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(boxRegionEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jLabel9.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        jLabel9.setText("Modificar Municipios");

        panelRound3.setBackground(new java.awt.Color(70, 74, 77));

        jLabel10.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        jLabel10.setText("Actualizar Nombres");

        jPanel3.setOpaque(false);

        jLabel11.setText("Región");

        boxRegionMuni.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                boxRegionMuniItemStateChanged(evt);
            }
        });

        jLabel12.setText("Departamento");

        boxDepartamentosM.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                boxDepartamentosMItemStateChanged(evt);
            }
        });

        jLabel13.setText("Municipio");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boxRegionMuni, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boxDepartamentosM, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boxMuniUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(boxRegionMuni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(boxDepartamentosM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(boxMuniUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
        );

        jLabel16.setText("Nuevo Nombre");

        btnEditarMuni.setText("Modificar");
        btnEditarMuni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarMuniActionPerformed(evt);
            }
        });

        txtMunicipioActual.setText("jLabel5");

        jLabel14.setText("Nombre Actual");

        jLabel17.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        jLabel17.setText("Eliminar Departamentos");

        tabla2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Codigo", "Nombre", "Eliminar"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tabla2);

        jLabel19.setText("Region");

        boxRegionDepE.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                boxRegionDepEItemStateChanged(evt);
            }
        });

        jLabel20.setText("Departamento");

        boxDeparE.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                boxDeparEItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout panelRound3Layout = new javax.swing.GroupLayout(panelRound3);
        panelRound3.setLayout(panelRound3Layout);
        panelRound3Layout.setHorizontalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel10))
                    .addGroup(panelRound3Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addGroup(panelRound3Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(18, 18, 18)
                                .addComponent(txtMunicipioActual, javax.swing.GroupLayout.PREFERRED_SIZE, 508, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtNuevoMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, 612, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelRound3Layout.createSequentialGroup()
                                .addGap(244, 244, 244)
                                .addComponent(btnEditarMuni, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(panelRound3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel17))
                    .addGroup(panelRound3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel19)
                        .addGap(18, 18, 18)
                        .addComponent(boxRegionDepE, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(boxDeparE, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelRound3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 599, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRound3Layout.setVerticalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtMunicipioActual))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNuevoMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEditarMuni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(boxRegionDepE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(boxDeparE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(jLabel9)
                    .addComponent(panelRound2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelRound3, javax.swing.GroupLayout.PREFERRED_SIZE, 624, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelRound2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelRound3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 670, Short.MAX_VALUE)
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void boxRegionUpdateItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_boxRegionUpdateItemStateChanged
        // TODO add your handling code here:
        Regiones depItem = (Regiones) boxRegionUpdate.getSelectedItem();
        if (depItem != null) {
            String codDepartamento = depItem.getCodigo();
            cargarDepartamentos(boxDepartamentosUpdate, codDepartamento);
        }

    }//GEN-LAST:event_boxRegionUpdateItemStateChanged

    private void btnEditarDepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarDepActionPerformed
        // TODO add your handling code here:
        ActualizarDepartamento();
    }//GEN-LAST:event_btnEditarDepActionPerformed

    private void boxRegionEliminarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_boxRegionEliminarItemStateChanged
        // TODO add your handling code here:
        cargarTabla1();
    }//GEN-LAST:event_boxRegionEliminarItemStateChanged

    private void boxRegionMuniItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_boxRegionMuniItemStateChanged
        // TODO add your handling code here:
        Regiones depItem = (Regiones) boxRegionMuni.getSelectedItem();
        if (depItem != null) {
            String codDepartamento = depItem.getCodigo();
            cargarDepartamentos(boxDepartamentosM, codDepartamento);
        }
    }//GEN-LAST:event_boxRegionMuniItemStateChanged

    private void boxDepartamentosMItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_boxDepartamentosMItemStateChanged
        // TODO add your handling code here:
        cargarMunicipios(boxMuniUpdate);


    }//GEN-LAST:event_boxDepartamentosMItemStateChanged

    private void btnEditarMuniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarMuniActionPerformed
        // TODO add your handling code here:
        actualizarMunicipio();
    }//GEN-LAST:event_btnEditarMuniActionPerformed

    private void boxRegionDepEItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_boxRegionDepEItemStateChanged
        // TODO add your handling code here:
        Regiones depItem = (Regiones) boxRegionDepE.getSelectedItem();
        if (depItem != null) {
            String codDepartamento = depItem.getCodigo();
            cargarDepartamentos(boxDeparE, codDepartamento);
        }
    }//GEN-LAST:event_boxRegionDepEItemStateChanged

    private void boxDeparEItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_boxDeparEItemStateChanged
        // TODO add your handling code here:
        cargarTabla2();
    }//GEN-LAST:event_boxDeparEItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<Object> boxDeparE;
    private javax.swing.JComboBox<Object> boxDepartamentosM;
    private javax.swing.JComboBox<Object> boxDepartamentosUpdate;
    private javax.swing.JComboBox<Object> boxMuniUpdate;
    private javax.swing.JComboBox<Object> boxRegionDepE;
    private javax.swing.JComboBox<Object> boxRegionEliminar;
    private javax.swing.JComboBox<Object> boxRegionMuni;
    private javax.swing.JComboBox<Object> boxRegionUpdate;
    private Elementos.ButtonRound btnEditarDep;
    private Elementos.ButtonRound btnEditarMuni;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblDepartamentoActual;
    private Elementos.PanelRound panelRound1;
    private Elementos.PanelRound panelRound2;
    private Elementos.PanelRound panelRound3;
    private Elementos.CutomTable.TableDark tabla1;
    private Elementos.CutomTable.TableDark tabla2;
    private javax.swing.JLabel txtMunicipioActual;
    private javax.swing.JTextField txtNuevoDepartamento;
    private javax.swing.JTextField txtNuevoMunicipio;
    // End of variables declaration//GEN-END:variables
}
