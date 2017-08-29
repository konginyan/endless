package endless.server;

import endless.drawer.attrs.Location;

public class Eserver {
    public static final String HOST = "127.0.0.1";
    public static final int PORT = 80;

    private static Location character;

    private Server server;
    private ServerHandler handler;

    public Eserver() {
        server = new Server();
        handler = new EserverHandler(server);
        character = new Location(0,1,0);
    }

    public void start(){
        server.start(PORT, handler);
        System.out.println(10);
    }

    public static void main(String args[]){
        new Eserver().start();
    }
}
