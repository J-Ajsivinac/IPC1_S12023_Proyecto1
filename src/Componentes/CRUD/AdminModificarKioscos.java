package Componentes.CRUD;

import Administrador.Departamentos;
import Administrador.Kioscos;
import Administrador.Regiones;
import Administrador.ctrlDepartamentos;
import Administrador.ctrlKioscos;
import Administrador.ctrlRegiones;
import Elementos.CutomTable.TableActionCellEditorEliminar;
import Elementos.CutomTable.TableActionCellRenderEliminar;
import Elementos.CutomTable.TableActionEvent;
import Interfaz.login;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mesoi
 */
public class AdminModificarKioscos extends javax.swing.JPanel {

    public ArrayList<Regiones> regio;
    private DefaultTableModel modelo;

    /**
     * Creates new form AdminModificarKioscos
     */
    public AdminModificarKioscos() {
        initComponents();
        this.setBounds(0, 0, 700, 455);
        modelo = (DefaultTableModel) tabla1.getModel();

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
                if (ctrlKioscos.eliminarKiosco(codigo)) {
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
        tabla1.getColumnModel().getColumn(2).setCellRenderer(new TableActionCellRenderEliminar());
        tabla1.getColumnModel().getColumn(2).setCellEditor(new TableActionCellEditorEliminar(event));

        tabla1.fixTable(jScrollPane2);
        jScrollPane2.getVerticalScrollBar().setUnitIncrement(30);
        txtNuevoNombre.setBorder(login.unselectedborder);
        Regiones depItem = (Regiones) boxRegionUpdate.getSelectedItem();
        if (depItem != null) {
            String codDepartamento = depItem.getIdRegion();
            cargarDepartamentos(boxDepartamentosUpdate, codDepartamento);
        }

    }

    public void cargarB() {
        cargarRegiones(boxRegionUpdate);
        cargarRegiones(boxRegionEliminar);
        cargarKiosco();
    }

    public void cargarRegiones(JComboBox caja) {
        caja.removeAllItems();
        regio = ctrlRegiones.getTodasRegiones();
        for (int i = 0; i < regio.size(); i++) {
            if (regio.get(i) != null) {
                String codeR = regio.get(i).getIdRegion();
                String nombreRegion = regio.get(i).getNombre();
                caja.addItem(new Regiones(codeR, nombreRegion));
            }
        }
    }

    public void cargarDepartamentos(JComboBox actualizar, String codKiosco) {
        //boxMunicipios.addItem("Municipios");
        // 
        limpiarBoxes(actualizar);
        ArrayList<Kioscos> kiosco = (ArrayList<Kioscos>) ctrlKioscos.getAllDepartamentosKiosco(codKiosco).clone();
        for (int i = 0; i < kiosco.size(); i++) {
            if (kiosco.get(i) != null) {
                String codeD = kiosco.get(i).getCodigoKioco();
                String nombreMuni = kiosco.get(i).getNombreKiosco();
                actualizar.addItem(new Kioscos(kiosco.get(i).getIdRegion(), codeD, nombreMuni, kiosco.get(i).getPrecioEstandar(), kiosco.get(i).getPrecioEspecial(), kiosco.get(i).getCodigoKioco(), kiosco.get(i).getNombreKiosco()));
            }
        }
    }

    public void limpiarBoxes(JComboBox eliminar) {
        eliminar.removeAllItems();
    }

    public void actualizarKiosco() {
        Kioscos dep = (Kioscos) boxDepartamentosUpdate.getSelectedItem();

        if (dep != null && boxDepartamentosUpdate != null && !txtNuevoNombre.getText().toString().equals("")) {
            String codDepartamento = dep.getCodigoKioco();
            String nNombre = txtNuevoNombre.getText();
            if (ctrlKioscos.modificarNombreKiosco(codDepartamento, nNombre)) {
                JOptionPane.showMessageDialog(null, "Nombre Actualizado");
                cargarB();
                txtNuevoNombre.setText("");
                //cargarDepartamentos(boxDepartamentosUpdate, codDepartamento);
            } else {
                JOptionPane.showMessageDialog(null, "Error");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Faltan datos para poder Actualizar");
        }

    }

    public void cargarTabla1() {
        modelo.setRowCount(0);
        //boxRegionEliminar
        Regiones depRegion = (Regiones) boxRegionEliminar.getSelectedItem();
        if (depRegion != null) {
            ArrayList<Kioscos> totalKioscoses = (ArrayList<Kioscos>) ctrlKioscos.getAllDepartamentosKiosco(depRegion.getIdRegion()).clone();
            for (Kioscos k : totalKioscoses) {
                Object datos[] = new Object[2];
                datos[0] = k.getCodigoKioco();
                datos[1] = k.getNombreKiosco();
                modelo.addRow(datos);
            }
        }
    }

    public void cargarKiosco() {
        Regiones depItem = (Regiones) boxRegionUpdate.getSelectedItem();
        if (depItem != null) {
            String codDepartamento = depItem.getIdRegion();
            cargarDepartamentos(boxDepartamentosUpdate, codDepartamento);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tableDark1 = new Elementos.CutomTable.TableDark();
        panelRound1 = new Elementos.PanelRound();
        jLabel1 = new javax.swing.JLabel();
        panelRound2 = new Elementos.PanelRound();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        boxRegionUpdate = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        boxDepartamentosUpdate = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtNuevoNombre = new javax.swing.JTextField();
        btnActualizar = new Elementos.ButtonRound();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla1 = new Elementos.CutomTable.TableDark();
        jLabel8 = new javax.swing.JLabel();
        boxRegionEliminar = new javax.swing.JComboBox<>();

        tableDark1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tableDark1);

        setOpaque(false);

        panelRound1.setBackground(new java.awt.Color(28, 28, 36));

        jLabel1.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Modificar Kioscos");

        panelRound2.setBackground(new java.awt.Color(19, 19, 26));
        panelRound2.setForeground(new java.awt.Color(19, 19, 26));
        panelRound2.setRoundBottomLeft(15);
        panelRound2.setRoundBottomRight(15);
        panelRound2.setRoundTopLeft(15);
        panelRound2.setRoundTopRight(15);

        jPanel2.setOpaque(false);

        jLabel2.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Región");

        boxRegionUpdate.setBackground(new java.awt.Color(40, 41, 52));
        boxRegionUpdate.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        boxRegionUpdate.setForeground(new java.awt.Color(255, 255, 255));
        boxRegionUpdate.setBorder(null);
        boxRegionUpdate.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                boxRegionUpdateItemStateChanged(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Kiosco");

        boxDepartamentosUpdate.setBackground(new java.awt.Color(40, 41, 52));
        boxDepartamentosUpdate.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        boxDepartamentosUpdate.setForeground(new java.awt.Color(255, 255, 255));
        boxDepartamentosUpdate.setBorder(null);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boxRegionUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(boxDepartamentosUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(boxRegionUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(boxDepartamentosUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9))
        );

        jLabel4.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nombre Actual:");

        jLabel5.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Nuevo Nombre");

        txtNuevoNombre.setBackground(new java.awt.Color(40, 41, 52));
        txtNuevoNombre.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        txtNuevoNombre.setForeground(new java.awt.Color(255, 255, 255));
        txtNuevoNombre.setBorder(null);
        txtNuevoNombre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNuevoNombreFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNuevoNombreFocusLost(evt);
            }
        });

        btnActualizar.setBorder(null);
        btnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizar.setText("Actualizar");
        btnActualizar.setBorderColor(new java.awt.Color(123, 127, 239));
        btnActualizar.setColor(new java.awt.Color(123, 127, 239));
        btnActualizar.setColorClick(new java.awt.Color(121, 118, 236));
        btnActualizar.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        btnActualizar.setRadius(15);
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound2Layout = new javax.swing.GroupLayout(panelRound2);
        panelRound2.setLayout(panelRound2Layout);
        panelRound2Layout.setHorizontalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNuevoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 603, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound2Layout.createSequentialGroup()
                        .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(260, 260, 260))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35))))
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNuevoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        jLabel7.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Eliminar Kioscos");

        tabla1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Codigo", "Nombre", "Borrar"
            }
        ));
        tabla1.setRowHeight(65);
        jScrollPane2.setViewportView(tabla1);

        jLabel8.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Región");

        boxRegionEliminar.setBackground(new java.awt.Color(40, 41, 52));
        boxRegionEliminar.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        boxRegionEliminar.setForeground(new java.awt.Color(255, 255, 255));
        boxRegionEliminar.setBorder(null);
        boxRegionEliminar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                boxRegionEliminarItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7)
                    .addComponent(jLabel1)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 635, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelRound1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(boxRegionEliminar, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addComponent(panelRound2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelRound2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(boxRegionEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void boxRegionUpdateItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_boxRegionUpdateItemStateChanged
        // TODO add your handling code here:
        cargarKiosco();
    }//GEN-LAST:event_boxRegionUpdateItemStateChanged

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // TODO add your handling code here:
        actualizarKiosco();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void boxRegionEliminarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_boxRegionEliminarItemStateChanged
        // TODO add your handling code here:
        cargarTabla1();
    }//GEN-LAST:event_boxRegionEliminarItemStateChanged

    private void txtNuevoNombreFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNuevoNombreFocusGained
        // TODO add your handling code here:
        AdminAgregarRegiones.selected(txtNuevoNombre, 1);
    }//GEN-LAST:event_txtNuevoNombreFocusGained

    private void txtNuevoNombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNuevoNombreFocusLost
        // TODO add your handling code here:
        AdminAgregarRegiones.selected(txtNuevoNombre, 0);
    }//GEN-LAST:event_txtNuevoNombreFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<Object> boxDepartamentosUpdate;
    private javax.swing.JComboBox<Object> boxRegionEliminar;
    private javax.swing.JComboBox<Object> boxRegionUpdate;
    private Elementos.ButtonRound btnActualizar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private Elementos.PanelRound panelRound1;
    private Elementos.PanelRound panelRound2;
    private Elementos.CutomTable.TableDark tabla1;
    private Elementos.CutomTable.TableDark tableDark1;
    private javax.swing.JTextField txtNuevoNombre;
    // End of variables declaration//GEN-END:variables
}
