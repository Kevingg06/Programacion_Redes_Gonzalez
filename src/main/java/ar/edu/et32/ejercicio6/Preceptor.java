package ar.edu.et32.ejercicio6;

import java.util.Random;

public class Preceptor implements Runnable {

    @Override
    public void run() {
        Random r = new Random();
        for (Alumnos a : OficinaDeAlumnos.listaAlumnos) {
            int[] asist = new int[9]; // Declaro variables porque no pude declararle mi amor
            for (int i = 0; i < asist.length; i++) {
                asist[i] = r.nextInt(2);

                a.setAsistencia(asist);

                double promedioAsistencia = a.getAsistenciaPromedio();
                a.setAlumnoRegular(promedioAsistencia >= 0.75);
            }
            System.out.println("Preceptor: asistencias asignadas.\n");
        }
    }
}
