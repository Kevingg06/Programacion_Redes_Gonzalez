package ar.edu.et32.ejercicio4;

public class Main {
    public static void main(String[] args) {
        int matriz[][] = new int[4][4];
        int matriz2[][] = new int[4][4];

        int[][] datos = {
                {5, 10, 2, 4},
                {0, 12, 6, 8},
                {9, 15, 3, 8},
                {17, 13, 20, 16}
        };

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                matriz[i][j] = datos[i][j];
                matriz2[i][j] = datos[i][j];
            }
        }

        Thread proceso = new Thread(new Hilos(matriz, matriz2, 0, 0));
        Thread proceso2 = new Thread(new Hilos(matriz, matriz2, 1, 0));
        Thread proceso3 = new Thread(new Hilos(matriz, matriz2, 2, 0));
        Thread proceso4 = new Thread(new Hilos(matriz, matriz2, 3, 0));

        try {
            proceso.start();
            Thread.sleep(1000);
            proceso2.start();
            Thread.sleep(1000);
            proceso3.start();
            Thread.sleep(1000);
            proceso4.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
