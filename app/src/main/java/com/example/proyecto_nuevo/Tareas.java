package com.example.proyecto_nuevo;

public class Tareas {
    private String Docente;
    private String Materia;
    private String Enunciado;
    private String Fecha_Entrega;

    private String Materia1;
    private String Docente1;
    private String Horario1;
    private String Link1;

    public Tareas() {

    }

    public Tareas(String Docente, String Materia, String Enunciado, String Fecha_Entrega, String Materia1, String Docente1,
    String Horario1, String Link1) {

        this.Docente = Docente;
        this.Materia = Materia;
        this.Enunciado = Enunciado;
        this.Fecha_Entrega = Fecha_Entrega;
        this.Materia1 = Materia1;
        this.Docente1 = Docente1;
        this.Horario1 = Horario1;
        this.Link1 = Link1;
    }


    public String getDocente() { return Docente; }

    public void setDocente(String Docente) { this.Docente = Docente; }

    public String getMateria() {
        return Materia;
    }

    public void setMateria(String Materia) {
        this.Materia = Materia;
    }

    public String getEnunciado() { return Enunciado; }

    public void setEnunciado(String Enunciado) { this.Enunciado = Enunciado; }

    public String getFecha_Entrega() { return Fecha_Entrega; }

    public void setFecha_Entrega(String Fecha_Entrega) { this.Fecha_Entrega = Fecha_Entrega;}



    public String getMateria1() {
        return Materia1;
    }

    public void setMateria1(String materia1) {
        Materia1 = materia1;
    }

    public String getHorario1() {
        return Horario1;
    }

    public void setHorario1(String horario1) {
        Horario1 = horario1;
    }

    public String getDocente1() {
        return Docente1;
    }

    public void setDocente1(String docente1) {
        Docente1 = docente1;
    }

    public String getLink1() {
        return Link1;
    }

    public void setLink1(String link1) {
        Link1 = link1;
    }


}
