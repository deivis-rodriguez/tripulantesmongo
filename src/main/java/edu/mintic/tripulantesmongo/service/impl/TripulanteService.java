package edu.mintic.tripulantesmongo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.mintic.tripulantesmongo.entity.Tripulante;
import edu.mintic.tripulantesmongo.repository.TripulanteRepository;
import edu.mintic.tripulantesmongo.service.ITripulanteService;

@Service
public class TripulanteService implements ITripulanteService{

    private TripulanteRepository repository;

    public TripulanteService(TripulanteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Tripulante crearTripulante(Tripulante tripulante) {
        return this.repository.save(tripulante);
    }

    @Override
    public Tripulante getTripulante(String id) {
        return this.repository.findById(id).orElseThrow();
    }

    @Override
    public List<Tripulante> listarTripulante() {
        return this.repository.findAll();
    }

    @Override
    public Tripulante actualizTripulante(String id, Tripulante tripulante) {
        Tripulante tripulanteA = this.repository.findById(id).get();
        tripulanteA.setCalificaciones(tripulante.getCalificaciones());
        tripulanteA.setCursos(tripulante.getCursos());
        tripulanteA.setDireccion(tripulante.getDireccion());
        tripulanteA.setNombre(tripulante.getNombre());

        return this.repository.save(tripulanteA);
    }

    @Override
    public void eliminarTripulante(String idTripulante) {
        this.repository.deleteById(idTripulante);
    }
    
}
