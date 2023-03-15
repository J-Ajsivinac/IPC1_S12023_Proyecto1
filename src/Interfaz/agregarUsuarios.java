package Interfaz;

import Administrador.Kioscos;
import Administrador.ctrlKioscos;
import Usuario.ctrlUsuarios;
import java.awt.Color;
import java.awt.Image;
import java.awt.MediaTracker;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author mesoi
 */
public class agregarUsuarios extends javax.swing.JFrame {

    Border unselectedborder;
    Border selectedborder;
    Border errorBorde;
    Border correctoBorde;
    Color selectedTxt;
    Color unselectedTxt;
    Color error;
    Color correcto;
    private boolean validarFecha = false;
    public ArrayList<Kioscos> kiosco;
    public String ruta = "src\\img\\usuario.png";
    private static String[] nombrePaises = {"Alemania", "Argentina", "Belice", "Brasil", "Canadá", "China",
        "El Salvador", "España", "Guatemala", "Honduras", "Japón", "México", "Portugal", "Uruguay"};

    /**
     * Creates new form agregarUsuarios
     */
    public agregarUsuarios() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        agregarBoxes();
        boxKiosco.setEnabled(false);
        kiosco = ctrlKioscos.getAllKioscos();
        cargarKioscos();
        selectedTxt = new Color(50, 51, 64);
        unselectedTxt = new Color(40, 41, 52);
        error = new Color(255, 75, 76);
        correcto = new Color(143, 235, 170);
        unselectedborder = BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(150, 157, 175));
        selectedborder = BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(210, 220, 222));
        errorBorde = BorderFactory.createMatteBorder(0, 0, 2, 0, error);
        correctoBorde = BorderFactory.createMatteBorder(0, 0, 2, 0, correcto);

        boxKiosco.setVisible(false);
        setBordes();

    }

    public void setBordes() {

    }

    public void cargarKioscos() {
        for (int i = 0; i < kiosco.size(); i++) {
            if (kiosco.get(i) != null) {
                String codeK = kiosco.get(i).getCodigoKioco();
                String nombreKiosco = kiosco.get(i).getNombreKiosco();
                boxKiosco.addItem(new Kioscos(kiosco.get(i).getIdRegion(), kiosco.get(i).getCodigo(), kiosco.get(i).getNombre(), kiosco.get(i).getPrecioEstandar(), kiosco.get(i).getPrecioEspecial(), codeK, nombreKiosco));
            }

        }
    }

    public void selected(JTextField cambiar, int tipo) {
        if (tipo == 0) {
            cambiar.setBackground(new Color(50, 51, 64));
        } else {
            cambiar.setBackground(new Color(40, 41, 52));
        }
    }

    public void limpiarTxt() {
        txtAlias.setText("");
        txtApellido.setText("");
        txtContra.setText("");
        txtCorreo.setText("");
        txtDPI.setText("");
        txtFecha.setText("");
        //txtKiosco.setBorder(unselectedborder);
        txtNombre.setText("");
        txtTelefono.setText("");
        txtValidar.setText("");
        ruta = "";
    }

    public void cargarImagen() {
        ruta = "";
        JFileChooser archivos = new JFileChooser();
        FileNameExtensionFilter filtrado = new FileNameExtensionFilter("JPG & PNG", "jpg", "png");
        archivos.setFileFilter(filtrado);

        int respuesta = archivos.showOpenDialog(this);
        if (respuesta == archivos.APPROVE_OPTION) {
            ruta = archivos.getSelectedFile().getPath();
            /// Cargar la imagen original

            ImageIcon originalImageIcon = new ImageIcon(ruta);
            if (originalImageIcon.getImageLoadStatus() != MediaTracker.COMPLETE) {
                ///htmls/factura.html
                originalImageIcon = new ImageIcon("src\\img\\usuario.png");
                JOptionPane.showMessageDialog(null, "Error al cargar la imagen \n se cargara una imagen por defecto","Error", JOptionPane.ERROR_MESSAGE);
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
        jLabel1 = new javax.swing.JLabel();
        panelRound1 = new Elementos.PanelRound();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        lblCorreo = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtApellido = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblop1 = new javax.swing.JLabel();
        lblop2 = new javax.swing.JLabel();
        lblop3 = new javax.swing.JLabel();
        lblop4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        boxRol = new javax.swing.JComboBox<>();
        txtKiosco = new javax.swing.JLabel();
        boxKiosco = new javax.swing.JComboBox<>();
        txtContra = new javax.swing.JPasswordField();
        txtValidar = new javax.swing.JPasswordField();
        jPanel5 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        boxNacionalidad = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        boxGenero = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        txtAlias = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        lblTelefono = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtDPI = new javax.swing.JTextField();
        lblDpi = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        panelRound2 = new Elementos.PanelRound();
        lblIniciarS = new javax.swing.JLabel();
        buttonRound1 = new Elementos.ButtonRound();
        panelRound3 = new Elementos.PanelRound();
        panelImagen = new javax.swing.JPanel();
        lblimagen = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        buttonRound2 = new Elementos.ButtonRound();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(19, 19, 26));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Registrar Usuarios");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 14, 170, -1));

        panelRound1.setBackground(new java.awt.Color(28, 28, 36));
        panelRound1.setRoundTopLeft(15);
        panelRound1.setRoundTopRight(15);

        jPanel3.setOpaque(false);
        jPanel3.setLayout(new java.awt.GridLayout(1, 2));

        jPanel4.setOpaque(false);

        jLabel2.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Correo Electronico *");

        txtCorreo.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        txtCorreo.setForeground(new java.awt.Color(255, 255, 255));
        txtCorreo.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtCorreo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCorreoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCorreoFocusLost(evt);
            }
        });
        txtCorreo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCorreoKeyReleased(evt);
            }
        });

        lblCorreo.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        lblCorreo.setForeground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nombre *");

        txtNombre.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        txtNombre.setForeground(new java.awt.Color(255, 255, 255));
        txtNombre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNombreFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNombreFocusLost(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Apellido *");

        txtApellido.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        txtApellido.setForeground(new java.awt.Color(255, 255, 255));
        txtApellido.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtApellidoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtApellidoFocusLost(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Contraseña *");

        jLabel7.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Valida Contraseña *");

        lblop1.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        lblop1.setForeground(new java.awt.Color(255, 75, 76));
        lblop1.setText("Letras Mayúsculas");

        lblop2.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        lblop2.setForeground(new java.awt.Color(255, 75, 76));
        lblop2.setText("Letras Minúsculas");

        lblop3.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        lblop3.setForeground(new java.awt.Color(255, 75, 76));
        lblop3.setText("Números");

        lblop4.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        lblop4.setForeground(new java.awt.Color(255, 75, 76));
        lblop4.setText("Caracteres Especiales");

        jLabel8.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Rol *");

        boxRol.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        boxRol.setForeground(new java.awt.Color(255, 255, 255));
        boxRol.setOpaque(false);
        boxRol.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                boxRolItemStateChanged(evt);
            }
        });

        txtKiosco.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        txtKiosco.setForeground(new java.awt.Color(255, 255, 255));
        txtKiosco.setText("Kiosco");

        boxKiosco.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        boxKiosco.setForeground(new java.awt.Color(255, 255, 255));

        txtContra.setForeground(new java.awt.Color(255, 255, 255));
        txtContra.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtContraFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtContraFocusLost(evt);
            }
        });
        txtContra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtContraKeyReleased(evt);
            }
        });

        txtValidar.setForeground(new java.awt.Color(255, 255, 255));
        txtValidar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtValidarFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtValidarFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel2))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lblop3, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(lblop4, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(152, 152, 152)
                        .addComponent(txtKiosco, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(boxRol, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(boxKiosco, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(lblop1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(lblop2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtContra)
                                .addGap(18, 18, 18)
                                .addComponent(txtValidar, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtApellido))))
                .addGap(11, 11, 11))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel2)
                .addGap(6, 6, 6)
                .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtContra, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtValidar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblop1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblop2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblop3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblop4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(txtKiosco))
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(boxKiosco, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(boxRol, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)))
        );

        jPanel3.add(jPanel4);

        jPanel5.setOpaque(false);

        jLabel10.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Fecha de Nacimiento *");

        txtFecha.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        txtFecha.setForeground(new java.awt.Color(255, 255, 255));
        txtFecha.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtFechaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtFechaFocusLost(evt);
            }
        });
        txtFecha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFechaKeyReleased(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Nacionalidad");

        boxNacionalidad.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        boxNacionalidad.setForeground(new java.awt.Color(255, 255, 255));

        jLabel12.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Genero");

        boxGenero.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        boxGenero.setForeground(new java.awt.Color(255, 255, 255));

        jLabel13.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Sobrenombre (Alias) *");

        txtAlias.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        txtAlias.setForeground(new java.awt.Color(255, 255, 255));
        txtAlias.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtAliasFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtAliasFocusLost(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Telefono *");

        txtTelefono.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        txtTelefono.setForeground(new java.awt.Color(255, 255, 255));
        txtTelefono.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTelefonoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTelefonoFocusLost(evt);
            }
        });
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyPressed(evt);
            }
        });

        lblTelefono.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        lblTelefono.setForeground(new java.awt.Color(255, 255, 255));

        jLabel16.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("DPI *");

        txtDPI.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        txtDPI.setForeground(new java.awt.Color(255, 255, 255));
        txtDPI.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDPIFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDPIFocusLost(evt);
            }
        });
        txtDPI.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDPIKeyPressed(evt);
            }
        });

        lblDpi.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        lblDpi.setForeground(new java.awt.Color(255, 255, 255));

        lblFecha.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        lblFecha.setForeground(new java.awt.Color(255, 255, 255));
        lblFecha.setText("dd/mm/yyyy");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblDpi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDPI, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtFecha, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(boxNacionalidad, javax.swing.GroupLayout.Alignment.LEADING, 0, 161, Short.MAX_VALUE))
                                .addGap(24, 24, 24)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(boxGenero, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(lblFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtAlias, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE))
                                .addGap(24, 24, 24)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTelefono)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 90, Short.MAX_VALUE))
                                    .addComponent(lblTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(14, 14, 14))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(boxNacionalidad, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(boxGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAlias, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDPI, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDpi, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel5);

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 770, Short.MAX_VALUE)
            .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(10, 10, 10)))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 410, Short.MAX_VALUE)
            .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                    .addContainerGap(15, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        jPanel1.add(panelRound1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 60, 770, 410));

        panelRound2.setBackground(new java.awt.Color(28, 28, 36));
        panelRound2.setRoundBottomLeft(15);
        panelRound2.setRoundBottomRight(15);

        lblIniciarS.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        lblIniciarS.setForeground(new java.awt.Color(255, 255, 255));
        lblIniciarS.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIniciarS.setText("Iniciar Sesión");
        lblIniciarS.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblIniciarS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblIniciarSMouseClicked(evt);
            }
        });
        lblIniciarS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lblIniciarSKeyPressed(evt);
            }
        });

        buttonRound1.setBorder(null);
        buttonRound1.setForeground(new java.awt.Color(255, 255, 255));
        buttonRound1.setText("Registrar");
        buttonRound1.setBorderColor(new java.awt.Color(85, 104, 254));
        buttonRound1.setColor(new java.awt.Color(85, 104, 254));
        buttonRound1.setColorClick(new java.awt.Color(83, 99, 229));
        buttonRound1.setColorOver(new java.awt.Color(73, 91, 226));
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
                .addGap(308, 308, 308)
                .addComponent(buttonRound1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(310, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblIniciarS, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(321, 321, 321))
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(buttonRound1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblIniciarS, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.add(panelRound2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 470, 770, 90));

        panelRound3.setBackground(new java.awt.Color(28, 28, 36));
        panelRound3.setRoundBottomLeft(15);
        panelRound3.setRoundBottomRight(15);
        panelRound3.setRoundTopLeft(15);
        panelRound3.setRoundTopRight(15);

        panelImagen.setBackground(new java.awt.Color(204, 204, 204));
        panelImagen.setOpaque(false);

        lblimagen.setMaximumSize(new java.awt.Dimension(120, 120));
        lblimagen.setMinimumSize(new java.awt.Dimension(1, 1));
        lblimagen.setPreferredSize(new java.awt.Dimension(110, 110));

        jLabel3.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Fotografía");

        buttonRound2.setBorder(null);
        buttonRound2.setForeground(new java.awt.Color(255, 255, 255));
        buttonRound2.setText("Subir");
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

        javax.swing.GroupLayout panelImagenLayout = new javax.swing.GroupLayout(panelImagen);
        panelImagen.setLayout(panelImagenLayout);
        panelImagenLayout.setHorizontalGroup(
            panelImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImagenLayout.createSequentialGroup()
                .addGroup(panelImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelImagenLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(buttonRound2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelImagenLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3))
                    .addGroup(panelImagenLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(lblimagen, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        panelImagenLayout.setVerticalGroup(
            panelImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImagenLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblimagen, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonRound2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelRound3Layout = new javax.swing.GroupLayout(panelRound3);
        panelRound3.setLayout(panelRound3Layout);
        panelRound3Layout.setHorizontalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelImagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelRound3Layout.setVerticalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelImagen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(panelRound3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 160, 220));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 998, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 576, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblIniciarSKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lblIniciarSKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblIniciarSKeyPressed

    private void lblIniciarSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIniciarSMouseClicked
        // TODO add your handling code here:
        login l = new login();
        l.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lblIniciarSMouseClicked

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
                //txtContra.setBorder(correctoBorde);
                txtContra.putClientProperty("Component.outlineWidth", 1);
                txtContra.putClientProperty("JComponent.outline", "correct");
            } else {
                labels[i].setForeground(error);
                //txtContra.setBorder(errorBorde);
                txtContra.putClientProperty("Component.outlineWidth", 1);
                txtContra.putClientProperty("JComponent.outline", "error");
            }
        }
        if (ctrlUsuarios.verificarPassword(String.valueOf(txtContra.getPassword()))) {
            txtContra.putClientProperty("Component.outlineWidth", 1);
            txtContra.putClientProperty("JComponent.outline", "correct");
        }
    }//GEN-LAST:event_txtContraKeyReleased

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
            if (txtTelefono.getText().toString().length() > 8) {
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

    private void txtDPIKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDPIKeyPressed
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            txtDPI.setEditable(false);
            txtDPI.putClientProperty("Component.outlineWidth", 1);
            txtDPI.putClientProperty("JComponent.outline", "error");
            lblDpi.setText("Solo numeros");
        } else {
            txtDPI.putClientProperty("Component.outlineWidth", 1);
            txtDPI.putClientProperty("JComponent.outline", "correct");
            txtDPI.setEditable(true);
            lblDpi.setText("");
        }
    }//GEN-LAST:event_txtDPIKeyPressed

    private void txtCorreoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCorreoKeyReleased
        // TODO add your handling code here:
        Pattern vCorreo = Pattern.compile("^[^@]+@[^@]+\\.[a-zA-Z]{2,}$");
        Matcher m = vCorreo.matcher(txtCorreo.getText());
        if (!m.find()) {
            lblCorreo.setForeground(error);
            lblCorreo.setText("Ingrese un correo valido");
            txtCorreo.putClientProperty("Component.outlineWidth", 1);
            txtCorreo.putClientProperty("JComponent.outline", "error");
        } else {
            lblCorreo.setText("");
            txtCorreo.putClientProperty("Component.outlineWidth", 1);
            txtCorreo.putClientProperty("JComponent.outline", "correct");
        }
    }//GEN-LAST:event_txtCorreoKeyReleased

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

    private void txtCorreoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCorreoFocusGained
        // TODO add your handling code here:
        selected(txtCorreo, 0);
    }//GEN-LAST:event_txtCorreoFocusGained

    private void txtCorreoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCorreoFocusLost
        // TODO add your handling code here:
        txtCorreo.setBackground(new Color(40, 41, 52));
    }//GEN-LAST:event_txtCorreoFocusLost

    private void txtNombreFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNombreFocusGained
        // TODO add your handling code here:
        selected(txtNombre, 0);
    }//GEN-LAST:event_txtNombreFocusGained

    private void txtNombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNombreFocusLost
        // TODO add your handling code here:
        selected(txtNombre, 1);
    }//GEN-LAST:event_txtNombreFocusLost

    private void txtFechaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFechaFocusGained
        // TODO add your handling code here:
        selected(txtFecha, 0);
    }//GEN-LAST:event_txtFechaFocusGained

    private void txtFechaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFechaFocusLost
        // TODO add your handling code here:
        selected(txtFecha, 1);
    }//GEN-LAST:event_txtFechaFocusLost

    private void txtAliasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAliasFocusGained
        // TODO add your handling code here:
        selected(txtAlias, 0);
    }//GEN-LAST:event_txtAliasFocusGained

    private void txtAliasFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAliasFocusLost
        // TODO add your handling code here:
        selected(txtAlias, 1);
    }//GEN-LAST:event_txtAliasFocusLost

    private void txtTelefonoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTelefonoFocusGained
        // TODO add your handling code here:
        selected(txtTelefono, 0);
    }//GEN-LAST:event_txtTelefonoFocusGained

    private void txtTelefonoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTelefonoFocusLost
        // TODO add your handling code here:
        selected(txtTelefono, 1);
    }//GEN-LAST:event_txtTelefonoFocusLost

    private void txtApellidoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtApellidoFocusGained
        // TODO add your handling code here:
        selected(txtApellido, 0);
    }//GEN-LAST:event_txtApellidoFocusGained

    private void txtApellidoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtApellidoFocusLost
        // TODO add your handling code here:
        selected(txtApellido, 1);
    }//GEN-LAST:event_txtApellidoFocusLost

    private void txtContraFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtContraFocusGained
        // TODO add your handling code here:
        selected(txtContra, 0);
    }//GEN-LAST:event_txtContraFocusGained

    private void txtContraFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtContraFocusLost
        // TODO add your handling code here:
        txtContra.setBackground(new Color(40, 41, 52));
    }//GEN-LAST:event_txtContraFocusLost

    private void txtValidarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtValidarFocusGained
        // TODO add your handling code here:
        selected(txtValidar, 0);
    }//GEN-LAST:event_txtValidarFocusGained

    private void txtValidarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtValidarFocusLost
        // TODO add your handling code here:
        selected(txtValidar, 1);
    }//GEN-LAST:event_txtValidarFocusLost

    private void txtFechaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFechaKeyReleased
        // TODO add your handling code here:
        Pattern vCorreo = Pattern.compile("^([0-2][0-9]|3[0-1])(\\/|-)(0[1-9]|1[0-2])\\2(\\d{4})$");
        Matcher m = vCorreo.matcher(txtFecha.getText());
        if (!m.find()) {
            lblFecha.setForeground(error);
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

    private void txtDPIFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDPIFocusGained
        // TODO add your handling code here:
        selected(txtDPI, 0);
    }//GEN-LAST:event_txtDPIFocusGained

    private void txtDPIFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDPIFocusLost
        // TODO add your handling code here:
        selected(txtDPI, 0);
    }//GEN-LAST:event_txtDPIFocusLost

    private void buttonRound1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRound1ActionPerformed
        // TODO add your handling code here:
        agregarUsuario();
    }//GEN-LAST:event_buttonRound1ActionPerformed

    private void buttonRound2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRound2ActionPerformed
        // TODO add your handling code here:
        cargarImagen();
    }//GEN-LAST:event_buttonRound2ActionPerformed
    public void agregarUsuario() {

        Kioscos kioscoItem = (Kioscos) boxKiosco.getSelectedItem();

        String correo = txtCorreo.getText();
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String contra = String.valueOf(txtContra.getPassword());
        String verificar = String.valueOf(txtValidar.getPassword());
        String dpi = txtDPI.getText();
        int rol = boxRol.getSelectedIndex();
        String rolCompleto = boxRol.getSelectedItem().toString();

        if (rol == 2 && kioscoItem != null) {
            rolCompleto = "";
            rolCompleto = "Kiosco," + kioscoItem.getCodigoKioco();
        }
        String img = "s";
        if (!ruta.equals("")) {
            img = ruta;
        }
        String fecha = txtFecha.getText();
        String nacionalidad = boxNacionalidad.getSelectedItem().toString();
        String genero = boxGenero.getSelectedItem().toString();
        String alias = txtAlias.getText();
        String numero = txtTelefono.getText();
        if (!validarFecha) {
            JOptionPane.showMessageDialog(null, "La fecha no es válida");
            return;
        }

        if (numero.length() > 8) {
            JOptionPane.showMessageDialog(null, "El número telefónico no es válido");
            return;
        }
        
        if(!numero.matches("[0-9]*")){
            JOptionPane.showMessageDialog(null, "El número telefónico no es válido");
            return;
        }
        
        if(dpi.trim().isEmpty() || correo.trim().isEmpty() || nombre.trim().isEmpty() || apellido.trim().isEmpty() || fecha.trim().isEmpty() || alias.trim().isEmpty() || numero.trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Tiene campos sin llenar correctamente");
            return;
        }
        
        if (!correo.equals("") && !nombre.equals("") && !apellido.equals("") && !contra.equals("")
                && !verificar.equals("") && !dpi.equals("") && !fecha.equals("")
                && !nacionalidad.equals("") && !genero.equals("") && !genero.equals("") && !alias.equals("") && !numero.equals("")) {
            int n = Integer.parseInt(numero);
            if (contra.equals(verificar)) {
                if (ctrlUsuarios.nuevoUsuario(correo, nombre, apellido, contra, dpi, fecha, genero, nacionalidad, alias, n, rolCompleto, img)) {
                    lblimagen.setIcon(null);

                    // Actualizar el JLabel para reflejar los cambios
                    lblimagen.revalidate();
                    lblimagen.repaint();
                    lblop1.setForeground(error);
                    lblop2.setForeground(error);
                    lblop3.setForeground(error);
                    lblop4.setForeground(error);
                    
                    lblCorreo.setText("");
                    JOptionPane.showMessageDialog(null, "Usuario Agregado");
                    limpiarTxt();
                    setBordes();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Tiene campos sin llenar correctamente");
        }

    }

    public void agregarBoxes() {
        boxGenero.addItem("Masculino");
        boxGenero.addItem("Femenino");

        for (String nombres : nombrePaises) {
            boxNacionalidad.addItem(nombres);
        }
        boxRol.addItem("Usuario Individual");
        boxRol.addItem("Usuario Empresarial");
        boxRol.addItem("Kiosko");

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
            java.util.logging.Logger.getLogger(agregarUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(agregarUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(agregarUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(agregarUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new agregarUsuarios().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> boxGenero;
    private javax.swing.JComboBox<Object> boxKiosco;
    private javax.swing.JComboBox<String> boxNacionalidad;
    private javax.swing.JComboBox<String> boxRol;
    private Elementos.ButtonRound buttonRound1;
    private Elementos.ButtonRound buttonRound2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel lblCorreo;
    private javax.swing.JLabel lblDpi;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblIniciarS;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JLabel lblimagen;
    private javax.swing.JLabel lblop1;
    private javax.swing.JLabel lblop2;
    private javax.swing.JLabel lblop3;
    private javax.swing.JLabel lblop4;
    private javax.swing.JPanel panelImagen;
    private Elementos.PanelRound panelRound1;
    private Elementos.PanelRound panelRound2;
    private Elementos.PanelRound panelRound3;
    private javax.swing.JTextField txtAlias;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JPasswordField txtContra;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDPI;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JLabel txtKiosco;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JPasswordField txtValidar;
    // End of variables declaration//GEN-END:variables
}
