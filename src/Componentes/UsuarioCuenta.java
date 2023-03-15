package Componentes;

import Administrador.Kioscos;
import Administrador.ctrlKioscos;
import Elementos.ScrollBarCustom;
import Interfaz.UsuarioCliente;
import Interfaz.login;
import Usuario.Usuario;
import Usuario.ctrlUsuarios;
import java.awt.Color;
import java.awt.Image;
import java.awt.MediaTracker;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author mesoi
 */
public class UsuarioCuenta extends javax.swing.JPanel {

    private static String[] nombrePaises = {"Alemania", "Argentina", "Belice", "Brasil", "Canadá", "China",
        "El Salvador", "España", "Guatemala", "Honduras", "Japón", "México", "Portugal", "Uruguay"};
    private UsuarioCliente cliente;
    private boolean validarFecha = false;

    /**
     * Creates new form UsuarioCuenta
     */
    public UsuarioCuenta() {
        initComponents();
        this.setBounds(0, 0, 724, 520);
        jScrollPane1.setVerticalScrollBar(new ScrollBarCustom());
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(28);

    }

    public UsuarioCuenta(UsuarioCliente uC) {
        initComponents();
        this.setBounds(0, 0, 724, 520);
        jScrollPane1.setVerticalScrollBar(new ScrollBarCustom());
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(28);
        this.cliente = uC;
        txtContra.setCaretColor(Color.WHITE);
        txtAnterior.setCaretColor(Color.WHITE);
    }

    public void cargarDatosU() {
        agregarBoxes();
        Usuario actualizado = ctrlUsuarios.getUsuarioIndice(login.posicionU);
        txtCorreo.setText(actualizado.getCorreo());
        txtApellido.setText(actualizado.getApellido());
        txtFecha.setText(actualizado.getFechaNacimiento());
        txtNombre.setText(actualizado.getNombre());
        txtDpi.setText(actualizado.getDpi());
        txtTelefono.setText(actualizado.getTelefono() + "");
        txtAlias.setText(actualizado.getAlias());
        cargarImg(actualizado);
        int n = 0;
        for (String nombrePais : nombrePaises) {
            if (actualizado.getNacionalidad().equals(nombrePais)) {
                boxNacionalidad.setSelectedIndex(n);
                break;
            }
            n++;
        }

        if (actualizado.getGenero().equals("Masculino")) {
            boxGenero.setSelectedIndex(0);
        } else {
            boxGenero.setSelectedIndex(1);
        }
        String[] rolP = actualizado.getRol().split(",");
        if (rolP[0].equals("Usuario Individual")) {
            boxRol.setSelectedIndex(0);
        } else if (rolP[0].equals("Usuario Empresarial")) {
            boxRol.setSelectedIndex(1);
        } else {
            ArrayList<Kioscos> kD = ctrlKioscos.getAllKioscos();
            if (kD.size() != 0) {
                boxRol.setSelectedIndex(2);
                int i = 0;
                for (Kioscos kiosco : kD) {
                    if (rolP[1].equals(kiosco.getCodigoKioco())) {
                        boxKiosco.setSelectedIndex(i);
                        break;
                    }
                    i++;
                }
            }

        }

    }

    public void cargarImg(Usuario userA) {
        ImageIcon originalImageIcon = new ImageIcon(userA.getFotografia());
        if (originalImageIcon.getImageLoadStatus() != MediaTracker.COMPLETE || userA.getFotografia().equals("")) {
            ///htmls/factura.html
            originalImageIcon = new ImageIcon("src\\img\\usuario.png");
        }
        // Obtener el tamaño original de la imagen
        int originalImageWidth = originalImageIcon.getIconWidth();
        int originalImageHeight = originalImageIcon.getIconHeight();

        // Obtener el tamaño del JLabel
        int labelWidth = lblimagen.getWidth();
        int labelHeight = lblimagen.getHeight();

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
        lblimagen.setIcon(scaledImageIcon);
    }

