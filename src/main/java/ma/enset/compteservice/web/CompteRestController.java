package ma.enset.compteservice.web;

import ma.enset.compteservice.entities.Compte;
import ma.enset.compteservice.repositories.CompteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/comptes")
public class CompteRestController {

    private final CompteRepository compteRepository;

    public CompteRestController(CompteRepository compteRepository) {
        this.compteRepository = compteRepository;
    }

    @GetMapping
    public List<Compte> comptes() {
        return compteRepository.findAll();
    }

    @GetMapping("/{id}")
    public Compte compte(@PathVariable Long id) {
        return compteRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Compte " + id + " introuvable"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Compte save(@RequestBody Compte compte) {
        compte.setId(null);
        if (compte.getDateCreation() == null) compte.setDateCreation(new Date());
        return compteRepository.save(compte);
    }

    @PutMapping("/{id}")
    public Compte update(@PathVariable Long id, @RequestBody Compte compte) {
        Compte existing = compte(id);
        if (compte.getSolde() != 0) existing.setSolde(compte.getSolde());
        if (compte.getType() != null) existing.setType(compte.getType());
        if (compte.getEtat() != null) existing.setEtat(compte.getEtat());
        if (compte.getDateCreation() != null) existing.setDateCreation(compte.getDateCreation());
        return compteRepository.save(existing);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        compte(id);
        compteRepository.deleteById(id);
    }
}
