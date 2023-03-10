package Componentes;

import Administrador.Departamentos;
import Administrador.Municipios;
import Administrador.ctrlDepartamentos;
import Administrador.ctrlRegiones;
import static Componentes.UsuarioTarjeta.selected;
import Elementos.ScrollBarCustom;
import Interfaz.login;
import Usuario.DatosFacturacion;
import Usuario.Envios;
import Usuario.Factura;
import Usuario.Guia;
import Usuario.Tarjeta;
import Usuario.Usuario;
import Usuario.ctrlEnvios;
import Usuario.ctrlUsuarios;
import static Usuario.ctrlUsuarios.getUsuarioIndice;
import java.awt.Color;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 *
 * @author mesoi
 */
public class UsuarioCotizacionCompra extends javax.swing.JPanel {

    public ArrayList<Departamentos> depa;
    public ButtonGroup size;
    public String sizePaquete;
    public static Guia guardarCotizacion;
    double total1;
    double total2;
    String tipoP;
    boolean realizoEnvio = false;
    private ArrayList<Tarjeta> tarj = new ArrayList<Tarjeta>();
    private Usuario user;
    private Color desactivado = new Color(34, 37, 47);
    private Color act = new Color(200, 187, 250);
    private Color textact = new Color(68, 68, 86);

    /**
     * Creates new form UsuarioCotizacionCompra
     */
    public UsuarioCotizacionCompra() {
        initComponents();
        this.setBounds(0, 0, 724, 520);
        depa = ctrlDepartamentos.getAllD();
        cargarCombos(boxDepartamentos);
        cargarCombos(boxDepartamentosD);
        size = new ButtonGroup();
        size.add(radioPeque);
        size.add(radioMediano);
        size.add(RadioGrande);
        sizePaquete = "Pequeño";

        ButtonGroup price = new ButtonGroup();
        price.add(radioEstandar);
        price.add(radioEspecial);
        ButtonGroup tipoPago = new ButtonGroup();
        tipoPago.add(radioContra);
        tipoPago.add(radioCuenta);
        //cargarDatosFacturacion();
        scroll.setVerticalScrollBar(new ScrollBarCustom());
        scroll.getVerticalScrollBar().setUnitIncrement(28);
        RadioGrande.setVisible(false);
        radioMediano.setVisible(false);
        radioPeque.setVisible(false);
        radioContra.setVisible(false);
        radioCuenta.setVisible(false);
        activarFondo1(panelPeque, lblP1, lblP2, lblP3);
        activarFondo2(panelEstandar, txtTotalEstandar, radioEstandar);
        activarFondo3(panelRound2, lblTitulo, lblDescripcion);
        setBordes();
        txtNombreTarjeta.setText("");
        txtNombreFacturacion.setText("");
    }

    public void test(Usuario user1) {
        this.user = user1;

        //System.out.println("Estoy en el usuario Cotizacion");
    }

    public void setBordes() {
        txtDireccionDestino.setBorder(login.unselectedborder);
        txtDireccionOrigen.setBorder(login.unselectedborder);
        txtNumeroPaquetes.setBorder(login.unselectedborder);
    }

    public void cargarDatosF() {
        Tarjeta tarItem = (Tarjeta) boxTarjetas.getSelectedItem();
        if (tarItem != null) {
            txtNombreTarjeta.setText(tarItem.getTarjetanombre());
        }

        DatosFacturacion tarItem1 = (DatosFacturacion) boxFacturacion.getSelectedItem();
        if (tarItem1 != null) {
            txtNombreFacturacion.setText(tarItem1.getNombreCompletoF());
        }

    }

    public void activarFondo1(JPanel activar, JLabel texto1, JLabel texto2, JLabel texto3) {
        panelPeque.setBackground(desactivado);
        panelGrande.setBackground(desactivado);
        panelMediano.setBackground(desactivado);
        lblP1.setForeground(Color.WHITE);
        lblP2.setForeground(Color.WHITE);
        lblP3.setForeground(Color.WHITE);
        lblM1.setForeground(Color.WHITE);
        lblM2.setForeground(Color.WHITE);
        lblM3.setForeground(Color.WHITE);
        lblG1.setForeground(Color.WHITE);
        lblG2.setForeground(Color.WHITE);
        lblG3.setForeground(Color.WHITE);
        activar.setBackground(act);
        texto1.setForeground(textact);
        texto2.setForeground(textact);
        texto3.setForeground(textact);
    }

    public void activarFondo2(JPanel activar, JLabel texto1, JRadioButton texto2) {
        panelEspecial.setBackground(desactivado);
        panelEstandar.setBackground(desactivado);
        txtTotalEstandar.setForeground(Color.WHITE);
        txtTotalEspecial.setForeground(Color.WHITE);
        radioEstandar.setForeground(Color.WHITE);
        radioEspecial.setForeground(Color.WHITE);
        activar.setBackground(act);
        texto1.setForeground(textact);
        texto2.setForeground(textact);
    }

    public void activarFondo3(JPanel activar, JLabel texto1, JLabel texto2) {
        panelRound3.setBackground(desactivado);
        panelRound2.setBackground(desactivado);
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo2.setForeground(Color.WHITE);
        lblDescripcion.setForeground(Color.WHITE);
        lblDesc2.setForeground(Color.WHITE);
        activar.setBackground(act);
        texto1.setForeground(textact);
        texto2.setForeground(textact);
    }

    public void cargarCombos(JComboBox caja) {
        caja.addItem(new Departamentos("", "", "", 0.0, 0.0, "", "Departamentos"));
        //boxDepartamentos.addItem("Departamento");
        for (int i = 0; i < depa.size(); i++) {
            if (depa.get(i) != null) {
                String codeR = depa.get(i).getCodigo();
                String nombreRegion = depa.get(i).getNombre();
                caja.addItem(new Departamentos(depa.get(i).getIdRegion(), codeR, nombreRegion, depa.get(i).getPrecioEstandar(), depa.get(i).getPrecioEspecial(), depa.get(i).getCodDepartamento(), depa.get(i).getNombreDepartamento()));
            }
        }
    }

    public void cargarMunicipios(String codDepartamento, JComboBox actualizar) {
        //boxMunicipios.addItem("Municipios");
        actualizar.addItem(new Municipios("", "Municipios", ""));
        ArrayList<Municipios> muni = ctrlDepartamentos.getMuniDepartamento(codDepartamento);
        for (int i = 0; i < muni.size(); i++) {
            if (muni.get(i) != null) {
                String codeR = muni.get(i).getCodigoMunicipio();
                String nombreMuni = muni.get(i).getNombreMunicipio();
                actualizar.addItem(new Municipios(codDepartamento, nombreMuni, codeR));

            }
        }
    }

    public void limpiarBoxes(JComboBox eliminar) {
        eliminar.removeAllItems();
    }

    public void limpiarTxtCotizacion() {
        txtDireccionOrigen.setText("");
        txtDireccionDestino.setText("");
        txtNumeroPaquetes.setText("");
        boxDepartamentos.setSelectedIndex(0);
        boxDepartamentosD.setSelectedIndex(0);
        boxMunicipios.setSelectedIndex(0);
        boxMunicipiosD.setSelectedIndex(0);
        txtTotalEstandar.setText("Total 0.00");
        txtTotalEspecial.setText("Total 0.00");
    }

