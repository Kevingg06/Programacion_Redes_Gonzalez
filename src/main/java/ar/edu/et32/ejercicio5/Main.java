package ar.edu.et32.ejercicio5;

import javax.swing.*;
import java.io.File;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        File carpeta = new File(".\\documentos");
        File[] archivos = carpeta.listFiles();
        int count = 0;

        var output = "Se encontraron los siguientes archivos: " + Arrays.toString(archivos);
        JOptionPane.showMessageDialog(null, output);
        for(int i = 0; i < archivos.length ; i++) {
            Thread proceso = new Thread(new ReadFile(archivos[i]));
            try {
                proceso.start();
                proceso.join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println("\nTotal de líneas en todos los archivos: " + ReadFile.getLineasTotales());

    }
}
