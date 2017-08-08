package endless.drawer.animation;

import com.jogamp.opengl.GL2;

public class Rotation implements Animation{

    private GL2 gl;
    private float angle;
    private float x;
    private float y;
    private float z;
    private float speed;

    private Rotation(GL2 gl2, float v, float v1, float v2, float v3, float sp){
        this.gl = gl2;
        this.angle = v;
        this.x = v1;
        this.y = v2;
        this.z = v3;
        this.speed = sp;
    }

    public void rotate(){
        gl.glRotatef(angle, x, y, z);
        angle += speed;
    }
}
