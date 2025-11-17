package ar.edu.et32.ejercicio3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class Liebre implements Runnable {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintStream ps = new PrintStream(System.out);
    static PrintStream err = new PrintStream(System.err);

    public static int probability(int min, int max) {
        int rango = (max - min) + 1;
        int random = (int) ((rango * Math.random()) + min);
        return random;
    }

    public void run() {
        int casillaActual = 0;
        while(casillaActual < 70) {
            int prob = probability(1,100);
            if(casillaActual < 0) casillaActual = 0;
            if( prob > 0 && prob <= 35 ) {
                casillaActual = casillaActual + 3;
                if(casillaActual > 70) casillaActual = 70;
                ps.println("La Liebre salta rapido anashe. Se mueve 3 casillas.");
            }else if(prob > 35 && prob <= 49) {
                if(casillaActual < 0) casillaActual = 0;
                casillaActual = casillaActual - 9;
                if(casillaActual > 70) casillaActual = 70;
                ps.println("La Liebre resbala y tropieza cayendose para atras (alto bot). Retrocede 9 casillas.");
            }else if(prob > 49 && prob <= 70) {
                if(casillaActual < 0) casillaActual = 0;
                casillaActual = casillaActual + 1;
                if(casillaActual > 70) casillaActual = 70;
                ps.println("La Liebre salta. Avanza 1 casilla. (consorti pelado)");
                try {
                    Thread.sleep(1000);
                    ps.println("La Liebre se encuentra en la casilla: " + casillaActual);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
