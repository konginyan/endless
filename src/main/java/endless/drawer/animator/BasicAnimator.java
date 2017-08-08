package endless.drawer.animator;

import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

public class BasicAnimator{

    private FPSAnimator animator;

    public BasicAnimator(GLCanvas canvas, int fps){
        this.animator = new FPSAnimator(canvas, fps);
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
