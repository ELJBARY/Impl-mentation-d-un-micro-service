package ma.enset.compteservice.repositories;

import ma.enset.compteservice.entities.Compte;
import ma.enset.compteservice.entities.EtatCompte;
import ma.enset.compteservice.entities.TypeCompte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource(path = "comptes")
public interface CompteRepository extends JpaRepository<Compte, Long> {
    @RestResource(path = "/byType")
    List<Compte> findByType(@Param("t") TypeCompte type);

    @RestResource(path = "/byEtat")
    List<Compte> findByEtat(@Param("e") EtatCompte etat);
}
