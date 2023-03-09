package Componentes;

import Elementos.CutomTable.TableActionCellEditorEliminar;
import Elementos.CutomTable.TableActionCellRenderEliminar;
import Elementos.CutomTable.TableActionEvent;
import Elementos.ScrollBarCustom;
import Interfaz.login;
import Usuario.DatosFacturacion;
import Usuario.Usuario;
import Usuario.ctrlUsuarios;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mesoi
 */
public class UsuarioDatosFacturacion extends javax.swing.JPanel {

    private Usuario user;
    private DefaultTableModel modelo;
    private DefaultTableModel modelo2;

    /**
     * Creates new form UsuarioDatosFacturacion
     */
    public UsuarioDatosFacturacion() {
        initComponents();
        this.setBounds(0, 0, 724, 520);
        jScrollPane1.setVerticalScrollBar(new ScrollBarCustom());
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(28);
        TableActionEvent event2 = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
            }

            @Override
            public void onDelete(int row) {
                if (tabla2.isEditing()) {
                    tabla2.getCellEditor().stopCellEditing();
                }

                DefaultTableModel model = (DefaultTableModel) tabla2.getModel();

                if (ctrlUsuarios.eliminarDatosF(login.credenciales.getCorreo(), login.posicionU, row)) {
                    model.removeRow(row);
                    cargarTablaNits();
                    listarNit();
                    cargarTablaNitsEliminar();
                    cargarBoxOpcion();

                    JOptionPane.showMessageDialog(null, "Dato de Facturación eliminada correctamente");
                }

            }

