package Interfaz;

import Administrador.Kioscos;
import Administrador.ctrlKioscos;
import Componentes.*;
import Usuario.Usuario;
import Usuario.ctrlUsuarios;
import java.awt.Color;
import java.awt.Image;
import java.awt.MediaTracker;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author mesoi
 */
public class UsuarioCliente extends javax.swing.JFrame {

    public UsuarioTarjeta usert;
    public UsuarioDatosFacturacion userDatos;
    public UsuarioCotizacionCompra userCotiz;
    private Usuario usuarioC;
    public UsuarioVerEnvios usuarioVer;
    private UsuarioCuenta usuarioCliente;
    public boolean activado;
    Border unselectedborder;
    Border selectedborder;

    /**
     * Creates new form UsuarioCliente
     */
    public UsuarioCliente() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        unselectedborder = BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(150, 157, 175));
        selectedborder = BorderFactory.createMatteBorder(0, 3, 0, 0, Color.WHITE);
        usert = new UsuarioTarjeta();
        userDatos = new UsuarioDatosFacturacion();
        userCotiz = new UsuarioCotizacionCompra();
        usuarioVer = new UsuarioVerEnvios();
        usuarioCliente = new UsuarioCuenta(this);
        menuContenido.add(usert);
        menuContenido.add(userDatos);
        menuContenido.add(userCotiz);
        menuContenido.add(usuarioVer);
        menuContenido.add(usuarioCliente);
        menuClick(usert);
        bordeMenu(panelTarjetas, lblTarjeta);
        verificar();
        cargarImg();
    }

    public void setUsuario(Usuario u) {
        this.usuarioC = u;
        lblNombreUsuario.setText(usuarioC.getNombre());
        lblTipoC.setText(usuarioC.getRol());
        usert.test(usuarioC);
        userDatos.test(usuarioC);
        userCotiz.test(usuarioC);
        usuarioVer.test(usuarioC);
    }
    
    public static void actualizarMenu(){
        Usuario us = ctrlUsuarios.getUsuarioIndice(login.posicionU);
        lblNombreUsuario.setText(us.getNombre());
        lblTipoC.setText(us.getRol());
    }

    public void hoverMenu(JPanel activar, int estado) {
        if (estado == 1) {
            activar.setBackground(new Color(19, 19, 26));
        } else {
            activar.setBackground(new Color(28, 28, 36));
        }
    }

    public void bordeMenu(JPanel panel, JLabel texto) {
        panelTarjetas.setBorder(unselectedborder);
        panelDatosF.setBorder(unselectedborder);
        panelEnvios.setBorder(unselectedborder);
        panelCotizacion.setBorder(unselectedborder);
        panelCuenta.setBorder(unselectedborder);
        lblCerrar.setForeground(Color.WHITE);
        lblCotizacion.setForeground(Color.WHITE);
        lblDatos.setForeground(Color.WHITE);
        lblTarjeta.setForeground(Color.WHITE);
        lblVer.setForeground(Color.WHITE);
        lblVer1.setForeground(Color.WHITE);

        panel.setBorder(selectedborder);
        texto.setForeground(new Color(205, 233, 255));
        //panel.setForeground(new Color(205, 233, 255));
    }

    public void cerrarSesion() {
        login l = new login();
        l.setVisible(true);
        this.dispose();
    }

    public void verificar() {
        Usuario actualizado = ctrlUsuarios.getUsuarioIndice(login.posicionU);
        boolean activar = false;
        String[] rolP = actualizado.getRol().split(",");
        if (rolP[0].equals("Usuario Individual")) {
        } else if (rolP[0].equals("Usuario Empresarial")) {
        } else {
            ArrayList<Kioscos> kD = ctrlKioscos.getAllKioscos();
            if (kD.size() != 0) {
                for (Kioscos kiosco : kD) {
                    if (rolP[1].equals(kiosco.getCodigoKioco())) {
                        activar = true;
                        break;
                    }
                }
            }

            if (!activar) {
                String rolCompleto="Usuario Individual";
                if (ctrlUsuarios.cambiarRol(rolCompleto, login.posicionU)) {
                    JOptionPane.showMessageDialog(null, "Se Kiosco no existe, su rol actual es: Usuario individual");
                    JOptionPane.showMessageDialog(null, "Si lo desea cambiar dirigase a Cuenta");
                }
            }

        }
    }
    
    public static void cargarImg(){
        Usuario userA = ctrlUsuarios.getUsuarioIndice(login.posicionU);
        ImageIcon originalImageIcon = new ImageIcon(userA.getFotografia());
        if (originalImageIcon.getImageLoadStatus() != MediaTracker.COMPLETE) {
            ///htmls/factura.html
            originalImageIcon = new ImageIcon("src\\img\\usuario.png");
        }
        // Obtener el tamaño original de la imagen
        int originalImageWidth = originalImageIcon.getIconWidth();
        int originalImageHeight = originalImageIcon.getIconHeight();

        // Obtener el tamaño del JLabel
        int labelWidth = jLabel1.getWidth();
        int labelHeight = jLabel1.getHeight();

        // Calcular la relación de aspecto de la imagen original y del JLabel
        double originalImageAspectRatio = (double) originalImageWidth / originalImageHeight;
        double labelAspectRatio = (double) labelWidth / labelHeight;

        // Escalar la imagen si es necesario
        ImageIcon scaledImageIcon;
        if (originalImageAspectRatio > labelAspectRatio) {
            // La imagen es más ancha que el JLabel
            int scaledImageWidth = labelWidth;
            int scaledImageHeight = (int) (scaledImageWidth / originalImageAspectRatio);
            Image scaledImage = originalImageIcon.getImage().getScaledInstance(scaledImageWidth, scaledImageHeight, Image.SCALE_SMOOTH);
            scaledImageIcon = new ImageIcon(scaledImage);
        } else {
            // La imagen es más alta que el JLabel
            int scaledImageHeight = labelHeight;
            int scaledImageWidth = (int) (scaledImageHeight * originalImageAspectRatio);
            Image scaledImage = originalImageIcon.getImage().getScaledInstance(scaledImageWidth, scaledImageHeight, Image.SCALE_SMOOTH);
            scaledImageIcon = new ImageIcon(scaledImage);
        }
        // Establecer el ImageIcon escalado en el JLabel
        jLabel1.setIcon(scaledImageIcon);
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lblNombreUsuario = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        lblTipoC = new javax.swing.JLabel();
        panelTarjetas = new javax.swing.JPanel();
        lblTarjeta = new javax.swing.JLabel();
        panelDatosF = new javax.swing.JPanel();
        lblDatos = new javax.swing.JLabel();
        panelCotizacion = new javax.swing.JPanel();
        lblCotizacion = new javax.swing.JLabel();
        panelEnvios = new javax.swing.JPanel();
        lblVer = new javax.swing.JLabel();
        btnCerrarSesion = new javax.swing.JPanel();
        lblCerrar = new javax.swing.JLabel();
        panelCuenta = new javax.swing.JPanel();
        lblVer1 = new javax.swing.JLabel();
        menuContenido = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(19, 19, 26));

        jPanel1.setBackground(new java.awt.Color(19, 19, 26));

        jPanel2.setBackground(new java.awt.Color(28, 28, 36));

        jPanel4.setOpaque(false);
        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0));

        lblNombreUsuario.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        lblNombreUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lblNombreUsuario.setText(" ");
        jPanel4.add(lblNombreUsuario);

        jPanel5.setOpaque(false);
        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0));

        lblTipoC.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        lblTipoC.setForeground(new java.awt.Color(255, 255, 255));
        lblTipoC.setText("Cliente");
        jPanel5.add(lblTipoC);

        panelTarjetas.setBackground(new java.awt.Color(28, 28, 36));
        panelTarjetas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelTarjetasMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelTarjetasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelTarjetasMouseExited(evt);
            }
        });
        panelTarjetas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTarjeta.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        lblTarjeta.setForeground(new java.awt.Color(255, 255, 255));
        lblTarjeta.setText("Registrar Tarjeta");
        panelTarjetas.add(lblTarjeta, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        panelDatosF.setBackground(new java.awt.Color(28, 28, 36));
        panelDatosF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelDatosFMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelDatosFMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelDatosFMouseExited(evt);
            }
        });
        panelDatosF.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblDatos.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        lblDatos.setForeground(new java.awt.Color(255, 255, 255));
        lblDatos.setText("Registrar datos de Facturación");
        panelDatosF.add(lblDatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        panelCotizacion.setBackground(new java.awt.Color(28, 28, 36));
        panelCotizacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelCotizacionMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelCotizacionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelCotizacionMouseExited(evt);
            }
        });
        panelCotizacion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCotizacion.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        lblCotizacion.setForeground(new java.awt.Color(255, 255, 255));
        lblCotizacion.setText("Cotización y Compra");
        panelCotizacion.add(lblCotizacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        panelEnvios.setBackground(new java.awt.Color(28, 28, 36));
        panelEnvios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelEnviosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelEnviosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelEnviosMouseExited(evt);
            }
        });
        panelEnvios.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblVer.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        lblVer.setForeground(new java.awt.Color(255, 255, 255));
        lblVer.setText("Ver Envios solicitados");
        panelEnvios.add(lblVer, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        btnCerrarSesion.setBackground(new java.awt.Color(28, 28, 36));
        btnCerrarSesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCerrarSesionMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCerrarSesionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCerrarSesionMouseExited(evt);
            }
        });
        btnCerrarSesion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCerrar.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        lblCerrar.setForeground(new java.awt.Color(255, 255, 255));
        lblCerrar.setText("Cerrar Sesión");
        btnCerrarSesion.add(lblCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        panelCuenta.setBackground(new java.awt.Color(28, 28, 36));
        panelCuenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelCuentaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelCuentaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelCuentaMouseExited(evt);
            }
        });
        panelCuenta.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblVer1.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        lblVer1.setForeground(new java.awt.Color(255, 255, 255));
        lblVer1.setText("Cuenta");
        panelCuenta.add(lblVer1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelDatosF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
            .addComponent(panelCotizacion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelEnvios, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnCerrarSesion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65))
            .addComponent(panelTarjetas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelCuenta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelTarjetas, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelDatosF, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelCotizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelEnvios, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 118, Short.MAX_VALUE)
                .addComponent(btnCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        menuContenido.setBackground(new java.awt.Color(28, 28, 36));

        javax.swing.GroupLayout menuContenidoLayout = new javax.swing.GroupLayout(menuContenido);
        menuContenido.setLayout(menuContenidoLayout);
        menuContenidoLayout.setHorizontalGroup(
            menuContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 728, Short.MAX_VALUE)
        );
        menuContenidoLayout.setVerticalGroup(
            menuContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 520, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(menuContenido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 14, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(menuContenido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void panelTarjetasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelTarjetasMouseClicked
        // TODO add your handling code here:
        menuClick(usert);
        bordeMenu(panelTarjetas, lblTarjeta);
    }//GEN-LAST:event_panelTarjetasMouseClicked

    private void panelDatosFMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelDatosFMouseClicked
        // TODO add your handling code here:
        userDatos.cargarTablaNits();
        userDatos.cargarTablaNitsEliminar();
        menuClick(userDatos);
        bordeMenu(panelDatosF, lblDatos);
    }//GEN-LAST:event_panelDatosFMouseClicked

    private void panelCotizacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelCotizacionMouseClicked
        // TODO add your handling code here:
        bordeMenu(panelCotizacion, lblCotizacion);
        menuClick(userCotiz);
        userCotiz.cargarDatosFacturacion();
        userCotiz.listarTarjetas();
        userCotiz.cargarDatosF();
    }//GEN-LAST:event_panelCotizacionMouseClicked

    private void btnCerrarSesionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarSesionMouseClicked
        // TODO add your handling code here:
        cerrarSesion();
    }//GEN-LAST:event_btnCerrarSesionMouseClicked

    private void panelEnviosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelEnviosMouseClicked
        // TODO add your handling code here:
        menuClick(usuarioVer);
        bordeMenu(panelEnvios, lblVer);
        usuarioVer.cargarDatos();
    }//GEN-LAST:event_panelEnviosMouseClicked

    private void panelTarjetasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelTarjetasMouseEntered
        // TODO add your handling code here:
        hoverMenu(panelTarjetas, 1);
    }//GEN-LAST:event_panelTarjetasMouseEntered

    private void panelDatosFMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelDatosFMouseEntered
        // TODO add your handling code here:
        hoverMenu(panelDatosF, 1);
    }//GEN-LAST:event_panelDatosFMouseEntered

    private void panelCotizacionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelCotizacionMouseEntered
        // TODO add your handling code here:
        hoverMenu(panelCotizacion, 1);
    }//GEN-LAST:event_panelCotizacionMouseEntered

    private void panelEnviosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelEnviosMouseEntered
        // TODO add your handling code here:
        hoverMenu(panelEnvios, 1);
    }//GEN-LAST:event_panelEnviosMouseEntered

    private void btnCerrarSesionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarSesionMouseEntered
        // TODO add your handling code here:
        hoverMenu(btnCerrarSesion, 1);
    }//GEN-LAST:event_btnCerrarSesionMouseEntered

    private void panelTarjetasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelTarjetasMouseExited
        // TODO add your handling code here:
        hoverMenu(panelTarjetas, 0);
    }//GEN-LAST:event_panelTarjetasMouseExited

    private void panelDatosFMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelDatosFMouseExited
        // TODO add your handling code here:
        hoverMenu(panelDatosF, 0);
    }//GEN-LAST:event_panelDatosFMouseExited

    private void panelCotizacionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelCotizacionMouseExited
        // TODO add your handling code here:
        hoverMenu(panelCotizacion, 0);
    }//GEN-LAST:event_panelCotizacionMouseExited

    private void panelEnviosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelEnviosMouseExited
        // TODO add your handling code here:
        hoverMenu(panelEnvios, 0);
    }//GEN-LAST:event_panelEnviosMouseExited

    private void btnCerrarSesionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarSesionMouseExited
        // TODO add your handling code here:
        hoverMenu(btnCerrarSesion, 0);
    }//GEN-LAST:event_btnCerrarSesionMouseExited

    private void panelCuentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelCuentaMouseClicked
        // TODO add your handling code here:
        usuarioCliente.cargarDatosU();
        bordeMenu(panelCuenta, lblVer1);
        menuClick(usuarioCliente);
    }//GEN-LAST:event_panelCuentaMouseClicked

    private void panelCuentaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelCuentaMouseEntered
        // TODO add your handling code here:
        hoverMenu(panelCuenta, 1);
    }//GEN-LAST:event_panelCuentaMouseEntered

    private void panelCuentaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelCuentaMouseExited
        // TODO add your handling code here:
        hoverMenu(panelCuenta, 0);
    }//GEN-LAST:event_panelCuentaMouseExited

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
            java.util.logging.Logger.getLogger(UsuarioCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UsuarioCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UsuarioCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UsuarioCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UsuarioCliente().setVisible(true);
            }
        });
    }

    public void menuClick(JPanel panel) {
        usert.setVisible(false);
        userDatos.setVisible(false);
        userCotiz.setVisible(false);
        usuarioVer.setVisible(false);
        usuarioCliente.setVisible(false);
        panel.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btnCerrarSesion;
    private static javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel lblCerrar;
    private javax.swing.JLabel lblCotizacion;
    private javax.swing.JLabel lblDatos;
    private static javax.swing.JLabel lblNombreUsuario;
    private javax.swing.JLabel lblTarjeta;
    private static javax.swing.JLabel lblTipoC;
    private javax.swing.JLabel lblVer;
    private javax.swing.JLabel lblVer1;
    private javax.swing.JPanel menuContenido;
    private javax.swing.JPanel panelCotizacion;
    private javax.swing.JPanel panelCuenta;
    private javax.swing.JPanel panelDatosF;
    private javax.swing.JPanel panelEnvios;
    private javax.swing.JPanel panelTarjetas;
    // End of variables declaration//GEN-END:variables
}
