package edu.mintic.tripulantesmongo.service;

import java.util.List;

import edu.mintic.tripulantesmongo.entity.Tripulante;

public interface ITripulanteService {
    Tripulante crearTripulante(Tripulante tripulante);
    Tripulante getTripulante(String id);
    List<Tripulante> listarTripulante();
    Tripulante actualizTripulante(String id, Tripulante tripulante);
    void eliminarTripulante(String idTripulante);

}
