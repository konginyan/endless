package endless.canvas;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;

public class GL2CanvasFactory{

    public GL2CanvasFactory(){

    }

    public GLCanvas getCanvas(int width, int height, GLEventListener drawer){
        GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        GLCanvas canvas = new GLCanvas(capabilities);
        canvas.setSize(width,height);
        canvas.addGLEventListener(drawer);
        return canvas;
    }
}
