package Componentes.CRUD;

import Administrador.Departamentos;
import Administrador.Municipios;
import Administrador.Regiones;
import Administrador.ctrlDepartamentos;
import Administrador.ctrlRegiones;
import Elementos.ScrollBarCustom;
import Interfaz.login;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mesoi
 */
public class AdminAgregarDepMun extends javax.swing.JPanel {

    public ArrayList<Regiones> regio;
    public ArrayList<Departamentos> depa;
    public ArrayList<Municipios> munic;
    private DefaultTableModel modelo;
    private DefaultTableModel modelo1;

    /**
     * Creates new form AdminAgregarDepMun
     */
    public AdminAgregarDepMun() {
        initComponents();
        this.setBounds(0, 0, 700, 455);
        regio = ctrlRegiones.getTodasRegiones();
        jScrollPane1.setVerticalScrollBar(new ScrollBarCustom());
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(28);
        depa = ctrlDepartamentos.getAllD();
        modelo = (DefaultTableModel) table1.getModel();
        modelo1 = (DefaultTableModel) table2.getModel();
        table1.fixTable(jScrollPane2);
        jScrollPane2.getVerticalScrollBar().setUnitIncrement(30);

        table2.fixTable(jScrollPane3);
        jScrollPane3.getVerticalScrollBar().setUnitIncrement(30);
    }

    public void cargarRegiones() {
        regio = ctrlRegiones.getTodasRegiones();
        boxRegion.removeAllItems();
        boxRegion1.removeAllItems();
        for (int i = 0; i < regio.size(); i++) {
            if (regio.get(i) != null) {
                String codeR = regio.get(i).getIdRegion();
                String nombreRegion = regio.get(i).getNombre();
                boxRegion.addItem(new Regiones(codeR, nombreRegion));
                boxRegion1.addItem(new Regiones(codeR, nombreRegion));
            }
        }
    }

    public void cargarDepartamentosMun() {
        //boxMunicipios.addItem("Municipios");
        boxDepartamento.removeAllItems();
        Regiones depItem = (Regiones) boxRegion1.getSelectedItem();
        
        if (depItem != null) {
            String codDepartamento = depItem.getIdRegion();
            System.out.println(codDepartamento);
            ArrayList<Departamentos> departamento = ctrlDepartamentos.getAllDepartamentosByCod(codDepartamento);
            for (int i = 0; i < departamento.size(); i++) {
                if (departamento.get(i) != null) {
                    String codeD = departamento.get(i).getCodDepartamento();
                    String nombreMuni = departamento.get(i).getNombreDepartamento();
                    boxDepartamento.addItem(new Departamentos(departamento.get(i).getIdRegion(), codeD, nombreMuni, departamento.get(i).getPrecioEstandar(), departamento.get(i).getPrecioEspecial(), departamento.get(i).getCodDepartamento(), departamento.get(i).getNombreDepartamento()));
                }
            }
        }else{
            System.out.println("xd");
        }

    }

    public void limpiarBoxes(JComboBox eliminar) {
        eliminar.removeAllItems();
    }

    public void limpiarDepartamentos() {
        boxDepartamento.removeAllItems();
    }

