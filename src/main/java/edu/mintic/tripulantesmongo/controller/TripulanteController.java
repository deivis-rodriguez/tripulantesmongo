package edu.mintic.tripulantesmongo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import edu.mintic.tripulantesmongo.entity.Tripulante;
import edu.mintic.tripulantesmongo.service.impl.TripulanteService;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
public class TripulanteController {

    private TripulanteService tripulanteService;

    public TripulanteController(TripulanteService tripulanteService) {
        this.tripulanteService = tripulanteService;
    }

    @GetMapping("/tripulantes")
    public List<Tripulante> listaTripulantes() {
        return tripulanteService.listarTripulante();
    }

    @GetMapping("/tripulantes/{idTripulante}")
    public Tripulante getTripulante(@PathVariable String idTripulante) {
        return tripulanteService.getTripulante(idTripulante);
    }

    @PostMapping("/tripulantes")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Tripulante crearTripulante(@RequestBody Tripulante tripulante) {
        return tripulanteService.crearTripulante(tripulante);
    }

    @PutMapping("/tripulantes/{idTripulante}")
    public Tripulante actualizTripulante(@PathVariable String idTripulante, @RequestBody Tripulante tripulante){
        return tripulanteService.actualizTripulante(idTripulante, tripulante);
    }

    @DeleteMapping("/tripulantes/{idTripulante}")
    public void deleteTripulante(@PathVariable String idTripulante) {
        tripulanteService.eliminarTripulante(idTripulante);
    }

    
}
