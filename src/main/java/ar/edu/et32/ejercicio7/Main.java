package ar.edu.et32.ejercicio7;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Thread registro = new Thread(new RegistroEmpleados());
        Thread control = new Thread(new ControlAsistencia());

        registro.start();
        registro.join();
        control.join();
        control.start();
    }
}
