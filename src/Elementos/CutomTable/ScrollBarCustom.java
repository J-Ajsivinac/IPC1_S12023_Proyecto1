
package Elementos.CutomTable;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JScrollBar;
import javax.swing.border.Border;

public class ScrollBarCustom extends JScrollBar {

    public ScrollBarCustom() {
        setUI(new ModernScrollBarUI());
        setPreferredSize(new Dimension(8, 8));
        setForeground(new Color(88, 88, 112));
        setBackground(new Color(28, 28, 36));
    }
}