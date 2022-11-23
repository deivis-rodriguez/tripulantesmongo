package edu.mintic.tripulantesmongo.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tripulantes")
public class Tripulante {

    private @Id String idTripulante;
    private String nombre;
    private String cursos;
    private Direccion direccion;
    private List<Calificacion> calificaciones;

    public Tripulante() {
    }

    public Tripulante(String nombre, String curso, Direccion direccion, List<Calificacion> calificaciones) {
        this.nombre = nombre;
        this.cursos = curso;
        this.direccion = direccion;
        this.calificaciones = calificaciones;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getCursos() {
        return this.cursos;
    }

    public String getIdTripulante() {
        return this.idTripulante;
    }

    public void setIdTripulante(String idTripulante) {
        this.idTripulante = idTripulante;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCursos(String cursos) {
        this.cursos = cursos;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public List<Calificacion> getCalificaciones() {
        return calificaciones;
    }

    public void setCalificaciones(List<Calificacion> calificaciones) {
        this.calificaciones = calificaciones;
    }

}
