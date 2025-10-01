package ar.edu.et32;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientMain {
    public static void main(String[] args) {
        String ip = "127.0.0.1";
        int port = 5000;

        try {
            Client client = new Client(EnumType.CLIENT, ip, port);
            client.setIp(ip);
            client.setPort(port);
            client.clientOn();

        } catch (IOException ex) {
            Logger.getLogger(ClientMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