            @Override
            public void onView(int row) {
            }
        };
        modelo = (DefaultTableModel) tabla1.getModel();
        modelo2 = (DefaultTableModel) tabla2.getModel();
        tabla1.fixTable(jScrollPane2);
        jScrollPane2.getVerticalScrollBar().setUnitIncrement(30);
        tabla2.fixTable(jScrollPane3);
        jScrollPane3.getVerticalScrollBar().setUnitIncrement(30);
        tabla2.getColumnModel().getColumn(2).setCellRenderer(new TableActionCellRenderEliminar());
        tabla2.getColumnModel().getColumn(2).setCellEditor(new TableActionCellEditorEliminar(event2));
        cargarBoxOpcion();
        setBordes();
    }
    
     public void setBordes() {
        txtDireccion.setBorder(login.unselectedborder);
        txtNit.setBorder(login.unselectedborder);
        txtNuevoV.setBorder(login.unselectedborder);
        txtNombreCompleto.setBorder(login.unselectedborder);
    }

    public void test(Usuario user1) {
        this.user = user1;
    }

    public void ingresarDatosF() {
        String nombreDF = txtNombreCompleto.getText();
        String direccionDF = txtDireccion.getText();
        String nitDF = txtNit.getText();
        String correoEnviar = user.getCorreo();
        if (!(nombreDF.equals("") && direccionDF.equals("") && nitDF.equals("")) && user != null) {
            if (ctrlUsuarios.agregarDatosFacturacion(correoEnviar, nombreDF, direccionDF, nitDF, login.posicionU)) {
                cargarTablaNits();
                listarNit();
                cargarTablaNitsEliminar();
                cargarBoxOpcion();
                JOptionPane.showMessageDialog(null, "Dato de Facturación ingresado Correctamente");
                limpiarTxt();
            }

        }
    }

    public void limpiarTxt() {
        txtNombreCompleto.setText("");
        txtNit.setText("");
        txtDireccion.setText("");
    }

    public void limpiarBoxes(JComboBox eliminar) {
        eliminar.removeAllItems();
    }

    public void listarNit() {
        ArrayList<DatosFacturacion> tarjeta = ctrlUsuarios.getAllDFacturacion(login.credenciales.getCorreo());
        limpiarBoxes(boxNit);
        for (int i = 0; i < tarjeta.size(); i++) {
            if (tarjeta.get(i) != null) {
                String nit = tarjeta.get(i).getNit();
                String direccion = tarjeta.get(i).getDireccionF();
                String nombre = tarjeta.get(i).getNombreCompletoF();
                boxNit.addItem(new DatosFacturacion(nombre, direccion, nit));
            }

        }
    }

    public void cargarTablaNits() {
        modelo.setRowCount(0);
        ArrayList<DatosFacturacion> users = (ArrayList<DatosFacturacion>) ctrlUsuarios.getAllDFacturacion(login.credenciales.getCorreo());
        for (DatosFacturacion nit : users) {
            Object datos[] = new Object[3];
            datos[0] = nit.getNit();
            datos[1] = nit.getNombreCompletoF();
            datos[2] = nit.getDireccionF();
            modelo.addRow(datos);
        }
    }

    public void cargarTablaNitsEliminar() {
        modelo2.setRowCount(0);
        ArrayList<DatosFacturacion> users = (ArrayList<DatosFacturacion>) ctrlUsuarios.getAllDFacturacion(login.credenciales.getCorreo());
        for (DatosFacturacion tarj : users) {
            Object datos[] = new Object[2];
            datos[0] = tarj.getNit();
            datos[1] = tarj.getNombreCompletoF();
            modelo2.addRow(datos);
        }
    }

    public void actualizarNits() {
        int posicion = boxNit.getSelectedIndex();
        int posT = boxCambiar.getSelectedIndex() + 1;
        DatosFacturacion nitItem = (DatosFacturacion) boxNit.getSelectedItem();
        String nuevoV = txtNuevoV.getText();
        if (!nuevoV.equals("") && nitItem != null) {
            if (ctrlUsuarios.cambiarDatosF(login.posicionU, posT, posicion, nuevoV)) {
                listarNit();
                cargarTablaNitsEliminar();
                cargarBoxOpcion();
                cargarTablaNits();
                JOptionPane.showMessageDialog(null, "Datos Actualizados");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Tiene algún dato sin llenar");
        }
    }

    public void cargarBoxOpcion() {
        txtNuevoV.setEditable(true);
        txtNuevoV.setText("");
        int op = boxCambiar.getSelectedIndex() + 1;
        DatosFacturacion tItem = (DatosFacturacion) boxNit.getSelectedItem();
        if (tItem != null) {
            if (op == 1) {
                lblValor.setText(tItem.getNombreCompletoF());
            } else if (op == 2) {
                lblValor.setText(tItem.getDireccionF());
            } else {
                lblValor.setText(tItem.getNit());
            }
        } else {
            lblValor.setText("");
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
        jPanel2 = new javax.swing.JPanel();
        panelRound1 = new Elementos.PanelRound();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtNombreCompleto = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        txtNit = new javax.swing.JTextField();
        lblNit = new javax.swing.JLabel();
        btnAgregarDatos = new Elementos.ButtonRound();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla1 = new Elementos.CutomTable.TableDark();
        panelRound2 = new Elementos.PanelRound();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        boxNit = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        boxCambiar = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        lblValor = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtNuevoV = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        buttonRound1 = new Elementos.ButtonRound();
        panelRound3 = new Elementos.PanelRound();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabla2 = new Elementos.CutomTable.TableDark();

        setBackground(new java.awt.Color(19, 19, 26));

        jPanel2.setBackground(new java.awt.Color(19, 19, 26));

        panelRound1.setBackground(new java.awt.Color(28, 28, 36));
        panelRound1.setRoundBottomLeft(15);
        panelRound1.setRoundBottomRight(15);
        panelRound1.setRoundTopLeft(15);
        panelRound1.setRoundTopRight(15);

        jLabel1.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText(" IngresarDatos de Facturación");

        jPanel1.setOpaque(false);

        jLabel2.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nombre Completo");

        txtNombreCompleto.setBackground(new java.awt.Color(40, 41, 52));
        txtNombreCompleto.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        txtNombreCompleto.setForeground(new java.awt.Color(255, 255, 255));
        txtNombreCompleto.setBorder(null);
        txtNombreCompleto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNombreCompletoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNombreCompletoFocusLost(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Dirección");

        jLabel4.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nit");

        txtDireccion.setBackground(new java.awt.Color(40, 41, 52));
        txtDireccion.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        txtDireccion.setForeground(new java.awt.Color(255, 255, 255));
        txtDireccion.setBorder(null);
        txtDireccion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDireccionFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDireccionFocusLost(evt);
            }
        });

        txtNit.setBackground(new java.awt.Color(40, 41, 52));
        txtNit.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        txtNit.setForeground(new java.awt.Color(255, 255, 255));
        txtNit.setBorder(null);
        txtNit.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNitFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNitFocusLost(evt);
            }
        });
        txtNit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNitKeyPressed(evt);
            }
        });

        lblNit.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        lblNit.setForeground(new java.awt.Color(255, 255, 255));

        btnAgregarDatos.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarDatos.setText("Registrar Datos");
        btnAgregarDatos.setBorderColor(new java.awt.Color(123, 127, 239));
        btnAgregarDatos.setColor(new java.awt.Color(123, 127, 239));
        btnAgregarDatos.setColorClick(new java.awt.Color(121, 118, 236));
        btnAgregarDatos.setColorOver(new java.awt.Color(121, 147, 251));
        btnAgregarDatos.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        btnAgregarDatos.setRadius(15);
        btnAgregarDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarDatosActionPerformed(evt);
            }
        });

        tabla1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nit", "Nombre", "Dirección"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla1.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        jScrollPane2.setViewportView(tabla1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombreCompleto, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3)
                                        .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(27, 27, 27)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtNit, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel4)
                                        .addComponent(lblNit, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(234, 234, 234)
                        .addComponent(btnAgregarDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNombreCompleto, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNit, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNit, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAgregarDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel1))
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelRound2.setBackground(new java.awt.Color(28, 28, 36));
        panelRound2.setRoundBottomLeft(15);
        panelRound2.setRoundBottomRight(15);
        panelRound2.setRoundTopLeft(15);
        panelRound2.setRoundTopRight(15);

        jLabel5.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Modificar Datos de Facturación");

        jLabel6.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Nit:");

        boxNit.setBackground(new java.awt.Color(40, 41, 52));
        boxNit.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        boxNit.setForeground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Cambiar:");

        boxCambiar.setBackground(new java.awt.Color(40, 41, 52));
        boxCambiar.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        boxCambiar.setForeground(new java.awt.Color(255, 255, 255));
        boxCambiar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nombre", "Dirección", "Nit" }));
        boxCambiar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                boxCambiarItemStateChanged(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Nuevo Valor");

        lblValor.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        lblValor.setForeground(new java.awt.Color(255, 255, 255));
        lblValor.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jLabel10.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Valor Actual: ");

        txtNuevoV.setBackground(new java.awt.Color(40, 41, 52));
        txtNuevoV.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        txtNuevoV.setForeground(new java.awt.Color(255, 255, 255));
        txtNuevoV.setBorder(null);
        txtNuevoV.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNuevoVFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNuevoVFocusLost(evt);
            }
        });
        txtNuevoV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNuevoVKeyPressed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));

        buttonRound1.setForeground(new java.awt.Color(255, 255, 255));
        buttonRound1.setText("Actualizar");
        buttonRound1.setBorderColor(new java.awt.Color(123, 127, 239));
        buttonRound1.setColor(new java.awt.Color(123, 127, 239));
        buttonRound1.setColorClick(new java.awt.Color(121, 118, 236));
        buttonRound1.setColorOver(new java.awt.Color(121, 147, 251));
        buttonRound1.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        buttonRound1.setRadius(15);
        buttonRound1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRound1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound2Layout = new javax.swing.GroupLayout(panelRound2);
        panelRound2.setLayout(panelRound2Layout);
        panelRound2Layout.setHorizontalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound2Layout.createSequentialGroup()
                        .addGap(181, 181, 181)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblValor, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelRound2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5)
                            .addGroup(panelRound2Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(boxNit, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(boxCambiar, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel8)
                            .addComponent(txtNuevoV)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 588, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelRound2Layout.createSequentialGroup()
                        .addGap(272, 272, 272)
                        .addComponent(buttonRound1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelRound2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(boxNit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(boxCambiar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblValor, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNuevoV, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonRound1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        panelRound3.setBackground(new java.awt.Color(28, 28, 36));
        panelRound3.setRoundBottomLeft(15);
        panelRound3.setRoundBottomRight(15);
        panelRound3.setRoundTopLeft(15);
        panelRound3.setRoundTopRight(15);

        jLabel12.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Eliminar Datos de Facturación");

        tabla2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nit", "Nombre Completo", "Eliminar"
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
        tabla2.setRowHeight(65);
        jScrollPane3.setViewportView(tabla2);

        javax.swing.GroupLayout panelRound3Layout = new javax.swing.GroupLayout(panelRound3);
        panelRound3.setLayout(panelRound3Layout);
        panelRound3Layout.setHorizontalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRound3Layout.setVerticalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelRound2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelRound3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelRound2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(panelRound3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jScrollPane1.setViewportView(jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarDatosActionPerformed
        // TODO add your handling code here:
        ingresarDatosF();
    }//GEN-LAST:event_btnAgregarDatosActionPerformed

    private void txtNitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNitKeyPressed
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            txtNit.setEditable(false);
            lblNit.setText("Solo numeros");
        } else {
            txtNit.setEditable(true);
            lblNit.setText("");
        }
    }//GEN-LAST:event_txtNitKeyPressed

    private void txtNuevoVKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNuevoVKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNuevoVKeyPressed

    private void boxCambiarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_boxCambiarItemStateChanged
        // TODO add your handling code here:
        cargarBoxOpcion();
    }//GEN-LAST:event_boxCambiarItemStateChanged

    private void buttonRound1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRound1ActionPerformed
        // TODO add your handling code here:
        actualizarNits();
    }//GEN-LAST:event_buttonRound1ActionPerformed

    private void txtNombreCompletoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNombreCompletoFocusGained
        // TODO add your handling code here:
         UsuarioTarjeta.selected(txtNombreCompleto,1);
    }//GEN-LAST:event_txtNombreCompletoFocusGained

    private void txtDireccionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDireccionFocusGained
        // TODO add your handling code here:
        UsuarioTarjeta.selected(txtDireccion,1);
    }//GEN-LAST:event_txtDireccionFocusGained

    private void txtNitFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNitFocusGained
        // TODO add your handling code here:
        UsuarioTarjeta.selected(txtNit,1);
    }//GEN-LAST:event_txtNitFocusGained

    private void txtNuevoVFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNuevoVFocusGained
        // TODO add your handling code here:
        UsuarioTarjeta.selected(txtNuevoV,1);
    }//GEN-LAST:event_txtNuevoVFocusGained

    private void txtNombreCompletoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNombreCompletoFocusLost
        // TODO add your handling code here:
        UsuarioTarjeta.selected(txtNombreCompleto,0);
    }//GEN-LAST:event_txtNombreCompletoFocusLost

    private void txtDireccionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDireccionFocusLost
        // TODO add your handling code here:
         UsuarioTarjeta.selected(txtDireccion,0);
    }//GEN-LAST:event_txtDireccionFocusLost

    private void txtNitFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNitFocusLost
        // TODO add your handling code here:
          UsuarioTarjeta.selected(txtNit,0);
    }//GEN-LAST:event_txtNitFocusLost

    private void txtNuevoVFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNuevoVFocusLost
        // TODO add your handling code here:
        UsuarioTarjeta.selected(txtNuevoV,0);
    }//GEN-LAST:event_txtNuevoVFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<Object> boxCambiar;
    private javax.swing.JComboBox<Object> boxNit;
    private Elementos.ButtonRound btnAgregarDatos;
    private Elementos.ButtonRound buttonRound1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblNit;
    private javax.swing.JLabel lblValor;
    private Elementos.PanelRound panelRound1;
    private Elementos.PanelRound panelRound2;
    private Elementos.PanelRound panelRound3;
    private Elementos.CutomTable.TableDark tabla1;
    private Elementos.CutomTable.TableDark tabla2;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtNit;
    private javax.swing.JTextField txtNombreCompleto;
    private javax.swing.JTextField txtNuevoV;
    // End of variables declaration//GEN-END:variables
}
