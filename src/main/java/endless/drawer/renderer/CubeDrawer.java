package endless.drawer.renderer;

import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import endless.canvas.GL2CanvasFactory;
import endless.drawer.animator.BasicAnimator;
import endless.drawer.attrs.Location;
import endless.drawer.base.BasicDrawer;
import endless.frame.BasicFrame;
import endless.listener.KeyDirectionListener;
import endless.util.ListBuilder;

import javax.swing.*;
import java.util.EventListener;

public class CubeDrawer implements GLEventListener{
    private GLU glu = new GLU();
    private Location location;
    private EventListener listener;

    public CubeDrawer(){
        location = new Location(0,0,3);
        listener = new KeyDirectionListener(location);
    }

    public EventListener listen(){
        return this.listener;
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
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
        glu.gluLookAt(location.getX(), 0, location.getZ(), location.getX(), 0, location.getZ()-3, 0, 1, 0);
        BasicDrawer.drawCube(gl,1f);
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

    public static void main(String args[]){
        GLCanvas canvas1 = new GL2CanvasFactory().getCanvas(400,400,new LayerDrawer());
        GLCanvas canvas2 = new GL2CanvasFactory().getCanvas(400,400,new CubeDrawer());
        BasicAnimator animator = new BasicAnimator(canvas2, 100);
        JFrame frame = new BasicFrame("endless",
                new ListBuilder<GLCanvas>().instance().add(canvas1).add(canvas2).list());
    }
}
