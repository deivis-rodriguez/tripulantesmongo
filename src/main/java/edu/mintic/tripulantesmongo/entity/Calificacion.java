package edu.mintic.tripulantesmongo.entity;

public class Calificacion {
    private double nota;
    private String comentario;
    private String tipoNota;

    public Calificacion(double nota, String comentario, String tipoNota) {
        this.nota = nota;
        this.comentario = comentario;
        this.tipoNota = tipoNota;
    }

    public Calificacion() {
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getTipoNota() {
        return tipoNota;
    }

    public void setTipoNota(String tipoNota) {
        this.tipoNota = tipoNota;
    }

}