    public void ingresarDepartamentos() {
        Regiones regItem = (Regiones) boxRegion.getSelectedItem();

        String nombreDepartament = txtNombreDepartamento.getText();
        if (!(nombreDepartament.equals("")) && regItem != null) {
            String codigo = regItem.getIdRegion();
            if (ctrlDepartamentos.nuevoDepartamento(codigo, nombreDepartament)) {
                JOptionPane.showMessageDialog(null, "Departamento ingresado Correctamente");
                cargarRegiones();
                txtNombreDepartamento.setText("");
                cargarDepartamentosMun();
                cargarTablaDepartamentos();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ingrese todos los datos");
        }
    }

    public void ingresarMunicipio() {
        Departamentos departa = (Departamentos) boxDepartamento.getSelectedItem();
        Regiones region = (Regiones) boxRegion1.getSelectedItem();
        String codigo = "";
        String nombreMuni = txtNombreMunicipio.getText();
        if (!(nombreMuni.equals("")) && departa != null && region != null) {
            codigo = departa.getCodDepartamento();
            if (ctrlDepartamentos.agregarMunicipios(codigo, nombreMuni)) {
                cargarTablaMunicipios();
                txtNombreMunicipio.setText("");
                JOptionPane.showMessageDialog(null, "Municipio ingresado Correctamente");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ingrese todos los datos");
        }
    }

    public void cargarTablaDepartamentos() {
        modelo.setRowCount(0);
        depa = (ArrayList<Departamentos>) ctrlDepartamentos.getAllD().clone();
        for (Departamentos departa : depa) {
            Object datos[] = new Object[3];
            datos[0] = departa.getCodDepartamento();
            String cod = departa.getIdRegion();
            datos[1] = ctrlRegiones.getRegionCodigo(cod).getNombre();
            datos[2] = departa.getNombreDepartamento();
            modelo.addRow(datos);
        }
    }

    public void cargarTablaMunicipios() {
        modelo1.setRowCount(0);
        munic = (ArrayList<Municipios>) ctrlDepartamentos.getAllMunicipios().clone();
        if (munic != null) {
            for (Municipios mun : munic) {
                Object datos[] = new Object[3];
                String codDep = mun.getCodigoDepartamento();
                String codRegion = ctrlDepartamentos.getDepartamentoCodigo(codDep).getIdRegion();
                if (codDep != null && codRegion != null) {
                    datos[0] = ctrlRegiones.getRegionCodigo(codRegion).getNombre();
                    datos[1] = codDep;
                    datos[2] = mun.getNombreMunicipio();
                    modelo1.addRow(datos);
                }

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
        panelRound2 = new Elementos.PanelRound();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        boxRegion = new javax.swing.JComboBox<>();
        buttonRound1 = new Elementos.ButtonRound();
        jScrollPane2 = new javax.swing.JScrollPane();
        table1 = new Elementos.CutomTable.TableDark();
        txtNombreDepartamento = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        panelRound3 = new Elementos.PanelRound();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        boxRegion1 = new javax.swing.JComboBox<>();
        boxDepartamento = new javax.swing.JComboBox<>();
        buttonRound2 = new Elementos.ButtonRound();
        jScrollPane3 = new javax.swing.JScrollPane();
        table2 = new Elementos.CutomTable.TableDark();
        jLabel7 = new javax.swing.JLabel();
        txtNombreMunicipio = new javax.swing.JTextField();

        panelRound1.setBackground(new java.awt.Color(80, 83, 84));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(19, 19, 26)));

        jPanel1.setBackground(new java.awt.Color(19, 19, 26));

        panelRound2.setBackground(new java.awt.Color(28, 28, 36));
        panelRound2.setRoundBottomLeft(15);
        panelRound2.setRoundBottomRight(15);
        panelRound2.setRoundTopLeft(15);
        panelRound2.setRoundTopRight(15);

        jLabel2.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Región");

        jLabel3.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nombre del Departamento");

        boxRegion.setForeground(new java.awt.Color(255, 255, 255));

        buttonRound1.setBorder(null);
        buttonRound1.setForeground(new java.awt.Color(255, 255, 255));
        buttonRound1.setText("Registrar Departamento");
        buttonRound1.setBorderColor(new java.awt.Color(123, 127, 239));
        buttonRound1.setColor(new java.awt.Color(123, 127, 239));
        buttonRound1.setColorClick(new java.awt.Color(121, 118, 236));
        buttonRound1.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        buttonRound1.setRadius(15);
        buttonRound1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRound1ActionPerformed(evt);
            }
        });

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Código", "Región", "Nombre"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table1.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        table1.setRowHeight(40);
        jScrollPane2.setViewportView(table1);

