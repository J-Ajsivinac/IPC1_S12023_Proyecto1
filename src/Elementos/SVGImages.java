
package Elementos;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Color;
import java.util.function.Function;
import javax.swing.JLabel;


public class SVGImages extends JLabel {
    private FlatSVGIcon svgIcono;
    public void setSvgImages(String imagen, int width, int height){
        svgIcono = new FlatSVGIcon(imagen, width,height);
        svgIcono.setColorFilter(new FlatSVGIcon.ColorFilter(new Function<Color, Color>(){
            @Override
            public Color apply(Color t) {
               return new Color(88,88,112);
            }
            
        }));
        setIcon(svgIcono);
    }
}
