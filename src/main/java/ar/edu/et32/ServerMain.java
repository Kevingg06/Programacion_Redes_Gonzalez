package ar.edu.et32;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerMain {
    public static void main(String[] args) {
        String ip = "127.0.0.1";
        int port = 5000;

        try ( Server server = new Server(EnumType.SERVER, ip, port) ){
            server.setIp(ip);
            server.setPort(port);

            server.serverOn();
        } catch (IOException ex) {
            Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
