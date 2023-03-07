package Interfaz;

import Usuario.Usuario;
import Usuario.ctrlUsuarios;
import java.awt.Color;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.plaf.ColorUIResource;

/**
 *
 * @author mesoi
 */
public class login extends javax.swing.JFrame {
    
    public static Border unselectedborder;
    public static Border selectedborder;
    public static Border errorBorde;
    public static Border correctoBorde;
    public static Color selectedTxt;
    public static Color unselectedTxt;
    public static Color error;
    public static Color correcto;
    public static Usuario credenciales;
    public static int posicionU;
    /**
     * Creates new form login
     */
    public login() {
        UIManager.put("TextField.caretForeground", new ColorUIResource(255, 255, 255));
        UIManager.put("ComboBox.selectionBackground", new ColorUIResource(57, 60, 72));
        UIManager.put("ComboBox.selectionForeground", new ColorUIResource(255, 255, 255));
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        Border bottomBorder = BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(150, 157, 175));
        Border border = BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(150, 157, 175)),
                BorderFactory.createEmptyBorder(0, 0, 0, 0)
        );
        txtMail.setBorder(border);
        txtPassword.setBorder(border);
        txtPassword.setCaretColor(Color.WHITE);
        btnCrear.requestFocusInWindow();
        ((JComponent) txtPassword.getParent()).setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        
        selectedTxt = new Color(50, 51, 64);
        unselectedTxt = new Color(40, 41, 52);
        error = new Color(255, 75, 76);
        correcto = new Color(143, 235, 170);
        unselectedborder = BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(150, 157, 175));
        selectedborder = BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(210, 220, 222));
        errorBorde = BorderFactory.createMatteBorder(0, 0, 1, 0, error);
        correctoBorde = BorderFactory.createMatteBorder(0, 0, 1, 0, correcto);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panelRound1 = new Elementos.PanelRound();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtMail = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        jPanel7 = new javax.swing.JPanel();
        btnIngresar = new Elementos.ButtonRound();
        jPanel8 = new javax.swing.JPanel();
        btnCrear = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(19, 19, 26));

        panelRound1.setBackground(new java.awt.Color(28, 28, 36));
        panelRound1.setRoundBottomLeft(20);
        panelRound1.setRoundBottomRight(20);
        panelRound1.setRoundTopLeft(20);
        panelRound1.setRoundTopRight(20);

        jPanel2.setBackground(new java.awt.Color(39, 43, 46));
        jPanel2.setOpaque(false);
        jPanel2.setLayout(null);

        jPanel3.setBackground(new java.awt.Color(45, 48, 53));
        jPanel3.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("INICIAR SESIÓN");
        jPanel3.add(jLabel1);

        jPanel2.add(jPanel3);
        jPanel3.setBounds(0, 10, 520, 30);

        jPanel4.setBackground(new java.awt.Color(45, 48, 53));
        jPanel4.setOpaque(false);
        jPanel4.setLayout(new java.awt.GridLayout(3, 0));

        jPanel5.setBackground(new java.awt.Color(45, 48, 53));
        jPanel5.setOpaque(false);
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(210, 220, 222));
        jLabel2.setText("Correo Electrónico");
        jPanel5.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        txtMail.setBackground(new java.awt.Color(40, 41, 52));
        txtMail.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        txtMail.setForeground(new java.awt.Color(255, 255, 255));
        txtMail.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtMail.setBorder(null);
        txtMail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtMailFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtMailFocusLost(evt);
            }
        });
        txtMail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtMailMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtMailMouseExited(evt);
            }
        });
        txtMail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMailActionPerformed(evt);
            }
        });
        jPanel5.add(txtMail, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 480, 30));

        jPanel4.add(jPanel5);

        jPanel6.setBackground(new java.awt.Color(45, 48, 53));
        jPanel6.setOpaque(false);
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(210, 220, 222));
        jLabel3.setText("Contraseña");
        jPanel6.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        txtPassword.setBackground(new java.awt.Color(40, 41, 52));
        txtPassword.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        txtPassword.setForeground(new java.awt.Color(255, 255, 255));
        txtPassword.setBorder(null);
        txtPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPasswordFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPasswordFocusLost(evt);
            }
        });
        jPanel6.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 480, 30));

        jPanel4.add(jPanel6);

        jPanel7.setBackground(new java.awt.Color(45, 48, 53));
        jPanel7.setOpaque(false);
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnIngresar.setBorder(null);
        btnIngresar.setForeground(new java.awt.Color(66, 133, 254));
        btnIngresar.setText("Ingresar");
        btnIngresar.setBorderColor(new java.awt.Color(255, 255, 255));
        btnIngresar.setColorClick(new java.awt.Color(222, 226, 254));
        btnIngresar.setColorOver(new java.awt.Color(222, 226, 254));
        btnIngresar.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        btnIngresar.setRadius(15);
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });
        jPanel7.add(btnIngresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, 150, 30));

        jPanel4.add(jPanel7);

        jPanel2.add(jPanel4);
        jPanel4.setBounds(0, 50, 520, 200);

        jPanel8.setBackground(new java.awt.Color(45, 48, 53));
        jPanel8.setOpaque(false);

        btnCrear.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        btnCrear.setForeground(new java.awt.Color(255, 255, 255));
        btnCrear.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnCrear.setText("Crear una Cuenta");
        btnCrear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCrear.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCrear.setPreferredSize(new java.awt.Dimension(135, 25));
        btnCrear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCrearMouseClicked(evt);
            }
        });
        jPanel8.add(btnCrear);

        jPanel2.add(jPanel8);
        jPanel8.setBounds(0, 250, 520, 35);

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(86, Short.MAX_VALUE)
                .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(86, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(201, 201, 201))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(70, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCrearMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCrearMouseClicked
        // TODO add your handling code here:
        agregarUsuarios a = new agregarUsuarios();
        a.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCrearMouseClicked

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed
        // TODO add your handling code here:
        iniciarSesion();
    }//GEN-LAST:event_btnIngresarActionPerformed

    private void txtPasswordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPasswordFocusLost
        // TODO add your handling code here:
        Border bottomBorder = BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(150, 157, 175));
        txtPassword.setBackground(new Color(40, 41, 52));
        txtPassword.setBorder(bottomBorder);
    }//GEN-LAST:event_txtPasswordFocusLost

    private void txtPasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPasswordFocusGained
        // TODO add your handling code here:
        Border bottomBorder = BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(210, 220, 222));
        txtPassword.setBackground(new Color(50, 51, 64));
        txtPassword.setBorder(bottomBorder);
    }//GEN-LAST:event_txtPasswordFocusGained

    private void txtMailMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMailMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMailMouseExited

    private void txtMailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMailMouseClicked
        // TODO add your handling code here:
        //Border bottomBorder = BorderFactory.createMatteBorder(0, 0, 2, 0, Color.red);

        //txtMail.setBorder(bottomBorder);
    }//GEN-LAST:event_txtMailMouseClicked

    private void txtMailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMailFocusLost
        // TODO add your handling code here:
        Border bottomBorder = BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(150, 157, 175));
        txtMail.setBackground(new Color(40, 41, 52));
        txtMail.setBorder(bottomBorder);
    }//GEN-LAST:event_txtMailFocusLost

    private void txtMailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMailFocusGained
        // TODO add your handling code here:
        Border bottomBorder = BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(210, 220, 222));
        txtMail.setBackground(new Color(50, 51, 64));
        txtMail.setBorder(bottomBorder);
    }//GEN-LAST:event_txtMailFocusGained

    private void txtMailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMailActionPerformed

    public void iniciarSesion() {
        String correo = txtMail.getText();
        String contrasena = String.valueOf(txtPassword.getPassword());
        int opcion = ctrlUsuarios.iniciarSesion(correo, contrasena);

        if (opcion == 0) {

        } else if (opcion == 1) {
            //acceder como Administrador
            Admin a = new Admin();
            a.setVisible(true);
            this.dispose();
        } else {
            //Acceder como Cliente
            posicionU = ctrlUsuarios.getPosicionUsuario(correo);
            credenciales = ctrlUsuarios.getUsuarioID(correo);
            UsuarioCliente uc = new UsuarioCliente();
            Usuario usarioEnviar = ctrlUsuarios.getUsuarioID(correo);
            uc.setUsuario(usarioEnviar);
            uc.setVisible(true);
            this.dispose();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(login.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(login.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(login.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(login.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnCrear;
    private Elementos.ButtonRound btnIngresar;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private Elementos.PanelRound panelRound1;
    private javax.swing.JTextField txtMail;
    private javax.swing.JPasswordField txtPassword;
    // End of variables declaration//GEN-END:variables
}
