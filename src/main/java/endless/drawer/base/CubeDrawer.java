package endless.drawer.base;

import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import endless.canvas.GL2CanvasFactory;
import endless.drawer.animator.BasicAnimator;
import endless.frame.BasicFrame;
import endless.util.ListBuilder;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CubeDrawer implements GLEventListener{
    private GLU glu = new GLU();
    private float xl;
    private float yl;
    private float zl;
    private static float angle = 0f;
    private static float pox = 0;
    private static float poy = 0;
    private static float poz = 3;

    public CubeDrawer(float xl, float yl, float zl){
        this.xl = xl;
        this.yl = yl;
        this.zl = zl;
    }

    public void init(GLAutoDrawable glAutoDrawable) {
        System.out.println("init");
        final GL2 gl = glAutoDrawable.getGL().getGL2();
        gl.glEnable( GL2.GL_DEPTH_TEST );
        gl.glDepthFunc( GL2.GL_LEQUAL );
        gl.glHint( GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST );
    }

    public void dispose(GLAutoDrawable glAutoDrawable) {
        System.out.println("dispose");
    }

    public void display(GLAutoDrawable glAutoDrawable) {
        final GL2 gl = glAutoDrawable.getGL().getGL2();
        gl.glClear (GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT );
//        gl.glLoadIdentity();
//        gl.glRotatef(angle, 0.0f, 1.0f, 0.0f);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
        glu.gluLookAt(pox, 0, poz, pox, 0, poz-3, 0, 1, 0);
        gl.glBegin( GL2.GL_QUADS ); // Start Drawing The Cube
        gl.glColor3f( 1f,0f,0f );   //red color
        gl.glVertex3f( 0.5f, 0.5f, -0.5f ); // Top Right Of The Quad (Top)
        gl.glVertex3f( -0.5f, 0.5f, -0.5f); // Top Left Of The Quad (Top)
        gl.glVertex3f( -0.5f, 0.5f, 0.5f ); // Bottom Left Of The Quad (Top)
        gl.glVertex3f( 0.5f, 0.5f, 0.5f ); // Bottom Right Of The Quad (Top)
        gl.glColor3f( 0f,1f,0f ); //green color
        gl.glVertex3f( 0.5f, -0.5f, 0.5f ); // Top Right Of The Quad
        gl.glVertex3f( -0.5f, -0.5f, 0.5f ); // Top Left Of The Quad
        gl.glVertex3f( -0.5f, -0.5f, -0.5f ); // Bottom Left Of The Quad
        gl.glVertex3f( 0.5f, -0.5f, -0.5f ); // Bottom Right Of The Quad
        gl.glColor3f( 0f,0f,1f ); //blue color
        gl.glVertex3f( 0.5f, 0.5f, 0.5f ); // Top Right Of The Quad (Front)
        gl.glVertex3f( -0.5f, 0.5f, 0.5f ); // Top Left Of The Quad (Front)
        gl.glVertex3f( -0.5f, -0.5f, 0.5f ); // Bottom Left Of The Quad
        gl.glVertex3f( 0.5f, -0.5f, 0.5f ); // Bottom Right Of The Quad
        gl.glColor3f( 1f,1f,0f ); //yellow (red + green)
        gl.glVertex3f( 0.5f, -0.5f, -0.5f ); // Bottom Left Of The Quad
        gl.glVertex3f( -0.5f, -0.5f, -0.5f ); // Bottom Right Of The Quad
        gl.glVertex3f( -0.5f, 0.5f, -0.5f ); // Top Right Of The Quad (Back)
        gl.glVertex3f( 0.5f, 0.5f, -0.5f ); // Top Left Of The Quad (Back)
        gl.glColor3f( 1f,0f,1f ); //purple (red + green)
        gl.glVertex3f( -0.5f, 0.5f, 0.5f ); // Top Right Of The Quad (Left)
        gl.glVertex3f( -0.5f, 0.5f, -0.5f ); // Top Left Of The Quad (Left)
        gl.glVertex3f( -0.5f, -0.5f, -0.5f ); // Bottom Left Of The Quad
        gl.glVertex3f( -0.5f, -0.5f, 0.5f ); // Bottom Right Of The Quad
        gl.glColor3f( 0f,1f, 1f ); //sky blue (blue +green)
        gl.glVertex3f( 0.5f, 0.5f, -0.5f ); // Top Right Of The Quad (Right)
        gl.glVertex3f( 0.5f, 0.5f, 0.5f ); // Top Left Of The Quad
        gl.glVertex3f( 0.5f, -0.5f, 0.5f ); // Bottom Left Of The Quad
        gl.glVertex3f( 0.5f, -0.5f, -0.5f ); // Bottom Right Of The Quad
        gl.glEnd(); // Done Drawing The Quad
        gl.glFlush();
    }

    public void reshape(GLAutoDrawable glAutoDrawable, int x, int y, int width, int height) {
        System.out.println("reshape");
        final GL2 gl = glAutoDrawable.getGL().getGL2();
        // get the OpenGL 2 graphics object
        if(height <=0)
            height =1;
        //preventing devided by 0 exception height =1;
        final float h = (float) width / (float) height;
        // display area to cover the entire window
        gl.glViewport(0, 0, width, height);
        //transforming projection matrix
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0f, h, 1.0, 20.0);
        //transforming model view gl.glLoadIdentity();
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    private static class KeyListener extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT){
                angle += 1f;
                pox -= 0.1f;
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT){
                angle -= 1f;
                pox += 0.1f;
            }
            if (e.getKeyCode() == KeyEvent.VK_UP){
                poz -= 0.1f;
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN){
                poz += 0.1f;
            }
        }
    }

    public static void main(String args[]){
        GLCanvas canvas = new GL2CanvasFactory().getCanvas(400,400,new CubeDrawer(0,0,0));
        BasicAnimator animator = new BasicAnimator(canvas, 1000);
        canvas.addKeyListener(new KeyListener());
        animator.start();
        JFrame frame = new BasicFrame("endless",
                new ListBuilder<GLCanvas>().instance().add(canvas).list());
    }
}
