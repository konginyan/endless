package endless.util;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;
import endless.Exception.DrawingException;

import java.io.File;
import java.io.IOException;

public class textureUtil {

    public static int getTexture(GL2 gl, String fileName){
        try
        {
            File im = new File("img\\map.jpg");
            Texture t = TextureIO.newTexture(im, true);
            return t.getTextureObject(gl);
        }
        catch(IOException e)
        {
            throw new DrawingException("获取本地图片失败");
        }
    }
}
