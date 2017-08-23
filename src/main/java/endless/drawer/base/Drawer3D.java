package endless.drawer.base;

import com.jogamp.opengl.GL2;

public class Drawer3D implements Drawer{

    private static Drawer3D drawer3D = new Drawer3D();

    public void drawCube(GL2 gl, float x, float y, float z, float lenx, float leny, float lenz){
        before(gl);
        gl.glBegin( GL2.GL_QUADS ); // Start Drawing The Cube
        gl.glColor3f( 1f,0f,0f );   //red color
        gl.glVertex3f(x+lenx /2,y+ leny /2, z-lenz /2 ); // Top Right Of The Quad (Top)
        gl.glVertex3f( x-lenx /2,y+ leny /2, z-lenz /2); // Top Left Of The Quad (Top)
        gl.glVertex3f( x-lenx /2,y+ leny /2,z+ lenz /2 ); // Bottom Left Of The Quad (Top)
        gl.glVertex3f(x+lenx /2,y+ leny /2,z+ lenz /2 ); // Bottom Right Of The Quad (Top)
        gl.glColor3f( 0f,1f,0f ); //green color
        gl.glVertex3f(x+lenx /2, y-leny /2,z+ lenz /2 ); // Top Right Of The Quad
        gl.glVertex3f( x-lenx /2, y-leny /2,z+ lenz /2 ); // Top Left Of The Quad
        gl.glVertex3f( x-lenx /2, y-leny /2, z-lenz /2 ); // Bottom Left Of The Quad
        gl.glVertex3f(x+lenx /2, y-leny /2, z-lenz /2 ); // Bottom Right Of The Quad
        gl.glColor3f( 0f,0f,1f ); //blue color
        gl.glVertex3f(x+lenx /2,y+ leny /2,z+ lenz /2 ); // Top Right Of The Quad (Front)
        gl.glVertex3f( x-lenx /2,y+ leny /2,z+ lenz /2 ); // Top Left Of The Quad (Front)
        gl.glVertex3f( x-lenx /2, y-leny /2,z+ lenz /2 ); // Bottom Left Of The Quad
        gl.glVertex3f(x+lenx /2, y-leny /2,z+ lenz /2 ); // Bottom Right Of The Quad
        gl.glColor3f( 1f,1f,0f ); //yellow (red + green)
        gl.glVertex3f(x+lenx /2, y-leny /2, z-lenz /2 ); // Bottom Left Of The Quad
        gl.glVertex3f( x-lenx /2, y-leny /2, z-lenz /2 ); // Bottom Right Of The Quad
        gl.glVertex3f( x-lenx /2,y+ leny /2, z-lenz /2 ); // Top Right Of The Quad (Back)
        gl.glVertex3f(x+lenx /2,y+ leny /2, z-lenz /2 ); // Top Left Of The Quad (Back)
        gl.glColor3f( 1f,0f,1f ); //purple (red + green)
        gl.glVertex3f( x-lenx /2,y+ leny /2,z+ lenz /2 ); // Top Right Of The Quad (Left)
        gl.glVertex3f( x-lenx /2,y+ leny /2, z-lenz /2 ); // Top Left Of The Quad (Left)
        gl.glVertex3f( x-lenx /2, y-leny /2, z-lenz /2 ); // Bottom Left Of The Quad
        gl.glVertex3f( x-lenx /2, y-leny /2,z+ lenz /2 ); // Bottom Right Of The Quad
        gl.glColor3f( 0f,1f, 1f ); //sky blue (blue +green)
        gl.glVertex3f(x+lenx /2,y+ leny /2, z-lenz /2 ); // Top Right Of The Quad (Right)
        gl.glVertex3f(x+lenx /2,y+ leny /2,z+ lenz /2 ); // Top Left Of The Quad
        gl.glVertex3f(x+lenx /2, y-leny /2,z+ lenz /2 ); // Bottom Left Of The Quad
        gl.glVertex3f(x+lenx /2, y-leny /2, z-lenz /2 ); // Bottom Right Of The Quad
        gl.glEnd(); // Done Drawing The Quad
        after(gl);
    }

    public void drawCube(GL2 gl, float x, float y, float z, float len){
        drawCube(gl,x,y,z,len,len,len);
    }

    public void drawCube(GL2 gl, float len){
        drawCube(gl,0,0,0,len,len,len);
    }

    public void drawImage(GL2 gl, float x, float y, int texture){
        before(gl);
        gl.glBindTexture(GL2.GL_TEXTURE_2D, texture);
        gl.glBegin( GL2.GL_QUADS );
        gl.glColor3f(1,1,1);
        gl.glTexCoord2f(0f,0f);gl.glVertex3f(-x/2,0f,y/2);
        gl.glTexCoord2f(1f,0f);gl.glVertex3f(x/2,0f,y/2);
        gl.glTexCoord2f(1f,1f);gl.glVertex3f(x/2,0f,-y/2);
        gl.glTexCoord2f(0f,1f);gl.glVertex3f(-x/2,0f,-y/2);
        gl.glEnd();
        after(gl);
    }

    public static Drawer3D get(){
        return drawer3D;
    }

    @Override
    public void before(GL2 gl) {
    }

    @Override
    public void after(GL2 gl) {
    }
}
