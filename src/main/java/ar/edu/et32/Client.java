package ar.edu.et32;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Client extends Connection {

    private DataInputStream disClient = null;
    private DataOutputStream dosClient = null;

    public Client(EnumType type, String ip, int port) throws UnknownHostException, IOException {
        super(type, ip, port);
    }

    public void clientOn() {
        try {
            disClient = new DataInputStream(new java.io.BufferedInputStream(sockC.getInputStream()));
            dosClient = new DataOutputStream(new java.io.BufferedOutputStream(sockC.getOutputStream()));

            ps.println(Utils.GREEN + "Conexión exitosa al servidor." + Utils.RESET);


            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) { }



            boolean con = true;
            while (con) {

                JFileChooser chooser = new JFileChooser();
                chooser.setDialogTitle("Seleccioná el archivo que desea enviar");
                int result = chooser.showOpenDialog(null);
                if (result != JFileChooser.APPROVE_OPTION) {
                    ps.println(Utils.CYAN + "No se seleccionó nignún archivo. Finalizando envío." + Utils.RESET);

                    dosClient.writeBoolean(false);
                    dosClient.flush();
                    break;
                }

                File file = chooser.getSelectedFile();
                if (!file.exists() || !file.isFile()) {
                    ps.println(Utils.RED + "Archivo inválido o no existe." + Utils.RESET);

                    int r = JOptionPane.showConfirmDialog(null, "Archivo inválido. ¿Desea intentar con otro archivo?", "Error", JOptionPane.YES_NO_OPTION);
                    if (r != JOptionPane.YES_OPTION) {
                        dosClient.writeBoolean(false);
                        dosClient.flush();
                        break;
                    } else {
                        continue;
                    }
                }


                dosClient.writeBoolean(true);
                dosClient.flush();

                String fileName = file.getName();
                long fileSize = file.length();

                ps.printf(Utils.YELLOW + "Enviando archivo: %s (%,d bytes)\n" + Utils.RESET, fileName, fileSize);


                dosClient.writeUTF(fileName);
                dosClient.writeLong(fileSize);

                try (FileInputStream fis = new FileInputStream(file);
                     BufferedInputStream bis = new BufferedInputStream(fis)) {

                    byte[] buffer = new byte[4096];
                    int read;
                    long total = 0;
                    while ((read = bis.read(buffer)) > 0) {
                        dosClient.write(buffer, 0, read);
                        total += read;
                    }
                    dosClient.flush();
                } catch (IOException ex) {
                    ps.println(Utils.RED + "Error al leer / enviar el archivo: " + ex.getMessage() + Utils.RESET);

                    try {
                        dosClient.writeUTF("ERROR");
                        dosClient.flush();
                    } catch (IOException e) {}
                    break;
                }


                String ack = disClient.readUTF();
                if ("OK".equals(ack)) {
                    ps.println(Utils.GREEN + "Archivo enviado correctamente." + Utils.RESET);
                } else {
                    ps.println(Utils.RED + "Servidor indicó error en la recepción." + Utils.RESET);
                }


                int ans = JOptionPane.showConfirmDialog(null, "¿Desea enviar otro archivo?", "Enviar otro?", JOptionPane.YES_NO_OPTION);
                if (ans != JOptionPane.YES_OPTION) {

                    dosClient.writeBoolean(false);
                    dosClient.flush();
                    con = false;

                }

            }

        }catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            ps.println(Utils.RED + "Error de conexión/transmisión: " + ex.getMessage() + Utils.RESET);
        } finally {
            try {
                if (dosClient != null) dosClient.close();
                if (disClient != null) disClient.close();
                if (sockC != null && !sockC.isClosed()) sockC.close();
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}