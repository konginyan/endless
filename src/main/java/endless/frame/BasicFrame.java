package endless.frame;

import com.jogamp.opengl.awt.GLCanvas;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BasicFrame extends JFrame{
    public BasicFrame(String title, List<GLCanvas> canvasList){
        super(title);
        JPanel panel = new JPanel();
        panel.setSize(400,400);
        this.setContentPane(panel);
//        canvasList.forEach(canvas->this.getContentPane().add(canvas));
        this.getContentPane().add(canvasList.get(0), BorderLayout.EAST);
        this.getContentPane().add(canvasList.get(1), BorderLayout.WEST);
        this.setSize(800,500);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