    public void agregarBoxes() {
        boxGenero.removeAllItems();
        boxRol.removeAllItems();
        boxKiosco.removeAllItems();
        boxNacionalidad.removeAllItems();

        boxGenero.addItem("Masculino");
        boxGenero.addItem("Femenino");
        for (String nombres : nombrePaises) {
            boxNacionalidad.addItem(nombres);
        }

        boxRol.addItem("Usuario Individual");
        boxRol.addItem("Usuario Empresarial");
        boxRol.addItem("Kiosko");
        ArrayList<Kioscos> kD = ctrlKioscos.getAllKioscos();
        for (Kioscos kiosco : kD) {
            boxKiosco.addItem(new Kioscos(kiosco.getIdRegion(), kiosco.getCodigo(), kiosco.getNombre(), kiosco.getPrecioEstandar(), kiosco.getPrecioEspecial(), kiosco.getCodigoKioco(), kiosco.getNombreKiosco()));
        }

    }

    public void actualizarIMG() {
        String ruta = "";
        JFileChooser archivos = new JFileChooser();
        try {
            FileNameExtensionFilter filtrado = new FileNameExtensionFilter("JPG & PNG", "jpg", "png");
            archivos.setFileFilter(filtrado);
        } catch (Exception e) {
            System.out.println("--" + e);
        }

        int respuesta = archivos.showOpenDialog(this);
        if (respuesta == archivos.APPROVE_OPTION) {

            /// Cargar la imagen original
            try {
                ruta = archivos.getSelectedFile().getPath();
                ImageIcon originalImageIcon = new ImageIcon(ruta);

                // Obtener el tamaño original de la imagen
                int originalImageWidth = originalImageIcon.getIconWidth();
                int originalImageHeight = originalImageIcon.getIconHeight();

                // Obtener el tamaño del JLabel
                int labelWidth = lblimagen.getWidth();
                int labelHeight = lblimagen.getHeight();

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

                if (ctrlUsuarios.cambiarIMG(login.posicionU, ruta)) {
                    lblimagen.setIcon(scaledImageIcon);
                    UsuarioCliente.cargarImg();
                    JOptionPane.showMessageDialog(null, "Fotografía cambiada con éxito");
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public void reset1() {
        lblimagen.revalidate();
        lblimagen.repaint();
        lblop1.setForeground(login.error);
        lblop2.setForeground(login.error);
        lblop3.setForeground(login.error);
        lblop4.setForeground(login.error);
        lblCorreo.setText("");
    }

    public void actualizarGeneral() {
        Usuario actualizado = ctrlUsuarios.getUsuarioIndice(login.posicionU);
        String correo = txtCorreo.getText();
        String ncontra = String.valueOf(txtContra.getPassword());
        String vcontra = String.valueOf(txtAnterior.getPassword());
        
        if(!correo.matches("^[^@]+@[^@]+\\.[a-zA-Z]{2,}$")){
            JOptionPane.showMessageDialog(null, "El correo ingresado no es válido");
            return;
        }
        
        if (!(ncontra.equals("") && vcontra.equals("") || correo.equals("")) && (!ncontra.trim().isEmpty() && !vcontra.trim().isEmpty())) {
            if (vcontra.equals(actualizado.getContrasena())) {
                int confirm = JOptionPane.showConfirmDialog(null, "¿Desea actualizar sus datos Generales?", "Confiramción", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (confirm == JOptionPane.YES_OPTION) {
                    if (ctrlUsuarios.cambiarDGeneral(login.posicionU, correo, ncontra, 2)) {
                        JOptionPane.showMessageDialog(null, "Datos Actualizados correctamente");
                        JOptionPane.showMessageDialog(null, "Se cerrara la sesión por seguridad");
                        login l = new login();
                        l.setVisible(true);
                        cliente.dispose();
                    }
                }

            } else {
                JOptionPane.showMessageDialog(null, "La contrseña anterior no es la registrada");
            }
        } else {
            if (!correo.equals("")) {
                int confirm = JOptionPane.showConfirmDialog(null, "¿Desea actualizar sus datos Generales?", "Confiramción", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (confirm == JOptionPane.YES_OPTION) {
                    if (ctrlUsuarios.cambiarDGeneral(login.posicionU, correo, ncontra, 1)) {
                        JOptionPane.showMessageDialog(null, "Datos Actualizados correctamente");
                        JOptionPane.showMessageDialog(null, "Se cerrara la sesión por seguridad");
                        login l = new login();
                        l.setVisible(true);
                        cliente.dispose();
                    }
                }
            }
        }
    }

    public void actualizarPerfil() {
        Usuario actualizado = ctrlUsuarios.getUsuarioIndice(login.posicionU);
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String fecha = txtFecha.getText();
        String nacionalidad = boxNacionalidad.getSelectedItem().toString();
        String telefono = txtTelefono.getText();
        String genero = boxGenero.getSelectedItem().toString();
        String dpi = txtDpi.getText();
        String alias = txtAlias.getText();

        String numero = txtTelefono.getText();
        if (!fecha.matches("^([0-2][0-9]|3[0-1])(\\/|-)(0[1-9]|1[0-2])\\2(\\d{4})$")) {
            JOptionPane.showMessageDialog(null, "La fecha no es válida");
            return;
        }

        if (numero.length() > 8) {
            JOptionPane.showMessageDialog(null, "El número telefónico no es válido");
            return;
        }

        if (!numero.matches("[0-9]*")) {
            JOptionPane.showMessageDialog(null, "El número telefónico no es válido");
            return;
        }
        
        if(nombre.trim().isEmpty() || apellido.trim().isEmpty() || fecha.trim().isEmpty() || telefono.trim().isEmpty() || dpi.trim().isEmpty() || alias.trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Existen campos sin llenar");
            return;
        }

        if (!(nombre.equals("") && apellido.equals("") && fecha.equals("") && telefono.equals("") && dpi.equals("") && alias.equals(""))) {
            String[] valores = {nombre, apellido, fecha, telefono, nacionalidad, genero, dpi, alias};
            if (ctrlUsuarios.cambiarPerfil(valores, login.posicionU)) {
                cargarDatosU();
                UsuarioCliente.actualizarMenu();
                JOptionPane.showMessageDialog(null, "Datos actualizados con éxito");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Existen campos sin llenar");
        }
    }

    public void actualizarRol() {
        Kioscos kioscoItem = (Kioscos) boxKiosco.getSelectedItem();
        int rol = boxRol.getSelectedIndex();
        String rolCompleto = boxRol.getSelectedItem().toString();

        if (rol == 2 && kioscoItem != null) {
            rolCompleto = "";
            rolCompleto = "Kiosco," + kioscoItem.getNombreKiosco();
        }
        if (ctrlUsuarios.cambiarRol(rolCompleto, login.posicionU)) {
            cargarDatosU();
            UsuarioCliente.actualizarMenu();
            JOptionPane.showMessageDialog(null, "Datos actualizdos con éxito");
        }
    }

    public void eliminarCuenta() {
        int decision = JOptionPane.showConfirmDialog(null, "¿Desea Elimianr su Cuenta?");
        if (decision == JOptionPane.YES_OPTION) {
            String contra = JOptionPane.showInputDialog(null, "Ingrese su contraseña para confirmar");
            if (ctrlUsuarios.eliminarUsuario(login.posicionU, contra)) {
                JOptionPane.showMessageDialog(null, "Usuario Eliminado Correctamente");
                JOptionPane.showMessageDialog(null, "Se cerrara la sesión");
                login l = new login();
                l.setVisible(true);
                cliente.dispose();
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        panelRound1 = new Elementos.PanelRound();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        lblCorreo = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblop1 = new javax.swing.JLabel();
        lblop2 = new javax.swing.JLabel();
        lblop4 = new javax.swing.JLabel();
        lblop3 = new javax.swing.JLabel();
        buttonRound1 = new Elementos.ButtonRound();
        txtContra = new javax.swing.JPasswordField();
        txtAnterior = new javax.swing.JPasswordField();
        panelRound2 = new Elementos.PanelRound();
        jLabel10 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtApellido = new javax.swing.JTextField();
        txtFecha = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        lblTelefono = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        boxNacionalidad = new javax.swing.JComboBox<>();
        boxGenero = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        txtDpi = new javax.swing.JTextField();
        lblDpi = new javax.swing.JLabel();
        buttonRound3 = new Elementos.ButtonRound();
        jLabel28 = new javax.swing.JLabel();
        txtAlias = new javax.swing.JTextField();
        panelRound3 = new Elementos.PanelRound();
        jLabel11 = new javax.swing.JLabel();
        lblimagen = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        buttonRound2 = new Elementos.ButtonRound();
        jLabel27 = new javax.swing.JLabel();
        panelRound4 = new Elementos.PanelRound();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        boxRol = new javax.swing.JComboBox<>();
        boxKiosco = new javax.swing.JComboBox<>();
        jLabel26 = new javax.swing.JLabel();
        buttonRound4 = new Elementos.ButtonRound();
        buttonRound5 = new Elementos.ButtonRound();

        setBackground(new java.awt.Color(19, 19, 26));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(19, 19, 26)));

        jPanel1.setBackground(new java.awt.Color(19, 19, 26));

        panelRound1.setBackground(new java.awt.Color(30, 30, 38));
        panelRound1.setRoundBottomLeft(15);
        panelRound1.setRoundBottomRight(15);
        panelRound1.setRoundTopLeft(15);
        panelRound1.setRoundTopRight(15);

        jLabel1.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("General");

        jLabel2.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Correo Electronico");

        txtCorreo.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        txtCorreo.setForeground(new java.awt.Color(255, 255, 255));
        txtCorreo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCorreoKeyReleased(evt);
            }
        });

        lblCorreo.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        lblCorreo.setForeground(new java.awt.Color(255, 255, 255));
        lblCorreo.setText("jLabel3");

        jLabel4.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nueva Contraseña");

        jLabel5.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Contraseña Anterior");

        lblop1.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        lblop1.setForeground(new java.awt.Color(255, 255, 255));
        lblop1.setText("Letras Mayúsculas");

        lblop2.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        lblop2.setForeground(new java.awt.Color(255, 255, 255));
        lblop2.setText("Letras Minúsculas");

        lblop4.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        lblop4.setForeground(new java.awt.Color(255, 255, 255));
        lblop4.setText("Caracteres Especiales");

        lblop3.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        lblop3.setForeground(new java.awt.Color(255, 255, 255));
        lblop3.setText("Números");

        buttonRound1.setBorder(null);
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

        txtContra.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        txtContra.setForeground(new java.awt.Color(255, 255, 255));
        txtContra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtContraKeyReleased(evt);
            }
        });

        txtAnterior.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        txtAnterior.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addComponent(lblop3, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblop4))
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(lblCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 596, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addComponent(lblop1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblop2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelRound1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 204, Short.MAX_VALUE))
                            .addGroup(panelRound1Layout.createSequentialGroup()
                                .addComponent(txtContra)
                                .addGap(29, 29, 29)))
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(30, 30, 30))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonRound1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(257, 257, 257))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(7, 7, 7)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtContra, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblop1)
                    .addComponent(lblop2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblop3)
                    .addComponent(lblop4))
                .addGap(18, 18, 18)
                .addComponent(buttonRound1, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addGap(15, 15, 15))
        );

        panelRound2.setBackground(new java.awt.Color(28, 28, 36));
        panelRound2.setRoundBottomLeft(15);
        panelRound2.setRoundBottomRight(15);
        panelRound2.setRoundTopLeft(15);
        panelRound2.setRoundTopRight(15);

        jLabel10.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Editar Perfil");

        jLabel14.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Nombre");

        txtNombre.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        txtNombre.setForeground(new java.awt.Color(255, 255, 255));

        jLabel15.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Apellido");

        txtApellido.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        txtApellido.setForeground(new java.awt.Color(255, 255, 255));

        txtFecha.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        txtFecha.setForeground(new java.awt.Color(255, 255, 255));
        txtFecha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFechaKeyReleased(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Fecha de Nacimiento");

        lblFecha.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        lblFecha.setForeground(new java.awt.Color(255, 255, 255));

        jLabel18.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Teléfono");

        txtTelefono.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        txtTelefono.setForeground(new java.awt.Color(255, 255, 255));
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyPressed(evt);
            }
        });

        lblTelefono.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        lblTelefono.setForeground(new java.awt.Color(255, 255, 255));

        jLabel20.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Nacionalidad");

        jLabel21.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Género");

        boxNacionalidad.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        boxNacionalidad.setForeground(new java.awt.Color(255, 255, 255));

        boxGenero.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        boxGenero.setForeground(new java.awt.Color(255, 255, 255));

        jLabel22.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("DPI");

        txtDpi.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        txtDpi.setForeground(new java.awt.Color(255, 255, 255));
        txtDpi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDpiKeyPressed(evt);
            }
        });

        lblDpi.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        lblDpi.setForeground(new java.awt.Color(255, 255, 255));

        buttonRound3.setBorder(null);
        buttonRound3.setForeground(new java.awt.Color(255, 255, 255));
        buttonRound3.setText("Actualizar");
        buttonRound3.setBorderColor(new java.awt.Color(123, 127, 239));
        buttonRound3.setColor(new java.awt.Color(123, 127, 239));
        buttonRound3.setColorClick(new java.awt.Color(121, 118, 236));
        buttonRound3.setColorOver(new java.awt.Color(121, 147, 251));
        buttonRound3.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        buttonRound3.setRadius(15);
        buttonRound3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRound3ActionPerformed(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Alias");

        txtAlias.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        txtAlias.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelRound2Layout = new javax.swing.GroupLayout(panelRound2);
        panelRound2.setLayout(panelRound2Layout);
        panelRound2Layout.setHorizontalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound2Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addGroup(panelRound2Layout.createSequentialGroup()
                                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(lblDpi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtDpi, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(boxNacionalidad, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtNombre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                                        .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblFecha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtFecha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                                        .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabel22))
                                .addGap(35, 35, 35)
                                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtApellido)
                                    .addComponent(txtTelefono)
                                    .addComponent(lblTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtAlias)
                                    .addComponent(boxGenero, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(panelRound2Layout.createSequentialGroup()
                                        .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel28)
                                            .addComponent(jLabel21)
                                            .addComponent(jLabel15))
                                        .addGap(0, 0, Short.MAX_VALUE))))))
                    .addGroup(panelRound2Layout.createSequentialGroup()
                        .addGap(251, 251, 251)
                        .addComponent(buttonRound3, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22))
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(boxNacionalidad, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(boxGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jLabel28))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDpi, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAlias, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDpi, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonRound3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        panelRound3.setBackground(new java.awt.Color(28, 28, 36));

        jLabel11.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Imagen de Perfil");

        jLabel13.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Imagenes en Formate PNG, JPEG, JPG");

        buttonRound2.setBorder(null);
        buttonRound2.setForeground(new java.awt.Color(255, 255, 255));
        buttonRound2.setText("Actualizar");
        buttonRound2.setBorderColor(new java.awt.Color(123, 127, 239));
        buttonRound2.setColor(new java.awt.Color(123, 127, 239));
        buttonRound2.setColorClick(new java.awt.Color(121, 118, 236));
        buttonRound2.setColorOver(new java.awt.Color(121, 147, 251));
        buttonRound2.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        buttonRound2.setRadius(15);
        buttonRound2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRound2ActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Montserrat", 2, 12)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Dependiendo del tamaño de la imagen puede demorar más tiempo");

        javax.swing.GroupLayout panelRound3Layout = new javax.swing.GroupLayout(panelRound3);
        panelRound3.setLayout(panelRound3Layout);
        panelRound3Layout.setHorizontalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(lblimagen, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(buttonRound2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRound3Layout.setVerticalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel11)
                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound3Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(buttonRound2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel27))
                    .addGroup(panelRound3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblimagen, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        panelRound4.setBackground(new java.awt.Color(28, 28, 36));

        jLabel24.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Editar Rol");

        jLabel25.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Rol");

        boxRol.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        boxRol.setForeground(new java.awt.Color(255, 255, 255));
        boxRol.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                boxRolItemStateChanged(evt);
            }
        });

        boxKiosco.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        boxKiosco.setForeground(new java.awt.Color(255, 255, 255));

        jLabel26.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel26.setText("Kiosco");

        buttonRound4.setBorder(null);
        buttonRound4.setForeground(new java.awt.Color(255, 255, 255));
        buttonRound4.setText("Actualizar");
        buttonRound4.setBorderColor(new java.awt.Color(123, 127, 239));
        buttonRound4.setColor(new java.awt.Color(123, 127, 239));
        buttonRound4.setColorClick(new java.awt.Color(121, 118, 236));
        buttonRound4.setColorOver(new java.awt.Color(121, 147, 251));
        buttonRound4.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        buttonRound4.setRadius(15);
        buttonRound4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRound4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound4Layout = new javax.swing.GroupLayout(panelRound4);
        panelRound4.setLayout(panelRound4Layout);
        panelRound4Layout.setHorizontalGroup(
            panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound4Layout.createSequentialGroup()
                .addGroup(panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound4Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelRound4Layout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addGap(309, 309, 309))
                            .addGroup(panelRound4Layout.createSequentialGroup()
                                .addGroup(panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel25)
                                    .addComponent(boxRol, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(boxKiosco, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(panelRound4Layout.createSequentialGroup()
                        .addGap(261, 261, 261)
                        .addComponent(buttonRound4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26))
        );
        panelRound4Layout.setVerticalGroup(
            panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel24)
                .addGap(18, 18, 18)
                .addGroup(panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(jLabel26))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(boxRol, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(boxKiosco, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addComponent(buttonRound4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        buttonRound5.setBorder(null);
        buttonRound5.setForeground(new java.awt.Color(226, 96, 104));
        buttonRound5.setText("Eliminar Cuenta");
        buttonRound5.setBorderColor(new java.awt.Color(62, 63, 72));
        buttonRound5.setColor(new java.awt.Color(47, 49, 53));
        buttonRound5.setColorOver(new java.awt.Color(47, 44, 64));
        buttonRound5.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        buttonRound5.setRadius(15);
        buttonRound5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRound5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelRound2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelRound3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelRound4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(223, 223, 223)
                        .addComponent(buttonRound5, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelRound3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelRound2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelRound4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(buttonRound5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 702, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buttonRound4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRound4ActionPerformed
        // TODO add your handling code here:
        actualizarRol();
    }//GEN-LAST:event_buttonRound4ActionPerformed

    private void buttonRound2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRound2ActionPerformed
        // TODO add your handling code here:
        int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea cambiar su fotografía?", "Confiramción", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (respuesta == JOptionPane.YES_OPTION) {
            actualizarIMG();
        }
    }//GEN-LAST:event_buttonRound2ActionPerformed

    private void buttonRound1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRound1ActionPerformed
        // TODO add your handling code here:
        actualizarGeneral();
    }//GEN-LAST:event_buttonRound1ActionPerformed

    private void buttonRound3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRound3ActionPerformed
        // TODO add your handling code here:
        int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea actualizar sus datos de Perfil?", "Confiramción", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (respuesta == JOptionPane.YES_OPTION) {
            actualizarPerfil();
        }
    }//GEN-LAST:event_buttonRound3ActionPerformed

    private void buttonRound5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRound5ActionPerformed
        // TODO add your handling code here:
        int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea Eliminar su cuenta?", "Confiramción", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (respuesta == JOptionPane.YES_OPTION) {
            eliminarCuenta();
        }
    }//GEN-LAST:event_buttonRound5ActionPerformed

    private void boxRolItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_boxRolItemStateChanged
        // TODO add your handling code here:
        if (boxRol.getSelectedIndex() == 2) {
            if (boxKiosco.getItemCount() == 0) {
                boxRol.setSelectedIndex(1);
                boxRol.setSelectedIndex(0);
                boxRol.getSelectedIndex();
                JOptionPane.showMessageDialog(null, "No hay Kioscos registrados");
            } else {
                boxKiosco.setEnabled(true);
                boxKiosco.setVisible(true);
            }

        } else {
            boxKiosco.setEnabled(false);
            boxKiosco.setVisible(false);
        }
    }//GEN-LAST:event_boxRolItemStateChanged

    private void txtContraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContraKeyReleased
        // TODO add your handling code here:
        Pattern vMayus = Pattern.compile("^(?=.*[A-Z])");
        Pattern vMinus = Pattern.compile("^(?=.*[a-z])");
        Pattern vNum = Pattern.compile("^(?=.*[0-9])");
        Pattern vEspecial = Pattern.compile("^(?=.*[!#%&@*$_-])");
        Pattern[] exp = {vMayus, vMinus, vNum, vEspecial};
        JLabel[] labels = {lblop1, lblop2, lblop3, lblop4};
        for (int i = 0; i < exp.length; i++) {
            Matcher m = exp[i].matcher(String.valueOf(txtContra.getPassword()));
            if (m.find()) {
                labels[i].setForeground(new Color(99, 220, 147));
                txtContra.putClientProperty("Component.outlineWidth", 1);
                txtContra.putClientProperty("JComponent.outline", "correct");
            } else {
                labels[i].setForeground(login.error);
                txtContra.putClientProperty("Component.outlineWidth", 1);
                txtContra.putClientProperty("JComponent.outline", "error");

            }
        }
        if (ctrlUsuarios.verificarPassword(String.valueOf(txtContra.getPassword()))) {
            txtContra.putClientProperty("Component.outlineWidth", 1);
            txtContra.putClientProperty("JComponent.outline", "correct");
        }
    }//GEN-LAST:event_txtContraKeyReleased

    private void txtCorreoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCorreoKeyReleased
        // TODO add your handling code here:
        Pattern vCorreo = Pattern.compile("^[^@]+@[^@]+\\.[a-zA-Z]{2,}$");
        Matcher m = vCorreo.matcher(txtCorreo.getText());
        if (!m.find()) {
            lblCorreo.setForeground(login.error);
            lblCorreo.setText("Ingrese un correo valido");
            txtCorreo.putClientProperty("Component.outlineWidth", 1);
            txtCorreo.putClientProperty("JComponent.outline", "error");
        } else {
            lblCorreo.setText("");
            txtCorreo.putClientProperty("Component.outlineWidth", 1);
            txtCorreo.putClientProperty("JComponent.outline", "correct");
        }
    }//GEN-LAST:event_txtCorreoKeyReleased

    private void txtFechaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFechaKeyReleased
        // TODO add your handling code here:
        Pattern vCorreo = Pattern.compile("^([0-2][0-9]|3[0-1])(\\/|-)(0[1-9]|1[0-2])\\2(\\d{4})$");
        Matcher m = vCorreo.matcher(txtFecha.getText());
        if (!m.find()) {
            lblFecha.setForeground(login.error);
            lblFecha.setText("Ingrese una fecha valida");
            validarFecha = false;
            txtFecha.putClientProperty("Component.outlineWidth", 1);
            txtFecha.putClientProperty("JComponent.outline", "error");
        } else {
            lblFecha.setForeground(Color.WHITE);
            lblFecha.setText("dd/mm/yyyy");
            txtFecha.putClientProperty("Component.outlineWidth", 1);
            txtFecha.putClientProperty("JComponent.outline", "correct");
            validarFecha = true;
        }
    }//GEN-LAST:event_txtFechaKeyReleased

    private void txtTelefonoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyPressed
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            txtTelefono.setEditable(false);
            lblTelefono.setText("Solo numeros");
            txtTelefono.putClientProperty("Component.outlineWidth", 1);
            txtTelefono.putClientProperty("JComponent.outline", "error");
            if (txtTelefono.getText().toString().length() >= 9) {
                txtTelefono.putClientProperty("Component.outlineWidth", 1);
                txtTelefono.putClientProperty("JComponent.outline", "error");
                lblTelefono.setText("Solo numeros de 8 digitos");
            } else {
                txtTelefono.putClientProperty("Component.outlineWidth", 1);
                txtTelefono.putClientProperty("JComponent.outline", "correct");
            }

        } else {
            txtTelefono.setEditable(true);
            lblTelefono.setText("");
            txtTelefono.putClientProperty("Component.outlineWidth", 1);
            lblTelefono.putClientProperty("JComponent.outline", "correct");
            if (txtTelefono.getText().toString().length() >= 9) {
                txtTelefono.putClientProperty("Component.outlineWidth", 1);
                txtTelefono.putClientProperty("JComponent.outline", "error");
                lblTelefono.setText("Solo numeros de 8 digitos");
            } else {
                lblTelefono.setText("");
                txtTelefono.putClientProperty("Component.outlineWidth", 1);
                txtTelefono.putClientProperty("JComponent.outline", "correct");
            }
        }
    }//GEN-LAST:event_txtTelefonoKeyPressed

    private void txtDpiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDpiKeyPressed
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            txtDpi.setEditable(false);
            txtDpi.putClientProperty("Component.outlineWidth", 1);
            txtDpi.putClientProperty("JComponent.outline", "error");
            lblDpi.setText("Solo numeros");
        } else {
            txtDpi.putClientProperty("Component.outlineWidth", 1);
            txtDpi.putClientProperty("JComponent.outline", "correct");
            txtDpi.setEditable(true);
            lblDpi.setText("");
        }
    }//GEN-LAST:event_txtDpiKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> boxGenero;
    private javax.swing.JComboBox<Object> boxKiosco;
    private javax.swing.JComboBox<String> boxNacionalidad;
    private javax.swing.JComboBox<String> boxRol;
    private Elementos.ButtonRound buttonRound1;
    private Elementos.ButtonRound buttonRound2;
    private Elementos.ButtonRound buttonRound3;
    private Elementos.ButtonRound buttonRound4;
    private Elementos.ButtonRound buttonRound5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCorreo;
    private javax.swing.JLabel lblDpi;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JLabel lblimagen;
    private javax.swing.JLabel lblop1;
    private javax.swing.JLabel lblop2;
    private javax.swing.JLabel lblop3;
    private javax.swing.JLabel lblop4;
    private Elementos.PanelRound panelRound1;
    private Elementos.PanelRound panelRound2;
    private Elementos.PanelRound panelRound3;
    private Elementos.PanelRound panelRound4;
    private javax.swing.JTextField txtAlias;
    private javax.swing.JPasswordField txtAnterior;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JPasswordField txtContra;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDpi;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