        txtNombreDepartamento.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        txtNombreDepartamento.setForeground(new java.awt.Color(255, 255, 255));
        txtNombreDepartamento.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNombreDepartamentoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNombreDepartamentoFocusLost(evt);
            }
        });

        javax.swing.GroupLayout panelRound2Layout = new javax.swing.GroupLayout(panelRound2);
        panelRound2.setLayout(panelRound2Layout);
        panelRound2Layout.setHorizontalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelRound2Layout.createSequentialGroup()
                        .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelRound2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(boxRegion, javax.swing.GroupLayout.PREFERRED_SIZE, 548, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtNombreDepartamento, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 603, Short.MAX_VALUE)))
                        .addContainerGap(16, Short.MAX_VALUE))))
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addGap(211, 211, 211)
                .addComponent(buttonRound1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(boxRegion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNombreDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonRound1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Registrar Departamentos");

        jLabel4.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Registrar Municipio");

        panelRound3.setBackground(new java.awt.Color(28, 28, 36));
        panelRound3.setRoundBottomLeft(15);
        panelRound3.setRoundBottomRight(15);
        panelRound3.setRoundTopLeft(15);
        panelRound3.setRoundTopRight(15);

        jLabel5.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Región");

        jLabel6.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Nombre del Departamento");

        boxRegion1.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        boxRegion1.setForeground(new java.awt.Color(255, 255, 255));
        boxRegion1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                boxRegion1ItemStateChanged(evt);
            }
        });

        boxDepartamento.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        boxDepartamento.setForeground(new java.awt.Color(255, 255, 255));

        buttonRound2.setBorder(null);
        buttonRound2.setForeground(new java.awt.Color(255, 255, 255));
        buttonRound2.setText("Registrar Departamento");
        buttonRound2.setBorderColor(new java.awt.Color(123, 127, 239));
        buttonRound2.setColor(new java.awt.Color(123, 127, 239));
        buttonRound2.setColorClick(new java.awt.Color(121, 118, 236));
        buttonRound2.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        buttonRound2.setRadius(15);
        buttonRound2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRound2ActionPerformed(evt);
            }
        });

        table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Región", "Código de Departamento", "Nombre del Municipio"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table2.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        table2.setRowHeight(40);
        jScrollPane3.setViewportView(table2);
        if (table2.getColumnModel().getColumnCount() > 0) {
            table2.getColumnModel().getColumn(0).setPreferredWidth(13);
        }

        jLabel7.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Nombre del Municipio");

        txtNombreMunicipio.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        txtNombreMunicipio.setForeground(new java.awt.Color(255, 255, 255));
        txtNombreMunicipio.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNombreMunicipioFocusGained(evt);
            }
        });

        javax.swing.GroupLayout panelRound3Layout = new javax.swing.GroupLayout(panelRound3);
        panelRound3.setLayout(panelRound3Layout);
        panelRound3Layout.setHorizontalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound3Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound3Layout.createSequentialGroup()
                        .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelRound3Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 602, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtNombreMunicipio, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelRound3Layout.createSequentialGroup()
                                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(boxRegion1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(boxDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))))
                        .addGap(15, 15, 15))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonRound2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(209, 209, 209))
        );
        panelRound3Layout.setVerticalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelRound3Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(boxDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelRound3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(boxRegion1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNombreMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonRound2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(panelRound3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelRound2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelRound2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelRound3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(17, 17, 17))
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 673, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void buttonRound1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRound1ActionPerformed
        // TODO add your handling code here:
        ingresarDepartamentos();
    }//GEN-LAST:event_buttonRound1ActionPerformed

    private void boxRegion1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_boxRegion1ItemStateChanged
        // TODO add your handling code here:
        cargarDepartamentosMun();
    }//GEN-LAST:event_boxRegion1ItemStateChanged

    private void buttonRound2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRound2ActionPerformed
        // TODO add your handling code here:
        ingresarMunicipio();
    }//GEN-LAST:event_buttonRound2ActionPerformed

    private void txtNombreDepartamentoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNombreDepartamentoFocusGained
        // TODO add your handling code here:
        AdminAgregarRegiones.selected(txtNombreDepartamento, 1);
    }//GEN-LAST:event_txtNombreDepartamentoFocusGained

    private void txtNombreMunicipioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNombreMunicipioFocusGained
        // TODO add your handling code here:
        AdminAgregarRegiones.selected(txtNombreMunicipio, 1);
    }//GEN-LAST:event_txtNombreMunicipioFocusGained

    private void txtNombreDepartamentoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNombreDepartamentoFocusLost
        // TODO add your handling code here:
        AdminAgregarRegiones.selected(txtNombreDepartamento, 0);
    }//GEN-LAST:event_txtNombreDepartamentoFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<Object> boxDepartamento;
    private javax.swing.JComboBox<Object> boxRegion;
    private javax.swing.JComboBox<Object> boxRegion1;
    private Elementos.ButtonRound buttonRound1;
    private Elementos.ButtonRound buttonRound2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private Elementos.PanelRound panelRound1;
    private Elementos.PanelRound panelRound2;
    private Elementos.PanelRound panelRound3;
    private Elementos.CutomTable.TableDark table1;
    private Elementos.CutomTable.TableDark table2;
    private javax.swing.JTextField txtNombreDepartamento;
    private javax.swing.JTextField txtNombreMunicipio;
    // End of variables declaration//GEN-END:variables
}
