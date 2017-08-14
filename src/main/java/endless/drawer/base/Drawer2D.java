package endless.drawer.base;

import com.jogamp.opengl.GL2;

public class Drawer2D implements Drawer{

    private static Drawer2D drawer2D = new Drawer2D();

    public void drawSquare(GL2 gl, float x, float y){
        before(gl);
        gl.glBegin( GL2.GL_QUADS );
        gl.glVertex2f(-x/2,y/2);
        gl.glVertex2f(x/2,y/2);
        gl.glVertex2f(x/2,-y/2);
        gl.glVertex2f(-x/2,-y/2);
        gl.glEnd();
        after(gl);
    }

    public static Drawer2D get(){
        return drawer2D;
    }

    @Override
    public void before(GL2 gl) {
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glPushMatrix();
        gl.glLoadIdentity();
        gl.glOrtho(-1,1,-1,1,0,1);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glPushMatrix();
        gl.glLoadIdentity();
    }

    @Override
    public void after(GL2 gl) {
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glPopMatrix();
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glPopMatrix();
    }
}