    public void saveCotizacion() {
        System.out.println(boxMunicipios.getSelectedIndex());
        //^\d+$
        Pattern vCorreo = Pattern.compile("^\\d+$");
        Matcher m = vCorreo.matcher(txtNumeroPaquetes.getText());
        if (!m.find()) {
            JOptionPane.showMessageDialog(null, "El numero de paquetes no es válido");
            return;
        }
        if (!txtNumeroPaquetes.getText().equals("") && !txtDireccionDestino.getText().equals("") && !txtDireccionOrigen.getText().equals("") && boxDepartamentos.getSelectedIndex() != 0
                && boxMunicipios.getSelectedIndex() != 0 && boxDepartamentosD.getSelectedIndex() != 0 && boxMunicipiosD.getSelectedIndex() != 0) {
            Departamentos depItemO = (Departamentos) boxDepartamentos.getSelectedItem();
            Municipios munItemO = (Municipios) boxMunicipios.getSelectedItem();

            Departamentos depItemD = (Departamentos) boxDepartamentosD.getSelectedItem();
            Municipios munItemD = (Municipios) boxMunicipiosD.getSelectedItem();

            int numeroPaquetes = Integer.parseInt(txtNumeroPaquetes.getText());
            //String sizePaquete = size
            String nombreRegionOrigen = ctrlRegiones.getRegionCodigo(depItemO.getIdRegion()).getNombre();
            String nombreRegionDestino = ctrlRegiones.getRegionCodigo(depItemO.getIdRegion()).getNombre();
            String origen = depItemO.getNombreDepartamento() + "," + munItemO.getNombreMunicipio() + "," + txtDireccionOrigen.getText() + "," + nombreRegionOrigen;
            String destino = depItemD.getNombreDepartamento() + "," + munItemD.getNombreMunicipio() + "," + txtDireccionDestino.getText() + "," + nombreRegionDestino;

            guardarCotizacion = new Guia(origen, destino, sizePaquete, numeroPaquetes);
            double multi1 = ctrlRegiones.getMultiplicador(depItemD.getCodigo(), 0);
            double multi2 = ctrlRegiones.getMultiplicador(depItemD.getCodigo(), 1);
            System.out.println(multi1 + "---" + multi2);
            double tamano1 = 0;
            if (radioPeque.isSelected()) {
                tamano1 = 1.4;
            } else if (radioMediano.isSelected()) {
                tamano1 = 1.7;
            } else if (RadioGrande.isSelected()) {
                tamano1 = 2;
            }

            total1 = multi1 * tamano1 * numeroPaquetes;
            total2 = multi2 * tamano1 * numeroPaquetes;
            BigDecimal bd = new BigDecimal(total1).setScale(2, RoundingMode.HALF_UP);
            total1 = bd.doubleValue();
            BigDecimal bd2 = new BigDecimal(total2).setScale(2, RoundingMode.HALF_UP);
            total2 = bd2.doubleValue();
            txtTotalEstandar.setText("Total " + total1);
            txtTotalEspecial.setText("Total " + total2);

            JOptionPane.showMessageDialog(null, "Cotización exitosa");
        } else {
            JOptionPane.showMessageDialog(null, "Llene todos los datos");
        }

    }

    public void listarTarjetas() {
        ArrayList<Tarjeta> tarjeta = ctrlUsuarios.getAllTarjetas(user.getCorreo());
        limpiarBoxes(boxTarjetas);
        for (int i = 0; i < tarjeta.size(); i++) {
            if (tarjeta.get(i) != null) {
                String tarjetaNombre = tarjeta.get(i).getTarjetanombre();
                String tarjetaNumero = tarjeta.get(i).getTarjetaNumero();
                String tarjetaVencimiento = tarjeta.get(i).getTarjetaVencimiento();

                boxTarjetas.addItem(new Tarjeta(tarjetaNombre, tarjetaNumero, tarjetaVencimiento));
            }

        }
    }

    public void cargarDatosFacturacion() {
        limpiarBoxes(boxFacturacion);
        ArrayList<DatosFacturacion> Data = ctrlUsuarios.getAllDFacturacion(user.getCorreo());
        System.out.println(Data.size());
        for (int i = 0; i < Data.size(); i++) {
            if (Data.get(i) != null) {
                String nombreC = Data.get(i).getNombreCompletoF();
                String direccion = Data.get(i).getDireccionF();
                String nit = Data.get(i).getNit();
                boxFacturacion.addItem(new DatosFacturacion(nombreC, direccion, nit));
            }

        }
    }

    public void registrarEnvios() {
        String precio = "";
        String[] origenDatos = guardarCotizacion.getOrigen().split(",");
        String codigoRegioOringe = origenDatos[0];
        DatosFacturacion facturaItem = (DatosFacturacion) boxFacturacion.getSelectedItem();
        String tPago = "";
        if (facturaItem == null) {
            JOptionPane.showMessageDialog(null, "No se han registrado datos de Facturación");
            return;
        }

        if (radioContra.isSelected()) {
            tPago = "contra entrega";
            total1 += 5;
            total2 += 5;
        } else if (radioCuenta.isSelected()) {
            tPago = "con tarjeta";
            String cvv;
            boolean val = false;
            while (!val) {
                cvv = JOptionPane.showInputDialog(null, "Ingrese el CVV");
                if (cvv != null) {
                    val = validarCVV(cvv);
                } else {
                    return;
                }
            }
        }
        Usuario actual = getUsuarioIndice(login.posicionU);
        String nombre = actual.getNombre() + " " + actual.getApellido();

        if (radioEstandar.isSelected()) {
            areaDetalles.setText("Servicio Especial \n" + "Total: " + total1);
            precio = "Estandar";

            if (ctrlEnvios.agregarEnvio(login.credenciales.getIdUsuario(), nombre, origenDatos[3], precio, guardarCotizacion.getDestino(), total1, tPago, guardarCotizacion.getOrigen(), facturaItem.getNit(), guardarCotizacion.getNumeropaquetes(), guardarCotizacion.getTamanoPaquete())) {
                JOptionPane.showMessageDialog(null, "La compra ha sido registrada Exitosamente");
                realizoEnvio = true;
                total1 = 0;
                total2 = 0;
            }
        } else if (radioEspecial.isSelected()) {
            areaDetalles.setText("Servicio Especial \n" + "Total: " + total2);
            precio = "Especial";
            if (ctrlEnvios.agregarEnvio(login.credenciales.getIdUsuario(), nombre, origenDatos[3], precio, guardarCotizacion.getDestino(), total2, tPago, guardarCotizacion.getOrigen(), facturaItem.getNit(), guardarCotizacion.getNumeropaquetes(), guardarCotizacion.getTamanoPaquete())) {
                limpiarTxtCotizacion();
                JOptionPane.showMessageDialog(null, "La compra ha sido registrada Exitosamente");
                realizoEnvio = true;
                total1 = 0;
                total2 = 0;
            }
        }

    }

