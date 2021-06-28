package com.example.proyecto_nuevo;

public class Tareas {
    private String Docente;
    private String Materia;


    public Tareas() {

    }

    public Tareas(String Docente, String Materia) {
        this.Docente = Docente;
        this.Materia = Materia;
    }

    public String getDocente() {
        return Docente;
    }

    public void setDocente(String Docente) {
        this.Docente = Docente;
    }

    public String getMateria() {
        return Materia;
    }

    public void setMateria(String Materia) {
        this.Materia = Materia;
    }
}