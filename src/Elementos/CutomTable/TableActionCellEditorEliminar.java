
package Elementos.CutomTable;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

/**
 *
 * @author RAVEN
 */
public class TableActionCellEditorEliminar extends DefaultCellEditor {

    private TableActionEvent event;

    public TableActionCellEditorEliminar(TableActionEvent event) {
        super(new JCheckBox());
        this.event = event;
    }

    @Override
    public Component getTableCellEditorComponent(JTable jtable, Object o, boolean bln, int row, int column) {
        PanelActioEliminar action = new PanelActioEliminar();
        action.initEvent(event, row);
        
        action.setBackground(jtable.getSelectionBackground());
        return action;
    }
}