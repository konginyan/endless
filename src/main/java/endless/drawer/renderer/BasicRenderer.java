package endless.drawer.renderer;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import endless.canvas.GL2CanvasFactory;
import endless.client.Client;
import endless.drawer.animator.BasicAnimator;
import endless.drawer.attrs.*;
import endless.drawer.base.Camera;
import endless.drawer.base.Drawer2D;
import endless.drawer.base.Drawer3D;
import endless.frame.BasicFrame;
import endless.listener.KeyAndMouseListener;
import endless.server.Eserver;
import endless.util.ListBuilder;
import endless.util.textureUtil;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.nio.DoubleBuffer;

public class BasicRenderer extends KeyAndMouseListener implements GLEventListener{
    private GLU glu = new GLU();
    private GLAutoDrawable drawable;
    private Point mousePoint;
    private Location location;
    private Location target;
    private Location character;
    private Head head;
    private int mapTexture;
    private Size2D<Float> mapSize;
    private static Size2D<Integer> canvasSize = new Size2D(400,400);
    private int mouseStatus = Status.NONE;

    public BasicRenderer(){
        mousePoint = new Point(0,0);
        location = new Location(0,3,0);
        target = new Location(0,0,0);
        head = new Head(0,0,-1);
        character = new Location(0,1,0);
        mapSize = new Size2D(10f,10f);
    }

    public void init(GLAutoDrawable glAutoDrawable) {
        System.out.println("init");
        this.drawable = glAutoDrawable;
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
        Drawer3D.get().drawCube(gl,character.getX(),character.getY(),character.getZ(),0.2f);
        Drawer2D.get().drawSquare(gl,0.1f,0.1f);
        Drawer3D.get().drawImage(gl,mapSize.getWidth(),mapSize.getHeight(),mapTexture);
        gl.glFlush();

        if(mouseStatus == Status.RIGHT_CLICK){
            character = getMouseCoordinate(gl, mousePoint.getX(),mousePoint.getY());
            mouseStatus = Status.NONE;
        }
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

    public Location getMouseCoordinate(GL2 gl, double x, double y){
        double result[] = new double[3];
        int viewport[] = new int[4];
        double modelView[] = new double[16];
        double projection[] = new double[16];

        gl.glGetDoublev(GL2.GL_MODELVIEW_MATRIX, modelView, 0);
        gl.glGetDoublev(GL2.GL_PROJECTION_MATRIX, projection, 0);
        gl.glGetIntegerv(GL2.GL_VIEWPORT, viewport, 0);

        y = viewport[3] - y;

        glu.gluUnProject(x,y,0.5,modelView,0,projection,0,viewport,0,result,0);
        return new Location((float)result[0],(float)result[1],(float)result[2]);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT){
            location.setX(location.getX()-0.1f);
            target.setX(target.getX()-0.1f);
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            location.setX(location.getX()+0.1f);
            target.setX(target.getX()+0.1f);
        }
        if (e.getKeyCode() == KeyEvent.VK_UP){
            location.setZ(location.getZ()-0.1f);
            target.setZ(target.getZ()-0.1f);
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN){
            location.setZ(location.getZ()+0.1f);
            target.setZ(target.getZ()+0.1f);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON3){
            mousePoint.setX(e.getX());
            mousePoint.setY(e.getY());
            mouseStatus = Status.RIGHT_CLICK;
        }
    }

    public static void main(String args[]){
        GLCanvas canvas2 = new GL2CanvasFactory().getCanvas(canvasSize.getWidth(),canvasSize.getHeight(),new BasicRenderer());
        BasicAnimator animator = new BasicAnimator(canvas2, 100);
        JFrame frame = new BasicFrame("endless",
                new ListBuilder<GLCanvas>().instance().add(canvas2).list());
        new Client().start(Eserver.HOST,Eserver.PORT);
    }
}
