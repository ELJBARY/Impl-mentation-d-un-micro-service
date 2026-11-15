package ma.enset.compteservice.repositories;

import ma.enset.compteservice.entities.Compte;
import ma.enset.compteservice.entities.EtatCompte;
import ma.enset.compteservice.entities.TypeCompte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompteRepository extends JpaRepository<Compte, Long> {
    List<Compte> findByType(TypeCompte type);
    List<Compte> findByEtat(EtatCompte etat);
}
