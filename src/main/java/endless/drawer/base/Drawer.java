package endless.drawer.base;

import com.jogamp.opengl.GL2;

public interface Drawer {

    void before(GL2 gl);

    void after(GL2 gl);
}
