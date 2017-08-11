package endless.drawer.base;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;
import endless.canvas.GL2CanvasFactory;
import endless.frame.BasicFrame;
import endless.util.ListBuilder;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class LayerDrawer implements GLEventListener{
    private GLU glu = new GLU();
    private int mapTexture;

    public void init(GLAutoDrawable glAutoDrawable) {
        final GL2 gl = glAutoDrawable.getGL().getGL2();
        gl.glEnable(GL2.GL_TEXTURE_2D);
        try
        {
            File im = new File("img\\map.jpg");
            Texture t = TextureIO.newTexture(im, true);
            mapTexture = t.getTextureObject(gl);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
        glu.gluLookAt(0,1,0,0,0,0,0,0,1);
    }

    public void dispose(GLAutoDrawable glAutoDrawable) {

    }

    public void display(GLAutoDrawable glAutoDrawable) {
        final GL2 gl = glAutoDrawable.getGL().getGL2();
        gl.glClear (GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT );
        gl.glBindTexture(GL2.GL_TEXTURE_2D, mapTexture);
        gl.glBegin( GL2.GL_QUADS );
        gl.glTexCoord2f(0f,1f);gl.glVertex3f(-1f,0f,1f);
        gl.glTexCoord2f(1f,1f);gl.glVertex3f(1f,0f,1f);
        gl.glTexCoord2f(1f,0f);gl.glVertex3f(1f,0f,-1f);
        gl.glTexCoord2f(0f,0f);gl.glVertex3f(-1f,0f,-1f);
        gl.glEnd();
        gl.glFlush();
    }

    public void reshape(GLAutoDrawable glAutoDrawable, int x, int y, int width, int height) {
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
        glu.gluPerspective(45.0f, h, 1.0, 100.0);
    }

    public static void main(String args[]){
        GLCanvas canvas1 = new GL2CanvasFactory().getCanvas(400,400,new LayerDrawer());
        GLCanvas canvas2 = new GL2CanvasFactory().getCanvas(400,400,new CubeDrawer(0,0,0));
        JFrame frame = new BasicFrame("endless",
                new ListBuilder<GLCanvas>().instance().add(canvas1).add(canvas2).list());
    }
}
