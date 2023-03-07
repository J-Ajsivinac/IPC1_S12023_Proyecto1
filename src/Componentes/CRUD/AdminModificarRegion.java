package Componentes.CRUD;

import Administrador.Regiones;
import Administrador.ctrlRegiones;
import Elementos.CutomTable.TableActionCellEditor;
import Elementos.CutomTable.TableActionCellEditorEliminar;
import Elementos.CutomTable.TableActionCellRender;
import Elementos.CutomTable.TableActionCellRenderEliminar;
import Elementos.CutomTable.TableActionEvent;
import Interfaz.login;
import java.awt.Color;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mesoi
 */
public class AdminModificarRegion extends javax.swing.JPanel {

    private ArrayList<Regiones> totalRegiones;
    private DefaultTableModel modelo;
    private ArrayList<Regiones> regio;
    private boolean cambioValor = false;
    private boolean activar = false;

    /**
     * Creates new form AdminModificarRegion
     */
    public AdminModificarRegion() {
        initComponents();
        this.setBounds(0, 0, 700, 455);
        modelo = (DefaultTableModel) tabla2.getModel();

        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
            }

            @Override
            public void onDelete(int row) {
                if (tabla2.isEditing()) {
                    tabla2.getCellEditor().stopCellEditing();
                }

                int respuesta = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar la Región?");
                if (respuesta == JOptionPane.YES_OPTION) {
                    DefaultTableModel model = (DefaultTableModel) tabla2.getModel();
                    String codigo = String.valueOf(modelo.getValueAt(row, 0));
                    if (ctrlRegiones.eliminarRegion(codigo, row)) {
                        model.removeRow(row);
                        JOptionPane.showMessageDialog(null, "Region eliminada correctamente");
                        cargarBoxRegiones();
                        cargarRegiones();
                    }
                }

            }

