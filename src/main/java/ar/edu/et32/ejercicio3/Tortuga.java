package ar.edu.et32.ejercicio3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class Tortuga implements Runnable {

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
        while (casillaActual < 70) {
            int prob = probability(1, 100);
            if (casillaActual < 0) casillaActual = 0;
            if (prob > 0 && prob <= 20) {
                if (casillaActual > 70)
                    casillaActual = 70;
                ps.println("La Tortuga se queda mimido (alto consorti). No avanza casillas.");
            } else if (prob > 20 && prob <= 40) {
                casillaActual = casillaActual + 9;
                if (casillaActual > 70) casillaActual = 70;
                ps.println("La Tortuga es re crack. Avanza 9 casillas");
            } else if (prob > 40 && prob <= 50) {
                casillaActual = casillaActual - 12;
                if (casillaActual < 0) casillaActual = 0;
                if (casillaActual > 70) casillaActual = 70;
                ps.println("La tortuga se resbala (alto bot). Retrocede 12 casillas.");
            } else if (prob > 50 && prob <= 80) {
                casillaActual = casillaActual + 1;
                if (casillaActual > 70) casillaActual = 70;
                ps.println("La Tortuga salta (como salta una tortuga? consorti gaga). Avanza 1 casilla.");
            } else if (prob > 80 && prob < 100) {
                if (casillaActual < 0) casillaActual = 0;
                casillaActual = casillaActual - 2;
                if (casillaActual > 70) casillaActual = 70;
                ps.println("La tortuga es un poquito bot y se resbala un poco. Retrocede 2 casillas.");
            }
            try {
                Thread.sleep(1000);
                ps.println("La Tortuga se encuentra en la casilla: " + casillaActual);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}