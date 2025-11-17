package ar.edu.et32.ejercicio1;

public class Main {

    public static void main(String[] args) {
        Thread hilo = new Thread(new HiloAlfanumerico());
        hilo.start();
    }

}
