package endless.drawer.base;

import com.jogamp.opengl.GL2;

public class BasicDrawer {
    public static void drawCube(GL2 gl, float x, float y, float z){
        gl.glBegin( GL2.GL_QUADS ); // Start Drawing The Cube
        gl.glColor3f( 1f,0f,0f );   //red color
        gl.glVertex3f( x/2, y/2, -z/2 ); // Top Right Of The Quad (Top)
        gl.glVertex3f( -x/2, y/2, -z/2); // Top Left Of The Quad (Top)
        gl.glVertex3f( -x/2, y/2, z/2 ); // Bottom Left Of The Quad (Top)
        gl.glVertex3f( x/2, y/2, z/2 ); // Bottom Right Of The Quad (Top)
        gl.glColor3f( 0f,1f,0f ); //green color
        gl.glVertex3f( x/2, -y/2, z/2 ); // Top Right Of The Quad
        gl.glVertex3f( -x/2, -y/2, z/2 ); // Top Left Of The Quad
        gl.glVertex3f( -x/2, -y/2, -z/2 ); // Bottom Left Of The Quad
        gl.glVertex3f( x/2, -y/2, -z/2 ); // Bottom Right Of The Quad
        gl.glColor3f( 0f,0f,1f ); //blue color
        gl.glVertex3f( x/2, y/2, z/2 ); // Top Right Of The Quad (Front)
        gl.glVertex3f( -x/2, y/2, z/2 ); // Top Left Of The Quad (Front)
        gl.glVertex3f( -x/2, -y/2, z/2 ); // Bottom Left Of The Quad
        gl.glVertex3f( x/2, -y/2, z/2 ); // Bottom Right Of The Quad
        gl.glColor3f( 1f,1f,0f ); //yellow (red + green)
        gl.glVertex3f( x/2, -y/2, -z/2 ); // Bottom Left Of The Quad
        gl.glVertex3f( -x/2, -y/2, -z/2 ); // Bottom Right Of The Quad
        gl.glVertex3f( -x/2, y/2, -z/2 ); // Top Right Of The Quad (Back)
        gl.glVertex3f( x/2, y/2, -z/2 ); // Top Left Of The Quad (Back)
        gl.glColor3f( 1f,0f,1f ); //purple (red + green)
        gl.glVertex3f( -x/2, y/2, z/2 ); // Top Right Of The Quad (Left)
        gl.glVertex3f( -x/2, y/2, -z/2 ); // Top Left Of The Quad (Left)
        gl.glVertex3f( -x/2, -y/2, -z/2 ); // Bottom Left Of The Quad
        gl.glVertex3f( -x/2, -y/2, z/2 ); // Bottom Right Of The Quad
        gl.glColor3f( 0f,1f, 1f ); //sky blue (blue +green)
        gl.glVertex3f( x/2, y/2, -z/2 ); // Top Right Of The Quad (Right)
        gl.glVertex3f( x/2, y/2, z/2 ); // Top Left Of The Quad
        gl.glVertex3f( x/2, -y/2, z/2 ); // Bottom Left Of The Quad
        gl.glVertex3f( x/2, -y/2, -z/2 ); // Bottom Right Of The Quad
        gl.glEnd(); // Done Drawing The Quad
    }

    public static void drawCube(GL2 gl, float len){
        drawCube(gl,len,len,len);
    }

    public static void drawImage(GL2 gl, float x, float y, int texture){
        gl.glBindTexture(GL2.GL_TEXTURE_2D, texture);
        gl.glBegin( GL2.GL_QUADS );
        gl.glTexCoord2f(1f,1f);gl.glVertex3f(-x,0f,y);
        gl.glTexCoord2f(0f,1f);gl.glVertex3f(x,0f,y);
        gl.glTexCoord2f(0f,0f);gl.glVertex3f(x,0f,-y);
        gl.glTexCoord2f(1f,0f);gl.glVertex3f(-x,0f,-y);
        gl.glEnd();
    }
}
