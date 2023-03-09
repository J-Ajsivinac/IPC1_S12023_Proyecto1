package Elementos.CutomTable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author mesoi
 */
public class PanelAction extends javax.swing.JPanel {

    /**
     * Creates new form PanelAction
     */
    public PanelAction() {
        initComponents();
    }

    public void initEvent(TableActionEvent event, int row) {
        cmdFactura.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                event.onEdit(row);
            }
        });
        cmdGuia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                event.onView(row);
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmdGuia = new Elementos.ButtonRound();
        cmdFactura = new Elementos.ButtonRound();

        cmdGuia.setBorder(null);
        cmdGuia.setForeground(new java.awt.Color(45, 136, 74));
        cmdGuia.setText("Guía");
        cmdGuia.setBorderColor(new java.awt.Color(193, 231, 206));
        cmdGuia.setColor(new java.awt.Color(193, 231, 206));
        cmdGuia.setColorClick(new java.awt.Color(131, 218, 160));
        cmdGuia.setColorOver(new java.awt.Color(176, 220, 192));
        cmdGuia.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        cmdGuia.setRadius(10);

        cmdFactura.setBorder(null);
        cmdFactura.setForeground(new java.awt.Color(38, 102, 222));
        cmdFactura.setText("Factura");
        cmdFactura.setBorderColor(new java.awt.Color(182, 231, 255));
        cmdFactura.setColor(new java.awt.Color(182, 231, 255));
        cmdFactura.setColorClick(new java.awt.Color(142, 211, 246));
        cmdFactura.setColorOver(new java.awt.Color(169, 216, 238));
        cmdFactura.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        cmdFactura.setRadius(10);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmdFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdGuia, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cmdFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmdGuia, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Elementos.ButtonRound cmdFactura;
    private Elementos.ButtonRound cmdGuia;
    // End of variables declaration//GEN-END:variables
}
