package Componentes;

import Elementos.CutomTable.TableActionCellEditor;
import Elementos.CutomTable.TableActionCellRender;
import Elementos.CutomTable.TableActionEvent;
import Interfaz.login;
import Usuario.Envios;
import Usuario.Factura;
import Usuario.Usuario;
import Usuario.ctrlEnvios;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 *
 * @author mesoi
 */
public class UsuarioVerEnvios extends javax.swing.JPanel {

    private DefaultTableModel modelo;
    private Usuario user;

    /**
     * Creates new form UsuarioVerEnvios
     */
    public UsuarioVerEnvios() {
        initComponents();
        this.setBounds(0, 0, 724, 520);
        table1.fixTable(jScrollPane2);
        jScrollPane2.getVerticalScrollBar().setUnitIncrement(30);
        modelo = (DefaultTableModel) table1.getModel();
        //cargarDatos();
        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                //System.out.println("Edit row : " + row);
                String dato = String.valueOf(modelo.getValueAt(row, 0));
                DescargarFactura(dato);
            }

            @Override
            public void onDelete(int row) {
                if (table1.isEditing()) {
                    table1.getCellEditor().stopCellEditing();
                }

            }

            @Override
            public void onView(int row) {
                String dato = String.valueOf(modelo.getValueAt(row, 0));
                descargarGuia(dato);
            }
        };

        table1.getColumnModel().getColumn(5).setCellRenderer(new TableActionCellRender());
        table1.getColumnModel().getColumn(5).setCellEditor(new TableActionCellEditor(event));
        //table1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        //table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    }

    public void test(Usuario user1) {
        this.user = user1;
        //System.out.println("Estoy en el usuario Cotizacion");
    }

    public void DescargarFactura(String dato) {
        Envios envios = ctrlEnvios.getEnvioByGuia(user.getCorreo().toString(), dato);

        JFileChooser guardarComo = new JFileChooser();
        int userSelection = 0;
        try {
            guardarComo.setDialogTitle("Guardar archivo");
            userSelection = guardarComo.showSaveDialog(null);
        } catch (Exception e) {
            System.out.print(e);
        }
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File directorio = new File(guardarComo.getSelectedFile().toString()+".html");
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
                Element dDireccionFacturacion = doc.getElementById("dDireccionFacturacion");
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

                Element dTarjetaCredito = doc.getElementById("dTarjetaCredito");

                Factura factura = envios.getFactura();

                codigoPaquete.text("#" + factura.getNumFactura());
                NumeroFacura.text(factura.getCodPaquete());

                dDireccionFacturacion.text(factura.getDireccionFacturacion());

                String[] datosO = factura.getOrigen().split(",");

                dOrigen.text(datosO[0] + "," + datosO[1]);
                dDireccionOrigen.text(datosO[2]);

                String[] datosD = factura.getDestino().split(",");

                dDestino.text(datosD[0] + "," + datosD[1]);
                dDireccionDestino.text(datosD[2]);
                dNumeroPaquetes.text(factura.getNit() + "");

                String[] dTipos = factura.getTipoPago().split(",");
                dTipoPago.text(dTipos[0]);
                dTarjetaCredito.text(dTipos[1]);

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
                JOptionPane.showMessageDialog(null, "Ese nombre ya existe en la carpeta","Error",JOptionPane.ERROR_MESSAGE);
            }
        }
        //System.out.println(guardarComo.getSelectedFile().getName());
    }

    public void descargarGuia(String dato) {
        Envios envios = ctrlEnvios.getEnvioByGuia(user.getCorreo().toString(), dato);

        JFileChooser guardarComo = new JFileChooser();
        int userSelection = 0;
        try {
            guardarComo.setDialogTitle("Guardar archivo");
            userSelection = guardarComo.showSaveDialog(null);
        } catch (Exception e) {
            System.out.print(e);
        }
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File directorio = new File(guardarComo.getSelectedFile().toString()+".html");
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
                Element dDireccionFacturacion = doc.getElementById("dDireccionFacturacion");
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
                Element dTarjetaCredito = doc.getElementById("dTarjetaCredito");

                Factura factura = envios.getFactura();

                codigoPaquete.text(envios.getGuia().getCodPaquete());
                fecha.text(envios.getGuia().getFechaEnvio());
                dDireccionFacturacion.text(factura.getDireccionFacturacion());
                String[] datosO = factura.getOrigen().split(",");
                dOrigen.text(datosO[0] + "," + datosO[1]);
                dDireccionOrigen.text(datosO[2]);
                String[] datosD = factura.getDestino().split(",");
                dDestino.text(datosD[0] + "," + datosD[1]);
                dDireccionDestino.text(datosD[2]);
                dNumeroPaquetes.text(factura.getNumeropaquetes() + "");

                String[] dTipos = factura.getTipoPago().split(",");
                dTipoPago.text(dTipos[0]);
                dTarjetaCredito.text(dTipos[1]);

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
                JOptionPane.showMessageDialog(null, "Ese nombre ya existe en la carpeta","Error",JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void cargarDatos() {
        modelo.setRowCount(0);
        if (user != null) {

            ArrayList<Envios> envios = ctrlEnvios.verEnvios(login.credenciales.getIdUsuario());
            for (Envios envio : envios) {
                Object datos[] = new Object[5];
                datos[0] = envio.getGuia().getCodPaquete();
                datos[1] = envio.getTipoServicio();

                String[] datosD = envio.getFactura().getDestino().split(",");
                String[] tipoPago = envio.getFactura().getTipoPago().split(",");
                String mostrarDestinatario = "<html>" + datosD[0] + "<br> " + datosD[1] + "<br> " + datosD[2] + "</html>";
                datos[2] = mostrarDestinatario;
                datos[3] = envio.getFactura().getTotal();
                datos[4] = tipoPago[0];
                modelo.addRow(datos);
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
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table1 = new Elementos.CutomTable.TableDark();

        setOpaque(false);

        panelRound1.setBackground(new java.awt.Color(19, 19, 26));

        jLabel1.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Ver Envios");

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Codigo del Paquete", "Tipo de Servicio", "Destinatario", "Total", "Tipo de Pago", "Descargar"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table1.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        table1.setRowHeight(92);
        table1.setSelectionBackground(new java.awt.Color(98, 101, 128));
        jScrollPane2.setViewportView(table1);
        if (table1.getColumnModel().getColumnCount() > 0) {
            table1.getColumnModel().getColumn(0).setPreferredWidth(40);
            table1.getColumnModel().getColumn(1).setPreferredWidth(40);
            table1.getColumnModel().getColumn(2).setPreferredWidth(80);
            table1.getColumnModel().getColumn(3).setPreferredWidth(18);
            table1.getColumnModel().getColumn(4).setPreferredWidth(28);
            table1.getColumnModel().getColumn(5).setPreferredWidth(70);
        }

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 705, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private Elementos.PanelRound panelRound1;
    private Elementos.CutomTable.TableDark table1;
    // End of variables declaration//GEN-END:variables
}
