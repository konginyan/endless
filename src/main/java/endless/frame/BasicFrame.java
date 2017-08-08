package endless.frame;

import com.jogamp.opengl.awt.GLCanvas;

import javax.swing.*;

public class BasicFrame extends JFrame{
    public BasicFrame(String title, GLCanvas canvas){
        super(title);
        this.getContentPane().add(canvas);
        this.setSize(this.getContentPane().getPreferredSize());
        this.setVisible(true);
    }
}
