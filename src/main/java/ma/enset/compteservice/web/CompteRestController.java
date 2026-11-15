package ma.enset.compteservice.web;

import ma.enset.compteservice.dtos.CompteRequestDTO;
import ma.enset.compteservice.dtos.CompteResponseDTO;
import ma.enset.compteservice.service.CompteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comptes")
public class CompteRestController {

    private final CompteService compteService;

    public CompteRestController(CompteService compteService) {
        this.compteService = compteService;
    }

    @GetMapping
    public List<CompteResponseDTO> comptes() {
        return compteService.getAllComptes();
    }

    @GetMapping("/{id}")
    public CompteResponseDTO compte(@PathVariable Long id) {
        return compteService.getCompte(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompteResponseDTO save(@RequestBody CompteRequestDTO request) {
        return compteService.addCompte(request);
    }

    @PutMapping("/{id}")
    public CompteResponseDTO update(@PathVariable Long id, @RequestBody CompteRequestDTO request) {
        return compteService.updateCompte(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        compteService.deleteCompte(id);
    }
}
