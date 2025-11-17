package ar.edu.et32.ejercicio6;

import java.util.ArrayList;
import java.util.List;

public class OficinaDeAlumnos implements Runnable {

    public static List<Alumnos> listaAlumnos = new ArrayList<>();

    @Override
    public void run() {
        listaAlumnos.add(new Alumnos("Tito", "Palmieri"));
        listaAlumnos.add(new Alumnos("Linkevin", "Gordonzalez"));
        listaAlumnos.add(new Alumnos("Bengaga", "Castrado madrid"));
        listaAlumnos.add(new Alumnos("Gonzo", "Javasorti"));

        System.out.println("OficinaDeAlumnos: se cargaron " + listaAlumnos.size() + " alumnos.\n");
    }
}