            @Override
            public void onView(int row) {
            }
        };
        Border unselect = BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(207, 207, 207));
        panelSuperior.setBorder(unselect);
        tabla2.getColumnModel().getColumn(2).setCellRenderer(new TableActionCellRenderEliminar());
        tabla2.getColumnModel().getColumn(2).setCellEditor(new TableActionCellEditorEliminar(event));

        tabla2.fixTable(jScrollPane2);
        jScrollPane2.getVerticalScrollBar().setUnitIncrement(30);
        cargarRegiones();
        mostrarDatos();

    }

    public void selected(JTextField cambiar, int tipo) {
        if (tipo == 1) {
            cambiar.setBackground(new Color(50, 51, 64));
            cambiar.setBorder(login.selectedborder);
        } else {
            cambiar.setBackground(new Color(40, 41, 52));
            cambiar.setBorder(login.unselectedborder);
        }
    }

    public void cargarBoxRegiones() {
        boxRegion.removeAllItems();
        regio = ctrlRegiones.getTodasRegiones();
        for (int i = 0; i < regio.size(); i++) {
            if (regio.get(i) != null) {
                String codeR = regio.get(i).getCodigo();
                String nombreRegion = regio.get(i).getNombre();
                boxRegion.addItem(new Regiones(codeR, nombreRegion, regio.get(i).getPrecioEstandar(), regio.get(i).getPrecioEspecial()));
            }

        }
    }

    public void cargarRegiones() {
        modelo.setRowCount(0);
        totalRegiones = (ArrayList<Regiones>) ctrlRegiones.getTodasRegiones().clone();
        if (totalRegiones != null) {
            for (Regiones Region : totalRegiones) {
                Object datos[] = new Object[2];
                datos[0] = Region.getCodigo();
                datos[1] = Region.getNombre();
                modelo.addRow(datos);
            }
        }

    }

    public void mostrarDatos() {
        regio = ctrlRegiones.getTodasRegiones();
        Regiones regItem = (Regiones) boxRegion.getSelectedItem();
        int opcion = boxPrecio.getSelectedIndex() + 1;

        if (regio != null && regItem != null) {
            if (opcion == 1) {
                txtNuevoPrecio.setEditable(true);
                txtNuevoNombre.setEditable(false);
                txtNuevoPrecio.setBackground(new Color(40, 41, 52));
                txtNuevoNombre.setBackground(new Color(28, 28, 36));
                txtNuevoPrecio.setBorder(login.unselectedborder);
                txtNuevoNombre.setBorder(null);
                lblPrecioActual.setText(regItem.getPrecioEstandar() + "");
                activar = true;
            } else if (opcion == 2) {
                txtNuevoPrecio.setEditable(true);
                txtNuevoNombre.setEditable(false);
                txtNuevoPrecio.setBackground(new Color(40, 41, 52));
                txtNuevoNombre.setBackground(new Color(28, 28, 36));
                txtNuevoPrecio.setBorder(login.unselectedborder);
                txtNuevoNombre.setBorder(null);
                lblPrecioActual.setText(regItem.getPrecioEspecial() + "");
                activar = true;
            } else if (opcion == 3) {
                txtNuevoPrecio.setEditable(false);
                txtNuevoNombre.setEditable(true);
                txtNuevoPrecio.setBorder(null);
                txtNuevoNombre.setBackground(new Color(40, 41, 52));
                txtNuevoPrecio.setBackground(new Color(28, 28, 36));
                txtNuevoNombre.setBorder(login.unselectedborder);
                lblPrecioActual.setText(regItem.getNombre() + "");
                activar = false;
            }
        }

    }

    public void actualizarRegion() {
        Regiones regItem = (Regiones) boxRegion.getSelectedItem();
        int opciones = boxPrecio.getSelectedIndex() + 1;
        double nuevoPrecio = 0;

        if (regItem == null) {
            return;
        }

        if (opciones == 1 || opciones == 2) {
            if (!txtNuevoPrecio.getText().toString().equals("")) {
                Pattern vPrecio = Pattern.compile("^[0-9]+\\.?[0-9]*$");
                Matcher m = vPrecio.matcher(txtNuevoPrecio.getText());
                if (m.find()) {
                    nuevoPrecio = Double.parseDouble(txtNuevoPrecio.getText());

                } else {
                    JOptionPane.showMessageDialog(null, "El nuevo precio no es válido");
                    return;
                }

            } else {
                JOptionPane.showMessageDialog(null, "Escriba el nuevo Precio");
                return;
            }
        } else {
            if (!txtNuevoNombre.getText().toString().equals("")) {
                JOptionPane.showMessageDialog(null, "Escriba el nuevo Nombre");
                return;
            }
        }
        if (ctrlRegiones.cambiarPrecios(regItem.getCodigo(), opciones, nuevoPrecio, txtNuevoNombre.getText().toString())) {
            cargarBoxRegiones();
            cargarRegiones();
            txtNuevoNombre.setText("");
            txtNuevoPrecio.setText("");
            mostrarDatos();
            JOptionPane.showMessageDialog(null, "Se cambiaron los datos correctamente");
        } else {
            JOptionPane.showMessageDialog(null, "Error al cambiar los datos");
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        panelRound2 = new Elementos.PanelRound();
        jLabel5 = new javax.swing.JLabel();
        txtNuevoPrecio = new javax.swing.JTextField();
        buttonRound1 = new Elementos.ButtonRound();
        panelSuperior = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        boxRegion = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        boxPrecio = new javax.swing.JComboBox<>();
        lblAdvertencia = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtNuevoNombre = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        lblPrecioActual = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla2 = new Elementos.CutomTable.TableDark();

        setOpaque(false);

        panelRound1.setBackground(new java.awt.Color(28, 28, 36));
        panelRound1.setRoundBottomLeft(15);
        panelRound1.setRoundBottomRight(15);
        panelRound1.setRoundTopLeft(15);
        panelRound1.setRoundTopRight(15);

        jLabel1.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Modificar");

        jLabel2.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Eliminar");

        panelRound2.setBackground(new java.awt.Color(19, 19, 26));
        panelRound2.setRoundBottomLeft(15);
        panelRound2.setRoundBottomRight(15);
        panelRound2.setRoundTopLeft(15);
        panelRound2.setRoundTopRight(15);

        jLabel5.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Nuevo Precio");

        txtNuevoPrecio.setBackground(new java.awt.Color(40, 41, 52));
        txtNuevoPrecio.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        txtNuevoPrecio.setBorder(null);
        txtNuevoPrecio.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNuevoPrecioFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNuevoPrecioFocusLost(evt);
            }
        });
        txtNuevoPrecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNuevoPrecioKeyPressed(evt);
            }
        });

        buttonRound1.setBorder(null);
        buttonRound1.setForeground(new java.awt.Color(255, 255, 255));
        buttonRound1.setText("Actualizar");
        buttonRound1.setBorderColor(new java.awt.Color(123, 127, 239));
        buttonRound1.setColor(new java.awt.Color(123, 127, 239));
        buttonRound1.setColorClick(new java.awt.Color(121, 118, 236));
        buttonRound1.setColorOver(new java.awt.Color(121, 147, 251));
        buttonRound1.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        buttonRound1.setRadius(15);
        buttonRound1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRound1ActionPerformed(evt);
            }
        });

        panelSuperior.setBackground(new java.awt.Color(19, 19, 26));
        panelSuperior.setOpaque(false);

        jLabel3.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Región");

        boxRegion.setBackground(new java.awt.Color(34, 37, 47));
        boxRegion.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        boxRegion.setForeground(new java.awt.Color(255, 255, 255));
        boxRegion.setBorder(null);

        jLabel4.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Cambiar:");

        boxPrecio.setBackground(new java.awt.Color(34, 37, 47));
        boxPrecio.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        boxPrecio.setForeground(new java.awt.Color(255, 255, 255));
        boxPrecio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Precio Estandar", "Precio Especial", "Nombre" }));
        boxPrecio.setBorder(null);
        boxPrecio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                boxPrecioItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout panelSuperiorLayout = new javax.swing.GroupLayout(panelSuperior);
        panelSuperior.setLayout(panelSuperiorLayout);
        panelSuperiorLayout.setHorizontalGroup(
            panelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSuperiorLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boxRegion, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boxPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelSuperiorLayout.setVerticalGroup(
            panelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSuperiorLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(panelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(boxRegion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(boxPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9))
        );

        jLabel6.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Nuevo Nombre");

        txtNuevoNombre.setBackground(new java.awt.Color(40, 41, 52));
        txtNuevoNombre.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        txtNuevoNombre.setBorder(null);
        txtNuevoNombre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNuevoNombreFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNuevoNombreFocusLost(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Valor Actual:");

        lblPrecioActual.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        lblPrecioActual.setForeground(new java.awt.Color(255, 255, 255));
        lblPrecioActual.setText(" ");

        javax.swing.GroupLayout panelRound2Layout = new javax.swing.GroupLayout(panelRound2);
        panelRound2.setLayout(panelRound2Layout);
        panelRound2Layout.setHorizontalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound2Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblPrecioActual, javax.swing.GroupLayout.PREFERRED_SIZE, 529, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound2Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblAdvertencia, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNuevoPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(txtNuevoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(17, Short.MAX_VALUE))
            .addComponent(panelSuperior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonRound1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(265, 265, 265))
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addComponent(panelSuperior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lblPrecioActual, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNuevoPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(txtNuevoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(lblAdvertencia, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonRound1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        tabla2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null}
            },
            new String [] {
                "Codigo", "Region", "Eliminar"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla2.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        tabla2.setRowHeight(60);
        tabla2.setSelectionBackground(new java.awt.Color(98, 101, 128));
        jScrollPane2.setViewportView(tabla2);

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(panelRound2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelRound2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
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

    private void buttonRound1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRound1ActionPerformed
        // TODO add your handling code here:
        actualizarRegion();
    }//GEN-LAST:event_buttonRound1ActionPerformed

    private void boxPrecioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_boxPrecioItemStateChanged
        // TODO add your handling code here:
        mostrarDatos();
    }//GEN-LAST:event_boxPrecioItemStateChanged

    private void txtNuevoPrecioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNuevoPrecioKeyPressed
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            txtNuevoPrecio.setEditable(false);
            lblAdvertencia.setText("Solo numeros");
            txtNuevoPrecio.setBorder(login.errorBorde);
            lblAdvertencia.setForeground(login.error);
        } else {
            txtNuevoPrecio.setEditable(true);
            lblAdvertencia.setText("");
            txtNuevoPrecio.setBorder(login.selectedborder);
            lblAdvertencia.setForeground(Color.WHITE);
        }
    }//GEN-LAST:event_txtNuevoPrecioKeyPressed

    private void txtNuevoPrecioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNuevoPrecioFocusGained
        // TODO add your handling code here:
        if (activar) {
            txtNuevoPrecio.setBackground(new Color(50, 51, 64));
            txtNuevoPrecio.setBorder(login.selectedborder);
            txtNuevoNombre.setBorder(null);
        }

    }//GEN-LAST:event_txtNuevoPrecioFocusGained

    private void txtNuevoNombreFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNuevoNombreFocusGained
        // TODO add your handling code here:
        if (!activar) {
            txtNuevoNombre.setBackground(new Color(50, 51, 64));
            txtNuevoNombre.setBorder(login.selectedborder);
            txtNuevoPrecio.setBorder(null);
        }

    }//GEN-LAST:event_txtNuevoNombreFocusGained

    private void txtNuevoPrecioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNuevoPrecioFocusLost
        // TODO add your handling code here:
        if (activar) {
            txtNuevoPrecio.setBackground(new Color(40, 41, 52));
            txtNuevoPrecio.setBorder(login.unselectedborder);
            txtNuevoNombre.setBorder(null);
        }

    }//GEN-LAST:event_txtNuevoPrecioFocusLost

    private void txtNuevoNombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNuevoNombreFocusLost
        // TODO add your handling code here:
        if (!activar) {
            txtNuevoNombre.setBackground(new Color(40, 41, 52));
            txtNuevoNombre.setBorder(login.unselectedborder);
            txtNuevoPrecio.setBorder(null);
        }
    }//GEN-LAST:event_txtNuevoNombreFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> boxPrecio;
    private javax.swing.JComboBox<Object> boxRegion;
    private Elementos.ButtonRound buttonRound1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAdvertencia;
    private javax.swing.JLabel lblPrecioActual;
    private Elementos.PanelRound panelRound1;
    private Elementos.PanelRound panelRound2;
    private javax.swing.JPanel panelSuperior;
    private Elementos.CutomTable.TableDark tabla2;
    private javax.swing.JTextField txtNuevoNombre;
    private javax.swing.JTextField txtNuevoPrecio;
    // End of variables declaration//GEN-END:variables
}
