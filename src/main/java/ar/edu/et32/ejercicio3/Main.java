package ar.edu.et32.ejercicio3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;

import javax.swing.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintStream ps = new PrintStream(System.out);
    static PrintStream err = new PrintStream(System.err);

    public static void main(String[] args) {
        try {
            Thread tortu = new Thread(new Tortuga());
            Thread liebre = new Thread(new Liebre());
            ps.println("¡Comienza la carrera!");
            Thread.sleep(2000);
            tortu.start();
            liebre.start();
            while(true) {
                if(tortu.isAlive() && !liebre.isAlive()) {
                    var output = "El ganador es la liebre.";
                    JOptionPane.showMessageDialog(null, output);
                    break;
                }else if(!tortu.isAlive() && liebre.isAlive()) {
                    var output = "El ganador es la tortuga.";
                    JOptionPane.showMessageDialog(null, output);
                    break;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
