package endless.drawer.base;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import endless.drawer.attrs.Head;
import endless.drawer.attrs.Location;

public class Camera implements Drawer{

    private static Camera camera = new Camera();

    public void move(GL2 gl, GLU glu, Location location, Location target, Head head){
        before(gl);
        glu.gluLookAt(location.getX(), location.getY(), location.getZ(),
                target.getX(), target.getY(), target.getZ(),
                head.getX(), head.getY(), head.getZ());
    }

    public static Camera get(){
        return camera;
    }

    @Override
    public void before(GL2 gl) {
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    @Override
    public void after(GL2 gl) {

    }
}
