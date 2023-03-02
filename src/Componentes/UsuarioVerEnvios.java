package Componentes;

import Elementos.CutomTable.TableActionCellEditor;
import Elementos.CutomTable.TableActionCellRender;
import Elementos.CutomTable.TableActionEvent;
import Elementos.ScrollBarCustom;
import Usuario.Envios;
import Usuario.Factura;
import Usuario.Usuario;
import Usuario.ctrlEnvios;
import Usuario.ctrlUsuarios;
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

        modelo = (DefaultTableModel) table.getModel();
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
                if (table.isEditing()) {
                    table.getCellEditor().stopCellEditing();
                }

            }

            @Override
            public void onView(int row) {
                String dato = String.valueOf(modelo.getValueAt(row, 0));
                descargarGuia(dato);
            }
        };

        table.getColumnModel().getColumn(5).setCellRenderer(new TableActionCellRender());
        table.getColumnModel().getColumn(5).setCellEditor(new TableActionCellEditor(event));

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
                Element dDestino = doc.getElementById("dDestino");
                Element dNumeroPaquetes = doc.getElementById("dNumeroPaquetes");
                Element dTipoPago = doc.getElementById("dTipoPago");
                Element dSize = doc.getElementById("dSize");
                Element dNumero = doc.getElementById("dNumero");
                Element dTotal = doc.getElementById("dTotal");
                Factura factura = envios.getFactura();

                codigoPaquete.text("#" + factura.getNumFactura());
                NumeroFacura.text(factura.getCodPaquete());
                dOrigen.text(envios.getGuia().getOrigen());
                dDestino.text(factura.getDestino());
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
                Element NumeroFacura = doc.getElementById("fecha");
                Element dOrigen = doc.getElementById("dOrigen");
                Element dDestino = doc.getElementById("dDestino");
                Element dNumeroPaquetes = doc.getElementById("dNumeroPaquetes");
                Element dTipoPago = doc.getElementById("dTipoPago");
                Element dTamano = doc.getElementById("dTamano");
                Element dTarjeta = doc.getElementById("dTarjeta");
                Element dTituloTarjeta = doc.getElementById("dTituloTarjeta");
                Element dTotal = doc.getElementById("dTotal");

                Factura factura = envios.getFactura();

                codigoPaquete.text("#" + factura.getNumFactura());
                NumeroFacura.text(factura.getCodPaquete());
                dOrigen.text(envios.getGuia().getOrigen());
                dDestino.text(factura.getDestino());
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

    public void cargarDatos() {
        modelo.setRowCount(0);
        if (user != null) {
            ArrayList<Envios> envios = ctrlEnvios.verEnvios(user.getCorreo());
            for (Envios envio : envios) {
                Object datos[] = new Object[4];
                datos[0] = envio.getGuia().getCodPaquete();
                datos[1] = envio.getTipoServicio();
                datos[2] = envio.getDestinatario();
                datos[3] = envio.getFactura().getTipoPago();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        jLabel1.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        jLabel1.setText("Ver Envios");

        table.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo del Paqute", "Tipo de Servicio", "Destinatario", "Total de Envío", "Tipo de Pago", "Descargar"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setRowHeight(80);
        table.setSelectionBackground(new java.awt.Color(75, 103, 236));
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setPreferredWidth(40);
            table.getColumnModel().getColumn(1).setPreferredWidth(50);
            table.getColumnModel().getColumn(2).setPreferredWidth(70);
            table.getColumnModel().getColumn(3).setPreferredWidth(40);
            table.getColumnModel().getColumn(4).setPreferredWidth(11);
        }

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 698, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private Elementos.PanelRound panelRound1;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
