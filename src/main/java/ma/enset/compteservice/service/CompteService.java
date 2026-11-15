package ma.enset.compteservice.service;

import ma.enset.compteservice.dtos.CompteRequestDTO;
import ma.enset.compteservice.dtos.CompteResponseDTO;

import java.util.List;

public interface CompteService {
    List<CompteResponseDTO> getAllComptes();
    CompteResponseDTO getCompte(Long id);
    CompteResponseDTO addCompte(CompteRequestDTO request);
    CompteResponseDTO updateCompte(Long id, CompteRequestDTO request);
    void deleteCompte(Long id);
}
