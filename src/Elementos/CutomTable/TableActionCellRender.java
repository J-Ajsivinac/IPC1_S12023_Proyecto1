package Elementos.CutomTable;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author RAVEN
 */
public class TableActionCellRender extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable jtable, Object o, boolean isSeleted, boolean bln1, int row, int column) {
        Component com = super.getTableCellRendererComponent(jtable, o, isSeleted, bln1, row, column);
        PanelAction action = new PanelAction();
        if (isSeleted == false && row % 2 == 0) {
            action.setBackground(new Color(40, 41, 52));
        } else if (isSeleted == false && row % 2 != 0) {
            action.setBackground(new Color(57, 53, 74));
        } else {
            action.setBackground(new Color(98, 101, 128));
        }
        return action;
    }
}
