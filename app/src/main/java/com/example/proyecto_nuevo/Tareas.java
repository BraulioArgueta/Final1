package com.example.proyecto_nuevo;

public class Tareas {
    private String Docente;
    private String Materia;
    private String Enunciado;
    private String Fecha_Entrega;


    public Tareas() {

    }

    public Tareas(String Docente, String Materia, String Enunciado, String Fecha_Entrega) {
        this.Docente = Docente;
        this.Materia = Materia;
        this.Enunciado = Enunciado;
        this.Fecha_Entrega = Fecha_Entrega;
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


    public String getEnunciado() {

        return Enunciado;
    }

    public void setEnunciado(String Enunciado) {

        this.Enunciado = Enunciado;
    }

    public String getFecha_Entrega() {

        return Fecha_Entrega;
    }

    public void setFecha_Entrega(String Fecha_Entrega) {

        this.Fecha_Entrega = Fecha_Entrega;
    }

}