    public void descargarFactura() {
        Envios envios = ctrlEnvios.verUltimoEnvios();

        JFileChooser guardarComo = new JFileChooser();
        int userSelection = 0;
        try {
            guardarComo.setDialogTitle("Guardar archivo");
            userSelection = guardarComo.showSaveDialog(null);
        } catch (Exception e) {
            System.out.print(e);
        }
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File directorio = new File(guardarComo.getSelectedFile().toString());
            if (!directorio.isFile() && !directorio.isDirectory()) {
                String htmFilePath = "/htmls/factura.html";
                InputStream inputStream = getClass().getResourceAsStream(htmFilePath);
                //File input = new File("src\\htmls\\factura.html");

                String htmlContent = "";
                try {
                    htmlContent = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
                } catch (IOException ex) {
                    Logger.getLogger(UsuarioVerEnvios.class.getName()).log(Level.SEVERE, null, ex);
                }

                Document doc = Jsoup.parse(htmlContent);
                Element codigoPaquete = doc.getElementById("codigoPaquete");
                Element NumeroFacura = doc.getElementById("fecha");
                Element dOrigen = doc.getElementById("dOrigen");
                Element dDireccionOrigen = doc.getElementById("dDireccionOrigen");
                Element dDestino = doc.getElementById("dDestino");
                Element dDireccionDestino = doc.getElementById("dDireccionDestino");
                Element dNumeroPaquetes = doc.getElementById("dNumeroPaquetes");
                Element dTipoPago = doc.getElementById("dTipoPago");
                Element dSize = doc.getElementById("dSize");
                Element dNumero = doc.getElementById("dNumero");
                Element dTotal = doc.getElementById("dTotal");
                Factura factura = envios.getFactura();

                codigoPaquete.text("#" + factura.getNumFactura());
                NumeroFacura.text(factura.getCodPaquete());

                String[] datosO = factura.getOrigen().split(",");

                dOrigen.text(datosO[0] + "," + datosO[1]);
                dDireccionOrigen.text(datosO[2]);

                String[] datosD = factura.getDestino().split(",");

                dDestino.text(datosD[0] + "," + datosD[1]);
                dDireccionDestino.text(datosD[2]);
                dNumeroPaquetes.text(factura.getNumeropaquetes() + "");
                dTipoPago.text(factura.getTipoPago());
                dSize.text(envios.getGuia().getTamanoPaquete() + "");
                dNumero.text(factura.getNumeropaquetes() + "");
                dTotal.text(factura.getTotal() + "");

                String htmlModificado = doc.outerHtml();
                FileWriter writer;

                try {
                    writer = new FileWriter(guardarComo.getSelectedFile() + ".html");
                    writer.write(htmlModificado);
                    JOptionPane.showMessageDialog(null, "Archivo Guardado Correctamente");
                    writer.close();
                } catch (IOException ex) {
                    Logger.getLogger(UsuarioVerEnvios.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Ese nombre ya existe en la carpeta");
            }
        }
        //System.out.println(guardarComo.getSelectedFile().getName());
    }

    public void descargarGuia() {
        Envios envios = ctrlEnvios.verUltimoEnvios();

        JFileChooser guardarComo = new JFileChooser();
        int userSelection = 0;
        try {
            guardarComo.setDialogTitle("Guardar archivo");
            userSelection = guardarComo.showSaveDialog(null);
        } catch (Exception e) {
            System.out.print(e);
        }
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File directorio = new File(guardarComo.getSelectedFile().toString());
            if (!directorio.isFile() && !directorio.isDirectory()) {
                String htmFilePath = "/htmls/guia.html";
                InputStream inputStream = getClass().getResourceAsStream(htmFilePath);
                //File input = new File("src\\htmls\\factura.html");

                String htmlContent = "";
                try {
                    htmlContent = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
                } catch (IOException ex) {
                    Logger.getLogger(UsuarioVerEnvios.class.getName()).log(Level.SEVERE, null, ex);
                }

                Document doc = Jsoup.parse(htmlContent);

                Element codigoPaquete = doc.getElementById("codigoPaquete");
                Element fecha = doc.getElementById("fecha");
                Element dOrigen = doc.getElementById("dOrigen");
                Element dDireccionOrigen = doc.getElementById("dDireccionOrigen");
                Element dDestino = doc.getElementById("dDestino");
                Element dDireccionDestino = doc.getElementById("dDireccionDestino");
                Element dNumeroPaquetes = doc.getElementById("dNumeroPaquetes");
                Element dTipoPago = doc.getElementById("dTipoPago");
                Element dTamano = doc.getElementById("dTamano");
                Element dTarjeta = doc.getElementById("dTarjeta");
                Element dTituloTarjeta = doc.getElementById("dTituloTarjeta");
                Element dTotal = doc.getElementById("dTotal");

                Factura factura = envios.getFactura();

                codigoPaquete.text("#" + factura.getNumFactura());
                fecha.text(envios.getGuia().getFechaEnvio());

                String[] datosO = factura.getOrigen().split(",");
                dOrigen.text(datosO[0] + "," + datosO[1]);
                dDireccionOrigen.text(datosO[2]);
                String[] datosD = factura.getDestino().split(",");
                dDestino.text(datosD[0] + "," + datosD[1]);
                dDireccionDestino.text(datosD[2]);
                dNumeroPaquetes.text(factura.getNumeropaquetes() + "");
                dTipoPago.text(factura.getTipoPago());
                dTamano.text(envios.getGuia().getTamanoPaquete() + "");
                dTarjeta.text("");
                dTituloTarjeta.text("");
                dTotal.text(factura.getTotal() + "");

                String htmlModificado = doc.outerHtml();
                FileWriter writer;

                try {
                    writer = new FileWriter(guardarComo.getSelectedFile() + ".html");
                    writer.write(htmlModificado);
                    JOptionPane.showMessageDialog(null, "Archivo Guardado Correctamente");
                    writer.close();
                } catch (IOException ex) {
                    Logger.getLogger(UsuarioVerEnvios.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Ese nombre ya existe en la carpeta");
            }
        }
    }

    public boolean validarCVV(String cvv) {
        return cvv.matches("[0-9]*") && cvv.length() == 3;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scroll = new javax.swing.JScrollPane();
        jPanel21 = new javax.swing.JPanel();
        panelRound4 = new Elementos.PanelRound();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        panelRound1 = new Elementos.PanelRound();
        boxDepartamentos = new javax.swing.JComboBox<>();
        boxMunicipios = new javax.swing.JComboBox<>();
        txtDireccionOrigen = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        panelRound11 = new Elementos.PanelRound();
        boxDepartamentosD = new javax.swing.JComboBox<>();
        boxMunicipiosD = new javax.swing.JComboBox<>();
        txtDireccionDestino = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNumeroPaquetes = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        panelPeque = new Elementos.PanelRound();
        lblP1 = new javax.swing.JLabel();
        lblP3 = new javax.swing.JLabel();
        radioPeque = new javax.swing.JRadioButton();
        lblP2 = new javax.swing.JLabel();
        panelEstandar = new Elementos.PanelRound();
        radioEstandar = new javax.swing.JRadioButton();
        txtTotalEstandar = new javax.swing.JLabel();
        panelEspecial = new Elementos.PanelRound();
        radioEspecial = new javax.swing.JRadioButton();
        txtTotalEspecial = new javax.swing.JLabel();
        panelMediano = new Elementos.PanelRound();
        lblM1 = new javax.swing.JLabel();
        lblM3 = new javax.swing.JLabel();
        radioMediano = new javax.swing.JRadioButton();
        lblM2 = new javax.swing.JLabel();
        panelGrande = new Elementos.PanelRound();
        lblG1 = new javax.swing.JLabel();
        lblG2 = new javax.swing.JLabel();
        RadioGrande = new javax.swing.JRadioButton();
        lblG3 = new javax.swing.JLabel();
        btnCotizar = new Elementos.ButtonRound();
        jLabel7 = new javax.swing.JLabel();
        btnGuardarCotizacion = new Elementos.ButtonRound();
        panelRound5 = new Elementos.PanelRound();
        panel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        panelRound9 = new Elementos.PanelRound();
        boxTarjetas = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNombreTarjeta = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        areaDetalles = new javax.swing.JTextArea();
        btnDGuia = new Elementos.ButtonRound();
        jLabel17 = new javax.swing.JLabel();
        panelRound2 = new Elementos.PanelRound();
        radioContra = new javax.swing.JRadioButton();
        lblTitulo = new javax.swing.JLabel();
        lblDescripcion = new javax.swing.JLabel();
        panelRound3 = new Elementos.PanelRound();
        radioCuenta = new javax.swing.JRadioButton();
        lblTitulo2 = new javax.swing.JLabel();
        lblDesc2 = new javax.swing.JLabel();
        btnrRealizar = new Elementos.ButtonRound();
        btnFactura = new Elementos.ButtonRound();
        panelRound12 = new Elementos.PanelRound();
        jLabel12 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtNombreFacturacion = new javax.swing.JLabel();
        boxFacturacion = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(19, 19, 26));

        scroll.setBorder(null);
        scroll.setOpaque(false);

        jPanel21.setBackground(new java.awt.Color(19, 19, 26));

        panelRound4.setBackground(new java.awt.Color(28, 28, 36));
        panelRound4.setRoundBottomLeft(15);
        panelRound4.setRoundBottomRight(15);
        panelRound4.setRoundTopLeft(15);
        panelRound4.setRoundTopRight(15);

        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(506, 532));

        jLabel1.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Cotización");

        jPanel3.setOpaque(false);

        panelRound1.setBackground(new java.awt.Color(19, 19, 26));
        panelRound1.setRoundBottomLeft(16);
        panelRound1.setRoundBottomRight(16);
        panelRound1.setRoundTopLeft(16);
        panelRound1.setRoundTopRight(16);

        boxDepartamentos.setBackground(new java.awt.Color(40, 41, 52));
        boxDepartamentos.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        boxDepartamentos.setForeground(new java.awt.Color(255, 255, 255));
        boxDepartamentos.setBorder(null);
        boxDepartamentos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                boxDepartamentosItemStateChanged(evt);
            }
        });
        boxDepartamentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxDepartamentosActionPerformed(evt);
            }
        });

        boxMunicipios.setBackground(new java.awt.Color(40, 41, 52));
        boxMunicipios.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        boxMunicipios.setForeground(new java.awt.Color(255, 255, 255));
        boxMunicipios.setBorder(null);

        txtDireccionOrigen.setBackground(new java.awt.Color(40, 41, 52));
        txtDireccionOrigen.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        txtDireccionOrigen.setForeground(new java.awt.Color(255, 255, 255));
        txtDireccionOrigen.setBorder(null);
        txtDireccionOrigen.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDireccionOrigenFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDireccionOrigenFocusLost(evt);
            }
        });

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtDireccionOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(boxDepartamentos, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(boxMunicipios, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(boxDepartamentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(boxMunicipios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtDireccionOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Origen");

        panelRound11.setBackground(new java.awt.Color(19, 19, 26));
        panelRound11.setRoundBottomLeft(16);
        panelRound11.setRoundBottomRight(16);
        panelRound11.setRoundTopLeft(16);
        panelRound11.setRoundTopRight(16);

        boxDepartamentosD.setBackground(new java.awt.Color(40, 41, 52));
        boxDepartamentosD.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        boxDepartamentosD.setForeground(new java.awt.Color(255, 255, 255));
        boxDepartamentosD.setBorder(null);
        boxDepartamentosD.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                boxDepartamentosDItemStateChanged(evt);
            }
        });

        boxMunicipiosD.setBackground(new java.awt.Color(40, 41, 52));
        boxMunicipiosD.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        boxMunicipiosD.setForeground(new java.awt.Color(255, 255, 255));
        boxMunicipiosD.setBorder(null);

        txtDireccionDestino.setBackground(new java.awt.Color(40, 41, 52));
        txtDireccionDestino.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        txtDireccionDestino.setForeground(new java.awt.Color(255, 255, 255));
        txtDireccionDestino.setBorder(null);
        txtDireccionDestino.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDireccionDestinoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDireccionDestinoFocusLost(evt);
            }
        });

        javax.swing.GroupLayout panelRound11Layout = new javax.swing.GroupLayout(panelRound11);
        panelRound11.setLayout(panelRound11Layout);
        panelRound11Layout.setHorizontalGroup(
            panelRound11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound11Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(panelRound11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtDireccionDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(boxMunicipiosD, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(boxDepartamentosD, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        panelRound11Layout.setVerticalGroup(
            panelRound11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound11Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(boxDepartamentosD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(boxMunicipiosD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(txtDireccionDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel13.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Destino");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelRound11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelRound11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jLabel5.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Numero de Paquetes");

        txtNumeroPaquetes.setBackground(new java.awt.Color(40, 41, 52));
        txtNumeroPaquetes.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        txtNumeroPaquetes.setForeground(new java.awt.Color(255, 255, 255));
        txtNumeroPaquetes.setBorder(null);
        txtNumeroPaquetes.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNumeroPaquetesFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNumeroPaquetesFocusLost(evt);
            }
        });
        txtNumeroPaquetes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNumeroPaquetesKeyPressed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Tamaño de paquetes");

        panelPeque.setBackground(new java.awt.Color(168, 149, 244));
        panelPeque.setRoundBottomLeft(20);
        panelPeque.setRoundBottomRight(20);
        panelPeque.setRoundTopLeft(20);
        panelPeque.setRoundTopRight(20);
        panelPeque.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelPequeMouseClicked(evt);
            }
        });
        panelPeque.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblP1.setBackground(new java.awt.Color(40, 41, 52));
        lblP1.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        lblP1.setForeground(new java.awt.Color(255, 255, 255));
        lblP1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP1.setText("Pequeño");
        panelPeque.add(lblP1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 120, -1));

        lblP3.setBackground(new java.awt.Color(40, 41, 52));
        lblP3.setFont(new java.awt.Font("Montserrat", 3, 13)); // NOI18N
        lblP3.setForeground(new java.awt.Color(255, 255, 255));
        lblP3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP3.setText("* 1.4");
        lblP3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        panelPeque.add(lblP3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 120, -1));

        radioPeque.setSelected(true);
        radioPeque.setOpaque(false);
        radioPeque.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                radioPequeItemStateChanged(evt);
            }
        });
        panelPeque.add(radioPeque, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        lblP2.setBackground(new java.awt.Color(40, 41, 52));
        lblP2.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        lblP2.setForeground(new java.awt.Color(255, 255, 255));
        lblP2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP2.setText("1lb - 10lb");
        lblP2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        panelPeque.add(lblP2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 120, -1));

        panelEstandar.setBackground(new java.awt.Color(168, 149, 244));
        panelEstandar.setRoundBottomLeft(18);
        panelEstandar.setRoundBottomRight(18);
        panelEstandar.setRoundTopLeft(18);
        panelEstandar.setRoundTopRight(18);
        panelEstandar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelEstandarMouseClicked(evt);
            }
        });

        radioEstandar.setBackground(new java.awt.Color(67, 63, 71));
        radioEstandar.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        radioEstandar.setForeground(new java.awt.Color(255, 255, 255));
        radioEstandar.setSelected(true);
        radioEstandar.setText("Servicio Estandar");
        radioEstandar.setOpaque(false);
        radioEstandar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioEstandarMouseClicked(evt);
            }
        });

        txtTotalEstandar.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        txtTotalEstandar.setForeground(new java.awt.Color(255, 255, 255));
        txtTotalEstandar.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtTotalEstandar.setText("Total 00.00");

        javax.swing.GroupLayout panelEstandarLayout = new javax.swing.GroupLayout(panelEstandar);
        panelEstandar.setLayout(panelEstandarLayout);
        panelEstandarLayout.setHorizontalGroup(
            panelEstandarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEstandarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(radioEstandar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 297, Short.MAX_VALUE)
                .addComponent(txtTotalEstandar, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        panelEstandarLayout.setVerticalGroup(
            panelEstandarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEstandarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelEstandarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioEstandar)
                    .addComponent(txtTotalEstandar))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        panelEspecial.setBackground(new java.awt.Color(34, 37, 47));
        panelEspecial.setRoundBottomLeft(18);
        panelEspecial.setRoundBottomRight(18);
        panelEspecial.setRoundTopLeft(18);
        panelEspecial.setRoundTopRight(18);
        panelEspecial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelEspecialMouseClicked(evt);
            }
        });

        radioEspecial.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        radioEspecial.setForeground(new java.awt.Color(255, 255, 255));
        radioEspecial.setText("Servicio Especial");
        radioEspecial.setOpaque(false);
        radioEspecial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioEspecialMouseClicked(evt);
            }
        });

        txtTotalEspecial.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        txtTotalEspecial.setForeground(new java.awt.Color(255, 255, 255));
        txtTotalEspecial.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtTotalEspecial.setText("Total 00.00");

        javax.swing.GroupLayout panelEspecialLayout = new javax.swing.GroupLayout(panelEspecial);
        panelEspecial.setLayout(panelEspecialLayout);
        panelEspecialLayout.setHorizontalGroup(
            panelEspecialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEspecialLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(radioEspecial)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtTotalEspecial, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        panelEspecialLayout.setVerticalGroup(
            panelEspecialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEspecialLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelEspecialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioEspecial)
                    .addComponent(txtTotalEspecial))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        panelMediano.setBackground(new java.awt.Color(34, 37, 47));
        panelMediano.setRoundBottomLeft(20);
        panelMediano.setRoundBottomRight(20);
        panelMediano.setRoundTopLeft(20);
        panelMediano.setRoundTopRight(20);
        panelMediano.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelMedianoMouseClicked(evt);
            }
        });
        panelMediano.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblM1.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        lblM1.setForeground(new java.awt.Color(255, 255, 255));
        lblM1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblM1.setText("Mediano");
        panelMediano.add(lblM1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 120, -1));

        lblM3.setFont(new java.awt.Font("Montserrat", 3, 13)); // NOI18N
        lblM3.setForeground(new java.awt.Color(255, 255, 255));
        lblM3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblM3.setText("* 1.7");
        panelMediano.add(lblM3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 120, -1));

        radioMediano.setOpaque(false);
        radioMediano.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                radioMedianoItemStateChanged(evt);
            }
        });
        panelMediano.add(radioMediano, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        lblM2.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        lblM2.setForeground(new java.awt.Color(255, 255, 255));
        lblM2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblM2.setText("1lb - 10lb");
        panelMediano.add(lblM2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 120, -1));

        panelGrande.setBackground(new java.awt.Color(34, 37, 47));
        panelGrande.setRoundBottomLeft(20);
        panelGrande.setRoundBottomRight(20);
        panelGrande.setRoundTopLeft(20);
        panelGrande.setRoundTopRight(20);
        panelGrande.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelGrandeMouseClicked(evt);
            }
        });
        panelGrande.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblG1.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        lblG1.setForeground(new java.awt.Color(255, 255, 255));
        lblG1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblG1.setText("Grande");
        panelGrande.add(lblG1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 120, -1));

        lblG2.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        lblG2.setForeground(new java.awt.Color(255, 255, 255));
        lblG2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblG2.setText("1lb - 10lb");
        panelGrande.add(lblG2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 120, -1));

        RadioGrande.setOpaque(false);
        RadioGrande.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                RadioGrandeItemStateChanged(evt);
            }
        });
        panelGrande.add(RadioGrande, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        lblG3.setFont(new java.awt.Font("Montserrat", 3, 13)); // NOI18N
        lblG3.setForeground(new java.awt.Color(255, 255, 255));
        lblG3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblG3.setText("* 2");
        panelGrande.add(lblG3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 120, -1));

        btnCotizar.setBorder(null);
        btnCotizar.setForeground(new java.awt.Color(255, 255, 255));
        btnCotizar.setText("Cotizar");
        btnCotizar.setBorderColor(new java.awt.Color(123, 127, 239));
        btnCotizar.setColor(new java.awt.Color(123, 127, 239));
        btnCotizar.setColorClick(new java.awt.Color(121, 118, 236));
        btnCotizar.setColorOver(new java.awt.Color(121, 147, 251));
        btnCotizar.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        btnCotizar.setRadius(15);
        btnCotizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCotizarActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("jLabel7");

        btnGuardarCotizacion.setBorder(null);
        btnGuardarCotizacion.setForeground(new java.awt.Color(96, 77, 182));
        btnGuardarCotizacion.setText("Descargar Cotización");
        btnGuardarCotizacion.setBorderColor(new java.awt.Color(197, 185, 248));
        btnGuardarCotizacion.setColor(new java.awt.Color(197, 185, 248));
        btnGuardarCotizacion.setColorClick(new java.awt.Color(182, 170, 246));
        btnGuardarCotizacion.setColorOver(new java.awt.Color(201, 190, 255));
        btnGuardarCotizacion.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        btnGuardarCotizacion.setRadius(15);
        btnGuardarCotizacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarCotizacionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 21, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(panelEspecial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelEstandar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(txtNumeroPaquetes, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(102, 102, 102)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(panelPeque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(37, 37, 37)
                                        .addComponent(panelMediano, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(31, 31, 31)
                                        .addComponent(panelGrande, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(btnCotizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnGuardarCotizacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(249, 249, 249)
                                .addComponent(jLabel6))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(txtNumeroPaquetes))
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelPeque, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                    .addComponent(panelMediano, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelGrande, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(21, 21, 21)
                .addComponent(btnCotizar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnGuardarCotizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 23, Short.MAX_VALUE)
                .addComponent(panelEstandar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelEspecial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout panelRound4Layout = new javax.swing.GroupLayout(panelRound4);
        panelRound4.setLayout(panelRound4Layout);
        panelRound4Layout.setHorizontalGroup(
            panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRound4Layout.setVerticalGroup(
            panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 675, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelRound5.setBackground(new java.awt.Color(28, 28, 36));
        panelRound5.setRoundBottomLeft(15);
        panelRound5.setRoundBottomRight(15);
        panelRound5.setRoundTopLeft(15);
        panelRound5.setRoundTopRight(15);

        panel2.setBackground(new java.awt.Color(34, 34, 43));
        panel2.setOpaque(false);

        jLabel2.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Compra");

        panelRound9.setBackground(new java.awt.Color(19, 19, 26));
        panelRound9.setRoundBottomLeft(15);
        panelRound9.setRoundBottomRight(15);
        panelRound9.setRoundTopLeft(15);
        panelRound9.setRoundTopRight(15);

        boxTarjetas.setBackground(new java.awt.Color(40, 41, 52));
        boxTarjetas.setForeground(new java.awt.Color(255, 255, 255));
        boxTarjetas.setEnabled(false);
        boxTarjetas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                boxTarjetasItemStateChanged(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Seleccione la tarjeta");

        jLabel4.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nombre de la Tarjeta: ");

        txtNombreTarjeta.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        txtNombreTarjeta.setForeground(new java.awt.Color(255, 255, 255));
        txtNombreTarjeta.setText("jLabel7");

        javax.swing.GroupLayout panelRound9Layout = new javax.swing.GroupLayout(panelRound9);
        panelRound9.setLayout(panelRound9Layout);
        panelRound9Layout.setHorizontalGroup(
            panelRound9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound9Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(panelRound9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound9Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(txtNombreTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel11)
                    .addComponent(boxTarjetas, javax.swing.GroupLayout.PREFERRED_SIZE, 578, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRound9Layout.setVerticalGroup(
            panelRound9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound9Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(boxTarjetas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelRound9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtNombreTarjeta))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jScrollPane1.setBackground(new java.awt.Color(28, 28, 36));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(28, 28, 36)));

        areaDetalles.setEditable(false);
        areaDetalles.setBackground(new java.awt.Color(40, 41, 52));
        areaDetalles.setColumns(20);
        areaDetalles.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        areaDetalles.setForeground(new java.awt.Color(255, 255, 255));
        areaDetalles.setRows(5);
        areaDetalles.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(28, 28, 36)));
        areaDetalles.setCaretColor(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(areaDetalles);

        btnDGuia.setBorder(null);
        btnDGuia.setForeground(new java.awt.Color(51, 141, 79));
        btnDGuia.setText("Descargar Guía");
        btnDGuia.setBorderColor(new java.awt.Color(193, 231, 206));
        btnDGuia.setColor(new java.awt.Color(193, 231, 206));
        btnDGuia.setColorClick(new java.awt.Color(131, 218, 160));
        btnDGuia.setColorOver(new java.awt.Color(176, 220, 192));
        btnDGuia.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        btnDGuia.setRadius(15);
        btnDGuia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDGuiaActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Tipo de Pago");

        panelRound2.setBackground(new java.awt.Color(168, 149, 244));
        panelRound2.setRoundBottomLeft(18);
        panelRound2.setRoundBottomRight(18);
        panelRound2.setRoundTopLeft(18);
        panelRound2.setRoundTopRight(18);
        panelRound2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelRound2MouseClicked(evt);
            }
        });

        radioContra.setSelected(true);
        radioContra.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                radioContraItemStateChanged(evt);
            }
        });

        lblTitulo.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(19, 19, 26));
        lblTitulo.setText("Cobro contra entrega");

        lblDescripcion.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        lblDescripcion.setForeground(new java.awt.Color(19, 19, 26));
        lblDescripcion.setText("cobro adicional de Q5.00");

        javax.swing.GroupLayout panelRound2Layout = new javax.swing.GroupLayout(panelRound2);
        panelRound2.setLayout(panelRound2Layout);
        panelRound2Layout.setHorizontalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitulo)
                    .addComponent(lblDescripcion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
                .addComponent(radioContra)
                .addContainerGap())
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(radioContra)
                    .addGroup(panelRound2Layout.createSequentialGroup()
                        .addComponent(lblTitulo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDescripcion)))
                .addGap(12, 12, 12))
        );

        panelRound3.setBackground(new java.awt.Color(34, 37, 47));
        panelRound3.setRoundBottomLeft(18);
        panelRound3.setRoundBottomRight(18);
        panelRound3.setRoundTopLeft(18);
        panelRound3.setRoundTopRight(18);
        panelRound3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelRound3MouseClicked(evt);
            }
        });

        radioCuenta.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                radioCuentaItemStateChanged(evt);
            }
        });

        lblTitulo2.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        lblTitulo2.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo2.setText("Cobro a mi Cuenta");

        lblDesc2.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        lblDesc2.setForeground(new java.awt.Color(255, 255, 255));
        lblDesc2.setText("Pago con Tarjeta de credito/Debito");

        javax.swing.GroupLayout panelRound3Layout = new javax.swing.GroupLayout(panelRound3);
        panelRound3.setLayout(panelRound3Layout);
        panelRound3Layout.setHorizontalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound3Layout.createSequentialGroup()
                        .addComponent(lblDesc2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelRound3Layout.createSequentialGroup()
                        .addComponent(lblTitulo2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(radioCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17))))
        );
        panelRound3Layout.setVerticalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound3Layout.createSequentialGroup()
                        .addGap(0, 7, Short.MAX_VALUE)
                        .addComponent(lblTitulo2))
                    .addComponent(radioCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDesc2)
                .addGap(12, 12, 12))
        );

        btnrRealizar.setBorder(null);
        btnrRealizar.setForeground(new java.awt.Color(255, 255, 255));
        btnrRealizar.setText("Relizar Pago y Enviar");
        btnrRealizar.setBorderColor(new java.awt.Color(123, 127, 239));
        btnrRealizar.setColor(new java.awt.Color(123, 127, 239));
        btnrRealizar.setColorClick(new java.awt.Color(121, 118, 236));
        btnrRealizar.setColorOver(new java.awt.Color(121, 147, 251));
        btnrRealizar.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        btnrRealizar.setRadius(15);
        btnrRealizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrRealizarActionPerformed(evt);
            }
        });

        btnFactura.setBorder(null);
        btnFactura.setForeground(new java.awt.Color(38, 102, 222));
        btnFactura.setText("Descargar Factura");
        btnFactura.setBorderColor(new java.awt.Color(182, 231, 255));
        btnFactura.setColor(new java.awt.Color(182, 231, 255));
        btnFactura.setColorClick(new java.awt.Color(142, 211, 246));
        btnFactura.setColorOver(new java.awt.Color(169, 216, 238));
        btnFactura.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        btnFactura.setRadius(15);
        btnFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFacturaActionPerformed(evt);
            }
        });

        panelRound12.setBackground(new java.awt.Color(19, 19, 26));
        panelRound12.setRoundBottomLeft(15);
        panelRound12.setRoundBottomRight(15);
        panelRound12.setRoundTopLeft(15);
        panelRound12.setRoundTopRight(15);

        jLabel12.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Seleccione Datos de Facturacion");

        jLabel23.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel23.setText("Nombre:");

        txtNombreFacturacion.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        txtNombreFacturacion.setForeground(new java.awt.Color(255, 255, 255));
        txtNombreFacturacion.setText("jLabel7");

        boxFacturacion.setBackground(new java.awt.Color(40, 41, 52));
        boxFacturacion.setForeground(new java.awt.Color(255, 255, 255));
        boxFacturacion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                boxFacturacionItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout panelRound12Layout = new javax.swing.GroupLayout(panelRound12);
        panelRound12.setLayout(panelRound12Layout);
        panelRound12Layout.setHorizontalGroup(
            panelRound12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound12Layout.createSequentialGroup()
                .addGroup(panelRound12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound12Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtNombreFacturacion, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelRound12Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(panelRound12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(boxFacturacion, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        panelRound12Layout.setVerticalGroup(
            panelRound12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound12Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(boxFacturacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(panelRound12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(txtNombreFacturacion))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addGap(217, 217, 217)
                                .addComponent(btnrRealizar, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2)
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(panel2Layout.createSequentialGroup()
                                        .addComponent(panelRound2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(panelRound3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(panelRound12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jScrollPane1)
                                    .addComponent(panelRound9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(btnFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69)
                        .addComponent(btnDGuia, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelRound3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelRound2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(21, 21, 21)
                .addComponent(panelRound9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelRound12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnrRealizar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDGuia, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout panelRound5Layout = new javax.swing.GroupLayout(panelRound5);
        panelRound5.setLayout(panelRound5Layout);
        panelRound5Layout.setHorizontalGroup(
            panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRound5Layout.setVerticalGroup(
            panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelRound4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelRound5, javax.swing.GroupLayout.PREFERRED_SIZE, 672, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelRound4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 35, Short.MAX_VALUE)
                .addComponent(panelRound5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        scroll.setViewportView(jPanel21);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 707, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 513, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCotizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCotizarActionPerformed
        // TODO add your handling code here:
        saveCotizacion();
        //cargarDatosFacturacion();
    }//GEN-LAST:event_btnCotizarActionPerformed

    private void panelGrandeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelGrandeMouseClicked
        // TODO add your handling code here:
        activarFondo1(panelGrande, lblG1, lblG2, lblG3);
        RadioGrande.setSelected(true);
        sizePaquete = "Grande";
    }//GEN-LAST:event_panelGrandeMouseClicked

    private void RadioGrandeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_RadioGrandeItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_RadioGrandeItemStateChanged

    private void panelMedianoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMedianoMouseClicked
        // TODO add your handling code here:
        activarFondo1(panelMediano, lblM1, lblM2, lblM3);
        radioMediano.setSelected(true);
        sizePaquete = "Mediano";
    }//GEN-LAST:event_panelMedianoMouseClicked

    private void radioMedianoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_radioMedianoItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_radioMedianoItemStateChanged

    private void panelEspecialMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelEspecialMouseClicked
        // TODO add your handling code here:
        activarFondo2(panelEspecial, txtTotalEspecial, radioEspecial);
        radioEspecial.setSelected(true);
    }//GEN-LAST:event_panelEspecialMouseClicked

    private void panelEstandarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelEstandarMouseClicked
        // TODO add your handling code here:
        activarFondo2(panelEstandar, txtTotalEstandar, radioEstandar);
        radioEstandar.setSelected(true);
    }//GEN-LAST:event_panelEstandarMouseClicked

    private void panelPequeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelPequeMouseClicked
        // TODO add your handling code here:
        activarFondo1(panelPeque, lblP1, lblP2, lblP3);
        radioPeque.setSelected(true);
        sizePaquete = "Pequeño";
    }//GEN-LAST:event_panelPequeMouseClicked

    private void radioPequeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_radioPequeItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_radioPequeItemStateChanged

    private void boxDepartamentosDItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_boxDepartamentosDItemStateChanged
        // TODO add your handling code here:
        Departamentos depItem = (Departamentos) boxDepartamentosD.getSelectedItem();
        limpiarBoxes(boxMunicipiosD);
        cargarMunicipios(depItem.getCodDepartamento(), boxMunicipiosD);
    }//GEN-LAST:event_boxDepartamentosDItemStateChanged

    private void boxDepartamentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxDepartamentosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boxDepartamentosActionPerformed

    private void boxDepartamentosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_boxDepartamentosItemStateChanged
        // TODO add your handling code here:
        Departamentos depItem = (Departamentos) boxDepartamentos.getSelectedItem();
        limpiarBoxes(boxMunicipios);
        cargarMunicipios(depItem.getCodDepartamento(), boxMunicipios);
    }//GEN-LAST:event_boxDepartamentosItemStateChanged

    private void btnrRealizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrRealizarActionPerformed
        // TODO add your handling code here:
        if (guardarCotizacion != null) {
            registrarEnvios();
        } else {
            JOptionPane.showMessageDialog(null, "No ha cotizado");
        }
    }//GEN-LAST:event_btnrRealizarActionPerformed

    private void radioCuentaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_radioCuentaItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_radioCuentaItemStateChanged

    private void radioContraItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_radioContraItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_radioContraItemStateChanged

    private void boxTarjetasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_boxTarjetasItemStateChanged
        // TODO add your handling code here:
        Tarjeta tarItem = (Tarjeta) boxTarjetas.getSelectedItem();
        if (tarItem != null) {
            txtNombreTarjeta.setText(tarItem.getTarjetanombre());
        }
    }//GEN-LAST:event_boxTarjetasItemStateChanged

    private void boxFacturacionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_boxFacturacionItemStateChanged
        // TODO add your handling code here:
        DatosFacturacion tarItem = (DatosFacturacion) boxFacturacion.getSelectedItem();
        if (tarItem != null) {
            txtNombreFacturacion.setText(tarItem.getNombreCompletoF());
        }
    }//GEN-LAST:event_boxFacturacionItemStateChanged

    private void panelRound3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelRound3MouseClicked
        // TODO add your handling code here:
        activarFondo3(panelRound3, lblTitulo2, lblDesc2);
        radioCuenta.setSelected(true);
        boxTarjetas.setEnabled(true);
    }//GEN-LAST:event_panelRound3MouseClicked

    private void panelRound2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelRound2MouseClicked
        // TODO add your handling code here:
        activarFondo3(panelRound2, lblTitulo, lblDescripcion);
        radioContra.setSelected(true);
        boxTarjetas.setEnabled(false);
    }//GEN-LAST:event_panelRound2MouseClicked

    private void btnFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFacturaActionPerformed
        // TODO add your handling code here:
        if (realizoEnvio) {
            descargarFactura();
        } else {
            JOptionPane.showMessageDialog(null, "No se ha realizado ningun envio aún");
        }
    }//GEN-LAST:event_btnFacturaActionPerformed

    private void btnGuardarCotizacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarCotizacionActionPerformed
        // TODO add your handling code here:
        if (guardarCotizacion != null) {

            JFileChooser guardarComo = new JFileChooser();
            int userSelection = 0;

            try {
                guardarComo.setDialogTitle("Guardar archivo");
                userSelection = guardarComo.showSaveDialog(null);
            } catch (Exception e) {
                System.out.print(e);
            }

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File directorio = new File(guardarComo.getSelectedFile().toString());
                if (!directorio.isFile() && !directorio.isDirectory()) {

                }
            }
            File input = new File("src\\htmls\\cotizacion.html");
            Document doc = null;
            try {
                doc = Jsoup.parse(input, "UTF-8");
            } catch (IOException ex) {
                Logger.getLogger(UsuarioCotizacionCompra.class.getName()).log(Level.SEVERE, null, ex);
            }

            Element numPaq = doc.getElementById("dNumeroPaquetes");
            Element tamPaq = doc.getElementById("dTamano");
            Element precioEst = doc.getElementById("dPrecioEst");
            Element precioEspe = doc.getElementById("dPrecioEspe");

            Element dOrigen = doc.getElementById("dOrigen");
            Element dDireccionOrigen = doc.getElementById("dDireccionOrigen");
            Element dDestino = doc.getElementById("dDestino");
            Element dDireccionDestino = doc.getElementById("dDireccionDestino");

            String[] datosO = guardarCotizacion.getOrigen().split(",");
            dOrigen.text(datosO[0] + "," + datosO[1]);
            dDireccionOrigen.text(datosO[2]);
            String[] datosD = guardarCotizacion.getDestino().split(",");
            dDestino.text(datosD[0] + "," + datosD[1]);
            dDireccionDestino.text(datosD[2]);

            numPaq.text(guardarCotizacion.getNumeropaquetes() + "");
            tamPaq.text(guardarCotizacion.getTamanoPaquete());
            precioEst.text(total1 + "");
            precioEspe.text(total2 + " ");
            String htmlModificado = doc.outerHtml();
            FileWriter writer;
            try {
                writer = new FileWriter(guardarComo.getSelectedFile() + ".html");
                writer.write(htmlModificado);
                JOptionPane.showMessageDialog(null, "Archivo Guardado Correctamente");
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(UsuarioCotizacionCompra.class.getName()).log(Level.SEVERE, null, ex);
            }

        }else{
            JOptionPane.showMessageDialog(null, "No se ha cotizado");
        }

    }//GEN-LAST:event_btnGuardarCotizacionActionPerformed

    private void btnDGuiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDGuiaActionPerformed
        // TODO add your handling code here:
        if (realizoEnvio) {
            descargarGuia();
        } else {
            JOptionPane.showMessageDialog(null, "No se ha realizado ningun envio aún");
        }
    }//GEN-LAST:event_btnDGuiaActionPerformed

    private void radioEstandarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radioEstandarMouseClicked
        // TODO add your handling code here:
        radioEstandar.setSelected(true);
        activarFondo2(panelEstandar, txtTotalEstandar, radioEstandar);
    }//GEN-LAST:event_radioEstandarMouseClicked

    private void radioEspecialMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radioEspecialMouseClicked
        // TODO add your handling code here:
        activarFondo2(panelEspecial, txtTotalEspecial, radioEspecial);
        radioEspecial.setSelected(true);
        activarFondo2(panelEspecial, txtTotalEspecial, radioEspecial);
    }//GEN-LAST:event_radioEspecialMouseClicked

    private void txtNumeroPaquetesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroPaquetesKeyPressed
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            txtNumeroPaquetes.setEditable(false);
            txtNumeroPaquetes.setBorder(login.errorBorde);
            jLabel7.setText("Solo numeros");
        } else {
            txtNumeroPaquetes.setBorder(login.selectedborder);
            txtNumeroPaquetes.setEditable(true);
            jLabel7.setText("");
        }
    }//GEN-LAST:event_txtNumeroPaquetesKeyPressed

    private void txtDireccionOrigenFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDireccionOrigenFocusGained
        // TODO add your handling code here:
        selected(txtDireccionOrigen, 1);
    }//GEN-LAST:event_txtDireccionOrigenFocusGained

    private void txtDireccionDestinoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDireccionDestinoFocusGained
        // TODO add your handling code here:
        selected(txtDireccionDestino, 1);
    }//GEN-LAST:event_txtDireccionDestinoFocusGained

    private void txtNumeroPaquetesFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNumeroPaquetesFocusGained
        // TODO add your handling code here:
        selected(txtNumeroPaquetes, 1);
    }//GEN-LAST:event_txtNumeroPaquetesFocusGained

    private void txtDireccionOrigenFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDireccionOrigenFocusLost
        // TODO add your handling code here:
        selected(txtDireccionOrigen, 0);
    }//GEN-LAST:event_txtDireccionOrigenFocusLost

    private void txtDireccionDestinoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDireccionDestinoFocusLost
        // TODO add your handling code here:
        selected(txtDireccionDestino, 0);
    }//GEN-LAST:event_txtDireccionDestinoFocusLost

    private void txtNumeroPaquetesFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNumeroPaquetesFocusLost
        // TODO add your handling code here:
        selected(txtNumeroPaquetes, 0);
    }//GEN-LAST:event_txtNumeroPaquetesFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton RadioGrande;
    private javax.swing.JTextArea areaDetalles;
    private javax.swing.JComboBox<Object> boxDepartamentos;
    private javax.swing.JComboBox<Object> boxDepartamentosD;
    private javax.swing.JComboBox<Object> boxFacturacion;
    private javax.swing.JComboBox<Object> boxMunicipios;
    private javax.swing.JComboBox<Object> boxMunicipiosD;
    private javax.swing.JComboBox<Object> boxTarjetas;
    private Elementos.ButtonRound btnCotizar;
    private Elementos.ButtonRound btnDGuia;
    private Elementos.ButtonRound btnFactura;
    private Elementos.ButtonRound btnGuardarCotizacion;
    private Elementos.ButtonRound btnrRealizar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDesc2;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblG1;
    private javax.swing.JLabel lblG2;
    private javax.swing.JLabel lblG3;
    private javax.swing.JLabel lblM1;
    private javax.swing.JLabel lblM2;
    private javax.swing.JLabel lblM3;
    private javax.swing.JLabel lblP1;
    private javax.swing.JLabel lblP2;
    private javax.swing.JLabel lblP3;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTitulo2;
    private javax.swing.JPanel panel2;
    private Elementos.PanelRound panelEspecial;
    private Elementos.PanelRound panelEstandar;
    private Elementos.PanelRound panelGrande;
    private Elementos.PanelRound panelMediano;
    private Elementos.PanelRound panelPeque;
    private Elementos.PanelRound panelRound1;
    private Elementos.PanelRound panelRound11;
    private Elementos.PanelRound panelRound12;
    private Elementos.PanelRound panelRound2;
    private Elementos.PanelRound panelRound3;
    private Elementos.PanelRound panelRound4;
    private Elementos.PanelRound panelRound5;
    private Elementos.PanelRound panelRound9;
    private javax.swing.JRadioButton radioContra;
    private javax.swing.JRadioButton radioCuenta;
    private javax.swing.JRadioButton radioEspecial;
    private javax.swing.JRadioButton radioEstandar;
    private javax.swing.JRadioButton radioMediano;
    private javax.swing.JRadioButton radioPeque;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTextField txtDireccionDestino;
    private javax.swing.JTextField txtDireccionOrigen;
    private javax.swing.JLabel txtNombreFacturacion;
    private javax.swing.JLabel txtNombreTarjeta;
    private javax.swing.JTextField txtNumeroPaquetes;
    private javax.swing.JLabel txtTotalEspecial;
    private javax.swing.JLabel txtTotalEstandar;
    // End of variables declaration//GEN-END:variables
}
