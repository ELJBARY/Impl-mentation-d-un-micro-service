package ma.enset.compteservice.service;

import ma.enset.compteservice.dtos.CompteRequestDTO;
import ma.enset.compteservice.dtos.CompteResponseDTO;
import ma.enset.compteservice.entities.Compte;
import ma.enset.compteservice.exceptions.CompteNotFoundException;
import ma.enset.compteservice.mappers.CompteMapper;
import ma.enset.compteservice.repositories.CompteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class CompteServiceImpl implements CompteService {

    private final CompteRepository compteRepository;
    private final CompteMapper compteMapper;

    public CompteServiceImpl(CompteRepository compteRepository, CompteMapper compteMapper) {
        this.compteRepository = compteRepository;
        this.compteMapper = compteMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CompteResponseDTO> getAllComptes() {
        return compteRepository.findAll().stream().map(compteMapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CompteResponseDTO getCompte(Long id) {
        return compteMapper.toResponse(find(id));
    }

    @Override
    public CompteResponseDTO addCompte(CompteRequestDTO request) {
        Compte compte = compteMapper.fromRequest(request);
        compte.setDateCreation(LocalDate.now());
        return compteMapper.toResponse(compteRepository.save(compte));
    }

    @Override
    public CompteResponseDTO updateCompte(Long id, CompteRequestDTO request) {
        Compte compte = find(id);
        if (request.getSolde() != null) compte.setSolde(request.getSolde());
        if (request.getType() != null) compte.setType(request.getType());
        if (request.getEtat() != null) compte.setEtat(request.getEtat());
        return compteMapper.toResponse(compteRepository.save(compte));
    }

    @Override
    public void deleteCompte(Long id) {
        compteRepository.delete(find(id));
    }

    private Compte find(Long id) {
        return compteRepository.findById(id).orElseThrow(() -> new CompteNotFoundException(id));
    }
}
