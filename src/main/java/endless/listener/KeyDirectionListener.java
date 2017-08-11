package endless.listener;

import endless.drawer.attrs.Location;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyDirectionListener extends KeyAdapter {

    private Location location;

    public KeyDirectionListener(Location location){
        this.location = location;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT){
            location.setX(location.getX()-0.1f);
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            location.setX(location.getX()+0.1f);
        }
        if (e.getKeyCode() == KeyEvent.VK_UP){
            location.setZ(location.getZ()-0.1f);
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN){
            location.setZ(location.getZ()+0.1f);
        }
    }
}
