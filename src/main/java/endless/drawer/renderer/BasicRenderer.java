package endless.drawer.renderer;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import endless.canvas.GL2CanvasFactory;
import endless.drawer.animator.BasicAnimator;
import endless.drawer.attrs.Head;
import endless.drawer.attrs.Location;
import endless.drawer.base.Camera;
import endless.drawer.base.Drawer2D;
import endless.drawer.base.Drawer3D;
import endless.frame.BasicFrame;
import endless.listener.KeyDirectionListener;
import endless.util.ListBuilder;
import endless.util.textureUtil;

import javax.swing.*;
import java.awt.event.KeyAdapter;

public class BasicRenderer implements GLEventListener{
    private GLU glu = new GLU();
    private Location location;
    private Location target;
    private Head head;
    private KeyAdapter listener;
    private int mapTexture;

    public BasicRenderer(){
        location = new Location(0,3,0);
        target = new Location(0,0,0);
        head = new Head(0,0,-1);
        listener = new KeyDirectionListener(location,target);
    }

    public KeyAdapter keylisten(){
        return this.listener;
    }

    public void init(GLAutoDrawable glAutoDrawable) {
        System.out.println("init");
        final GL2 gl = glAutoDrawable.getGL().getGL2();
        gl.glEnable( GL2.GL_DEPTH_TEST );
        gl.glDepthFunc( GL2.GL_LEQUAL );
        gl.glHint( GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST );
        gl.glEnable(GL2.GL_TEXTURE_2D);
        mapTexture = textureUtil.getTexture(gl,"img\\map.jpg");
    }

    public void dispose(GLAutoDrawable glAutoDrawable) {
        System.out.println("dispose");
    }

    public void display(GLAutoDrawable glAutoDrawable) {
        final GL2 gl = glAutoDrawable.getGL().getGL2();
        gl.glClear (GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT );
        Camera.get().move(gl,glu,location,target,head);
        Drawer3D.get().drawCube(gl,0.2f);
        Drawer2D.get().drawSquare(gl,0.1f,0.1f);
        Drawer3D.get().drawImage(gl,2f,2f,mapTexture);
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
    }

    public static void main(String args[]){
        GLCanvas canvas2 = new GL2CanvasFactory().getCanvas(400,400,new BasicRenderer());
        BasicAnimator animator = new BasicAnimator(canvas2, 100);
        JFrame frame = new BasicFrame("endless",
                new ListBuilder<GLCanvas>().instance().add(canvas2).list());
    }
}
