package endless.listener;

import endless.drawer.attrs.Location;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyDirectionListener extends KeyAdapter {

    private Location location;
    private Location target;

    public KeyDirectionListener(Location location, Location target){
        this.location = location;
        this.target = target;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT){
            location.setX(location.getX()-0.1f);
            target.setX(target.getX()-0.1f);
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            location.setX(location.getX()+0.1f);
            target.setX(target.getX()+0.1f);
        }
        if (e.getKeyCode() == KeyEvent.VK_UP){
            location.setZ(location.getZ()-0.1f);
            target.setZ(target.getZ()-0.1f);
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN){
            location.setZ(location.getZ()+0.1f);
            target.setZ(target.getZ()+0.1f);
        }
    }
}
