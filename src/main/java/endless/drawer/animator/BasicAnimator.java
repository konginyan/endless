package endless.drawer.animator;

import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

public class BasicAnimator{

    private FPSAnimator animator;

    public BasicAnimator(GLCanvas canvas, int fps, boolean autoStart){
        this.animator = new FPSAnimator(canvas, fps);
        if(autoStart) this.start();
    }

    public BasicAnimator(GLCanvas canvas, int fps){
        this(canvas,fps,true);
    }

    public void start(){
        animator.start();
    }

    public void stop(){
        animator.stop();
    }

    public void setFPS(int fps){
        animator.setFPS(fps);
    }
}
